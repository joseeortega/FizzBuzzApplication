package com.game.fizzbuzz.exception;

public class CustomNullException extends NullPointerException{
	
	public CustomNullException(String message) {
        super(message);
    }
	
	public String getMessage() {
		return "- Error - Null value.";
	}
	

}
