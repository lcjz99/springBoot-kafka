package com.ddmc.commonutils.base;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ddmc.commonutils.common.RespBaseModel;
import com.ddmc.commonutils.common.utils.GsonUtils;
import com.ddmc.commonutils.exception.GlobalExceptionEnum;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		叮咚买菜
 * Author:		lichao
 * Version:		1.0  
 * Create at:	2018年7月28日 下午11:00:40  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 返回json
	 *
	 * @param response
	 * @param object
	 */
	public void renderToJosn(HttpServletResponse response, Object object) {
		renderToJosn(response, object, null);
	}

	/**
	 * 返回json
	 *
	 * @param response
	 * @param object
	 * @param date
	 */
	public void renderToJosn(HttpServletResponse response, Object object, String date) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();

			// TODO 清理参数
			// if (object instanceof PagingResults) {
			// PagingResults result = (PagingResults) object;
			// // result.getParams().clear();
			// }
			if (StringUtils.isBlank(date)) {
				date = "yyyy-MM-dd HH:mm";
			}
			out.write(GsonUtils.getJson(object, date));
			out.flush();
		} catch (Exception e) {
		}
	}

	/**
	 * 处理request参数
	 *
	 * @param request
	 * @return
	 */
	public Map<String, String> processParams(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> paramsName = request.getParameterNames();
		while (paramsName.hasMoreElements()) {
			String name = paramsName.nextElement();
			params.put(name, request.getParameter(name) == null ? "" : request.getParameter(name).trim());
		}
		return params;
	}

	public <T> T getParam(HttpServletRequest request, String param) {
		return getParam(request, param, String.class);
	}

	/**
	 * 从request取参数，返回自己想要的类型
	 *
	 * @param request
	 * @param param
	 * @param clazz
	 * @return
	 */
	public <T> T getParam(HttpServletRequest request, String param, Class clazz) {
		String obj = request.getParameter(param) == null ? "" : request.getParameter(param).trim();
		if (StringUtils.isNotBlank(obj)) {
			if (Integer.class.equals(clazz)) {
				return (T) new Integer(obj);
			} else if (Double.class.equals(clazz)) {
				return (T) new Double(obj);
			} else if (Long.class.equals(clazz)) {
				return (T) new Long(obj);
			} else if (BigDecimal.class.equals(clazz)) {
				return (T) new BigDecimal(obj);
			} else {
				return (T) new String(obj);
			}
		}
		return null;
	}

	/**
	 * 校验参数
	 *
	 * @param result
	 * @param model
	 */
	public void validModel(BindingResult result, Model model) {
		Map map = new HashMap();
		if (result.hasErrors()) {
			for (FieldError fieldError : result.getFieldErrors()) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		model.addAttribute("errorMap", map);
	}

	public RespBaseModel respErrorMsg(BindingResult result) {
		RespBaseModel respModel = new RespBaseModel();
		List<FieldError> list = result.getFieldErrors();
		String respCode = GlobalExceptionEnum.PARM_ERROR.getCode();
		if (list.size() > 0) {
			String msg = list.get(0).getDefaultMessage();
			respModel.setResponseMsg(msg);
		}
		respModel.setResponseCode(respCode);
		respModel.setResponseMsg(GlobalExceptionEnum.PARM_ERROR.getMessage());
		return respModel;
	}
}
