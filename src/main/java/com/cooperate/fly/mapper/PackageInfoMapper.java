package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface PackageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PackageInfo record);

    int insertSelective(PackageInfo record);

    PackageInfo selectByPrimaryKey(Integer id);
    
    /**
     * 根据模型id取出所有的数据包
     * @param id
     * @return
     */
    
    List<PackageInfo> selectByModelId(Integer id);

    int updateByPrimaryKeySelective(PackageInfo record);

    int updateByPrimaryKeyWithBLOBs(PackageInfo record);

    int updateByPrimaryKey(PackageInfo record);
}