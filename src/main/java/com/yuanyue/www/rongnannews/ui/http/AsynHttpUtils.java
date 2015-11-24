package com.yuanyue.www.rongnannews.ui.http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Administrator on 2015/11/23.
 */
public class AsynHttpUtils extends AsyncTask<String,Integer,String> {


    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection =null;
        String jsonresult = "";
        try {
            String ipAdress = "http://172.16.1.184:8080/rongnannews/"+ params[0];
            Log.i("url", ipAdress);
            URL url = new URL(ipAdress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            Log.i("Httpcode", "the is " + code);
            if (code == 200) {
                jsonresult =  changeIputStream(connection.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return jsonresult;
    }
    public String  changeIputStream(InputStream inputStream) {
        String  jsonString ="";
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        try {
            while ( (len=inputStream.read(data) )!= -1){
                outputStream.write(data,0,len);
            }
            jsonString = new String(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  jsonString;
    }
}
