package com.benign.drone.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException() {
		super("entity already exists");
	}

}
