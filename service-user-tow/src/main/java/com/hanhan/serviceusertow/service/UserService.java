package com.hanhan.serviceusertow.service;

import com.hanhan.pojo.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getUser();
    List<UserEntity> login(UserEntity user );
}
