package com.yuanyue.www.rongnannews;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yuanyue.www.rongnannews.adapter.NewsListAdapter;
import com.yuanyue.www.rongnannews.ui.jsonparse.JsonTools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsListActivity extends AppCompatActivity {

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.listView)
    ListView listView;

    NewsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);
        showview();
    }

    private void showview() {
        AsynGetNewsList task = new AsynGetNewsList();
        task.execute("servlet/NewsLisAction");

    }




    class AsynGetNewsList extends AsyncTask<String,Integer,String> {


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
        protected void onPostExecute(String result){
            final List<String> newsList;
            newsList = JsonTools.getList("newslist",result);
            mAdapter = new NewsListAdapter(NewsListActivity.this,newsList);
            listView.setAdapter(mAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NewsListActivity.this,NewsActivity.class);
                    intent.putExtra("titlefromactivity",newsList.get(position));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_list, menu);
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
