package com.sixtyrobbers.GSQ.fourm.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * <pre>
 * Explain: http 连接工具
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:25
 * Version: V1.0
 * </pre>
 */
public class HttpClientUtil {

    /**
     * <pre>
     * Explain: http post请求
     * Author: holennnnnn_
     * Create_Time: 2019/3/26 13:26
     * Version: V1.0
     * </pre>
     */
    public static String httpPost(String url, String paramJson) throws IOException {
        //获取可关闭的 httpCilent
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(3000).setConnectionRequestTimeout(3000).
                setSocketTimeout(3000).setRedirectsEnabled(true).build();
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        httpPost.setConfig(requestConfig);
        //装配post请求参数
        //List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        //请求参数
        //list.add(new BasicNameValuePair("patName", patientTaskReq.getPatName()));
        //list.add(new BasicNameValuePair("mobileNo", patientTaskReq.getMobileNo()));
        //UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
        //设置post求情参数
        httpPost.setEntity(new StringEntity(paramJson, ContentType.create("application/json", "utf-8")));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String strResult = "";
        if (httpResponse != null) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity());
            } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                strResult = "Error Response: " + httpResponse.getStatusLine().toString();
            } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                strResult = "Error Response: " + httpResponse.getStatusLine().toString();
            } else {
                strResult = "Error Response: " + httpResponse.getStatusLine().toString();
            }
        } else {
            return "Error Response: " + httpResponse.getStatusLine().toString();
        }
        httpClient.close(); //释放资源
        return strResult;
    }

    /**
     * <pre>
     * Explain: http get请求
     * Author: holennnnnn_
     * Create_Time: 2019/3/26 13:26
     * Version: V1.0
     * </pre>
     */
    public static String httpGet(String url) {
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
                System.out.println(srtResult);
            } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                srtResult = "Error Response: " + httpResponse.getStatusLine().toString();
            } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                srtResult = "Error Response: " + httpResponse.getStatusLine().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return srtResult;
    }

}

