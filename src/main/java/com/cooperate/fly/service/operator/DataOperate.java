package com.cooperate.fly.service.operator;

import java.util.List;

import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.DataValue;
import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.bo.PackageVersion;

public interface DataOperate {

	/**
	 * 得到用户userId所负责的所有数据包的Id
	 * @param userId
	 * @return
	 */
	int[] getPackagesOwn(int userId);
	
	/**
	 * 获取信息，在用户（不包括数据区管理员）登录后，调用此方法，该方法启动一个线程，
	 * 隔断时间查询一次数据库，如有则在前台显示。
	 * @param user_name
	 */
	void getMessage(String user_name);
	
	/**
	 * 创建数据包草稿，选中一个数据包点击创建草稿是，首先获取这个数据包的Id，
	 * 然后弹出一个框，列出所有这个数据包上游数据包的所有版本，供用户进行选择，
	 * 选择完成后，将用户选择的版本id连接成parent_version_id，使用逗号连接
	 * @param packageId
	 * @param parent_version_id 
	 * @return  PackageVersion对象 作为commitversion的参数之一
	 */
	int createVersion(int packageId, String parent_version_id);
	
	/**
	 * 得到所有的上游数据包
	 * @param packageId
	 * @return
	 */
	List<PackageInfo> getUpPackages(int packageId);
	
	/**
	 * 得到packageId的所有版本
	 * @param packageId
	 * @return
	 */
	List<PackageVersion> getPackageVersions(int packageId);
	//List<PackageVersion> getHistoryVersions(int packageId, int versionId);
	
	/**
	 * 编辑数据包草稿，也就是填写数据项那张表，传入表中每一条的三项：数据项id，数据项类型，数据项的值
	 * @param datainfoId
	 * @param type
	 * @param value
	 * @return 数据项的列表，作为commitVersion的参数之一
	 */
	List<DataValue> editVersion(int[] datainfoId, int[] type, String[] value);
	
	/**
	 * 得到该数据包的所有数据项
	 * @param packageId
	 * @return
	 */
	List<DataInfo> getDataOfPackage(int packageId);
	
	/**
	 * 得到数据列表中的分类数据项，用于前端显示
	 * @param list
	 * @return
	 */
	List<DataInfo> getNonLeafData(List<DataInfo> list);
	
	/**
	 * 得到数据表中的数据项，用于前端显示
	 * @param list
	 * @return
	 */
	List<DataInfo> getLeafData(List<DataInfo> list);
	
	/**
	 * 在创建数据包草稿，编辑数据包草稿之后，提交数据包版本
	 * @param pv 创建数据包草稿的返回值
	 * @param value_list 编辑数据包草稿的返回值
	 * @return 
	 */
	int commitVersion(PackageVersion pv, List<DataValue> value_list);
	
	/**
	 * 判断主模型是否关闭
	 * @param packageId
	 * @return 1 主模型已经正常关闭
	 */
	int isModelOver(int packageId);
	
	/**
	 * 根据id得到数据包
	 * @param id 数据包id
	 * @return
	 */
	PackageInfo getPackageById(int id);
	
	/**
	 * 根据版本id(注意和版本号的区别)得到数据值
	 * @param version_id
	 * @return
	 */
	List<DataValue> getDataValueOfVersion(int version_id);
	
	/**
	 * 得到数据包package_id的草稿
	 * @return
	 */
	PackageVersion getCaogao(int package_id);
	/**
	 * 初始化草稿各项值
	 * @param caogao_id
	 * @param package_id
	 */
	void initCaogaoValue(int caogao_id,int package_id);
	/**
	 * 更新草稿各项值
	 * @param input
	 * @param data_info
	 */
	void updateCaogaoValue(String input,String data_info,int caogao_id);
	
	void commitCaogao(int package_id);
}
