package com.example.hoangbao.apptracnghiem.model;

public class CauHoi {
    String macauhoi;
    String tencauhoi;
    String ya,yb,yc,yd;
    String dapan;

    public CauHoi() {
    }

    public CauHoi(String macauhoi, String tencauhoi, String ya, String yb, String yc, String yd, String dapan) {
        this.macauhoi = macauhoi;
        this.tencauhoi = tencauhoi;
        this.ya = ya;
        this.yb = yb;
        this.yc = yc;
        this.yd = yd;
        this.dapan = dapan;
    }

    public String getMacauhoi() {
        return macauhoi;
    }

    public void setMacauhoi(String macauhoi) {
        this.macauhoi = macauhoi;
    }

    public String getTencauhoi() {
        return tencauhoi;
    }

    public void setTencauhoi(String tencauhoi) {
        this.tencauhoi = tencauhoi;
    }

    public String getYa() {
        return ya;
    }

    public void setYa(String ya) {
        this.ya = ya;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getYc() {
        return yc;
    }

    public void setYc(String yc) {
        this.yc = yc;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }
}
