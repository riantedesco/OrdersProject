package com.compass.mspayment.exception;

public class NotFoundAttributeException extends RuntimeException {

	private static final long serialVersionUID = -8802565387705835064L;

	public NotFoundAttributeException(String mensagem) {
		super(mensagem);
	}
}
