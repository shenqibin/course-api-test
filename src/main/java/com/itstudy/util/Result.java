package com.itstudy.util;

import lombok.Data;

//用于封装状态和数据
@Data
public class Result<T> {

	private String status ;	
	private T data;

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", data=" + data + "]";
	}
	
	
}
