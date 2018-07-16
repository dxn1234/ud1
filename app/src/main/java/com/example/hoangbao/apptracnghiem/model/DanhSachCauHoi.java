package com.example.hoangbao.apptracnghiem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("datum1")
    @Expose
    private List<Datum1> datum1 = null;

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

    public List<Datum1> getDatum1() {
        return datum1;
    }

    public void setDatum1(List<Datum1> datum1) {
        this.datum1 = datum1;
    }
}
