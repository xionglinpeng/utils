package com.util.utils.crypt.exception;

public class AESDecryptFailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AESDecryptFailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AESDecryptFailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AESDecryptFailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AESDecryptFailException(String secretKey,String data) {
		super("AES decrypt fail. decrypt param : secret key = "+secretKey+"; data = "+data);
		// TODO Auto-generated constructor stub
	}

	public AESDecryptFailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
