package com.hanhan.application.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.hanhan.application.constants.Constants;
import com.hanhan.application.controller.BaseAction;
import com.hanhan.application.controller.BaseRedisService;
import com.hanhan.application.enums.RequestZuulURLEnum;
import com.hanhan.application.utils.CookieUtil;
import com.hanhan.application.utils.TokenUtils;
import com.hanhan.pojo.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class LoginAction extends BaseAction<UserEntity> {

    @RequestMapping(value = "/")
    public String index(HttpServletRequest reqest, Model model) throws Exception {
        String token  = CookieUtil.getUid(request,Constants.COOKIE_MEMBER_TOKEN);
        Object user =  BaseRedisService.getString(token);
        if(null != user){
            model.addAttribute("user",JSONObject.parseObject(String.valueOf(user),UserEntity.class));
            return "index";
        }
        return "login";
    }


    @RequestMapping(value = "/login")
    public String toLogin() throws Exception {
        return "login";
    }

    @RequestMapping(value = "/doLogin")
    public String doLogin(String name, String pwd, HttpServletResponse response) throws Exception {
        UserEntity user = new UserEntity(name,pwd);
         List<UserEntity> result = this.post(RequestZuulURLEnum.USER_LOGINS.getValue(),JSONObject.toJSONString(user));
        System.out.println("body"+JSONObject.toJSONString(result));
       if(result!=null && result.size()>0){
           String token = TokenUtils.getMemberToken();
           // 3.将token信息存放在cookie里面
           CookieUtil.addCookie(response,Constants.COOKIE_MEMBER_TOKEN, token , Constants.COOKIE_TOKEN_MEMBER_TIME);
           BaseRedisService.setString(token,JSONObject.toJSONString(result.get(0)),Constants.REDIS_USER);
           request.setAttribute("user",JSONObject.parseObject(String.valueOf(user),UserEntity.class));
           return "/index";
       }
       return "/login";
    }


}
