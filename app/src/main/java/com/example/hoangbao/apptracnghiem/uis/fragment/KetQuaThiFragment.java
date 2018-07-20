package com.example.hoangbao.apptracnghiem.uis.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.adapter.LoaiMenuAdapter;
import com.example.hoangbao.apptracnghiem.model.DanhSachCauHoi;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;
import com.example.hoangbao.apptracnghiem.model.NgayThi;
import com.example.hoangbao.apptracnghiem.rest.RestClient;
import com.example.hoangbao.apptracnghiem.uis.activity.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KetQuaThiFragment extends Fragment{
    int tongdiem=0;
    TextView txtketquathimenuhoten,txtketquathimenusobaodanh,txtketquathimenungaysinh;
    TextView txtketquathisobaodanh,txtketquathihoten,txtketquathibatdau,txtketquathiketthuc,txtketquathithoigainlambai;
    TextView txtketquathitongdiem;
    Button btnxemchitiet;
    View view;
    ArrayList<LoaiMenu> loaiMenuArrayList;
    ImageButton imgbtnKetQuaThi;
    DrawerLayout drawerLayoutketquathi;
    ListView lvmenuketquathi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_ket_qua_thi,container,false);
        anhXa();
        loaiMenuArrayList.add(new LoaiMenu(R.drawable.next,"Đăng Xuất"));
        LoaiMenuAdapter loaiMenuAdapter=new LoaiMenuAdapter(getActivity(),R.layout.dong_menu,loaiMenuArrayList);
        lvmenuketquathi.setAdapter(loaiMenuAdapter);

        for(int i=0;i<LamBaiThiFragment.questionArrayList.size();i++){
            if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtona().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("A")){
                    tongdiem+=4;
                }
            }
            if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtonb().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("B")){
                    tongdiem+=4;
                }
            }
            if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtonc().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("C")){
                    tongdiem+=4;
                }
            }
            if(LamBaiThiFragment.questionArrayList.get(i).getRadioButtond().isChecked()){
                if(LamBaiThiFragment.stringArrayListdapan.get(i).equals("D")){
                    tongdiem+=4;
                }
            }
        }
        txtketquathisobaodanh.setText(LoginActivity.edtsobaodanh.getText());
        txtketquathihoten.setText(LoginActivity.name);
        Calendar calendar=Calendar.getInstance();
        Log.d("ju",calendar.getTime()+"");
        txtketquathithoigainlambai.setText(30+" phút");
        txtketquathitongdiem.setText(tongdiem+"");
        batSuKien();
        txtketquathimenusobaodanh.setText("SBD:"+LoginActivity.edtsobaodanh.getText().toString());
        txtketquathimenuhoten.setText(LoginActivity.name);
        txtketquathimenungaysinh.setText("Ngày sinh:"+LoginActivity.ngaysinh);
        getdulieu();
        return view;
    }

    private void getdulieu() {
        String s="";
        for(int i=0;i<LamBaiThiFragment.stringArrayListdapan.size();i++){
            s+=LamBaiThiFragment.questionArrayList.get(i).getMacauhoi()+","+LamBaiThiFragment.stringArrayListdapan.get(i);
        }
        Call<NgayThi>ngayThiCall= RestClient.getAPIs().getngaythi(LoginActivity.sobaodanh,DanhSachMonThiFragment.mamon,tongdiem,s);
        ngayThiCall.enqueue(new Callback<NgayThi>() {
            @Override
            public void onResponse(Call<NgayThi> call, Response<NgayThi> response) {
                NgayThi ngayThi=response.body();
                String data=ngayThi.getData();
                String s1="";
                String s2="";
                int gan=0;
                for(int i=0;i<data.length();i++){
                    if(data.charAt(i)!=';'){
                        s1+=data.charAt(i);
                    }
                    else{
                        gan=i;
                        break;
                    }
                }
                for(int i=gan+1;i<data.length();i++){
                    s2+=data.charAt(i);
                }
                txtketquathibatdau.setText(s1);
                txtketquathiketthuc.setText(s2);
            }

            @Override
            public void onFailure(Call<NgayThi> call, Throwable t) {

            }
        });
    }

    private void batSuKien() {
        imgbtnKetQuaThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutketquathi.openDrawer(GravityCompat.START);
            }
        });
        btnxemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null){
                    FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                    KetQuaChiTietFragment ketQuaChiTietFragment=new KetQuaChiTietFragment();
                    if(ketQuaChiTietFragment.isAdded()){
                        fragmentTransaction.show(ketQuaChiTietFragment);
                    }
                    else{
                        fragmentTransaction.add(R.id.framelayout,ketQuaChiTietFragment);
                        fragmentTransaction.addToBackStack("ketquachitiet");
                    }
                    if(LamBaiThiFragment.ketQuaThiFragment.isAdded()){
                        fragmentTransaction.hide(LamBaiThiFragment.ketQuaThiFragment);
                    }
                    fragmentTransaction.commit();
                }
            }
        });
        lvmenuketquathi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog=new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_custom);
                dialog.show();
                Button btnkhongthoat=dialog.findViewById(R.id.btn_khongthoat);
                Button btndongy=dialog.findViewById(R.id.btn_dongythoat);
                btndongy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }
                });
                btnkhongthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    private void anhXa() {
        txtketquathimenuhoten=view.findViewById(R.id.txt_ketquathimenuhoten);
        txtketquathimenusobaodanh=view.findViewById(R.id.txt_ketquathimenusobaodanh);
        txtketquathimenungaysinh=view.findViewById(R.id.txt_ketquathingaysinh);
        txtketquathithoigainlambai=view.findViewById(R.id.txt_ketquathithoigian);
        txtketquathisobaodanh=view.findViewById(R.id.txt_ketquathisobaodanh);
        txtketquathihoten=view.findViewById(R.id.txt_ketquathihoten);
        txtketquathibatdau=view.findViewById(R.id.txt_ketquathibatdau);
        txtketquathiketthuc=view.findViewById(R.id.txt_ketquathiketthuc);
        txtketquathitongdiem=view.findViewById(R.id.txt_ketquathitongdiem);
        btnxemchitiet=view.findViewById(R.id.btn_xemchitiet);
        loaiMenuArrayList =new ArrayList<>();
        imgbtnKetQuaThi=view.findViewById(R.id.imgbtn_ketquathi);
        drawerLayoutketquathi=view.findViewById(R.id.drawerlayout_ketquathi);
        lvmenuketquathi=view.findViewById(R.id.lv_menuketquathi);
    }
}
