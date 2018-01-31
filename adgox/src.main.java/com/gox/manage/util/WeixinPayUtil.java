package com.gox.manage.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class WeixinPayUtil {
	
	
	private static String byteArrayToHexString(byte b[]) {  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++)  
            resultSb.append(byteToHexString(b[i]));  
  
        return resultSb.toString();  
    }  
  
    private static String byteToHexString(byte b) {  
        int n = b;  
        if (n < 0)  
            n += 256;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
  
    public static String MD5Encode(String origin, String charsetname) {  
        String resultString = null;  
        try {  
            resultString = new String(origin);  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            if (charsetname == null || "".equals(charsetname))  
                resultString = byteArrayToHexString(md.digest(resultString  
                        .getBytes()));  
            else  
                resultString = byteArrayToHexString(md.digest(resultString  
                        .getBytes(charsetname)));  
        } catch (Exception exception) {  
        }  
        return resultString;  
    }  
  
    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
    
    
    private static String Key ="1482315681gu1ddre7zlq0gf6qnlmqaq";
    
    
    /** 
     * 微信支付签名算法sign 
     * @param characterEncoding 编码方式 
     * @param parameters 请求参数
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){  
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)   
                    && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + Key);
        String sign = MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
        return sign;  
    }  
    
    
    
  //xml解析  
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {  
          strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");  

          if(null == strxml || "".equals(strxml)) {  
              return null;  
          }  
            
          Map m = new HashMap();  
          InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));  
          SAXBuilder builder = new SAXBuilder();  
          Document doc = builder.build(in);  
          Element root = doc.getRootElement();  
          List list = root.getChildren();  
          Iterator it = list.iterator();  
          while(it.hasNext()) {  
              Element e = (Element) it.next();  
              String k = e.getName();  
              String v = "";  
              List children = e.getChildren();  
              if(children.isEmpty()) {  
                  v = e.getTextNormalize();  
              } else {  
                  v = getChildrenText(children);  
              }  
                
              m.put(k, v);  
          }  
            
          //关闭流  
          in.close();  
            
          return m;  
      } 
    
    
    public static String getChildrenText(List children) {  
        StringBuffer sb = new StringBuffer();  
        if(!children.isEmpty()) {  
            Iterator it = children.iterator();  
            while(it.hasNext()) {  
                Element e = (Element) it.next();  
                String name = e.getName();  
                String value = e.getTextNormalize();  
                List list = e.getChildren();  
                sb.append("<" + name + ">");  
                if(!list.isEmpty()) {  
                    sb.append(getChildrenText(list));  
                }  
                sb.append(value);  
                sb.append("</" + name + ">");  
            }  
        }  
          
        return sb.toString();  
    }  
    
    

}
