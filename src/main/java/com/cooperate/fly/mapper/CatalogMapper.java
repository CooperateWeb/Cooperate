package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface CatalogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Catalog record);

    int insertSelective(Catalog record);

    Catalog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Catalog record);

    int updateByPrimaryKey(Catalog record);
    
    /**
     * 根据目录名称获取
     * @param name
     * @return
     */
    Catalog selectByName(String name);
    Catalog selectByPid(Integer id);
    
    /**
     * 修改节点名称
     * @param name
     * @param Id
     * @return
     */
    int updateNameById(Catalog catalog);
    
    /**
     * 获取所有目录节点
     * @return
     */
    List<Catalog> selectAll();
}