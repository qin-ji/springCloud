package com.hanhan.application.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;



public interface RequestZuulBase<T> {

	 ResponseEntity<Integer> requestZuulMap(String url ) throws Exception  ;
	
	 ResponseEntity<Integer> requestZuulT(String url ,T t) throws Exception  ;
	
	 List<T> requestZuulListT(String url) throws Exception  ;

	 List<T> requestZuulListTParamT(String url,T t) throws Exception  ;

	ResponseEntity<List>  requestZuulListTParamMap(String url) throws Exception  ;

	List<T> post(String url,String jsonParam) throws Exception  ;
}