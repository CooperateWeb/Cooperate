package com.cooperate.fly.service.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.mapper.CatalogMapper;
import com.cooperate.fly.mapper.DataInfoMapper;
import com.cooperate.fly.mapper.ModelInfoMapper;
import com.cooperate.fly.mapper.PackageInfoMapper;
import com.cooperate.fly.service.model.ModelDesign;
import com.cooperate.fly.util.Utils;

@Service("modelDesign")
public class ModelDesignImpl implements ModelDesign {

	int id = 0;
	@Autowired
	CatalogMapper catalogmapper;
	@Autowired
	DataInfoMapper datainfomapper;
	@Autowired
	PackageInfoMapper packageinfomapper;
	@Autowired
	ModelInfoMapper modelinfomapper;
	@Override
	public int createModelCatalogNode(String nodeName, int parentId) {
		id ++;
		Catalog record = new Catalog();
		record.setId(id);
		record.setName(nodeName);
		record.setParentId(parentId);
		record.setType(0);        //1:����ڵ�
		catalogmapper.insert(record);
		return 0;
	}
	
	@Override
	public int createModelNode(String nodeName, int parentId){
		id ++;
		Catalog record = new Catalog();
		record.setId(id);
		record.setName(nodeName);
		record.setParentId(parentId);
		record.setType(1);        //1:��ģ�ͽڵ�
		catalogmapper.insert(record);
		//create model
		ModelInfo model = new ModelInfo();
		model.setId(id);
		model.setName(nodeName);
		model.setState(0);//��ʼ��Ϊ���ɱ༭
		modelinfomapper.insert(model);
		return 0;
	}

	@Override
	public int createPackageCatalogNode(String nodeName, int parentId) {
		id ++;
		Catalog record = new Catalog();
		record.setId(id);
		record.setName(nodeName);
		record.setParentId(parentId);
		record.setType(2);        //2
		catalogmapper.insert(record);
		return 0;
	}
	
	@Override
	public int createPackageNode(String nodeName, int parentId) {
		id ++;
		Catalog record = new Catalog();
		record.setId(id);
		record.setName(nodeName);
		record.setParentId(parentId);
		record.setType(3);        //2:��ݰ�ڵ�
		catalogmapper.insert(record);
		//create package
		PackageInfo new_package = new PackageInfo();
		new_package.setId(id);
		new_package.setName(nodeName);
		new_package.setPid("");
		new_package.setSid("");
		new_package.setDirectorId(0);
		int modelId = getItsModelId(id);
		new_package.setModelId(modelId);
		return 0;
	}
	
	@Override
	public int deleteCatalogNode(int nodeId){
		catalogmapper.deleteByPrimaryKey(nodeId);
		return 0;
	}
	
	public int updateCatalogNode(Catalog catalog){
		catalogmapper.updateNameById(catalog);
		return 0;
	}

	@Override
	public int createNoneLeafDataNode(String nodeName,int nodeId, int parentId) {//DataInfoMapper.xml��insertҪ��,���ò���id����
		DataInfo record = new DataInfo();
		record.setName(nodeName);
		record.setParentId(parentId);
		record.setType(1);
		record.setPackageId(nodeId);
		datainfomapper.insert(record);
		return 0;
	}

	@Override
	public int createLeafDataNode(String nodeName, int type, int nodeId, int parentId) {
		DataInfo record = new DataInfo();
		record.setName(nodeName);
		record.setParentId(parentId);
		record.setType(type);
		record.setPackageId(nodeId);
		datainfomapper.insert(record);
		return 0;
	}
	
	@Override
	public int deleteDataNode(int nodeId){
		datainfomapper.deleteByPrimaryKey(nodeId);
		return 0;
	}
	
	@Override
	public int updateDataNode(DataInfo dataInfo){
		datainfomapper.updateNameById(dataInfo);
		return 0;
	}
	
	@Override
	public void modelStart(ModelInfo modelInfo){//����ΪmodelID
		//�ø�ģ������ݰ�Ϊ�ɱ༭
		//update model_info set state = 1 where modelId = this.modelId
		modelinfomapper.updateStateById(modelInfo);
	}
	
	@Override
	public void modelOver(ModelInfo modelInfo){
		//�ø�ģ������ݰ�Ϊ���ɱ༭
		//update model_info set state = 0 where modelId = this.modelId
		modelinfomapper.updateStateById(modelInfo);
	}
	
	@Override
	public List<PackageInfo> getPackages(int modelId){
		//select * from package_info where package_info.modelId == modelId
		List<PackageInfo> packages = new ArrayList<PackageInfo>();
		packages = packageinfomapper.selectByModelId(modelId);
		return packages;
	}
	
//	@Override
//	public PackageInfo[] getOtherPackages(PackageInfo[] packages, int packageId){
//		//select * from packages where package_info.id != packageId
//		
//		return null;
//	}
	
//	@Override
//	public int[] getPackagesId(int modelId){
//		//select packageId from packages
//		
//		
//		return null;
//	}
	
	@Override
	public int checkFiliation(int modelId){
		List<PackageInfo> packages = new ArrayList<PackageInfo>();
		packages = getPackages(modelId);
		int nodeId = 0;
	    String pid = null;
		String sid = null;
		int count = packages.size();
		for(int i = 0; i < packages.size(); i ++){
			nodeId = packages.get(i).getId();
			pid = packages.get(i).getPid();
			sid = packages.get(i).getSid();
			if(pid.equals("")){             //�ҵ�û��������ݰ����ݰ�
				count --;
				String[] sid_array = sid.split(",");
				for(int k=1;k<sid_array.length;k++){
					int index = getPackageIndexById(packages, Utils.StringToInt(sid_array[k])); //��sidΪ����ʱ������Ӧ����ѭ��
					if(index!=-1){
						String pid2 = packages.get(index).getPid();
						String nodeId_charsequence = ","+ nodeId;
						pid2.replace(nodeId_charsequence, "");
						packages.get(index).setPid(pid2);//�Ѷ�ӦnodeId���Ǹ�pid��Ϊ""
					}
				}
			}
		}
		if(count==0){
			return 1;
		}
		return 0;
	}
	
	@Override
	public int updateSonIds(int nodeId){
		//1. select pids from package_info where package_info.nodelId == nodelId
		PackageInfo the_package = packageinfomapper.selectByPrimaryKey(nodeId);
		String pids = the_package.getPid();
		//2. for each {pid} in pids, add nodeId to their sids
		String[] pid_array = pids.split(",");
		for(int i=1;i<pid_array.length;i++){
			int pid = Utils.StringToInt(pid_array[i]);
			PackageInfo the_P_package = packageinfomapper.selectByPrimaryKey(pid);
			String sid = the_P_package.getSid() + "," + nodeId;
			the_P_package.setSid(sid);
		}
		return 0;
	}
	
//private========================================================================================
	private int getPackageIndexById(List<PackageInfo> packages, long id){
		for(int i = 0; i < packages.size(); i ++){
			if(packages.get(i).getId()==id){
				return i;
			}
		}
		return -1;
	}
	
	private int getItsModelId(int packageId){
		Catalog node = catalogmapper.selectByPrimaryKey(packageId);
		int type = node.getType();
		while(type!=1){
			int parentId = node.getParentId();
			node = catalogmapper.selectByPid(parentId);
			type = node.getType();
		}
		return node.getId();
	}

	@Override
	public List<Catalog> getCatalogNodes() {
		return catalogmapper.selectAll();
	}


}
