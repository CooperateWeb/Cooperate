package com.cooperate.fly.datasource;

public abstract class CustomerContextHolder {
	
	private static final String COOPERATE_FLY="CooperateFly";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setContextType(String contextType) {
		contextHolder.set(CustomerContextHolder.COOPERATE_FLY);
	}

	public static String getContextType() {
		return contextHolder.get();
	}

	public static void clearContextType() {
		contextHolder.remove();
	}

}
