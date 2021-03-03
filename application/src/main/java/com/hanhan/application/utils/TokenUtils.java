package com.hanhan.application.utils;

import com.hanhan.application.constants.Constants;

import java.util.UUID;




public class TokenUtils {

	 public static String getMemberToken(){
		 return Constants.TOKEN_MEMBER+"-"+UUID.randomUUID();
	 }
	
}
