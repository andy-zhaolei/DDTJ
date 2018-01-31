package com.gox.manage.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088911914273526";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK3SbZrkwbsu+F86BdNSYPomUtL1U8cl/lLV6dgCGhLovPcH4Gdezzo5HULGALk+E4LXzEk5gnOJlPPgb4tR+LkxgofKWHwQ+JWM9p7nWGSGjZ+1MSxDsWYB1nWTSXlnM42FgXvuadUIEvRbniDtOvR5n15eYBzvwLn9FAJKzrQXAgMBAAECgYA0CZ9Ok9V72F/7Aj/kN/FYckixZ0ihgjpVSj0rsdVke9C2k+EWkUewwpCEAQsOagslTL2X3mOzeyjgqfw61VuEdwKM2OwH5maTI1xs2p/paotvCO6RJsnQT9yz+NdpkCSotJUiuLBUdXFwm5Se2/o9e3J4vtaSPp0z4j4T/4wKQQJBAOass2ZKWPPZhR9yc1Rrbiwrd+lrPBz8DEDi6XWkEWxQ5bktHvw0bCqrDcmLRyl6cpeQaO0OTN5mH0ZmbUMG208CQQDA59Zu5k3hEZQiXbPBGvFPhDdKyWhiEFUWOCEaUFrIBY+DShckYI6g5BcLFwvcX0FaJumGn2hY99M+jzrFFEi5AkBaxIIjMgYmjeUwJpzzDwLfJoucsbGJxjQcJC2xOMoSURLT9slGfGpDr+NQ9xkdXS1SLmTbQtFEgfK0hQFhaoVJAkEArVWkkLHQeNRazyZzRqj2/ARJyqF/cd5LFlQJKqPZbrHQaqBAeydoAZnyOrzSo4nUJzWf0J+cG5VT83qvzDaVwQJAKpmZcoKypALLKUz7nzQEJkg+/ESe0l/Zyv0pjKimY0rpjjgqpWp55W3jUOhm1oahsW3sP2UoLwSBhbzLHXnAJw==";

	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	//public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
