
package com.gox.manage.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import net.sf.json.JSONObject;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    public static String getTime(Date date){
    	DateFormat df=new SimpleDateFormat(simple);
    	synchronized(df){
    		return df.format(date);
    	}
    }
    public static void main(String[] args) throws Exception{
//    	for(int i=0;i<20;i++){
//    		String taskId = com.gox.manage.util.UtilDate.getOrderNum() +"-"+ (long) ((Math.random() * 5 + 1) * 10000) +"-"+ System.currentTimeMillis();
//    	System.out.println(taskId);
//    	}
//    	String gender ="-1";
//    	gender =String.valueOf((int)Float.parseFloat(gender));
//    	
//    	System.out.println(gender);
//    	
//    	boolean tag_flag=false;
//    	
//    	String s_tags="11,22,33,55,33,124";
//    	String tags="12,22,313,55,33,94,35,67,89,54,234,45,76,43,211,233=0.8,0.12,0.25,0,34,0,891";
//    	String[] t=s_tags.split(",");                     /** 勾选人物标签数组[11,22,33,55,33,124]  */
//		String[] alltags = tags.split("=")[0].split(",");/******DMP返回标签分割成数组 [12,22,313,55,33,94,35,67,89,54,234,45,76,43,211,233]*****/
//   	    for(int i=0;i<t.length;i++){
//				String tag = t[i];
//				boolean flag = true;
//				if(flag==true){
//						for(int k=0;k<alltags.length;k++){
//							String k_tag = alltags[k];
//								if(tag.equals(k_tag)){    /**只要一个标签匹配成功 并且这个APP投放媒体也匹配 */
//									flag =false;
//									break;
//								}
//						 }
//				}
//				if(flag==false){
//					tag_flag=true;
//					break;
//				}
//				
//			}
    	//String tag_flag="{\"tags\":\"5,6,13,14,17,20,27,35,38,45,46,70,78,91,93,107,110,113,114,115,117,120,125,127,140,147,148,160,162,163,172,173,174,192,196,197,199,204,209,238,240,248,253,259,262,263,264,265,272,277,279,287,296,303,309,317,318,325,330,333,334,336,362,365,367,375,380,383,385,393,400,401,405,406,410,415,429,430,439,444,446,455,457,458,459,470,484,486,494,500,501,505,509,526,534,536,539,544,546,548,556,568,573,581,583,584,592,594,597,598,599,602,615,621,625,633,636,665,667,670,671,678,682,684,695,709,714,717,719,730,736,743,745,749,756,766,771,778,813,832,837=0.0073,0.0045,0.0038,0.0022,0.0044,0.0084,0.0094,0.0017,0.0007,0.0039,0.0015,0.015,0.0738,0.0059,0.0571,0.0169,0.0035,0.0066,0.0131,0.0073,0.0012,0.0049,0.0117,0.0301,0.019,0.0247,0.0533,0.0032,0.0044,0.0068,0.0089,0.0506,0.0057,0.0164,0.0027,0.0031,0.0155,0.0052,0.0125,0.0013,0.003,0.0094,0.006,0.0398,0.006,0.0015,0.0095,0.01,0.0017,0.053,0.0164,0.0047,0.0025,0.006,0.0403,0.0065,0.0044,0.0046,0.0321,0.0032,0.0106,0.01,0.0103,0.006,0.0059,0.0169,0.003,0.0058,0.0054,0.0056,0.0024,0.0067,0.0084,0.0326,0.0054,0.0042,0.0274,0.0112,0.0005,0.0079,0.0457,0.0018,0.0068,0.004,0.0172,0.0308,0.0011,0.0034,0.0033,0.0034,0.0046,0.0181,0.001,0.0037,0.0293,0.0113,0.0036,0.0026,0.0017,0.0018,0.0015,0.0022,0.0016,0.0096,0.0039,0.0219,0.0028,0.006,0.0142,0.0059,0.0042,0.0145,0.0028,0.0131,0.0089,0.0092,0.0023,0.0241,0.0112,0.0023,0.0039,0.0037,0.0112,0.0007,0.0031,0.0064,0.0032,0.0194,0.0049,0.0016,0.0049,0.0074,0.0014,0.0095,0.0007,0.0034,0.0135,0.0285,0.0042,0.0143,0.0117\",\"segment\":\"3\",\"agebin\":\"2\",\"mac\":\"d0:65:ca:0d:41:14\",\"kids\":\"0.0\",\"ip\":\"116.225.78.170\",\"country\":\"中国\",\"city\":\"上海\",\"androidid\":\"2c339d885d1b0257\",\"tot_install_apps\":\"50\",\"income\":\"1.0\",\"model_level\":\"0\",\"province\":\"上海\",\"gender\":\"0\",\"edu\":\"2.0\",\"cell_factory\":\"HUAWEI\"}";

    	
//		String res="{\"busiSerialNo\":\"20170418122638253501492489598996\",\"resCode\":\"000000\",\"resMsg\":\"成功\",\"dataRange\":\"ci4awss1iJTCfPSh3SuoVeOLPya4VEFnykiKgrTuym2L1pXNKt8ETAXskzl39GyH1l0qdEJrLfLzu0LKLYPrm0PxTgsYmFtX68YY7Y9GfhCx0CySO6ZSdPEsQD4+/q+gGAtvpawHMxtu+V8alV6o5rWF2EMBHv1P0ruoL4zx6FNBtOtYkRN0y/EaPlnky2kBZCyK6Ccse8IAMAGzDi2n218rKoveQl2CkKlZnX1MC2GIijKsStf8iOiRl05P+20ReYffHEh93A9v61zaeYhDhXK/ML0q8PzF5uzLSMY9NYqVI2sCcxPfuILGkMx8ZJ6jP1Qe3CvbFFSmdq8PmoOc8w2B2FlUNXIwXdEXSb9hGBvR3i5LS6yk3v/3bJvYZdnFB4DMlhiXJ3HeHmuLNYg+ZTNnOkMsPhtWIAp+KW+XQ5vbe9JYFdYpXTH2Ds9pylVDpGrkqwxGjFSFwHYgMfwy5qcHH6t5a7q84IR4qBfTH5uIH7PbmC3xh7c+hO8o/SYd55wxhLMfO4f6b8HK5KQDPXMQWDtQlJPOHjgWKcu5grlVS3HR3Ww/dt6mKolquOYTkJGF5YwWmfLsAXR0ba8QtFioroBJGTNBgUUH8zqmdwGG/Zc9z2tqA6XR2G4xK9WdcD+APT8onP6o8xXRxlkiYA/MtO14OZp6BhGfXpN5fiE1FfzmFKQunHjS7tuiS8uLjVlAl8UhEd/9mEGJ1iOiTiop70enopNjBsRijB8Jih3LH9gegl18HjN8hPeMZEFopC81f9qU1DWPqJFlY2MevDuYiCIXBgvKHrIgjcKAbcsLlrDlN6Qt2XUFXx9xJ8PUG7dyRQsSWCvu2XhB2rE2iMbP/J0fpiLgUz0myuXhgLoih1QzW/QQa0zjqFZGuXy4yp6uEZ2nFCR6s8GiwDOOVYf5Tkajsk34LoJeJb6ucVNk9Zpx3W8cUTDAbzRbynNCO27vgqZtk+erWg0n1ouiDlLaSRhX6cAJsMzV9DoddSzNysmyECkbOf0iIxRQysV9KbEcv1C4FqsTuPYvBRlbp9BvWHXXRio5QC9V40rGPyN1ogEb4oQukx0BlSLUU8YU1K2QdvnDTSwKdH2fRiyL9L+cjeX/wjFAum1s/wiiOkfX3vaN55fSda75XmRQKYJ/uJQU9kSm56JD5sRbNl2dPdJZIa0QJefICv7nhA9+rhwLNbEKVK2MLmj2wfdz6ZyviZdxDw/V0PagiPn685UCmxARqvBhvJ9aS//j3TedU7n/8dL1kMtWbg0iB9K3o6NCaCvNemH4t0dGDsCHe2vvfg==\",\"timeStamp\":\"1492489599495\",\"statusCode\":200}";
//		String cKey = "1234667890123456";
//		JSONObject resJson =null;
//		resJson = JSONObject.fromObject(res);
//		String dr = resJson.getString("dataRange");//AES 加过密的
//		String drDmp = AES1.Decrypt(dr, cKey);//解密DMP返回的结果
//		System.out.println(drDmp);
    	
    	//System.out.println(1);
    	java.util.Random random=new java.util.Random();// 定义随机类
    	int result=random.nextInt(2);// 返回[0,10)集合中的整数，注意不包括10
   	    System.out.println(result);
   	    
   	 Date date=new Date();//取时间
	 Calendar calendar = new GregorianCalendar();
	 calendar.setTime(date);
	 calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
	 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 String dateString = formatter.format(date);
	 
	 System.out.println(dateString);
   	 
//    	String dataRange ="D004:0|@|D013:0,1,0|@|D005:1|@|D012:0";
//    	dataRange =dataRange.replace("|", "@");
//    	String[] dataRan = dataRange.split("@@@");
//    	System.out.println(dataRan[0]);
//    	System.out.println(dataRan[1]);
//    	System.out.println(dataRan[2]);
//    	
//    	String a="";
//    	String b="1";
//    	if(a.contains(b)){
//    		System.out.println("into");
//    	}
//    	System.out.println("##################################################");
//    	String ss ="98,12,24,#1";
//    	//System.out.println(ss.substring(0, ss.lastIndexOf(",")));
//    	System.out.println(ss.split("#")[1]);
//    	
//    	boolean flag1 =true;
//    	boolean flag2 =false;
//    	if(flag1&&flag2){
//    		System.out.println("aaaaaaaaa");
//    	}
//    	
//    	String res="{\"busiSerialNo\":\"20170320171914413361490001554131\",\"resCode\":\"000000\",\"resMsg\":\"成功\",\"dataRange\":null,\"timeStamp\":\"1490001554462\",\"statusCode\":200}";
//    	JSONObject resJson = JSONObject.fromObject(res);
//			/////////////////////JSONObject dr = resJson.getJSONObject("dataRange");
//		   String dr = resJson.getString("dataRange");//AES 加过密的
//					 
//		   if("null".equals(dr)){
//			   System.out.println("vvvvvvvv");
//			}
//		   
//		   ss =ss.replace("|", "@");
//		   System.out.println(ss);
//		   String[] dataRan1 = ss.split("@@@");
//		   System.out.println(dataRan1[0]);
		   
    }
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * 
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public synchronized static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
}
