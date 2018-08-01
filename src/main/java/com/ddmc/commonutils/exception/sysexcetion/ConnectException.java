/**
 * 
 */
package com.ddmc.commonutils.exception.sysexcetion;

/**
 * @title 对外连接异常
 * @description 在于外部单位进行通讯过程中发生错误时抛出该异常
 * @author lichao
 */

public class ConnectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7259193909589439693L;

	public ConnectException() {
		super();
	}

	public ConnectException(String msg, Throwable t) {
		super(msg, t);
	}

	public ConnectException(String msg) {
		super(msg);
	}

	public ConnectException(Throwable t) {
		super(t);
	}

}
