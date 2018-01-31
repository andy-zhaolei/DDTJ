package com.gox.manage.util;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*******************************************************************************
 * AES加解密算法
 * 
 * @author arix04
 * AES加解密算法，使用Base64做转码以及辅助加密
 */

public class AES1 {

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("1234567892546398".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("1234567892546398"
                    .getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         * 此处使用AES-128-CBC加密模式，key需要为16位。
         */
        String cKey = "1234667890123456";
        // 需要加密的字串
      //  String cSrc = "Email : arix04@xxx.com";
        String cSrc = "354667836788";
        System.out.println("AES加密前："+cSrc);
        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AES1.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);

        //long lUseTime = System.currentTimeMillis() - lStart;
        //System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();//WFZ/eg83TqfPDpC1nZMPdA==
        String DeString = AES1.Decrypt("duSEtw9sH+uLIT1xds7OZ6a0S/h+YY5vI4mrJAxb2ss8nd3kTCOTkcUmkWc2K83pRsSzoSwfI9B4qZGAa3yBpLQ7eY9BqGoQkWzPqIBSTW+v8VIOTJnu7rucZrSpldWJGB4zNHB2dXY4iXZkn5d1iy2y+5yXPpnmHBWseSCiIk4=", cKey);
        System.out.println("解密后的字串是：" + DeString);
        //lUseTime = System.currentTimeMillis() - lStart;
        //System.out.println("解密耗时：" + lUseTime + "毫秒");
    }
}