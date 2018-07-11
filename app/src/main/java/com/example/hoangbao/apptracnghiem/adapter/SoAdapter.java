package com.example.hoangbao.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.model.So;

import java.util.ArrayList;

public class SoAdapter extends BaseAdapter{
    Context context;
    int layout;
    ArrayList<So> soArrayList;

    public SoAdapter(Context context, int layout, ArrayList<So>soArrayList) {
        this.context = context;
        this.layout = layout;
        this.soArrayList=soArrayList;
    }

    @Override
    public int getCount() {
        return soArrayList.size();
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
        view=layoutInflater.inflate(R.layout.dong_gridview,null);
        TextView txtso=view.findViewById(R.id.txt_sttcau);
        txtso.setText(soArrayList.get(i).getTen());
        txtso.setBackgroundColor(soArrayList.get(i).getMausac());
        return view;
    }
}
