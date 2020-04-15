package com.reactiveworks.stocktrade.dao.exceptions;

public class DaoOperationNotSupportedException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoOperationNotSupportedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoOperationNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DaoOperationNotSupportedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoOperationNotSupportedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoOperationNotSupportedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
