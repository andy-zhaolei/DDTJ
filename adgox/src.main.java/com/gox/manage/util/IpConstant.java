package com.gox.manage.util;

public class IpConstant {
	//private static String baseIp="https://lhgw.100bei.com";//正式
	private static String baseIp="http://14.119.110.172";//
	
	//private static String GoxIp="http://192.168.1.146:8080";
	//private static String GoxIp="http://ddtj.shzstr.cn";//正式
	private static String GoxIp="http://39.108.247.168:8090";//测试
	
	public static String getBaseIp(){
		return baseIp;
	}
	public static String getGoxIp(){
		return GoxIp;
	}

}
