
package com.example.hoangbao.apptracnghiem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("hodem")
    @Expose
    private String hodem;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("ngaysinh")
    @Expose
    private String ngaysinh;
    @SerializedName("noisinh")
    @Expose
    private String noisinh;
    @SerializedName("phongthi")
    @Expose
    private String phongthi;

    public String getHodem() {
        return hodem;
    }

    public void setHodem(String hodem) {
        this.hodem = hodem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getPhongthi() {
        return phongthi;
    }

    public void setPhongthi(String phongthi) {
        this.phongthi = phongthi;
    }

}
