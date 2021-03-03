package com.hanhan.serviceusertow.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hanhan.pojo.UserEntity;
import com.hanhan.serviceusertow.mapper.UserMapper;
import com.hanhan.serviceusertow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getUser() {
        System.out.println("8885》getUser");
        return userMapper.getUser();
    }

    @Override
    public List<UserEntity> login(UserEntity user ) {
        System.out.println("8885》login");
        return userMapper.login(user);
    }
}
