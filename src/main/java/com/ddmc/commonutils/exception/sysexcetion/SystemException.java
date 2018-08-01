package com.ddmc.commonutils.exception.sysexcetion;

/**
 * @title 系统异常(使用者无法自行处理,需要开发人员干预的问题)
 * @description
 * @author Lincoln
 */
public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5583917294685206042L;

	/**
	 * 
	 */
	public SystemException() {
		super();

	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public SystemException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);

	}
}
