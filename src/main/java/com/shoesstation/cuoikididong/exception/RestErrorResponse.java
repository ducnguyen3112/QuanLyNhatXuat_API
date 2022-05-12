package com.shoesstation.cuoikididong.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class RestErrorResponse {
	private HttpStatus status;
	private LocalDateTime timeStamp;
	private String message;
	public RestErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public RestErrorResponse(String message, HttpStatus status, LocalDateTime timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
