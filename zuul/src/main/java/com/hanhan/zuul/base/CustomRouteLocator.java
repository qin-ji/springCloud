package com.hanhan.zuul.base;

import java.util.LinkedHashMap;


import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{
 
 
    private JdbcTemplate jdbcTemplate;
 
    private ZuulProperties properties;
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
 
    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        log.info("servletPath:{}",servletPath);
    }  
 
    @Override
    public void refresh() {
        doRefresh();

    }
 
    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        //优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        log.info("ZUUL >>> 本次刷新的路由结果: "+ JSONObject.toJSONString(values));
       
        return values;
    }
 
    private Map<String, ZuulRoute> locateRoutesFromDB(){
    	
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteVO> results = jdbcTemplate.query("select * from zuul_api  where enabled = true ",new BeanPropertyRowMapper<>(ZuulRouteVO.class));
       log.info("ZUUL >>> 从数据库中读取路由:共  : "+results.size()+" 个  ...");
        for (ZuulRouteVO result : results) {
            if(StringUtils.isEmpty(result.getPath())){
                continue;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            try {
                BeanUtils.copyProperties(result,zuulRoute);
            } catch (Exception e) {
                log.error("ZUUL >>> 从数据库读取路由异常 ",e);
            }
            routes.put(zuulRoute.getPath(),zuulRoute);
        }
        return routes;
    }
 
    public static class ZuulRouteVO {
 
        /**
         * The ID of the route (the same as its map key by default).
         */
        private String id;
 
        /**
         * The path (pattern) for the route, e.g. /foo/**.
         */
        private String path;
 
        /**
         * The service ID (if any) to map to this route. You can specify a physical URL or
         * a service, but not both.
         */
        private String serviceId;
 
        /**
         * A full physical URL to map to the route. An alternative is to use a service ID
         * and service discovery to find the physical address.eg.http://localhost:8090
         */
        private String url;
 
        /**
         * Flag to determine whether the prefix for this route (the path, minus pattern
         * patcher) should be stripped before forwarding.
         */
        private boolean stripPrefix = true;
 
        /**
         * Flag to indicate that this route should be retryable (if supported). Generally
         * retry requires a service ID and ribbon.
         */
        private Boolean retryable;
 
        private String apiName;
 
        private Boolean enabled;
 
        public String getId() {
            return id;
        }
 
        public void setId(String id) {
            this.id = id;
        }
 
        public String getPath() {
            return path;
        }
 
        public void setPath(String path) {
            this.path = path;
        }
 
        public String getServiceId() {
            return serviceId;
        }
 
        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }
 
        public String getUrl() {
            return url;
        }
 
        public void setUrl(String url) {
            this.url = url;
        }
 
        public boolean isStripPrefix() {
            return stripPrefix;
        }
 
        public void setStripPrefix(boolean stripPrefix) {
            this.stripPrefix = stripPrefix;
        }
 
        public Boolean getRetryable() {
            return retryable;
        }
 
        public void setRetryable(Boolean retryable) {
            this.retryable = retryable;
        }
 
        public String getApiName() {
            return apiName;
        }
 
        public void setApiName(String apiName) {
            this.apiName = apiName;
        }
 
        public Boolean getEnabled() {
            return enabled;
        }
 
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }
}