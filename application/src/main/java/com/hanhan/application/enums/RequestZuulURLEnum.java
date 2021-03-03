package com.hanhan.application.enums;

/**
 *  网关枚举类
 *  zuul ： 为网关的服务名
 *  feign : feign微服务的服务名 ，改服务名已在网关中配置通过权限 ，所以可以正常通过
 */
public enum RequestZuulURLEnum {

		//-----------------start :: ZUUL - URL ---------------------------
		QUERY_ZUUL_LIST("QUERY_ZUUL_LIST","http://zuul/feign/zuul/getAllZuul"),
		UPDATE_ZUUL("UPDATE_ZUUL","http://zuul/feign/zuul/updateZuul"),
		DELETE_ZUUL("DELETE_ZUUL","http://zuul/feign/zuul/deleteZuul"),
		ADD_ZUUL("DELETE_ZUUL","http://zuul/feign/zuul/addZuul"),
		//-----------------end :: ZUUL - URL ---------------------------

		//-----------------start :: USER - URL ---------------------------
		USER_LOGINS("USER_LOGINS","http://zuul/feign/user/login"),
		QUERY_USER_LIST("QUERY_USER_LIST","http://zuul/feign/user/getUser");
		//-----------------end :: USER - URL ---------------------------
		
		
		private String name;
		private String value;
		private RequestZuulURLEnum(String name,String value) {
			this.name = name;
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setType(String value) {
			this.value = value;
		}
		
		

	
}
