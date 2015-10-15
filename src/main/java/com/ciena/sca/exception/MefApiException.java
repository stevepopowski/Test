package com.ciena.sca.exception;


public class MefApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_FORMAT = "MEF API Operation Failure, error: %s";
	

	public MefApiException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));
	}

}
