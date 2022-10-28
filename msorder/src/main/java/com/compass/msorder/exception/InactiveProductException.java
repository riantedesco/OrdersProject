package com.compass.msorder.exception;

public class InactiveProductException extends RuntimeException {

	private static final long serialVersionUID = -8802565387705835064L;

	public InactiveProductException(String mensagem) {
		super(mensagem);
	}
}
