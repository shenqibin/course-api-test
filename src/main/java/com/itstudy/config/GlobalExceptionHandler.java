package com.itstudy.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itstudy.exception.ParameterException;
//@ControllerAdvice
public class GlobalExceptionHandler {

	// 处理自定义异常
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(ParameterException.class)
    @ResponseBody
    public ResponseEntity handleCustomException(ParameterException e) {
    	Map<String,String> map = new HashMap<>();
    	map.put("code", e.getCode());
    	map.put("message", e.getMessage());
    	return ResponseEntity.badRequest().body(map);
    }

    // 处理其他未捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception e) {
      	Map<String,String> map = new HashMap<>();
      	System.out.println("1111111111111");
      	e.printStackTrace();
    	map.put("code", "E099");
    	map.put("message", e.getMessage());
    	return ResponseEntity.status(500).body(map);
    }
    
}
