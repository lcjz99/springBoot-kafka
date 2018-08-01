package com.ddmc.commonutils.exception.sysexcetion;

public class ExceptionUtils {

	private static final String TEMPLATE_NAME = "riskctrl.errotemplate";

	public static void throwRiskException(String orderNo, String errorCode) throws BusinessException {
		throw new BusinessException(TEMPLATE_NAME, orderNo, errorCode);
	}
}