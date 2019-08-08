package com.rong.src.study.thread;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rong.src.study.entity.MessageInfo;
import com.rong.src.study.service.MessageService;

/**
 * @Description 启动加载数据库数据
 * @author rongh
 * @date 2019-02-07 18:30
 * @Copyright: Copyright (c) 2018
 */
@Component
public class InitMessageCache implements CommandLineRunner {
	public static final Map<String, Collection<?>> cache = new HashMap<String, Collection<?>>();
	public static final String MESSAGE_CACHE = "messageCache";
	@Autowired
	private MessageService messageService;

	/**
	 * @author rongh
	 * @date 2019-02-07 18:30
	 * @Copyright: Copyright (c) 2018
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		List<MessageInfo> messageList = messageService.listAll();
		cache.put(MESSAGE_CACHE, messageList);
	}

}
