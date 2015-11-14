package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.UserGroup;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer groupId);
    
    UserGroup selectByGroupName(String groupName);
    
    List<UserGroup> selectAll();

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}