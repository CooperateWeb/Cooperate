package com.cooperate.fly.util;

import java.util.Date;

public class Utils {

	
	public static int findParentWhenCreated(){   //�����ڵ�ʱ�ҵ��ڵ�λ�ã�����ҳ����йأ������Ǵ�XML��ȡ
		return 0;
	}
	
	public static int getIdWhenfocused(){       //���ĳ�ڵ�ʱ���õ��ýڵ�ID�������Ǵ�XML��ȡ
		return 0;
	}
	
	public static int StringToInt(String s){
		int length = s.length();
		int result = 0;
		for(int i=0;i<length;i++){
			result += ((s.charAt(i)-48)*power(10,length-1-i));
		}
		return result;
	}
	
	public static Date NowTime(){
		Date now = new Date();
		return now;
	}
	
	public static void showMessage(String message){
		//show message on html
		
	}
	
	private static int power(int number, int i){
		int result = 1;
		for(int k=0;k<i;k++){
			result *= number;
		}
		return result;
	}
	
	
	
}
