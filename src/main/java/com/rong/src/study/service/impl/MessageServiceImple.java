package com.rong.src.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rong.src.study.dao.MessageDao;
import com.rong.src.study.entity.MessageInfo;
import com.rong.src.study.service.MessageService;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-07 18:42
 * @Copyright: Copyright (c) 2018
 */
@Service
public class MessageServiceImple implements MessageService {
	@Autowired
	private MessageDao messageDao;

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-07 18:43
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	@Override
	public List<MessageInfo> listAll() {
		return messageDao.listAll();
	}

}
