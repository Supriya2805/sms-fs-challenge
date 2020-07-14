package com.sms.server.exception;

public class CityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3306903205435120155L;
	

	public CityNotFoundException(int id) {

		super(String.format("City with Id %d not found", id));
	}
}
