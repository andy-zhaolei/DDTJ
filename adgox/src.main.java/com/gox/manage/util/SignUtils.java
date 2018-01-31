package com.gox.manage.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.ArrayUtils;
import org.apache.ws.security.WSSecurityException;

import com.mongodb.util.JSON;

public class SignUtils {

	private static final String ALGORITHM = "RSA"; 

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	
	public static final String WEIYING_API_KEY="f7a95071e3af3a0af840f841205a3bb1";
	public static final String YIDIAN_API_KEY="d2c5f9f70b78877f0ffd66bca54bbc1e";//亿典
	public static final String BOHETONG_API_KEY="dfa514b4be711f45e6e42e389ba062e9";//博和通
	public static final String ZHIWEI_API_KEY="7d31faffd19b7cbb85d513dd737f2b57";//指微科技
	public static final String DIANCHUAN_API_KEY="1e097db3c4918c97079c0815afec542a";//点传
	public static final String ADFLASH_API_KEY="99fbe79e92c9ccc21148cba38d51709b";//adflash
	public static final String ZHANGZHONG_API_KEY="69175aecde84a2daf738d332c241d20e";//掌众
	public static final String FONGZHIYOU_API_KEY="77e203cdb98bed6add0e5665acbeb3fb";//风之游
	public static final String XINGZHETIANXIA_API_KEY="d5f0819c2772f28acc83618241f0ebf8";//行者天下
	public static final String ZHENQU_API_KEY="c751922c00fe0b682e8bd95212db0241";//真趣
	public static final String DUOTUI_API_KEY="3c8f68d2621c5d64e773f8d28d71658e";//多推
	public static final String LJD_API_KEY="95c1a35027d4f4e9b1c60d35536ccd74";//乐泾达
	public static final String RANSHI_API_KEY="95c1a35027d4f4e9b1c60d35536ccd75";//冉十
	public static final String YOUYUAN_API_KEY="95c1a35027d4f4e9b1c60d35536ccd76";//有缘
	public static final String YOUYUAN_IP="116.225.64.7";//有缘IP
	public static final String HUANQU_API_KEY="ef8e744f7597475135ef6cf4703197a1";//欢趣
	public static final String HUANQU_IP="119.137.53.71";//欢趣
	
	public static final String WEIYING_CUID ="1001012";
	public static final String YIDIAN_CUID  ="1001023";//亿典
	public static final String BOHETONG_CUID="1001034";//博和通
	public static final String ZHIWEI_CUID="1001045";//指微科技
	public static final String DIANCHUAN_CUID="1001056";//点传
	public static final String ADFLASH_CUID="1001067";//adflash
	public static final String ZHANGZHONG_CUID="1001078";//掌众
	public static final String FONGZHIYOU_CUID="1001089";//风之游
	public static final String XINGZHETIANXIA_CUID="1001090";//行者天下
	public static final String ZHENQU_CUID="1002020";//真趣
	public static final String DUOTUI_CUID="1002010";//多推
	public static final String FENGQU_CUID="1002030";//丰趣
	public static final String KUAIYOU_CUID="1002040";//快友
	public static final String ADVIEW_CUID="1002050";//adview
	public static final String LJD_CUID="1002060";//乐泾达
	public static final String RANSHI_CUID="1003030";//冉十
	
	
	/**
	 * 签名生成规则
	 * @param params
	 * @param app_key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String signForServer(Map params,String url){
		if(null == params || params.isEmpty()){
			return null;
		}		
		//LOG("params =" + params.toString() + " app_key=" + app_key);
		//每个cp游戏的appkey不同,将其作为一个参数存入map,一起做签名
		//params.put("goxpay",app_key);
		//LOG("params after add goxpay =" + params.toString());
		//将map中的key的所有字母做升序
	
		
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
		System.out.println(result);
		String appSecret="1457LFM2XFHUQIMD3UZ1PT0K0EO8KWDD";//加密密钥
		result=url+result+appSecret;
		//result=HMACSHA.sha256_HMAC(result, appSecret);
		result=EncryptUtils.encryptHMAC(appSecret, result, EncryptUtils.ENCODE_TYPE_BASE64);
		//result=HMACSHA.HMACSHA256(result.getBytes(),appSecret.getBytes());
		System.out.println(result);
		
		//result=Base64.encode(result.getBytes());
		System.out.println(result);
		
		return result;
	}
	public static void main(String[] args) throws UnsupportedEncodingException, WSSecurityException{
		//Map<String,String> map = new HashMap<String,String>();
		//map.put("bbc", "1");
		//map.put("aac", "2");
		//String sign =SignUtils.signForServer(map, "1234567890");
		
		//System.out.println(sign);
		
		//System.out.println(md5("e10adc3949ba59abbe56e057f20f883e"));
		//System.out.println(md5("weiying"));
	//	System.out.println(md5("weiyin")); System.out.println(md5("f7a95071e3af3a0af840f841205a3bb1"));
		
		System.out.println(md5("862629034939309")); 
	//	System.out.println(md5("bohetong"));System.out.println(md5("dfa514b4be711f45e6e42e389ba062e9"));
	//	System.out.println(md5("zhiwei"));System.out.println(md5("7d31faffd19b7cbb85d513dd737f2b57"));
	//	System.out.println(md5("dianchuan"));System.out.println(md5("1e097db3c4918c97079c0815afec542a"));
	//	System.out.println(md5("adflash"));System.out.println(md5("99fbe79e92c9ccc21148cba38d51709b"));
	//	System.out.println(md5("zhangzhong"));System.out.println(md5("69175aecde84a2daf738d332c241d20e"));
	//	System.out.println(md5("fongzhiyou"));System.out.println(md5("77e203cdb98bed6add0e5665acbeb3fb"));
	//	System.out.println(md5("xingzhetianxia"));System.out.println(md5("d5f0819c2772f28acc83618241f0ebf8"));
	//	System.out.println(md5("duotui"));System.out.println(md5("3c8f68d2621c5d64e773f8d28d71658e"));
	//	System.out.println(md5("zhenqu"));System.out.println(md5("c751922c00fe0b682e8bd95212db0241"));
		//System.out.println(md5("c751922c00fe0b682e8bd95212db0241dddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee222222222222fsdgdgrrr"));
		
		
//		System.out.println(md5("DC84A576-AD4A-47AA-BCCC-C468CB1EF2DA"));
//		System.out.println(md5("33137E01-D88B-4D49-84F1-87CA634AC348"));
//		System.out.println(md5("93332B87-6934-468D-85E9-E3D3E08D0462"));
//		System.out.println(md5("4486704C-0198-4643-B85F-77297EDA309E"));
//		System.out.println(md5("97DF13C7-B892-4CC2-A731-8C1A58645A41"));
//		System.out.println(md5("B5F87938-404D-4523-9DA9-301F667F9680"));
//		System.out.println(md5("DB07E332-7E69-4A8B-84A5-45779C3B88A1"));
//		System.out.println(md5("F8320F78-A410-44B5-8807-0B5E960D7CDA"));
//		System.out.println(md5("1B35480B-56AA-4B9C-A2EE-A2546ADA4DCC"));
//		System.out.println(md5("A0C49557-8073-4B66-86F7-AE6FB027067C"));
		
		           //http://adgox.cn/adgox/adgoxGdtCallback?app_type=ios&c_from=huajiao&source=xztx&muid=56c1e3debbfb56621b19c056a2e30a24
//		String url ="http://adgox.cn/adgox/adgoxGdtCallback?app_type=ios&c_from=huajiao&source=xztx&muid=56c1e3debbfb56621b19c056a2e30a24";
//		String query_data=java.net.URLEncoder.encode(url, "utf-8");
//		String data=Base64.encode(query_data.getBytes());
//		String encrypt_key="CACAAAAAAAAAIyQL";
//		String base_data=data+"&GET&"+ encrypt_key ;
//		String sign_str=com.gox.manage.util.SignUtils.md5(base_data);
//		System.out.println(sign_str);
		
		//System.out.println(new String(Base64.decode(data)));
		//System.out.println(java.net.URLDecoder.decode(new String(Base64.decode(data)),"utf-8"));
		
		

		
		//String simple_xor=encode2("muid=40c7084b4845eebce9d07b8a18a055fc&conv_time=1422263664&client_ip=10.11.12.13&sign=afd9818f4aca4152a6b5aa1dc7e85d98", "BAAAAAAAAAAAHxZW");
		//System.out.println(simple_xor);
		//String data = Base64.encode(simple_xor.getBytes());
		//System.out.println(data);
		//String simple_xor1=encode2(simple_xor, "BAAAAAAAAAAAHxZW");
		//System.out.println(simple_xor1);

	}

	/**
	 * 简单异或加密解密算法
	 * @param str 要加密的字符串
	 * @return
	 */
	public static String encode2(String str,String key) {
	// int code = 112; // 密钥
	 char[] charArray = str.toCharArray();
	 char[] charKey = key.toCharArray();
	 int j=0;
	 for(int i = 0; i < charArray.length; i++){
	  charArray[i] = (char) (charArray[i] ^ charKey[j]);
	  j++;
	  j=j%key.length();
	 // System.out.println(j);
	 }
	 return new String(charArray);
	}
	
	
	
	
	
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}
}
