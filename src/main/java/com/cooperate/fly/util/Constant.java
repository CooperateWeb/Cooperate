package com.cooperate.fly.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.cooperate.fly.bo.PackageDesign;

/**
 * 全局静态变量
 * @author Stellar_Lee
 *
 */
public class Constant {
	
	public static String getSystemAdminName(){
		Properties pps=new Properties();
		try {
			InputStream in=new BufferedInputStream(new FileInputStream("src/main/resources/conf/local/user.properties"));
			pps.load(in);
			String name=pps.getProperty("SystemAdminName");
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSystemAdminPwd(){
		Properties pps=new Properties();
		try {
			InputStream in=new BufferedInputStream(new FileInputStream("src/main/resources/conf/local/user.properties"));
			pps.load(in);
			String pwd=pps.getProperty("SystemAdminPassword");
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*public static void main(String[] args){
		System.out.println(Constant.getSystemAdminName()+Constant.getSystemAdminPwd());
	}*/
	
	public final static String USER_SESSION_KEY="currentUser";
	
	/**
	 * 系统默认密码
	 */
	public final static String DEFAULT_USER_PASSWORD="123456";
	
	
	//系统初始化数据包信息
	public static List<PackageDesign> getPackageDesigns(){
		PackageDesign pk1=new PackageDesign();
		pk1.setId(1);
		pk1.setName("【属性分类】数据权限分配");
		pk1.setParentId(0);
		
		PackageDesign pk11=new PackageDesign();
		pk11.setId(2);
		pk11.setName("【属性】数据包名称");
		pk11.setParentId(1);
		
		PackageDesign pk12=new PackageDesign();
		pk12.setId(3);
		pk12.setName("【属性】数据包承担人");
		pk12.setParentId(1);
		
		PackageDesign pk2=new PackageDesign();
		pk2.setId(4);
		pk2.setName("【属性分类】数据包谱系关系");
		pk2.setParentId(0);
		
		PackageDesign pk21=new PackageDesign();
		pk21.setId(5);
		pk21.setName("【属性】上游数据包");
		pk21.setParentId(4);
		
		PackageDesign pk22=new PackageDesign();
		pk22.setId(6);
		pk22.setName("【属性】下游数据包");
		pk22.setParentId(4);
		
		PackageDesign pk3=new PackageDesign();
		pk3.setId(7);
		pk3.setName("【属性分类】其他信息");
		pk3.setParentId(0);
		
		PackageDesign pk31=new PackageDesign();
		pk31.setId(8);
		pk31.setName("【属性】数据包扩展信息");
		pk31.setParentId(7);
		
		List<PackageDesign> pks=new ArrayList<PackageDesign>();
		pks.add(pk1);
		pks.add(pk11);
		pks.add(pk12);
		pks.add(pk2);
		pks.add(pk21);
		pks.add(pk22);
		pks.add(pk3);
		pks.add(pk31);
		return pks;
	}
	
}
