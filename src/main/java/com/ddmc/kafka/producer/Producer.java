package com.ddmc.kafka.producer;

import com.ddmc.commonutils.common.utils.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		叮咚买菜
 * Author:		lichao
 * Version:		1.0  
 * Create at:	2018年7月28日 下午11:01:05  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

@Component
public class Producer {
	@Value("${topicName}")
	private String topicName;// topicName

    @Autowired
    private KafkaTemplate kafkaTemplate;


    //发送消息方法
    public void send(String msg) {
        Message message = new Message();
//        message.setId("KFK_"+System.currentTimeMillis());
//        message.setMsg("["+UUID.randomUUID().toString()+"]->"+msg);
//        message.setSendTime(new Date());
        message.setMsg(msg);
        kafkaTemplate.send(topicName, GsonUtils.getJson(message));
    }

}
