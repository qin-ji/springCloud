package com.hanhan.application.constants;

public interface Constants {
		// 响应请求成功
		String HTTP_RES_CODE_200_VALUE = "success";
		// 系统错误
		String HTTP_RES_CODE_500_VALUE = "error";
		// 响应请求成功code
		Integer HTTP_RES_CODE_200 = 200;
		// 系统错误
		Integer HTTP_RES_CODE_500 = 500;
		//未关联QQ账号
		Integer HTTP_RES_CODE_201 = 201;
		// 发送邮件
		String MSG_EMAIL ="email";

		// 会员token
		String TOKEN_MEMBER ="TOKEN_HANHAN";
		// 用户有效期 90天
		Long TOKEN_MEMBER_TIME =(long) (60*60*24*90);
		// 用户权限集合 90天
		Long USER_MEN_TIME =(long) (60*60*24*90);
		// 颜色 90天
		Long COLOR_TIME =(long) (60*60*24*90);
		// 用户头像
		Long USER_IMG_TIME =(long) (60*60*24*90);

		//Redis  - 用户登录信息缓存时间
		Long REDIS_USER =(long)(60*60*24*90);

		int COOKIE_TOKEN_MEMBER_TIME =(60*60*24*90);
		// cookie 会员 totoken 名称
		String COOKIE_MEMBER_TOKEN ="cookie_hanhan_token";


				
}