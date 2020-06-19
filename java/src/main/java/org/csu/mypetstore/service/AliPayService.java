package org.csu.mypetstore.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.csu.mypetstore.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class AliPayService {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//    netapp映射的地址，，，根据自己的需要修改
    private static String neturl = "http://6dynnj.natappfree.cc";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102900775633";

    // 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxluiE0tsreUjoVUtrHWisL36h71EzWfh7zsFVHgHsQrfQ2/oIxx3U2S9OkbocTPooB8ZdPt0zCKkshHsipx2E1JHo+2xhqlH6lqdrZ5VB2O5f+XPk7ECIlb2BuoQcTbFCgX0HBoKBDMHuTZQAqPRWbfGJ6Ow1zMNkZ88B4M+2b5d9hDrBiKsBAh/AwbR2qMHZuVjqPHS9rSJHyPh+UQlXOHM3z/fzRHK57wVWJIGNaRwypfGn1AOG9gQXDFGklK8rn2Qw9MBcgo5F64JRpY0wEVhRqPbZjWHgGl4zppMPFybN4Aww222fWOQzmBqKdvZygw+YSURLAOw3t/hgc/tHAgMBAAECggEAbh/mkHcw8gSD8Y7y9bmG7z+Eh2agM/fW3u+Xc/DnwFqfIiVHJ6pSFWuYqmAzyNjzR1+K4sfY44y7W5/Lu/FlnLk0wgUncGjt7Tb/Gttg8zRSnbFS7OKUIh65JXlD80k6YsNphLHEu8HtgJka7k3XNsF2xs5pq/MON3UoKUH/jGEc534tz82XNA9hdUit7pUFm2McV0tZwKkJqoFPRBQBvGs2YTZe0uOBQKKUw8bdLs4N/crh40dQyGzmG4LQg5VFynpoeH5mvTQEbN071M8qhvKLqpzxQXLzDaoIbUw9+Hiu+3Yg3TMU8D0TEjeBRR6IBbDyKluR+XkFnWYcf0RMAQKBgQDs4DvfLxXMgltnfL5e9EujYAfrIhOKh3ExQnXNQxdGeNkKFuwE48FdZepRw08vTLxFFDAgViQFwHrQtL7+rk/lwmqoCWPHeVrRg+UOMOZsYaisPkcb8QS3v30t/BV5N9ZJxLg2mn/18UMxQ8OPWlWHi27qnD4iwHXwDPxE2DYO/wKBgQC/7Va8pJ6RFGE+FPtzzO2FYHD6HPlv+1tSdbwG6fhqEn++68vCn9Mu5vE3KW/sUiQxWZEIQoLp6/0f+taFUL1E+lauh6yXwYZ3nLkyGWuuLCjbdrRTQjVI9ugI9u7DIkxOZT47dmIBVa54yXbj5vXPbi2YHQPlvhOokt+evHHbuQKBgB2xMfAU/9+Fy51aRqayarTM+dEgkWCA/9BT615SdZ1w0ovwOZ+XSQrDFq8vE0LzlHn4QNwc0CEkcoCs13AnO36qcyIjIy3C6X5v0QuJV6joSPNmTxWSjavWxgM4oRxzwlm23fyCO14B4xFO1Dmo8iWudbXU5fkHNQFlL04V6BjTAoGBAKbMIWF4v8XeLFTD73RIWPRdSFJV9AH6NPeJEov/ssCcqyDF1COkuug8FM6X2IlNKd8ps/21sAiT6zxrxxu9e4ADAGpVxw1sRuyU6+46YXbRMgDpPPZOx/hNfIKt9/rmR+Kp4lfTHoGCcUrP699MzbwkSMO2E7kKZBNQ8b8Gjv0hAoGAaL+yVtvDY7C4QQETcKYR8Xfiu9RanTT1UAXc+DiMV0qRx2wr3DGMumA+PSL/7Mi93u00HoE7bOOnCi/c5a4vQPncj4lxJlpz7Oc/lCmW6dxPUaPwyEIPDKqlAT/4a6tDWc0McXE92fTc4KwnriEz6gkx7LbodIAhI+6EGIEG1wc=";

    // 支付宝公钥,对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzR005ZlGpOAbfS7K36K36cRf2sP/BU4lC0sf+A5unsLUeDBEGLd1QBhxGFKxgohS1fUSjXRfY9P4Zgio3ZpHPmFb0hzFAp8U9Wf0zOq84HelUb1EBByvZNwu5ll2YY6HbdTLR27j4Uzwpsl+BJ/mXmNPW5kslj69yE0LdR1VKqwZ04SvVTYFa8iXVT3EfXOIGzZU+BSTTGRKvc2HXtFJ4Hev7aWivPpG/8ofZz86mkNkifhTee3KZ/Nmu5yPqx27gqQeqCnGxqmxabzeYvkgIMhO4wApqrTS+w1HK54Ulq9qTWIzDfzD2EWdQqtYXyHwsE9bk5xX9h7m0wXkD4Ca6QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url = neturl+"/alipay/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url = neturl+"/alipay/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";




//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    static public String  toPay(Order order) throws AlipayApiException
    {
        //获得初始化的AlipayClient
        AlipayClient alipayClient =  new DefaultAlipayClient(
                gatewayUrl,
                app_id,
                merchant_private_key,
                "json",
                charset,
                alipay_public_key,
                sign_type);
        //创建API对应的request
        //此处为网页支付
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl(return_url );
        alipayRequest.setNotifyUrl( notify_url);

        //如果 ISV 代商家接入电脑网站支付能力，则需要传入 app_auth_token，使用第三方应用授权；自研开发模式请忽略
        //alipayRequest.putOtherTextParam("app_auth_token", "201611BB8xxxxxxxxxxxxxxxxxxxedcecde6");

        //填充业务参数
        Map<String, String> data = new HashMap<>();
        //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
        data.put("out_trade_no", order.getOrderId());
        //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        data.put("product_code", "FAST_INSTANT_TRADE_PAY");
        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        data.put("total_amount", Float.toString(order.getSubTotal()));
        //订单标题
        data.put("subject", order.getUsername()+"'s Order");
        alipayRequest.setBizContent(JSON.toJSONString(data));

        String response = alipayClient.pageExecute(alipayRequest).getBody();

        return response;
    }


}

