package com.cooperate.fly.common.mybatis;

public class Dialect {
	public boolean supportsLimit(){
		return false;
	}
	
	public boolean supportLimitOffset(){
		return supportsLimit();
	}
	
	/**
	 * ��sql��Ϊʹ��offset,limit��ֵ��Ϊռλ��ķ�ҳsql���
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	public String getLimitString(String sql,int offset,int limit){
		return getLimitString(sql, offset, Integer.toString(offset),limit,Integer.toString(limit));
	}
	
	public String getLimitString(String sql,int offset,String offsetPlaceholder,int limit,String limitPlaceholder){
		throw new UnsupportedOperationException("paged queries not supported");		
	}
	
	/**
	 * ��sqlת��Ϊ�ܼ�¼��SQL
	 */
	public String getCountString(String sql){
		return "select count(1) from ("+sql+") temp_count";
	}
}