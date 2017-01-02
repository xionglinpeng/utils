package com.util.utils.crypt.exception;

import java.security.Key;

public class RSADecryptFailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RSADecryptFailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RSADecryptFailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RSADecryptFailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RSADecryptFailException(Key key,String data) {
		super("RSA decrypt fail. decrypt param : key = "+key+"; data = "+data);
		// TODO Auto-generated constructor stub
	}

	public RSADecryptFailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
