package com.example.hoangbao.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.hoangbao.apptracnghiem.model.MonThi;

import java.util.ArrayList;

public class MonThiAdapter extends BaseAdapter{
    Context context;
    int layout;
    ArrayList<MonThi>monThiArrayList;

    public MonThiAdapter(Context context, int layout, ArrayList<MonThi> monThiArrayList) {
        this.context = context;
        this.layout = layout;
        this.monThiArrayList = monThiArrayList;
    }

    @Override
    public int getCount() {
        return monThiArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(layout,null);

        return view;
    }
}
