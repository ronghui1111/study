/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-07 16:55
 * @Title: ProducerThread.java
 * @Company: CORSWORK
 * @Copyright: Copyright (c) 2018
 */
package com.rong.src.study.thread;

import static com.rong.src.study.thread.InitMessageCache.MESSAGE_CACHE;
import static com.rong.src.study.thread.InitMessageCache.cache;

import java.util.List;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.rong.src.study.entity.MessageInfo;
import com.rong.src.study.service.RateLimitedService;
/**
 * @author rongh
 *
 */
@Component
public class ProducerThread extends Thread {
	public static boolean flag = true;

    private KafkaTemplate<String, String> kafkaTemplate;
	public ProducerThread(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	@Override
	public void run() {
		while (flag) {
			while (RateLimitedService.tryAcquire()) {
			List<MessageInfo> list = (List<MessageInfo>) cache.get(MESSAGE_CACHE);
			Random random = new Random();
			MessageInfo info = list.get(random.nextInt(list.size()));
			if(info!=null) {
				kafkaTemplate.send("messageTopic", info.toString());
				CountThread.increment();
			}
			}
		}
	}

}
