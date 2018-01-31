package com.gox.manage.util;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
public class HMACSHA { //http://blog.csdn.net/qq_35624642/article/details/76619763(加密来源)
      //   SECRET KEY    
private final static String secret_key = "ndE2jdZNFixH9G6Aidsfyf7lYT3PxW";  
  /**     
  * 将加密后的字节数组转换成字符串   
  *    
  * @param b 字节数组    
  * @return 字符串     
  */   
 private static String byteArrayToHexString(byte[] b) {  
      StringBuilder hs = new StringBuilder();     
      String stmp;      
  for (int n = 0; b!=null && n < b.length; n++) {   
         stmp = Integer.toHexString(b[n] & 0XFF);    
         if (stmp.length() == 1)           
         hs.append('0');      
         hs.append(stmp);    
    }      
   return hs.toString().toLowerCase();   
 }   
 /**   
  * sha256_HMAC加密    
  * @param message 消息   
  * @param secret  秘钥 
  * @return 加密后字符串  
  */  
 public static String sha256_HMAC(String message, String secret) {     
   String hash = "";      
   try {         
       Mac sha256_HMAC = Mac.getInstance("HmacSHA256");     
       SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");      
       sha256_HMAC.init(secret_key);       
       byte[] bytes = sha256_HMAC.doFinal(message.getBytes());   
       hash = byteArrayToHexString(bytes);         
       System.out.println(hash);    
       } catch (Exception e) {     
       System.out.println("Error HmacSHA256 ===========" + e.getMessage());   
     }       
    return hash;   
   } 
 public static String byte2hex(byte[] b) {
     StringBuilder hs = new StringBuilder();
     String stmp;
     for (int n = 0; b!=null && n < b.length; n++) {
         stmp = Integer.toHexString(b[n] & 0XFF);
         if (stmp.length() == 1)
             hs.append('0');
         hs.append(stmp);
     }
     return hs.toString().toUpperCase();
 }
 
 public static String HMACSHA256(byte[] data, byte[] key) {
       try  {
          SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
          Mac mac = Mac.getInstance("HmacSHA256");
          mac.init(signingKey);
          return byte2hex(mac.doFinal(data));
       } catch (Exception e) {
          e.printStackTrace();
       } 
       return null;
 } 
 
 }