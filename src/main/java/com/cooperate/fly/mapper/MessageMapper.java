package com.cooperate.fly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cooperate.fly.bo.Message;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface MessageMapper {

	int insertMessageByUserId(Message message);
	List<Message> getMessageOfUser(Integer user_id);
	List<Message> getMessageUnread(Integer user_id);
	int setMessageRead(Integer id);
}
