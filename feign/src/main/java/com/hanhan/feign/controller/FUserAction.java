package com.hanhan.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanhan.feign.service.FUserService;
import com.hanhan.pojo.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class FUserAction {

        @Autowired
        private FUserService userService;

        @RequestMapping("/getUser")
        public List<UserEntity> getUser() {
                System.out.println("feign-getUser");
                return userService.getUser();
        }

        @RequestMapping(value = "/login" ,method = RequestMethod.POST)
        public List<UserEntity> login(@RequestBody String users) {
                System.out.println(users);
                UserEntity user = JSONObject.parseObject(users,UserEntity.class);
                System.out.println("feign-login");
                return userService.login(user);
        }




}
