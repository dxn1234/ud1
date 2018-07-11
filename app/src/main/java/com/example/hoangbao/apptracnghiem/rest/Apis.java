package com.example.hoangbao.apptracnghiem.rest;

import com.example.hoangbao.apptracnghiem.model.ExamListRespone;
import com.example.hoangbao.apptracnghiem.model.LoginRespone;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * mieu ta cac apis trong du an
 */
public interface Apis {
    @FormUrlEncoded
    @POST("testlogin.php")
    Call<LoginRespone> login(@Field("sbd") String sbd, @Field("password") String password);

    @GET("danhsachmonthi.php")
    Call<ExamListRespone> getExamList();
}
