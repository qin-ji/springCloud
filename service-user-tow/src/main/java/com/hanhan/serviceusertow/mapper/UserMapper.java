package com.hanhan.serviceusertow.mapper;

import com.hanhan.pojo.UserEntity;
import com.hanhan.serviceusertow.mapper.sql.GetSql;
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
