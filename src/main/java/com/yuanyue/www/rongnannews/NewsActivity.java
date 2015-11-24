package com.yuanyue.www.rongnannews;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yuanyue.www.rongnannews.adapter.CommentAdapter;
import com.yuanyue.www.rongnannews.ui.entity.Comment;
import com.yuanyue.www.rongnannews.ui.entity.News;
import com.yuanyue.www.rongnannews.ui.jsonparse.JsonTools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {

    @Bind(R.id.news_textView3)
    TextView newsTextView3;
    @Bind(R.id.news_textView4)
    TextView newsTextView4;
    @Bind(R.id.news_textView5)
    TextView newsTextView5;
    @Bind(R.id.news_textView6)
    TextView newsTextView6;
    @Bind(R.id.news_listView2)
    ListView newsListView2;
    @Bind(R.id.news_editText2)
    EditText newsEditText2;
    @Bind(R.id.news_button)
    Button newsButton;

    CommentAdapter mCommentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        ButterKnife.bind(this);
        showview();
    }

    private void showview() {

        Intent intent = getIntent();
        String thestring = intent.getStringExtra("titlefromactivity");
            AsynGetNews task = new AsynGetNews();
            task.execute("servlet/NewsAction?title=" + getTheUTF8String(thestring)+ "");

            AsynGetNews task2 = new AsynGetNews();
            task2.execute("servlet/CommentAction?news=" +getTheUTF8String(thestring) + "");
    }
    private String getTheUTF8String(String param) {
        String thestd = "";
        try {
            thestd= URLEncoder.encode(param,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return thestd;
    }
    class AsynGetNews extends AsyncTask<String,Integer,String>{


        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection =null;
            String jsonresult = "";
            try {

                String ipAdress = "http://172.16.1.184:8080/rongnannews/"+params[0];
                Log.i("url", ipAdress);
                URL url = new URL(ipAdress);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "text/html");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("contentType", "UTF-8");
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
        protected void onPostExecute(String result){
            News mNews;
            mNews = JsonTools.getNews("news", result);
            if(mNews != null){
            newsTextView3.setText(mNews.getTitle());
            newsTextView4.setText(mNews.getReporter()+mNews.getTime());
            newsTextView5.setText(mNews.getContext());
            }
            else {
                List<Comment> mm = new ArrayList<Comment>();
                mm = JsonTools.getComment("comment",result);
                mCommentAdapter = new CommentAdapter(NewsActivity.this,mm);
                newsListView2.setAdapter(mCommentAdapter);
            }

        }
    }



















    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("theusername");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
