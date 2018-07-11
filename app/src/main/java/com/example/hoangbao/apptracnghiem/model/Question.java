package com.example.hoangbao.apptracnghiem.model;

import android.widget.RadioButton;

public class Question {
    int stt;
    String cauhoi;
    RadioButton radioButtona,radioButtonb,radioButtonc,radioButtond;

    public Question() {
    }

    public Question(int stt, String cauhoi, RadioButton radioButtona, RadioButton radioButtonb, RadioButton radioButtonc, RadioButton radioButtond) {
        this.stt = stt;
        this.cauhoi = cauhoi;
        this.radioButtona = radioButtona;
        this.radioButtonb = radioButtonb;
        this.radioButtonc = radioButtonc;
        this.radioButtond = radioButtond;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
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
}
