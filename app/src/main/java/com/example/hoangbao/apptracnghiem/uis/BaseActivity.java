package com.example.hoangbao.apptracnghiem.uis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity{
    ProgressDialog dialog;
    Context baseContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(injectLayout());
        baseContext=BaseActivity.this;
        initViews();
        initValueable();
    }

    /**
     * layout hien thi cho activity
     */

    public abstract int injectLayout();

    /**
     *xay dung cac view
     */
    public abstract void initViews();

    /**
     * khoi tao cac tham so gan gia tri cho cac views
     */
    public abstract void initValueable();

    public void showProgressDialog(){
        try {
            if(dialog==null){
                dialog=new ProgressDialog(baseContext);
                dialog.setCancelable(false);
                dialog.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeProgressDialog(){
        try {
            if(dialog!=null){
                dialog.cancel();
                dialog=null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
