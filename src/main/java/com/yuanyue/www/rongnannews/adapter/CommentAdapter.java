package com.yuanyue.www.rongnannews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuanyue.www.rongnannews.R;
import com.yuanyue.www.rongnannews.ui.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public class CommentAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context ctx;
    private List<Comment> lm = new ArrayList<Comment>();

    public CommentAdapter(Context context, List<Comment> mMap) {
        ctx = context;
        lm = mMap;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return lm.size();
    }

    @Override
    public Object getItem(int position) {
        return lm.get(position);
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
            convertView = inflater.inflate(R.layout.comment_item, null);

            holder.comment = (TextView)convertView.findViewById(R.id.comment_textView);
            holder.userandtime = (TextView)convertView.findViewById(R.id.comment_textView1);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.comment.setText((String)lm.get(position).getComment());
        holder.userandtime.setText((String)lm.get(position).getUser()+
                                (String)lm.get(position).getTime());


        return convertView;
    }




    class ViewHolder {
        TextView comment;
        TextView userandtime;
    }
}
