package com.ddmc.commonutils.common.utils;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		叮咚买菜
 * Author:		lichao
 * Version:		1.0  
 * Create at:	2018年7月30日 上午11:36:48  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Listener {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@KafkaListener(topics = { "test" })
	public void listen(ConsumerRecord<?, ?> record) {
		logger.info("kafka的key: " + record.key());
		logger.info("kafka的value: " + record.value().toString());
	}
}