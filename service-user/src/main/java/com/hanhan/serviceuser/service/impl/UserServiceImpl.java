package com.hanhan.serviceuser.service.impl;

import com.hanhan.pojo.UserEntity;
import com.hanhan.serviceuser.mapper.UserMapper;
import com.hanhan.serviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getUser() {
        System.out.println("8884》getUser");
        return userMapper.getUser();
    }

    @Override
    public List<UserEntity> login(UserEntity user ) {
        System.out.println("8884》login");
        return userMapper.login(user);
    }
}
