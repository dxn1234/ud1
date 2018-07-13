package com.example.hoangbao.apptracnghiem.model;

import android.widget.RadioButton;

public class Question {
    String macauhoi;
    String tencauhoi;
    RadioButton radioButtona,radioButtonb,radioButtonc,radioButtond;
    String dapan;

    public Question() {
    }

    public Question(String macauhoi, String tencauhoi, RadioButton radioButtona, RadioButton radioButtonb, RadioButton radioButtonc, RadioButton radioButtond, String dapan) {
        this.macauhoi = macauhoi;
        this.tencauhoi = tencauhoi;
        this.radioButtona = radioButtona;
        this.radioButtonb = radioButtonb;
        this.radioButtonc = radioButtonc;
        this.radioButtond = radioButtond;
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

    public RadioButton getRadioButtona() {
        return radioButtona;
    }

    public void setRadioButtona(RadioButton radioButtona) {
        this.radioButtona = radioButtona;
    }

    public RadioButton getRadioButtonb() {
        return radioButtonb;
    }

    public void setRadioButtonb(RadioButton radioButtonb) {
        this.radioButtonb = radioButtonb;
    }

    public RadioButton getRadioButtonc() {
        return radioButtonc;
    }

    public void setRadioButtonc(RadioButton radioButtonc) {
        this.radioButtonc = radioButtonc;
    }

    public RadioButton getRadioButtond() {
        return radioButtond;
    }

    public void setRadioButtond(RadioButton radioButtond) {
        this.radioButtond = radioButtond;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }
}
