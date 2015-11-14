package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.UserRole;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    List<UserRole> selectAll();
}