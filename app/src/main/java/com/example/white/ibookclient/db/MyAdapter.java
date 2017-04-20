package com.example.white.ibookclient.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.white.ibookclient.R;
import com.example.white.ibookclient.beans.IBookInfo;

import java.util.ArrayList;

/*
 * 用来填充ListView,这次成功把这个类从主页面分离，只要传过来两个值，inflater ,和数据ArrayList<>;
 */
public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<IBookInfo> array;

    public MyAdapter(LayoutInflater inf, ArrayList<IBookInfo> arry) {
        this.inflater = inf;
        this.array = arry;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_listview, null);//注意导包，别导系统包
            viewHolder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
            viewHolder.txt_time = (TextView) convertView.findViewById(R.id.txt_time);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.txt_title.setText(array.get(position).getTitle());
        viewHolder.txt_time.setText(array.get(position).getTimes());
        return convertView;
    }

    class ViewHolder {
        TextView txt_title, txt_time;
    }
}
