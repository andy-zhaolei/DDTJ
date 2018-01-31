package com.gox.manage.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUploadUtil  {
   
	private static Logger log = Logger.getLogger(FileUploadUtil.class); 
	

	/** 参数描述
	 * byte[]    : 文件字节数组
	 * path      : /upload/monk/images/20150818/GIXLGYGIEL1/
	 * filename  : xxxx.jpg 
	 * */
	public static String upload(byte[] bytes,String path,String filename) {
		FileOutputStream fos=null;
		try{
			
			log.info("path="+path+",filename="+filename);
			String sPath = UrlProperties.fileWriteAddress;
			
			 File fl = new File(sPath+path);
	             if(!fl.isDirectory())      
	             {       
	                 fl.mkdirs();    
	             }
	             
	             File fe = new File(sPath+path+filename);
	             if(fe.exists())      
	             {       
	                 fe.delete();    
	             }
	             
	         fos = new FileOutputStream(fe);
	         fos.write(bytes);
	         
	 		
	 		return path+filename;
	 		
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			 
	}

	public String test(String name) {
		return "333";
	}

	

}
