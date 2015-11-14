package com.cooperate.fly.util;

public class PwdHelper {
	
	public static String encryptPassword(String pwdSource){
		return EncryptUtils.encryptByMD5(pwdSource);
	}
}
