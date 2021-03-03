package com.hanhan.serviceuser.mapper.sql;

import com.hanhan.pojo.UserEntity;

public class GetSql {
    public static String getUser(){
        return "select  * from mb_user";
    }


    public static String login(UserEntity user){
        return "select  * from mb_user where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'";
    }

}
