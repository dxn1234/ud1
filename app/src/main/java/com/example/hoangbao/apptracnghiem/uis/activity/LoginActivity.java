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
                    Data data = response.body().getData();
                    name = data.getHodem() + " " + data.getTen();
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
//        String url="http://14.160.93.98:8672/testlogin.php";
//        RequestQueue requestQueue=Volley.newRequestQueue(LoginActivity.this);
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    Log.d("kiemtra",response);
//                    int code=jsonObject.getInt("code");
//                    if(code==200){
//                        startActivity(new Intent(LoginActivity.this,Main2Activity.class));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String>hashMap=new HashMap<>();
//                hashMap.put("sbd",edtsobaodanh.getText().toString());
//                hashMap.put("password",edtpassword.getText().toString());
//                return hashMap;
//            }
//        };
//        requestQueue.add(stringRequest);
    }
}
