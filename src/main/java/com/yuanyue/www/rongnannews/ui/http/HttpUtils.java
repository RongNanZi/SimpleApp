package com.yuanyue.www.rongnannews.ui.http;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Administrator on 2015/11/21.
 */
public class HttpUtils {

   // private static Thread mThread;


    public  static String getJsonContent(final String urlpath){

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
                 String jsonresult = "";
                HttpURLConnection connection = null;
                try {
                    String ipAdress = "http://172.16.1.184:8080/rongnannews/"+ urlpath;
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
//            }
//        };
//        mThread = new Thread(runnable);
//        mThread.start();


        return jsonresult;
    }

    private static String  changeIputStream(InputStream inputStream) {
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
