package com.rong.src.study.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rong.src.study.entity.MessageInfo;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-02-07 17:03
 * @Copyright: Copyright (c) 2018
 */
@Mapper
public interface MessageDao {
	public int insert(MessageInfo info);

	public int update(MessageInfo info);

	public int delete(MessageInfo info);

	public int save(MessageInfo info);

	public int insertBatch(List<MessageInfo> list);

	@Select("select * from message")
	public List<MessageInfo> listAll();

}
