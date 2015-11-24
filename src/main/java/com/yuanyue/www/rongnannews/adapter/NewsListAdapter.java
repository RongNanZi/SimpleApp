package com.yuanyue.www.rongnannews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuanyue.www.rongnannews.NewsListActivity;
import com.yuanyue.www.rongnannews.R;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public class NewsListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context ctx;
    private List<String> ls;

    public NewsListAdapter(NewsListActivity context, List<String> mystring) {
        ctx = context;
        ls = mystring;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.news_list_item, null);
            holder.textView2 = (TextView)convertView.findViewById(R.id.news_list_item_textView2);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.textView2.setText((String)ls.get(position));
        return convertView;
    }




    class ViewHolder {
        TextView textView2;
    }
}
