package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.DataValue;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface DataValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataValue record);

    int insertSelective(DataValue record);

    DataValue selectByPrimaryKey(Integer id);
    
    List<DataValue> selectByVersion(Integer version_id);

    int updateByPrimaryKeySelective(DataValue record);

    int updateByPrimaryKeyWithBLOBs(DataValue record);

    int updateByPrimaryKey(DataValue record);
    
    int updateCaogaoValue(DataValue record);
    
}