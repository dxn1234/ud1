package com.example.hoangbao.apptracnghiem.model;

public class LoaiMenu {
    int hinhanh;
    String ten;

    public LoaiMenu() {
    }

    public LoaiMenu(int hinhanh, String ten) {
        this.hinhanh = hinhanh;
        this.ten = ten;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
