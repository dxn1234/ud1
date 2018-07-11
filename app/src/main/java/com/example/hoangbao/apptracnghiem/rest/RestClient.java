package com.example.hoangbao.apptracnghiem.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static final String BASE_APIS = "http://14.160.93.98:8672/";
    public static Retrofit retrofit = null;

    public static Apis getAPIs() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //cau hinh retrofit de ket noi voi server
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_APIS)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(Apis.class);
    }
}
