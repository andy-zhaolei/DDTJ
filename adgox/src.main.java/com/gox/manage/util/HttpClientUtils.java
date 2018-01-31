package com.gox.manage.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
public class HttpClientUtils {
	
	public static long pretime=1457522472367L;
	
	
	
	   
	 //HttpCLient实现对被GZip压缩过的Response进行解压
	 public static String getRequestAddHead(String url,String agent,String ip,long reqtime)  {  
         
         HttpClient http = new HttpClient();  
         CustomGetMethod get = new CustomGetMethod(url);  
         String publisherid="157";
         String token="c54b245106f138cb42edcb6b988b9764";
         String sign=publisherid+token+reqtime;//签名
         String signMD5=SignUtils.md5(sign);
         
        //添加头信息告诉服务端可以对Response进行GZip压缩  
         get.setRequestHeader("X-CM-SIGN",   signMD5);  
         get.setRequestHeader("X-Forwarded-For",   ip);  
         get.setRequestHeader("Accept-Encoding",   "gzip,deflate"); 
         get.setRequestHeader("User-Agent",   agent); 
         
         try {  
             int statusCode = http.executeMethod(get);  
             if (statusCode != HttpStatus.SC_OK) {  
                 System.out.println("Method failed: "   + get.getStatusLine());  
             }  
   
             //打印解压后的返回信息  
             //System.out.println(get.getResponseBodyAsString());  
            return get.getResponseBodyAsString();
         } catch (Exception e) {  
             e.printStackTrace();  
             return e.getMessage();
         } finally {  
        	 get.releaseConnection();  
         }  
}  
	 
	 
	 
	  
	     /**  
	     * <p>httpClient的get请求方式</p>  
	     * @param url = "https://www.99bill.com/webapp/receiveDrawbackAction.do";  
	     * @param charset = ="utf-8";  
	     * @return  
	     * @throws Exception  
	     */   
	     public   static  String getDoGetURL(String url, String charset) throws  Exception {   
	  
	        HttpClient client =  new  HttpClient();   
	        GetMethod method1 =  new  GetMethod(url);   
	  
	         if  ( null  == url || !url.startsWith( "http" )) {   
	             throw   new  Exception( "请求地址格式不对" );   
	        }   
	  
	         // 设置请求的编码方式   
	         if  ( null  != charset) {   
	            method1.addRequestHeader( "Content-Type" ,   
	                     "application/x-www-form-urlencoded; charset="  + charset);   
	        }  else  {   
	            method1.addRequestHeader( "Content-Type" ,   
	                     "application/x-www-form-urlencoded; charset="  +  "utf-8" );   
	        }   
	         int  statusCode = client.executeMethod(method1);   
	  
	         if  (statusCode != HttpStatus.SC_OK) { // 打印服务器返回的状态   
	            System.out.println( "Method failed: "  + method1.getStatusLine());   
	        }   
	         // 返回响应消息   
	         byte [] responseBody = method1.getResponseBodyAsString().getBytes(method1.getResponseCharSet());   
	         // 在返回响应消息使用编码(utf-8或gb2312)   
	       // String response =  new  String(responseBody,  "utf-8" );   
	        String response =  new  String(responseBody,  charset ); 
	        //System.out.println( "------------------response:" +response);   
	         // 释放连接   
	        method1.releaseConnection();   
	         return  response;   
	    }
	 
	     public static String postRequest(String url,Map<String,String> map) throws HttpException, IOException {  
	         HttpClient client = new HttpClient();  
	        PostMethod method = new PostMethod(url);  
	        // method.getParams().setIntParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 100);
	         method.setRequestHeader("Content-Type",   "application/x-www-form-urlencoded;charset=utf-8");  
	     //    method.setRequestHeader("X-Forwarded-For",   "64.124.253.1");  
	    //     method.setRequestHeader("Content-Type",   "application/json");
	         NameValuePair[] param =new NameValuePair[map.size()];
	         int i=0;
	         for(String key: map.keySet()){
	        	 String value =map.get(key);
	        	 param[i]=new NameValuePair(key, value);
	        	 i++;
	         }
	         

	         method.setRequestBody(param);  
	         int statusCode = client.executeMethod(method);  
	         System.out.println(statusCode);  
	         StringBuffer response =  new  StringBuffer();  
	         if  (method.getStatusCode() == HttpStatus.SC_OK) {   
    	         InputStream responseBody = method.getResponseBodyAsStream();
    	         BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody,"utf-8"));
    	         String line ;
    	         while((line = reader.readLine()) !=  null){
    	         // System.out.println(new String(line.getBytes()));
    	         // line = reader.readLine();
    	          response.append(line);
    	         }
	         }
	         method.releaseConnection();  
	         
	         return response.toString();
 }  
	     public static Logger log = Logger.getLogger(HttpClientUtils.class);
	     
	     public static String postJson(String url,String transJson) throws HttpException, IOException {  
	        HttpClient client = new HttpClient();  
	        PostMethod method = new PostMethod(url);  
	        method.setRequestHeader("Content-Type",   "application/json");
	        method.setRequestHeader("Connection", "close");
	        RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
	        method.setRequestEntity(se);
	        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());//使用系统提供的默认的恢复策略
	        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000);  //设置超时的时间
	        StringBuffer response =  new  StringBuffer();
	        
	        try{
	        
	             int statusCode = client.executeMethod(method);  
	             
	             
	             
	             
	             
	             
	             //System.out.println(statusCode);  
	              log.info(statusCode);
	         
	        
		         if  (method.getStatusCode() == HttpStatus.SC_OK) {   
	    	         InputStream responseBody = method.getResponseBodyAsStream();
	    	         
	    	         BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody,"utf-8"));
	    	         String line ;
	    	         while((line = reader.readLine()) !=  null){
	    	         // System.out.println(new String(line.getBytes()));
	    	         // line = reader.readLine();
	    	          response.append(line);
	    	         }
		         }
	         }catch(Exception e){
	        	 log.info(e.getMessage());
	         }finally{
	        	 method.releaseConnection();  
	         }
	         
	        
	         
	         return response.toString();
 }
	     
	     

	       
	     /**  
	     * <p>httpClient的get请求方式2</p>  
	     * @param url = "https://www.99bill.com/webapp/receiveDrawbackAction.do";  
	     * @param charset = ="utf-8";  
	     * @return  
	     * @throws Exception  
	     */   
	     public   static  String getDoGetURL2(String url, String charset)   
	             throws  Exception {   
	         /*  
	         * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤:   
	         * 1:生成一个 HttpClinet 对象并设置相应的参数。  
	         * 2:生成一个 GetMethod 对象并设置响应的参数。   
	         * 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get 方法。   
	         * 4:处理响应状态码。   
	         * 5:若响应正常，处理 HTTP 响应内容。   
	         * 6:释放连接。  
	         */   
	           
	         /* 1 生成 HttpClinet 对象并设置参数 */   
	        HttpClient httpClient =  new  HttpClient();   
	         // 设置 Http 连接超时为5秒   
	        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( 5000 );   
	  
	         /* 2 生成 GetMethod 对象并设置参数 */   
	        GetMethod getMethod =  new  GetMethod(url);   
	         // 设置 get 请求超时为 5 秒   
	        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  5000 );   
	         // 设置请求重试处理，用的是默认的重试处理：请求三次   
	        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new  DefaultHttpMethodRetryHandler());   
	           
	        String response = "" ;   
	         /* 3 执行 HTTP GET 请求 */   
	         try  {   
	             int  statusCode = httpClient.executeMethod(getMethod);   
	             /* 4 判断访问的状态码 */   
	             if  (statusCode != HttpStatus.SC_OK) {   
	                System.err.println( "Method failed: " + getMethod.getStatusLine());   
	            }   
	  
	             /* 5 处理 HTTP 响应内容 */   
	             // HTTP响应头部信息，这里简单打印   
	            Header[] headers = getMethod.getResponseHeaders();   
	             for  (Header h : headers)   
	                System.out.println(h.getName() +  "------------ "  + h.getValue());   
	               
	             // 读取 HTTP 响应内容，这里简单打印网页内容   
	             byte [] responseBody = getMethod.getResponseBody(); // 读取为字节数组   
	            response =  new  String(responseBody, charset);   
	            System.out.println( "----------response:" +response);   
	               
	             // 读取为 InputStream，在网页内容数据量大时候推荐使用   
	             //InputStream response = getMethod.getResponseBodyAsStream();   
	               
	        }  catch  (HttpException e) {   
	             // 发生致命的异常，可能是协议不对或者返回的内容有问题   
	            System.out.println( "Please check your provided http address!" );   
	            e.printStackTrace();   
	        }  catch  (IOException e) {   
	             // 发生网络异常   
	            e.printStackTrace();   
	        }  finally  {   
	             /* 6 .释放连接 */   
	            getMethod.releaseConnection();   
	        }   
	         return  response;   
	    }   
	       
	     /**   
	     * <p>执行一个HTTP POST请求，返回请求响应的HTML</p>   
	     *   
	     * @param url       请求的URL地址   
	     * @param params    请求的查询参数,可以为null   
	     * @param charset   字符集   
	     * @param pretty    是否美化   
	     * @return          返回请求响应的HTML   
	     */     
	     public   static  String getDoPostResponseDataByURL(String url,   
	            Map<String, String> params, String charset,  boolean  pretty) {   
	           
	        StringBuffer response =  new  StringBuffer();   
	           
	        HttpClient client =  new  HttpClient();   
	        HttpMethod method =  new  PostMethod(url);   
	           
	         //设置Http Post数据    
	         if  (params !=  null ) {   
	            HttpMethodParams p =  new  HttpMethodParams();   
	             for  (Map.Entry<String, String> entry : params.entrySet()) {   
	                p.setParameter(entry.getKey(), entry.getValue());   
	            }   
	            method.setParams(p);   
	        }   
	         try  {   
	            client.executeMethod(method);   
	             if  (method.getStatusCode() == HttpStatus.SC_OK) {   
	                 //读取为 InputStream，在网页内容数据量大时候推荐使用   
	                BufferedReader reader =  new  BufferedReader(   
	                         new  InputStreamReader(method.getResponseBodyAsStream(),   
	                                charset));   
	                String line;   
	                 while  ((line = reader.readLine()) !=  null ) {   
	                     if  (pretty)   
	                        response.append(line).append(System.getProperty( "line.separator" ));   
	                     else   
	                        response.append(line);   
	                }   
	                reader.close();   
	            }   
	        }  catch  (IOException e) {   
	            System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
	            e.printStackTrace();   
	        }  finally  {   
	            method.releaseConnection();   
	        }   
	        System.out.println( "--------------------" +response.toString());   
	         return  response.toString();   
	    }    
}
