package com.hanhan.application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SuppressWarnings("unchecked")
public class BaseAction<T> implements RequestZuulBase<T> {


    @Autowired
    public HttpServletRequest request;
    @Autowired
    public HttpServletResponse response;
    @Autowired
    private RestTemplate restTemplate;

    public MultiValueMap<String, Object> map;

    public void writeStream(Object obj, String coding) {

        PrintWriter out = null;
        response.setContentType(
                "text/html;charset=" + ((coding != null) && (!"".equals(coding.trim())) ? coding : "utf-8"));
        try {
            out = response.getWriter();
            out.print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    //该方法用于吧请求参数封装成json字符串
    public String getRequestJson(String[] k, Object[] v) {
        JSONObject resultJson = new JSONObject();
        for (int i = 0; i < k.length; i++) {
            resultJson.put(k[i], v[i]);
        }
        return resultJson.toString();
    }

    //该方法吧json字符串赋值给map ，key可自定义 ，同数据访问模块一样
    @SuppressWarnings("serial")
    public void putMapInfo(String... key_value) {
        map = new LinkedMultiValueMap<String, Object>() {
            {
                add(key_value[0], key_value[1]);
            }
        };
    }

    @Override
    public ResponseEntity<Integer> requestZuulMap(String url) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(map,
                    headers);
            return restTemplate.postForEntity(url, formEntity, Integer.class);
        } catch (Exception e) {
            log.info("连接网关出现异常: method:requestZuulMap");
            throw new Exception("连接网关出现异常:" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<Integer> requestZuulT(String url, T t) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<T> formEntity = new HttpEntity<T>(t, headers);
            return restTemplate.postForEntity(url, formEntity, Integer.class);
        } catch (Exception e) {
            log.info("连接网关出现异常>>> method:requestZuulT >>> requestText: " + JSONObject.toJSONString(t));
            throw new Exception("连接网关出现异常:" + e.getMessage());
        }
    }

    @Override
    public List<T> requestZuulListT(String url) throws Exception {
        try {
            return restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            log.info("连接网关出现异常>>> method:requestZuulListT");
            throw new Exception("连接网关出现异常:" + e.getMessage());
        }
    }

    @Override
    public List<T> requestZuulListTParamT(String url, T t) throws Exception {
        try {
            return restTemplate.getForObject(url, List.class, t);
        } catch (Exception e) {
            log.info("连接网关出现异常>>> method:requestZuulListTParamT >>> requestText: " + JSONObject.toJSONString(t));
            throw new Exception("连接网关出现异常:" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List>  requestZuulListTParamMap(String url) throws Exception {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(map,
                    headers);
            ResponseEntity<List> result = restTemplate.postForEntity(url, formEntity, List.class);
            return  result;
        } catch (Exception e) {
            log.info("连接网关出现异常>>> method:requestZuulListTParamMap :" + e.getMessage());
            throw new Exception("连接网关出现异常:" + e.getMessage());
        }
    }

    @Override
    public List<T> post(String url, String json) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        List<T> result = restTemplate.postForObject(url, requestEntity, List.class);
        return result;
    }

}
