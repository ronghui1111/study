package com.rong.src.study.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rong.src.study.annotation.AspectTest;
import com.rong.src.study.service.RateLimitedService;
import com.rong.src.study.thread.ProducerThread;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-07 19:08
 * @Copyright: Copyright (c) 2018
 */
@RestController
@RequestMapping("message")
public class MessageController {

	@Value("${threadSize.producer}")
	private int producerSize;

	@Value("${threadSize.consumer}")
	private int consumerSize;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	@RequestMapping("start")
	@AspectTest
	public boolean startProduce() {
		ExecutorService executor = Executors.newFixedThreadPool(producerSize);
		ProducerThread.flag = true;
		for (int i = 0; i < producerSize; i++) {
			executor.execute(new ProducerThread(kafkaTemplate));
		}
		return true;

	}

	@RequestMapping("stop")
	@AspectTest
	public boolean stopProduce() {
		ProducerThread.flag = false;
		return true;
	}

	@RequestMapping("setQps")
	public boolean setQps(long rate) {
		RateLimitedService.setRate(rate);
		return true;

	}
}
