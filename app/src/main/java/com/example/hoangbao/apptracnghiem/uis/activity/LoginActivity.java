package com.example.hoangbao.apptracnghiem.uis.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.model.Data;
import com.example.hoangbao.apptracnghiem.model.LoginRespone;
import com.example.hoangbao.apptracnghiem.rest.RestClient;
import com.example.hoangbao.apptracnghiem.uis.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity{
    public static String ngaysinh;
    public static String name;
    public static String sobaodanh;
    TextInputLayout tiuername, tipassword;
    public static EditText edtsobaodanh, edtpassword;
    Button btnDangNhap;

    @Override
    public int injectLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        btnDangNhap = findViewById(R.id.btn_dangnhap);
        tipassword = findViewById(R.id.ti_matkhau);
        tiuername = findViewById(R.id.ti_sobaodanh);
        edtpassword = findViewById(R.id.edt_matkhau);
        edtsobaodanh = findViewById(R.id.edt_sobaodanh);



        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtsobaodanh.getText().toString();
                String password = edtpassword.getText().toString();
                login(username, password);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    @Override
    public void initValueable() {

    }
    private void login(String username, String password) {
        showProgressDialog();
        retrofit2.Call<LoginRespone> callLogin = RestClient.getAPIs().login(username, password);
        callLogin.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(retrofit2.Call<LoginRespone> call, Response<LoginRespone> response) {
                int code = response.body().getCode();
                if (code == 200) {
                    sobaodanh=edtsobaodanh.getText().toString();
                    ngaysinh=response.body().getData().getNgaysinh();
                    Data data = response.body().getData();
                    name = data.getHodem() + " " + data.getTen();
                    startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }

            @Override
            public void onFailure(retrofit2.Call<LoginRespone> call, Throwable t) {
                //that bai
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                closeProgressDialog();
            }
        });
    }
}
