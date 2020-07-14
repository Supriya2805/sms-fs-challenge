package com.sms.server.exception;

public class NoDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582384710970979269L;
	
	 public NoDataFoundException() {
	        super("No data found.");
	    }

}
