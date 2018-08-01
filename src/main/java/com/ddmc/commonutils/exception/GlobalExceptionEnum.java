package com.ddmc.commonutils.exception;

public enum GlobalExceptionEnum {

	SUCCESS("1001", "成功"), FAIL("1002", "失败"), SYSTEM_ERROR("2000", "系统异常"), PARM_ERROR("1005", "参数错误");

	private String code;

	private String message;

	GlobalExceptionEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
