package com.example.hoangbao.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;

import java.util.ArrayList;

public class LoaiMenuAdapter extends BaseAdapter{
    Context context;
    int layout;
    ArrayList<LoaiMenu> loaiMenuArrayList;

    public LoaiMenuAdapter(Context context, int layout, ArrayList<LoaiMenu> loaiMenuArrayList) {
        this.context = context;
        this.layout = layout;
        this.loaiMenuArrayList = loaiMenuArrayList;
    }

    @Override
    public int getCount() {
        return loaiMenuArrayList.size();
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
        ImageView imageView=view.findViewById(R.id.img_loaithi);
        TextView textView=view.findViewById(R.id.txt_loaithi);
        imageView.setImageResource(loaiMenuArrayList.get(i).getHinhanh());
        textView.setText(loaiMenuArrayList.get(i).getTen());
        return view;
    }
}
