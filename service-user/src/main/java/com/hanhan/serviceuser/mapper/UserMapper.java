package com.hanhan.serviceuser.mapper;

import com.hanhan.pojo.UserEntity;
import com.hanhan.serviceuser.mapper.sql.GetSql;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface UserMapper {


    @SelectProvider(method="getUser",type= GetSql.class)
    List<UserEntity> getUser();


    @SelectProvider(method="login",type= GetSql.class)
    List<UserEntity> login( UserEntity user);
}

