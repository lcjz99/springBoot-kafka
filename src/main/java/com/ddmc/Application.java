package com.ddmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.ddmc.commonutils.common.utils.SnowflakeIdWorker;
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

@SpringBootApplication
@PropertySource(value = "file:/app/config/kafka_config")
public class Application {
	@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
		return idWorker;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
