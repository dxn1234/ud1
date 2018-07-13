package com.example.hoangbao.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.model.Question;

import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter{
    Context context;
    int layout;
    ArrayList<Question>questionArrayList;

    public QuestionAdapter(Context context, int layout, ArrayList<Question> questionArrayList) {
        this.context = context;
        this.layout = layout;
        this.questionArrayList = questionArrayList;
    }

    @Override
    public int getCount() {
        return questionArrayList.size();
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
        TextView txtlambaithimacauhoi=convertView.findViewById(R.id.txt_lambaithi_macauhoi);
        TextView txtlambaithitencauhoi=convertView.findViewById(R.id.txt_lambaithi_cauhoi);
        RadioButton radioButtona=convertView.findViewById(R.id.rb_lambaithi_a);
        RadioButton radioButtonb=convertView.findViewById(R.id.rb_lambaithi_b);
        RadioButton radioButtonc=convertView.findViewById(R.id.rb_lambaithi_c);
        RadioButton radioButtond=convertView.findViewById(R.id.rb_lambaithi_d);
        return convertView;
    }
}
