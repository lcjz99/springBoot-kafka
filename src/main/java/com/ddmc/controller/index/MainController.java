package com.ddmc.controller.index;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ddmc.commonutils.base.BaseController;
import com.ddmc.commonutils.common.RespBaseModel;
import com.ddmc.commonutils.exception.GlobalExceptionEnum;
import com.ddmc.kafka.producer.Producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "用户行为日志收集")
public class MainController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	@ResponseBody
//	public String helloOpenApi() {
//		logger.info("欢迎...");
//		return "welcome";
//	}
    @Autowired
    private Producer producer;
    
    @ApiOperation(value = "用户行为日志收集", notes = "根据前端埋点收集用户行为日志")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "message", required = true, value = "日志参数", dataType = "String", paramType = "form"), })
    @RequestMapping(value = "/", method = RequestMethod.POST)
	public RespBaseModel sendKafka(@Valid @RequestBody String message, BindingResult result) {
    	RespBaseModel respModel = new RespBaseModel();
		if (message == null) {
			logger.info(GlobalExceptionEnum.PARM_ERROR.getMessage());
			return respErrorMsg(result);
		}
		try {
			logger.info("kafka的消息={}", message);
			 producer.send(message);
			respModel.setResponseCode(GlobalExceptionEnum.SUCCESS.getCode());
			respModel.setResponseMsg("消息发送"+GlobalExceptionEnum.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("发送kafka失败", e);
			respModel.setResponseCode(GlobalExceptionEnum.FAIL.getCode());
			respModel.setResponseMsg("消息发送"+GlobalExceptionEnum.FAIL.getMessage());
		}
		return respModel;
	}
}
