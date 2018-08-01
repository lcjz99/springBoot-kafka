package com.ddmc.commonutils.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddmc.commonutils.common.ErrorModel;

@ControllerAdvice
public class GlobalExceptionHandler {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorModel<String> jsonErrorHandler(HttpServletRequest req, Exception ex) {
		ErrorModel<String> r = new ErrorModel<String>();

		if (ex instanceof GlobalException) {
			GlobalException globalException = (GlobalException) ex;
			r.setErrCode(globalException.getGlobalExceptionEnum().getCode());
			r.setErrMsg(globalException.getGlobalExceptionEnum().getMessage());
			r.setUrl(req.getRequestURL().toString());
		} else {
			r.setErrCode("500");// 系统错误
			r.setErrMsg(ex.getMessage());
			r.setUrl(req.getRequestURL().toString());
		}

		return r;
	}
}
