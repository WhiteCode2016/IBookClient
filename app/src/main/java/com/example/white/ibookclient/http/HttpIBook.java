package com.example.white.ibookclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by White on 2017/4/19.
 */
public class HttpIBook {
    private static final String strUrl = "http://localhost:8080/ibook/iBookInfo/query_iBookInfo_json";

    public String getHttpAllIBookInfo() {
        URL url = null;
        String result = null;
        try {
            url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");//使用GET方法获取
            urlConn.setConnectTimeout(5000);
            int code = urlConn.getResponseCode();
            if (code == 200) {
                // 如果获取的code为200，则证明数据获取是正确的
                InputStream is = urlConn.getInputStream();
                result = HttpUtils.readMyInputStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
