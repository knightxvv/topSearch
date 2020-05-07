package com.tx.utils;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class MyHttpClient {
    
    //不带字符编码的get请求
    public String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response=null;
        HttpGet httpGet = new HttpGet(url);
//        httpGet.addHeader("api_gateway_auth_token",tokenString);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        try {
//            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();
            httpClient =HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .build();
          
//          httpGet.setConfig(requestConfig);
//          httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//          httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                System.out.println("访问成功："+response.getStatusLine().getStatusCode());
//              System.out.println(response.getEntity().getContentEncoding());
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }else {
//                System.out.println("访问失败："+response.getStatusLine().getStatusCode());
//                System.out.println("访问失败："+Arrays.toString(response.getAllHeaders()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    //带字符编码的get请求
    public String doGet(String url,String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response=null;
        HttpGet httpGet = new HttpGet(url);
//        httpGet.addHeader("api_gateway_auth_token",tokenString);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        try {
//            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();
            httpClient =HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .build();
          
//          httpGet.setConfig(requestConfig);
//          httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//          httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                System.out.println("访问成功："+response.getStatusLine().getStatusCode());
//              System.out.println(response.getEntity().getContentEncoding());
                String res = EntityUtils.toString(response.getEntity(),charset);
                return res;
            }else {
//                System.out.println("访问失败："+response.getStatusLine().getStatusCode());
//                System.out.println("访问失败："+Arrays.toString(response.getAllHeaders()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 以post方式调用第三方接口
     * @param url
     * @param json
     * @return
     */
    @SuppressWarnings("null")
    public String doPost(String url, JSONObject json) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response=null;
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity se = new StringEntity(json.toString());
            se.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            se.setContentType("application/x-www-form-urlencoded");
            //设置请求参数
            httpPost.setEntity(se);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
