package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.RoleMenu;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByMenuId(Integer menuId);
    int deleteByRoleId(Integer roleId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);
    
    List<Integer> selectMenuIdByRoleId(Integer roleId);
}