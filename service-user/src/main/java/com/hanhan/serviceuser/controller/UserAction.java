package com.hanhan.serviceuser.controller;

import com.hanhan.pojo.UserEntity;
import com.hanhan.serviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public List<UserEntity> getUser(){
        return userService.getUser();
    }

    @RequestMapping("/login")
    public List<UserEntity> login(@RequestBody UserEntity user ){
        return userService.login(user);
    }
}
