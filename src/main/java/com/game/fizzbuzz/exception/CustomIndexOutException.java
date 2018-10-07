package com.game.fizzbuzz.exception;

public class CustomIndexOutException extends IndexOutOfBoundsException{
	
	public CustomIndexOutException(String message) {
        super(message);
    }
	
	public String getMessage() {
		return "- Error - The index is out of bounds.";
	}
	

}
