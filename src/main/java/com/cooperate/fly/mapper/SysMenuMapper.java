package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.SysMenu;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> selectAll();
    
    /**
     * 通过孩子节点id搜索parentId递归整个树到根节点
     * @param menId
     * @return
     */
    List<SysMenu> selectByChildId(List<Integer> menId);
}