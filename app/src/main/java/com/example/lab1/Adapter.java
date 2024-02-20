package com.example.testapapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter implements Filter.FilterListener{
    public void setData(ArrayList<com.example.testapapter.Contact> data){
        this.data = data;
    }

    private ArrayList<com.example.testapapter.Contact> data;
    private ArrayList<com.example.testapapter.Contact> databackup;

    private Activity context;

    private LayoutInflater inflater;

    public Adapter(ArrayList<com.example.testapapter.Contact> data, Activity activity){
        this.data = data;
        this.context= activity;
        this.inflater= (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null) v = inflater.inflate(R.layout.testlayout, null);
        ImageView imgprofile = v.findViewById(R.id.imageView4);
        TextView tvname = v.findViewById(R.id.tvname);
        tvname.setText(data.get(position).getName());
        TextView tvphone = v.findViewById(R.id.tvphone);
        tvphone.setText(data.get(position).getPhone());
        return v;
    }

    @Override
    public void onFilterComplete(int count) {

    }
}