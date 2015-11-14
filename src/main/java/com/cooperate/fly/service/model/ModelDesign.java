package com.cooperate.fly.service.model;

import java.util.List;

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.bo.PackageInfo;

public interface ModelDesign {

	/**
	 * 创建主模型目录节点，一般操作为点击父节点，创建新节点，点击父节点时需要获取到其Id，
	 * 在创建第一个节点时，parrentId默认为0
	 * @param nodeName 用户输入的节点名称 
	 * @param parentId 父节点Id
	 * @return 
	 */
	int createModelCatalogNode(String nodeName, int parentId);
	
	/**
	 * 创建主模型节点
	 * @param nodeName
	 * @param parentId
	 * @return
	 */
	int createModelNode(String nodeName, int parentId);
	
	/**
	 * 创建数据包目录节点
	 * @param nodeName
	 * @param parentId
	 * @return
	 */
	int createPackageCatalogNode(String nodeName, int parentId);
	
	/**
	 * 创建数据包节点
	 * @param nodeName
	 * @param parentId
	 * @return
	 */
	int createPackageNode(String nodeName, int parentId);
	
	/**
	 * 删除该节点
	 * @param nodeId
	 * @return
	 */
	int deleteCatalogNode(int nodeId);
	
	/**
	 * 更新节点
	 * @param catalog
	 * @return
	 */
	int updateCatalogNode(Catalog catalog);
	
	/**
	 * 创建数据项分类节点，一般操作为点击目录树中的数据包节点，然后创建此数据包的数据项，
	 * 点击数据包节点获取到数据包节点的Id
	 * @param nodeName 数据项名称
	 * @param nodeId 所属数据包节点Id
	 * @return
	 */
	int createNoneLeafDataNode(String nodeName, int nodeId, int parentId);
	
	/**
	 * 创建数据项节点
	 * @param nodeName 节点的名称
	 * @param type 数据项类型
	 * @param nodeId 数据包几点的id
	 * @return
	 */
	int createLeafDataNode(String nodeName, int type, int nodeId, int parentId);
	
	/**
	 * 删除数据项节点
	 * @param nodeId
	 * @return
	 */
	int deleteDataNode(int nodeId);
	
	/**
	 * 更新数据项节点
	 * @param dataInfo
	 * @return
	 */
	int updateDataNode(DataInfo dataInfo);
	
	/**
	 * 发起主模型
	 * @param modelInfo
	 */
	void modelStart(ModelInfo modelInfo);
	
	/**
	 * 关闭主模型
	 * @param modelInfo
	 */
	void modelOver(ModelInfo modelInfo);

	/**
	 * 得到id为modelId主模型下的所有数据包
	 * @param modelId
	 * @return
	 */
	List<PackageInfo> getPackages(int modelId);
	//PackageInfo[] getOtherPackages(PackageInfo[] packages, int packageId);
	//int[] getPackagesId(int modelId)
	
	/**
	 * 检查id为moldeId的主模型下的数据包之间的谱系关系
	 * @param modelId
	 * @return
	 */
	int checkFiliation(int modelId);
	
	/**
	 * 更新数据包下游信息，每当创建一个数据包，定义其上游数据包时，就调用此方法更新这个数据包(nodeId)的
	 * 上游数据包的下游信息
	 * @param modelId 为nodeId
	 * @return
	 */
	int updateSonIds(int modelId);
	
	/**
	 * 获取所有目录节点
	 * @return
	 */
	List<Catalog> getCatalogNodes();
	
	
	
}
