package com.example.hoangbao.apptracnghiem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DanhSachCauHoi {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("socau")
    @Expose
    private Integer socau;
    @SerializedName("thoigianthi")
    @Expose
    private Integer thoigianthi;
    @SerializedName("data")
    @Expose
    private List<Datum1> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSocau() {
        return socau;
    }

    public void setSocau(Integer socau) {
        this.socau = socau;
    }

    public Integer getThoigianthi() {
        return thoigianthi;
    }

    public void setThoigianthi(Integer thoigianthi) {
        this.thoigianthi = thoigianthi;
    }

    public List<Datum1> getData() {
        return data;
    }

    public void setData(List<Datum1> data) {
        this.data = data;
    }
}
