package com.rong.src.study.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rong.src.study.entity.MessageInfo;

/**
 * @Description ��Ϣ���ݷ�����
 * @author rongh
 * @date 2019-08-07 18:39
 * @Copyright: Copyright (c) 2018
 */
public interface MessageService {
	public List<MessageInfo> listAll();

}
