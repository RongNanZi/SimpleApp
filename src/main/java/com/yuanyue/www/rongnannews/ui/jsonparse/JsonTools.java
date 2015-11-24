package com.yuanyue.www.rongnannews.ui.jsonparse;

import com.yuanyue.www.rongnannews.ui.entity.Comment;
import com.yuanyue.www.rongnannews.ui.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/21.
 */
public class JsonTools {

    public JsonTools() {
    }

    public  static  boolean getResult   (String key,String jsonString){

        boolean flag = false;
        try {
            JSONObject mJSON = new JSONObject(jsonString);
            String jj=mJSON.getString(key);
           flag = Boolean.parseBoolean(jj);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  flag;
    }

    public static Map<String,Object> getMap(String key, String jsonString) {
        Map<String,Object>  mMap = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject mJson = jsonObject.getJSONObject(key);
            Iterator<String> iterator = mJson.keys();
            while (iterator.hasNext()) {
                String json_key = iterator.next();
                Object json_value = mJson.get(json_key);
                if (json_value == null) {
                    json_value = "";
                }
                mMap.put(json_key, json_value);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return mMap;
    }
    public static News getNews(String key, String jsonString) {
        News  mMap = new News();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject mJson = jsonObject.getJSONObject(key);

            mMap.setTitle(mJson.getString("title"));
            mMap.setReporter(mJson.getString("reporter"));
            mMap.setContext(mJson.getString("context"));
            mMap.setTime(mJson.getString("time"));


        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return mMap;
    }

    public static List<Map<String, Object>> listKeyMaps(String key,
                                                        String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                Map<String, Object> map = new HashMap<String, Object>();
                Iterator<String> iterator = jsonObject2.keys();
                while (iterator.hasNext()) {
                    String json_key = iterator.next();
                    Object json_value = jsonObject2.get(json_key);
                    if (json_value == null) {
                        json_value = "";
                    }
                    map.put(json_key, json_value);
                }
                list.add(map);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    public static List<String> getList(String key, String jsonString) {
        List<String> list = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {

                String msg = jsonArray.getString(i);

                JSONObject mJSON = new JSONObject(msg);
                String jj=mJSON.getString("title");

                list.add(jj);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    public static List<Comment> getComment(String key,String jsonString) {
        List<Comment>  list = new ArrayList<Comment>();
        Comment temp =new Comment();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                temp.setTime(jsonArray.getJSONObject(i).getString("time"));
                temp.setUser(jsonArray.getJSONObject(i).getString("user"));
                temp.setComment(jsonArray.getJSONObject(i).getString("comment"));
                temp.setNews(jsonArray.getJSONObject(i).getString("news"));
                list.add(temp);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
}
