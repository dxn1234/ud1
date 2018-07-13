package com.example.hoangbao.apptracnghiem.uis.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.adapter.LoaiMenuAdapter;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;
import com.example.hoangbao.apptracnghiem.uis.activity.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class KetQuaThiFragment extends Fragment{
    TextView txtketquathisobaodanh,txtketquathihoten,txtketquathibatdau,txtketquathiketquathiketthuc,txtketquathithoigainlambai;
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
        int tongdiem=0;
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
        return view;
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
                    fragmentTransaction.replace(R.id.framelayout,ketQuaChiTietFragment);
                    fragmentTransaction.commit();
                }
            }
        });
        lvmenuketquathi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
    }


    private void anhXa() {
        txtketquathithoigainlambai=view.findViewById(R.id.txt_ketquathithoigian);
        txtketquathisobaodanh=view.findViewById(R.id.txt_ketquathisobaodanh);
        txtketquathihoten=view.findViewById(R.id.txt_ketquathihoten);
        txtketquathibatdau=view.findViewById(R.id.txt_ketquathibatdau);
        txtketquathiketquathiketthuc=view.findViewById(R.id.txt_ketquathiketthuc);
        txtketquathitongdiem=view.findViewById(R.id.txt_ketquathitongdiem);
        btnxemchitiet=view.findViewById(R.id.btn_xemchitiet);
        loaiMenuArrayList =new ArrayList<>();
        imgbtnKetQuaThi=view.findViewById(R.id.imgbtn_ketquathi);
        drawerLayoutketquathi=view.findViewById(R.id.drawerlayout_ketquathi);
        lvmenuketquathi=view.findViewById(R.id.lv_menuketquathi);
    }
}
