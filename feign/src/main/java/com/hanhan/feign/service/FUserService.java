package com.hanhan.feign.service;

import com.hanhan.feign.rollback.FUserRollback;
import com.hanhan.pojo.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "service-user",fallbackFactory = FUserRollback.class)
public interface FUserService {

    @RequestMapping(value = "/user/getUser" , method = RequestMethod.POST)
    public List<UserEntity> getUser() ;


    @RequestMapping(value = "/user/login" , method = RequestMethod.POST)
    public List<UserEntity> login(UserEntity user) ;


}
