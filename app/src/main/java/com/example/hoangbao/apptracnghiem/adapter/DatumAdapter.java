package com.example.hoangbao.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.model.Datum;

import java.util.ArrayList;

public class DatumAdapter extends BaseAdapter{
    Context context;
    int layout;
    ArrayList<Datum>datumArrayList;

    public DatumAdapter(Context context, int layout, ArrayList<Datum> datumArrayList) {
        this.context = context;
        this.layout = layout;
        this.datumArrayList = datumArrayList;
    }

    @Override
    public int getCount() {
        return datumArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(layout,null);
        TextView txtmamon=convertView.findViewById(R.id.txt_mamon);
        txtmamon.setText(datumArrayList.get(position).getMamon());
        TextView txttenmon=convertView.findViewById(R.id.txt_tenmon);
        txttenmon.setText(datumArrayList.get(position).getTenmon());
        return convertView;
    }
}
