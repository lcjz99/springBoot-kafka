package com.ddmc.commonutils.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ddmc.commonutils.common.utils.SnowflakeIdWorker;

/**
 * 统一日志处理 Created by lichao on 2017/3/24.
 */
@Aspect
@Component
public class LogHandler4InOut {

	@Autowired
	private SnowflakeIdWorker idWorker;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.ddmc.controller..*.*(..))")
	public Object logInAndOut(ProceedingJoinPoint pjp) throws Throwable {
		MDC.put("SEQ_FLAG", String.valueOf(idWorker.nextId()));
		// 拿到request对象，并获取访问地址，ip等信息
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 请求参数
		String paramStr = "";
		Object[] objects = pjp.getArgs();
		if (objects != null && objects.length > 0) {
			paramStr = objects[0].toString();
		}
		// 获取执行类路径+方法名
		String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

		logger.info("请求url：{}, 调用方法为:{}， 请求参数为：{}", request.getRequestURL(), method, paramStr);

		Object o = pjp.proceed();
		logger.info("请求url：{}, 调用方法为:{}， 返回结果为：{}", request.getRequestURL(), method, o);
		MDC.clear();
		return o;
	}
}
