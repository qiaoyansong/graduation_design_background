package com.qiaoyansong.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/1 22:08
 * description：
 */
public class AlipayConfig {

	/**
	 * AppID
	 */
	public static String app_id = "2021000116666800";
	
    /**
     * 商户私钥
     */
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1tq/d6KZ2zJ/4brQD6taP5mLUTlazxvb9IxQmfPneneBoPvGp6lz5jwWJvGHVPvZPO7B2x/aWy8CKZvsgxHNyaVjEnlhfSuHKXyVhqCt/F0Q2d5UVBhQNcTJmPtX1FDf7KMPVIOwNbpHPZOVdmJCZOXYw0Onsh0/5d/P+/98g7ClXywzakvCgXt4Fy9SN4ULAWWwijrYg0xgTRP7A82vqjwPZitxRifGixqjuADvyzc17jdSZFQoUEnkD0VD3mynp94gnT3ciOMwVTOAAVxCZU1DWZ8Dsuq9cnd6F/o7zRwr3xfNy2kzxVH3y2fMR6fzuRcf73qq0YgPCuWgEYxU/AgMBAAECggEANMRbJ46CoGblYUgMYELWkazuMAabqNLzMCj6lf6etKWV38+/b38OBq4ghpG8uybwcau8n5GLqth6Dfa/oBFMEQJEc7wjn3BoRtMAUO57ywkfls187kD4m7quTwCaMvPvGbEJxR8tTO05zEbZUCeYfdYw2xYfh773W1MY+Iim67ELADb2QwkbRUL+tPralmPUEoJbQ7dgkCHIJfTivk5OXyZEQqhTccFXe1L5VH7vdTircbCP098e0S3p6rai1w4Kczuh9P6LA3VYcF5GHq8dPWIICCk+3JjaISEKXnPWt3bhue9ZQyiPEL4/UtuYmn4k8mLg7XxS3NyQWAtQy78S8QKBgQDl0b9WO/Z4VkIomTAKi4EO30P17wYqiEiGOjWiRCLxEIpTNvbsEWkrokd1PP+pNtB3l674W0DAkPO+tdBANNELT+vO4cNaN37494TlLKweksZR8jJKV2oqPq093tFbNfCLVfHRF2MCFVlL3jDLQcWYHzUK4W2OVq0B7UnaPBJW1wKBgQDKaga61Hr/cpW4jxB5rBc79Y02TfiKazDbN5ktqNPqF2PXkL8XHImgZTeksXFMop4us0vW3lKW9pfmUuSl4KdrtBLkFIJOeRuQ/c9s7M5PoSlQDn9TjpsggvyzVLOAgSx1lg6v3NxTTYzPTZ3SrQJ2bGmmg07l+mn/XSeoDYov2QKBgQCsfPSCoJaoUvVsyyzHfVTr+J5lY3TZT1g/hkji9QjUrXlkY4iIPbkVysKfN50bP8zPNcVZGgRLg3+tz3/x8LJFL0LpD4EzoRtneT8BUroMaFT5BDLCoUFJW6ljGm0/bAiSS3snV8ozshpHWwh8RW0sc5+3/uHfRyaqn5IifZu2GQKBgFXm20ZMyIpCccHqjT5lx1J96lkCjBkKVqiiFtLm1FCDbq7cUgyX4tx8ODofyAqS9PiyxNvCbGB+9IYd5cVz6vltaZr8DTUKdqV44Z/De+cVaTf9fxHR8onArUfaCXfe2iRo84C1BZgg0EJmGTWAkQv7BHdTQJefR37FZQAQBvY5AoGBAIxO426jDXnyI8jBWeZpRsX3cFWZdowNRKWb85v3duLcgae/VBxJ0jDMQQrjR9NpMELkA1OFTjle4p3kVdJ6OEwCSl7dcA9mmgSrX7N19xOJHGID8elerVrQCHRS0lLcpLf4jy0OFCQcTKTd/dckDezMb7dLdmOV/MBDnwvXdB8n";
	
	/**
	 * 支付宝公钥
	 */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi5e8+lu8ojMCL8DJon7qE4tXDwxTbJOoMAIf3LkHzo+8QNIWccPdLIomkJtrYfx9Zekm5cfL/ASLj0AONX0r1HMwDRKi2fnbINIfMH1HCTZbjpKm6MPUPleQK2CA0TivCqDG48luQW/TCFb6Ik+IAIB0KmOASAkPNUGZ+ydp8EXNh2xpCOOzMvv69Ytgd7uKGqCIhHdQUKFmMIkf5ef431x1b1XjP/oSTh9wPHXSuLBESJ23qWhqJ+cr1pGiXi68GMOU2rGFL4UYwl1JY8ZiXyARDRu6IrXA1ACG6Xz5oFzWCb8DrzumWoQNNA+pd9seTcbLMGqNPmx0kEDDWs7C9QIDAQAB";

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
	public static String notify_url = "http://localhost:8080/notify_url.jsp";

	/**
	 * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	 */
	public static String return_url = "http://localhost:8080/order/deliverAuction";

	/**
	 * 签名方式
	 */
	public static String sign_type = "RSA2";
	
	/**
	 * 字符编码格式
	 */
	public static String charset = "utf-8";
	
	/**
	 * 支付宝网关
	 */
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 日志地址
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

