package com.gox.manage.util;
import java.nio.charset.Charset; 

public class DeEnCode {

	   public static String encryptAndUncrypt (String value, char secret) {               
	   // 对value加密，secret密文字符  
	           byte[] bt = value.getBytes();     
	   // 将需要加密的内容转换为字节数组  
	           for (int i = 0; i < bt.length; i++) {  
	               bt[i] = (byte) (bt[i] ^ (int) 
	   secret);          // 通过异或运算进行加密  
	           }  
	           return new String(bt, 0, bt.length); 
	   // 返回加密后的字符串  
	       }
	   
	   
	   public static void main(String[] args) {  
	        String value = "学习Java";          
	// 需要加密的内容  
	        char secret = '8';                   
	// 密文字符  
	        System.out.println("原字符串为：" + value);  
	        // 加密  
	        String encryptResult = DeEnCode.
	encryptAndUncrypt(value, secret);  
	        System.out.println("加密后的值：" + 
	encryptResult);  
	        // 解密  
	        String uncryptResult = DeEnCode.
	encryptAndUncrypt(encryptResult, secret);  
	        System.out.println("解密后的值：" + uncryptResult);  
	    }  
}
