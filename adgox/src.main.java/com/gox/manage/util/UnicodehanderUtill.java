package com.gox.manage.util;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.HttpException;

/*******
 *处理客户端传递过来的昵称中有特殊的  表情  比如气球.....
 * @author chengsheng
 *
 */
public class UnicodehanderUtill {
	public static String toUnicode(String str){
	    char[]arChar=str.toCharArray();
	    int iValue=0;
	    String uStr="";
	    for(int i=0;i<arChar.length;i++){
	        iValue=(int)str.charAt(i);           
	        if(iValue<=256){
	          // uStr+="& "+Integer.toHexString(iValue)+";";
	            uStr+="\\"+Integer.toHexString(iValue);
	        }else{
	          // uStr+="&#x"+Integer.toHexString(iValue)+";";
	            uStr+="\\"+Integer.toHexString(iValue);
	        }
	    }
	    return uStr;
	}

	public   static   String   unicodeToGB(String   s)   {      
	    StringBuffer   sb   =   new   StringBuffer();      
	    StringTokenizer   st   =   new   StringTokenizer(s,   "\\u");      
	    while   (st.hasMoreTokens())   {      
	        sb.append(   (char)   Integer.parseInt(st.nextToken(),   16));      
	    }      
	    return   sb.toString();      
	} 
	public static String removeEspecialChartoString (String nickname){
		nickname = UnicodehanderUtill.toUnicode(nickname);
		
		nickname = nickname.substring(1,nickname.length());
		String[] array = nickname.split("\\\\");
		StringBuffer buff = new StringBuffer();
		
		for(String a:array){
			int i =Integer.parseInt(a,16);
			if(i>=Integer.parseInt("d800",16) && i<=Integer.parseInt("dbff",16)){
				
			}
			else if(i>=Integer.parseInt("dc00",16) && i<=Integer.parseInt("dfff",16)){
				
			}
			else{
				buff.append("\\"+a);
			}
		}
		
		//s="\\u738b\\u7490\\ud83c\\udf88";
		String str = UnicodehanderUtill.unicodeToGB(buff.toString()); 
	//	str = UnicodehanderUtill.unicodeToGB(buff.toString()); 
		return str;
		//System.out.println(str);
	}
	
	
	
	
	
	
	
	
	
public static void main(String[] args) throws HttpException, IOException {
		
		String s =UnicodehanderUtill.removeEspecialChartoString("a🎈🎈b123啊比🎈王璐!@#$"); 
		System.out.println(s);
		


}
}
