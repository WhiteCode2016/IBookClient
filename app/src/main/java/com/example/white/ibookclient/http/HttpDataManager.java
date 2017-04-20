package com.example.white.ibookclient.http;

import com.example.white.androidbasic.beans.IBookInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by White on 2017/4/19.
 */
public class HttpDataManager {

    public static ArrayList<IBookInfo> getIBookInfos(String jsonData) {
        ArrayList<IBookInfo> iBookInfos = new ArrayList<IBookInfo>();
        try {
            //将json字符串jsonData装入JSON数组，即JSONArray
            //jsonData可以是从文件中读取，也可以从服务器端获得
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i< jsonArray.length(); i++) {
                //循环遍历，依次取出JSONObject对象
                //用getInt和getString方法取出对应键值
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int ids = jsonObject.getInt("ibookId");
                String title = jsonObject.getString("ibookTitle");
                String content = jsonObject.getString("ibookContent");
                String time = jsonObject.getString("ibookTime");
                IBookInfo iBookInfo = new IBookInfo(title,ids,content,time);
                iBookInfos.add(iBookInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iBookInfos;
    }
}
