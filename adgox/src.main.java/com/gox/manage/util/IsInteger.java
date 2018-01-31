package com.gox.manage.util;

public class IsInteger {
	
	
	/**
	  * 判断字符串是否是数字
	  */
	 public static boolean isInteger(String value) {
	  try {
	   Integer.parseInt(value);
	   return true;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

}
