package com.compass.msorder.exception;

public class InvalidAttributeException extends RuntimeException {

	private static final long serialVersionUID = -8802565387705835064L;

	public InvalidAttributeException(String mensagem) {
		super(mensagem);
	}
}
