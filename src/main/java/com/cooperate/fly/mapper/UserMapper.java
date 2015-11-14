package com.cooperate.fly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteMessageByUserName(String userName);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    User selectByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    void updatePassword(@Param("param1")Integer userId,@Param("param2")String password);
    
    List<User> selectAll();
    List<User> selectByGroupId(Integer groupId);
    
    Page<User> selectPage(Pageable pageRequest);
    Page<User> selectPageByParams(@Param("param1")Map<String, Object> params,@Param("param2")Pageable pageRequest);
    
    int updateUserByRoleNull(Integer roleId);
    
    int countByUserName(String userName);
    
    User loadUserByUserNameAndPassword(@Param("param1")String userName,@Param("param2")String password);
    
}