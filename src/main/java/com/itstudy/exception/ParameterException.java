package com.itstudy.exception;

public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String  code; // 可选，添加状态码或错误代码

    public ParameterException(String code,String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
