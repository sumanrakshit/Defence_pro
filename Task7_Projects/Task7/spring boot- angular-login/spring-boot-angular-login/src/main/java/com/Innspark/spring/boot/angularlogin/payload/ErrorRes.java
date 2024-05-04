package com.Innspark.spring.boot.angularlogin.payload;

import org.springframework.http.HttpStatus;


//Error response when authentication done
public class ErrorRes {
	
	 HttpStatus httpStatus;
	    String message;

	    public ErrorRes(HttpStatus httpStatus, String message) {
	        this.httpStatus = httpStatus;
	        this.message = message;
	    }

	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }

	    public void setHttpStatus(HttpStatus httpStatus) {
	        this.httpStatus = httpStatus;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

		@Override
		public String toString() {
			return "ErrorRes [httpStatus=" + httpStatus + ", message=" + message + "]";
		}
	    

}
