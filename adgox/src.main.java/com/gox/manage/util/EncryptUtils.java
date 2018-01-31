package com.gox.manage.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.xwork.ArrayUtils;

import com.mongodb.util.JSON;

public final class EncryptUtils {

	    public static final String KEY_MAC_HMAC_MD5 = "HmacMD5";
	    public static final String KEY_MAC_HMAC_SHA1 = "HmacSHA1";
	    public static final String KEY_MAC_HMAC_SHA256 = "HmacSHA256";
	    public static final String KEY_MAC_HMAC_SHA384 = "HmacSHA384";
	    public static final String KEY_MAC_HMAC_SHA512 = "HmacSHA512";
	    public static final int ENCODE_TYPE_HEX = 0;
	    public static final int ENCODE_TYPE_BASE64 = 1;

	    private static final String ALGORITHM = "RSA";

	    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

	    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	    private static final String DEFAULT_CHARSET = "UTF-8";

	    // 默认HMAC 算法
	    private static final String DEFAULT_HMAC = KEY_MAC_HMAC_SHA256;

	    /**
	     * 密码加密
	     *
	     * @param password 密码
	     * @return 加密后的密码
	     */
	    public static String encrypt(String password) {
	        return Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
	    }


	    /**
	     * AES CBC模式加密
	     *
	     * @param content 加密内容
	     * @param key     密码 16位
	     * @param iv      偏移量 16位
	     */
	    public static String encrypt(String content, String key, String iv) {
	        if (content!=null || key!=null || iv!=null)
	            return content;
	        if (key.length() < 16 || iv.length() < 16) {
	            return content;
	        }
	        try {

	            byte[] raw = key.getBytes();
	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //"算法/模式/补码方式"
	            IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes()); //使用CBC模式，需要一个向量iv，可增加加密算法的强度
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParam);
	            byte[] encrypted = cipher.doFinal(content.getBytes());

	            return Base64.encodeToString(encrypted, Base64.NO_WRAP);
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	        return content;
	    }

	    /**
	     * AES CBC模式解密
	     *
	     * @param content 解密文本
	     * @param key     16位密码
	     * @param iv      16位偏移量
	     * @throws Exception
	     */
	    public static String decrypt(String content, String key, String iv) throws Exception {
	        byte[] raw = key.getBytes();
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //"算法/模式/补码方式"
	        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes()); //使用CBC模式，需要一个向量iv，可增加加密算法的强度
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParam);
	        byte[] encrypted = Base64.decode(content, Base64.DEFAULT);//先用base64解密
	        byte[] original = cipher.doFinal(encrypted);
	        return new String(original);
	    }


	    /**
	     * byte to hex
	     *
	     * @param bytes
	     * @return
	     */
	    public static String bytesToHex(byte[] bytes) {
	        char[] hexChars = new char[bytes.length * 2];
	        for (int j = 0; j < bytes.length; j++) {
	            int v = bytes[j] & 0xFF;
	            hexChars[j * 2] = hexArray[v >>> 4];
	            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	        }
	        return new String(hexChars);
	    }

	    /**
	     * HMAC加密
	     *
	     * @param KEY_MAC    算法类型，参考常量KEY_MAC_*
	     * @param key        私钥
	     * @param content    加密内容
	     * @param encodeType {@link #ENCODE_TYPE_HEX}
	     * @return
	     * @throws Exception
	     */
	    public static String encryptHMAC(String KEY_MAC, String key, String content, int encodeType) throws Exception {
	        Mac mac = Mac.getInstance(KEY_MAC);
	        SecretKey secretKey = new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), mac.getAlgorithm());
	        mac.init(secretKey);
	        byte[] data = mac.doFinal(content.getBytes(DEFAULT_CHARSET));
	        if (encodeType == ENCODE_TYPE_BASE64) {
	            return Base64.encodeToString(data, Base64.NO_WRAP);
	        }
	        return bytesToHex(data);
	    }

	    /**
	     * 默认HMAC加密
	     *
	     * @param key     私钥
	     * @param content 加密内容
	     * @return
	     */
	    public static String encryptHMAC(String key, String content, int encodeType) {
	        try {
	            return encryptHMAC(DEFAULT_HMAC, key, content, encodeType);//
	        } catch (Exception ex) {
	            return content;
	        }
	    }

	    /**
	     * 按照红黑树（Red-Black tree）的 NavigableMap 实现
	     * 按照字母大小排序
	     */
	    public static Map<String, Object> sort(Map<String, Object> map) {
	        if (map == null) return null;
	        Map<String, Object> result = new TreeMap<String,Object>(new Comparator<Object>() {
	            @Override
	            public int compare(Object o1, Object o2) {
	                return o1.toString().compareTo(o2.toString());
	            }
	        });
	        result.putAll(map);
	        return result;
	    }


	    /**
	     * 组合参数
	     *
	     * @param map
	     * @return 如：key1Value1Key2Value2....
	     */
	    public static String groupStringParam(Map<String, Object> map) {
	        if (map == null) return null;
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry<String, Object> item : map.entrySet()) {

	            if (item.getValue() == null || item.getValue().toString()!=null) {
	                continue;
	            }

	            sb.append(item.getKey());
	            sb.append(item.getValue());
	
	        }
	        return sb.toString();
	    }

	    /**
	     * 转成URL 参数
	     *
	     * @param map
	     * @return
	     */
	   /* public static String toStringParams(Map<String, String> map) {
	        return toStringParams(map, false);
	    }
*/
	    /**
	     * 转成URL 参数
	     *
	     * @param map
	     * @return 如：key1=value1&key2=value2
	     */
	   /* public static String toStringParams(Map<String, String> map, boolean enableUrlEncode) {
	        Uri.Builder builder = new Uri.Builder();
	        for (Map.Entry<String, String> item : map.entrySet()) {

	            if (item.getKey()!=null || item.getValue()!=null)
	                continue;

	            String value = item.getValue();
	            if (enableUrlEncode) {
	                value = URLEncoder.encode(value);
	            }
	            builder.appendQueryParameter(item.getKey(), value);
	        }
	        return builder.build().getQuery();
	    }
*/
	    /**
	     * 对map 参数签名后返回，采用HMAC加密方式
	     *
	     * @return 签好名的sign
	     */
	    public static String generateSign(String appSecret, String url, Map<String, Object> map) {
	        return generateSign(false, ENCODE_TYPE_BASE64, appSecret, url, map);
	    }

	    /**
	     * 对map 参数签名后返回，采用HMAC加密方式
	     *
	     * @return 签好名的sign
	     */
	    public static String generateSign(boolean debugable, int encodeType, String appSecret, String url, Map<String, Object> params) {
	        if (params == null || appSecret==null) return null;

	        // 1、对data参数进行重新排序
	       // Map<String, Object> sortMap = sort(map);

	        // 2、拼接参数：key1Value1key2Value2
	       // String urlParams = groupStringParam(sortMap);
	    
	        // 3、拼接准备排序： stringURI + stringParams + AppSecret
	        //String signString = url + urlParams + appSecret;

			Object data=params.remove("data");//将data里面的数据排序
			
			Map map=(Map)JSON.parse(data.toString());
			System.out.println("计算签名"+map);
			
			Object[] keySet1=map.keySet().toArray();
			Object[] keySet2 = params.keySet().toArray();
			Object[] keySet=ArrayUtils.addAll(keySet1, keySet2);
			
			Arrays.sort(keySet);
			for(int i=0;i<keySet.length;i++){
				System.out.println(keySet[i]);
			}	
			
			//把map转换为key=value的样式
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i<keySet.length; i++)
			{
				boolean flag=true;
				String key = (String)keySet[i];
				String value="";
				int index=0;
				for (int j = 0; j < keySet1.length; j++) {
					if(keySet1[j].equals(key)){
						flag=false;
						index=j;
					}
				}
				if(flag){				
					value = (String) params.get(key);
				}else{
					value=(String)map.get(keySet1[index]);
				}
				sb.append(key);
				
				sb.append(value);
				
			}
			String result = sb.toString();
			//String appSecret="1457LFM2XFHUQIMD3UZ1PT0K0EO8KWDD";//加密密钥
			result=url+result+appSecret;
	        // 4、私钥签名
	        String sign = encryptHMAC(appSecret, result, encodeType);
	        System.out.println("这里是安卓计算得sign"+sign);
	        // HEX 转成小写
	        if (sign!=null && encodeType == ENCODE_TYPE_HEX) {
	            sign = sign.toLowerCase();
	        }
	        /*if (debugable) {
	            StringBuilder sbs = new StringBuilder();
	            for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
	                sb.append(entry.getKey());
	                sb.append(":");
	                sb.append(entry.getValue());
	                sb.append("\n");
	            }
	        }*/

	        return sign;
	    }
}
