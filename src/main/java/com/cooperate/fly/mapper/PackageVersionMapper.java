package com.cooperate.fly.mapper;

import java.util.List;

import com.cooperate.fly.bo.PackageVersion;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface PackageVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PackageVersion record);

    int insertSelective(PackageVersion record);

    PackageVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PackageVersion record);

    int updateByPrimaryKey(PackageVersion record);
    
    /**
     * 根据数据包Id选出所有的版本
     */
    List<PackageVersion> selectByPackageId(Integer packageId);
    /**
     * 得到数据包的草稿
     * @return
     */
    PackageVersion selectCaogao(Integer packageId);
    /**
     * 提交草稿
     * @param packageId
     * @return
     */
    int commitCaogao(PackageVersion record);
}