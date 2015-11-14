package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface DataInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataInfo record);

    int insertSelective(DataInfo record);

    DataInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataInfo record);

    int updateByPrimaryKey(DataInfo record);
    
    int updateNameById(DataInfo dataInfo);
    
    List<DataInfo> selectByPackageId(Integer packageId);
}