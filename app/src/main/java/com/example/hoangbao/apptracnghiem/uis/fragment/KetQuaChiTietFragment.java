package com.example.hoangbao.apptracnghiem.uis.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.adapter.SoAdapter;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;
import com.example.hoangbao.apptracnghiem.model.So;
import com.example.hoangbao.apptracnghiem.uis.activity.LoginActivity;

import java.util.ArrayList;

public class KetQuaChiTietFragment extends Fragment{
    RadioGroup radioGroup;
    TextView txtketquachitiethoten,txtketquachitietngaysinh,txtketquachitietsobaodanh;
    TextView txtketquachitietmacauhoi,txtketquachitietcauhoi;
    RadioButton rbketquachitieta,rbketquachitietb,rbketquachitietc,rbketquachitietd;
    int stt=0;
    Button btnketquachitietquaylai,btnketquachitietcautiep;
    ArrayList<So>soArrayList;
    GridView gridViewketquachitiet;
    TextView txtketquachitietchuthich;
    View view;
    ImageButton imgbtnketquachitiet;
    ArrayList<LoaiMenu> loaiMenuArrayList;
    DrawerLayout drawerLayoutketquachitiet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_ket_qua_chi_tiet,container,false);
        anhXa();
        txtketquachitietmacauhoi.setText(LamBaiThiFragment.questionArrayList.get(0).getMacauhoi());
        txtketquachitietcauhoi.setText(LamBaiThiFragment.questionArrayList.get(0).getTencauhoi());
        rbketquachitieta.setText("A. "+LamBaiThiFragment.questionArrayList.get(0).getRadioButtona().getText());
        rbketquachitietb.setText("B. "+LamBaiThiFragment.questionArrayList.get(0).getRadioButtonb().getText());
        rbketquachitietc.setText("C. "+LamBaiThiFragment.questionArrayList.get(0).getRadioButtonc().getText());
        rbketquachitietd.setText("D. "+LamBaiThiFragment.questionArrayList.get(0).getRadioButtond().getText());
        if(LamBaiThiFragment.stringArrayListdapan.get(0).equals("A")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauxanh));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else{
                radioGroup.check(0);
            }

        }
        if(LamBaiThiFragment.stringArrayListdapan.get(0).equals("B")){
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauxanh));
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else{
                radioGroup.check(0);
            }
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(0).equals("C")){
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauxanh));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else{
                radioGroup.check(0);
            }
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(0).equals("D")){
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauxanh));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(0).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else{
                radioGroup.check(0);
            }
        }
        txtketquachitietchuthich.setPaintFlags(txtketquachitietchuthich.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        for(int i=1;i<=25;i++){
            soArrayList.add(new So(i+"",getResources().getColor(R.color.mauvang)));
        }
        SoAdapter soAdapter=new SoAdapter(getActivity(),R.layout.dong_gridview,soArrayList);
        gridViewketquachitiet.setAdapter(soAdapter);
        for(int i=0;i<LamBaiThiFragment.questionArrayList.size();i++){
            if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtona().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("A")){
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.mauxanh));
                }
                else{
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.maudo));
                }
            }
            else if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtonb().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("B")){
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.mauxanh));
                }
                else{
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.maudo));
                }
            }
            else if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtonc().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("C")){
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.mauxanh));
                }
                else{
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.maudo));
                }
            }
            else if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtond().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("D")){
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.mauxanh));
                }
                else{
                    soArrayList.get(i).setMausac(getResources().getColor(R.color.maudo));
                }
            }
            else{
                soArrayList.get(i).setMausac(getResources().getColor(R.color.mauvang));
            }
        }
        soAdapter.notifyDataSetChanged();
        batSuKien();
        txtketquachitietsobaodanh.setText("SBD:"+LoginActivity.edtsobaodanh.getText().toString());
        txtketquachitietngaysinh.setText("Ngày sinh:"+LoginActivity.ngaysinh);
        txtketquachitiethoten.setText(LoginActivity.name);
        return view;
    }

    private void batSuKien() {
        imgbtnketquachitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutketquachitiet.openDrawer(GravityCompat.START);
            }
        });
        btnketquachitietcautiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kt = 0;
                stt++;
                if (stt >= LamBaiThiFragment.questionArrayList.size()) {
                    stt =LamBaiThiFragment.questionArrayList.size() - 1;
                } else {
                    kt++;
                    cauHoiKeTiep();
                }
            }
        });
        btnketquachitietquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kt = 0;
                stt--;
                if (stt < 0) {
                    stt = 0;
                } else {
                    kt++;
                    cauHoiTruoc();
                }
            }
        });

    }
    private void cauHoiKeTiep() {
        int dem=stt+1;
        txtketquachitietmacauhoi.setText(LamBaiThiFragment.questionArrayList.get(stt).getMacauhoi());
        txtketquachitietcauhoi.setText(LamBaiThiFragment.questionArrayList.get(stt).getTencauhoi());
        rbketquachitieta.setText("A. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().getText());
        rbketquachitietb.setText("B. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().getText());
        rbketquachitietc.setText("C. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().getText());
        rbketquachitietd.setText("D. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().getText());
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("A")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauxanh));
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("B")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauxanh));
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("C")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauxanh));
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("D")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauxanh));
        }
    }

    private void cauHoiTruoc() {
        txtketquachitietmacauhoi.setText(LamBaiThiFragment.questionArrayList.get(stt).getMacauhoi());
        txtketquachitietcauhoi.setText(LamBaiThiFragment.questionArrayList.get(stt).getTencauhoi());
        rbketquachitieta.setText("A. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().getText());
        rbketquachitietb.setText("B. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().getText());
        rbketquachitietc.setText("C. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().getText());
        rbketquachitietd.setText("D. "+LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().getText());
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("A")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauxanh));
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("B")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauxanh));
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("C")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauxanh));
        }
        if(LamBaiThiFragment.stringArrayListdapan.get(stt).equals("D")){
            rbketquachitieta.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietb.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietc.setTextColor(getResources().getColor(R.color.mauden));
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauden));
            if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtona().isChecked()){
                rbketquachitieta.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_a);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonb().isChecked()){
                rbketquachitietb.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_b);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtonc().isChecked()){
                rbketquachitietc.setTextColor(getResources().getColor(R.color.maudo));
                radioGroup.check(R.id.rb_ketquachitiet_c);
            }
            else if(LamBaiThiFragment.questionArrayList.get(stt).getRadioButtond().isChecked()){
                rbketquachitietd.setTextColor(getResources().getColor(R.color.mauxanh));
                radioGroup.check(R.id.rb_ketquachitiet_d);
            }
            else{
                radioGroup.check(0);
            }
            rbketquachitietd.setTextColor(getResources().getColor(R.color.mauxanh));
        }
    }

    private void anhXa() {
        radioGroup=view.findViewById(R.id.radiogroup);
        txtketquachitiethoten=view.findViewById(R.id.txt_ketquachitiethoten);
        txtketquachitietngaysinh=view.findViewById(R.id.txt_ketquachitietngaysinh);
        txtketquachitietsobaodanh=view.findViewById(R.id.txt_ketquachitietsobaodanh);
        txtketquachitietmacauhoi=view.findViewById(R.id.txt_ketquachitietmacauhoi);
        txtketquachitietcauhoi=view.findViewById(R.id.txt_ketquachitietcauhoi);
        rbketquachitieta=view.findViewById(R.id.rb_ketquachitiet_a);
        rbketquachitietb=view.findViewById(R.id.rb_ketquachitiet_b);
        rbketquachitietc=view.findViewById(R.id.rb_ketquachitiet_c);
        rbketquachitietd=view.findViewById(R.id.rb_ketquachitiet_d);
        btnketquachitietcautiep=view.findViewById(R.id.btn_ketquachitietcautiep);
        btnketquachitietquaylai=view.findViewById(R.id.btn_ketquachitietquaylai);
        soArrayList=new ArrayList<>();
        gridViewketquachitiet=view.findViewById(R.id.gridview_ketquachitiet);
        txtketquachitietchuthich=view.findViewById(R.id.txt_ketquachitiet_chuthich);
        loaiMenuArrayList =new ArrayList<>();
        drawerLayoutketquachitiet=view.findViewById(R.id.drawerlayout_ketquachitiet);
        imgbtnketquachitiet=view.findViewById(R.id.imgbtn_ketquachitiet);
    }
}
