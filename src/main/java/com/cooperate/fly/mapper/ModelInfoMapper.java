package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface ModelInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelInfo record);

    int insertSelective(ModelInfo record);

    ModelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelInfo record);

    int updateByPrimaryKey(ModelInfo record);
    
    int updateStateById(ModelInfo modelInfo);
}