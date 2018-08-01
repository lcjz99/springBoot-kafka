package com.ddmc.commonutils.exception.sysexcetion;

/**
 * @title 业务异常
 * @description 当业务执行过程中,需要提示某些业务信息时抛出该异常
 * @author lichao
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3239975665962607154L;

	/**
	 * 业务信息编码
	 */
	private String businessCode;
	
	/**
	 * 业务异常信息
	 */
	private String message;

	/**
	 * 业务信息参数
	 */
	private final Object[] args;

	/**
	 * @param errorCode
	 * @param args
	 */
	public BusinessException(String errorCode, Object... theArgs) {
		super(errorCode);
		this.businessCode = errorCode;
		this.args = theArgs;
	}
	
	public BusinessException(String code, String message, Throwable t) {
		super(message, t);
		this.businessCode = code;
		this.message = message;
		this.args = new Object[]{message, t};
	}

	public BusinessException(String message, Throwable t) {
		this("ERR-0000",message, t);
	}
	
	public BusinessException(String message) {
		this(message, (Throwable)null);
	}

	/**
	 * 获取“args”(类型：Object[])
	 */
	public Object[] getArgs() {
		return args;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		StringBuffer argsBuf = new StringBuffer();
		for (int i = 0; args != null && i < args.length; i++) {
			argsBuf.append(args[i]);
			if (i + 1 < args.length) {
				argsBuf.append(",");
			}
		}

		return "{" + getClass().getName() + "@" + hashCode() + "[" + businessCode + "(" + argsBuf + ")]}";
	}

	/**
	 * @return the busiInfoCode
	 */
	public String getBusinessCode() {
		return businessCode;
	}

	/**
	 * @param busiInfoCode the busiInfoCode to set
	 */
	public void setBusinessCode(String busiInfoCode) {
		this.businessCode = busiInfoCode;
	}
	
	/**
	 * @return 自定义的错误信息
	 */
	public String getBuziMessage() {
		return this.message;
	}
}