package com.example.hoangbao.apptracnghiem.uis.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.model.Data;
import com.example.hoangbao.apptracnghiem.model.LoginRespone;
import com.example.hoangbao.apptracnghiem.rest.RestClient;
import com.example.hoangbao.apptracnghiem.uis.BaseActivity;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity{
    TextInputLayout tiuername, tipassword;
    EditText edtsobaodanh, edtpassword;
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
    public void initValueable() {

    }
    private void login(String username, String password) {
        showProgressDialog();
        retrofit2.Call<LoginRespone> callLogin = RestClient.getAPIs().login(username, password);
        callLogin.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(retrofit2.Call<LoginRespone> call, Response<LoginRespone> response) {
                //lay ve thanh cong
                int code = response.body().getCode();
                if (code == 200) {
                    Data data = response.body().getData();
                    String name = data.getHodem() + " " + data.getTen();
                    Toast.makeText(LoginActivity.this, name, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "dang nhap that bai", Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }

            @Override
            public void onFailure(retrofit2.Call<LoginRespone> call, Throwable t) {
                //that bai
                Toast.makeText(LoginActivity.this, "dang nhap that bai", Toast.LENGTH_SHORT).show();
                closeProgressDialog();
            }
        });
    }
}
