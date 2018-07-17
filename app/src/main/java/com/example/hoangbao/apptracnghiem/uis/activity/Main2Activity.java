package com.example.hoangbao.apptracnghiem.uis.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangbao.apptracnghiem.uis.fragment.DanhSachMonThiFragment;
import com.example.hoangbao.apptracnghiem.R;

public class Main2Activity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    public static DanhSachMonThiFragment danhSachMonThiFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        danhSachMonThiFragment=new DanhSachMonThiFragment();
        if(danhSachMonThiFragment.isAdded()){
           fragmentTransaction.show(danhSachMonThiFragment);
        }
        else{
            fragmentTransaction.add(R.id.framelayout,danhSachMonThiFragment);
            fragmentTransaction.addToBackStack("danhsachmonthi");
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Log.d("kiemtra",getSupportFragmentManager().getBackStackEntryCount()+"");
        if(getSupportFragmentManager().getBackStackEntryCount()==4){
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().popBackStack();
        }
        else if(getSupportFragmentManager().getBackStackEntryCount()==3){
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().popBackStack();
        }
        else if(getSupportFragmentManager().getBackStackEntryCount()>1){
            getSupportFragmentManager().popBackStack();
        }
        else{
            startActivity(new Intent(Main2Activity.this,LoginActivity.class));
        }
    }
}
