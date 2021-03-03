package com.hanhan.feign.rollback;

import com.alibaba.fastjson.JSONObject;
import com.hanhan.feign.service.FUserService;
import com.hanhan.pojo.UserEntity;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FUserRollback implements FallbackFactory<FUserService> {

    @Override
    public FUserService create(Throwable throwable) {
        return new FUserService() {
            @Override
            public List<UserEntity> getUser( ) {
                log.info("FEIGN >>> queryUser() 降级:"+throwable.getMessage());
                return null;
            }

            @Override
            public List<UserEntity> login(UserEntity user) {
                log.info("FEIGN >>> login("+ JSONObject.toJSONString(user) +") 降级:"+throwable.getMessage());
                return null;
            }
        };
    }
}
