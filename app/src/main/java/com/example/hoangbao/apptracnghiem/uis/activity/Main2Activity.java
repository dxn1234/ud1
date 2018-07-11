package com.example.hoangbao.apptracnghiem.uis.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangbao.apptracnghiem.uis.fragment.DanhSachMonThiFragment;
import com.example.hoangbao.apptracnghiem.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        DanhSachMonThiFragment danhSachMonThiFragment=new DanhSachMonThiFragment();
        fragmentTransaction.replace(R.id.framelayout,danhSachMonThiFragment);
        fragmentTransaction.commit();
    }
}
