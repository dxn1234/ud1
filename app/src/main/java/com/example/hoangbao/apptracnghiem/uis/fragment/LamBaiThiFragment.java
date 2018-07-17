package com.example.hoangbao.apptracnghiem.uis.fragment;

import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.adapter.SoAdapter;
import com.example.hoangbao.apptracnghiem.model.CauHoi;
import com.example.hoangbao.apptracnghiem.model.DanhSachCauHoi;
import com.example.hoangbao.apptracnghiem.model.Datum1;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;
import com.example.hoangbao.apptracnghiem.model.Question;
import com.example.hoangbao.apptracnghiem.model.So;
import com.example.hoangbao.apptracnghiem.rest.RestClient;
import com.example.hoangbao.apptracnghiem.uis.activity.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LamBaiThiFragment extends Fragment{
    public static int thoigian;
    public static ArrayList<String>getStringArrayListdapan;
    ArrayList<CauHoi>cauHoiArrayList;
    public static KetQuaThiFragment ketQuaThiFragment;
    public static ArrayList<Datum1>datum1ArrayList;
    TextView txtlambaithihoten,txtlambaithisobaodanh,txtlambaithingaysinh;
    RadioGroup radioGroup;
    public static ArrayList<Question>questionArrayList;
    public static ArrayList<String> stringArrayListdapan = new ArrayList<>();
    public static int tongdiem = 0;
    String hou = "", min = "", sec = "";
    int gio, phut, giay;
    TextView txtlambaithithoigian;
    SoAdapter soAdapter;
    GridView gridViewlambaithi;
    ArrayList<So> soArrayList;
    int stt = 0;
    java.text.SimpleDateFormat simpleDateFormat;
    TextView txtlambaithimacauhoi, txtlambaithicauhoi, txtlambaithichuthich;
    RadioButton rblambaithia, rblambaithib, rblambaithic, rblambaithid;
    Button btnlambaithiquaylai, btnlambaithicautiep;
    Button btnNopbai;
    View view;
    ArrayList<LoaiMenu> loaiMenuArrayList;
    NavigationView navigationView;
    DrawerLayout drawerLayoutlambaithi;
    ImageButton imgbtnLamBaiThi;
    int laco = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lam_bai_thi, container, false);
        anhXa();
        getDuLieu();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        thoigian *=60;
        chuyendoithoigian();
        txtlambaithithoigian.setText(hou + ":" + min + ":" + sec);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                thoigian -= 1;
                if (thoigian > 0) {
                    chuyendoithoigian();
                    txtlambaithithoigian.setText(hou + ":" + min + ":" + sec);
                    handler.postDelayed(this, 1000);
                } else {
                    if(getActivity()!=null){
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        ketQuaThiFragment = new KetQuaThiFragment();
                        if(ketQuaThiFragment.isAdded()){
                            Log.d("chandoilamco","hhh");
                            fragmentTransaction.show(ketQuaThiFragment);
                        }
                        else{
                            fragmentTransaction.add(R.id.framelayout, ketQuaThiFragment);
                            fragmentTransaction.addToBackStack("ketquathi");
                        }
                        if(DanhSachMonThiFragment.lamBaiThiFragment.isAdded()){
                            fragmentTransaction.hide(DanhSachMonThiFragment.lamBaiThiFragment);
                        }
                        fragmentTransaction.commit();
                    }
                }
            }
        }, 1000);
        for (int i = 1; i <= 25; i++) {
            soArrayList.add(new So(i + "", getResources().getColor(R.color.mauden)));
        }
        soAdapter = new SoAdapter(getActivity(), R.layout.dong_gridview, soArrayList);
        gridViewlambaithi.setAdapter(soAdapter);
        initDataFake();
        txtlambaithimacauhoi.setText(questionArrayList.get(0).getMacauhoi());
        txtlambaithicauhoi.setText(questionArrayList.get(0).getTencauhoi());
        rblambaithia.setText("A. "+questionArrayList.get(0).getRadioButtona().getText());
        rblambaithib.setText("B. "+questionArrayList.get(0).getRadioButtonb().getText());
        rblambaithic.setText("C. "+questionArrayList.get(0).getRadioButtonc().getText());
        rblambaithid.setText("D. "+questionArrayList.get(0).getRadioButtond().getText());
        rblambaithia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stringArrayListdapan.get(0).equals("A")){
                    tongdiem+=4;
                }
                questionArrayList.get(stt).getRadioButtona().setChecked(true);
                questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                questionArrayList.get(stt).getRadioButtond().setChecked(false);
                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
                soAdapter.notifyDataSetChanged();
                Log.d("kiemtra", "a");
            }
        });
        rblambaithib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stringArrayListdapan.get(0).equals("B")){
                    tongdiem+=4;
                }
                questionArrayList.get(stt).getRadioButtona().setChecked(false);
                questionArrayList.get(stt).getRadioButtonb().setChecked(true);
                questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                questionArrayList.get(stt).getRadioButtond().setChecked(false);
                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
                soAdapter.notifyDataSetChanged();
                Log.d("kiemtra", "b");
            }
        });
        rblambaithic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stringArrayListdapan.get(0).equals("C")){
                    tongdiem+=4;
                }
                questionArrayList.get(stt).getRadioButtona().setChecked(false);
                questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                questionArrayList.get(stt).getRadioButtonc().setChecked(true);
                questionArrayList.get(stt).getRadioButtond().setChecked(false);
                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
                soAdapter.notifyDataSetChanged();
                Log.d("kiemtra", "c");
            }
        });
        rblambaithid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stringArrayListdapan.get(0).equals("D")){
                    tongdiem+=4;
                }
                questionArrayList.get(stt).getRadioButtona().setChecked(false);
                questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                questionArrayList.get(stt).getRadioButtond().setChecked(true);
                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
                soAdapter.notifyDataSetChanged();
                Log.d("kiemtra", "d");
            }
        });
        txtlambaithichuthich.setPaintFlags(txtlambaithichuthich.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        batSuKien();
        txtlambaithihoten.setText(LoginActivity.name);
        txtlambaithisobaodanh.setText("SBD:"+LoginActivity.edtsobaodanh.getText().toString());
        txtlambaithingaysinh.setText("Ngày sinh:"+LoginActivity.ngaysinh);
        super.onActivityCreated(savedInstanceState);
    }

    private void getDuLieu() {
//        Call<DanhSachCauHoi>danhSachCauHoiCall= RestClient.getAPIs().getdanhsachcauhoi(LoginActivity.sobaodanh,DanhSachMonThiFragment.mamon);
//        danhSachCauHoiCall.enqueue(new Callback<DanhSachCauHoi>() {
//            @Override
//            public void onResponse(Call<DanhSachCauHoi> call, Response<DanhSachCauHoi> response) {
//                DanhSachCauHoi danhSachCauHoi=response.body();
//                datum1ArrayList= (ArrayList<Datum1>) danhSachCauHoi.getData();
//                for(int i=0;i<datum1ArrayList.size();i++) {
//                    String macauhoi=datum1ArrayList.get(i).getMacauhoi();
//                    String tencauhoi=datum1ArrayList.get(i).getTencauhoi();
//                    String cauhoia=datum1ArrayList.get(i).getA();
//                    String cauhoib=datum1ArrayList.get(i).getB();
//                    String cauhoic=datum1ArrayList.get(i).getC();
//                    String cauhoid=datum1ArrayList.get(i).getD();
//                    String dapan=datum1ArrayList.get(i).getDapan();
//                    RadioButton radioButtona=new RadioButton(getActivity());
//                    radioButtona.setText(cauhoia);
//                    RadioButton radioButtonb=new RadioButton(getActivity());
//                    radioButtonb.setText(cauhoib);
//                    RadioButton radioButtonc=new RadioButton(getActivity());
//                    radioButtonc.setText(cauhoic);
//                    RadioButton radioButtond=new RadioButton(getActivity());
//                    radioButtond.setText(cauhoid);
//                    questionArrayList.add(new Question(macauhoi,tencauhoi,radioButtona,radioButtonb,radioButtonc,radioButtond,dapan));
//                    stringArrayListdapan.add(dapan);
//                }
//            }
//            @Override
//            public void onFailure(Call<DanhSachCauHoi> call, Throwable t) {
//
//            }
//        });
        String api="{\"code\":200,\"message\":\"Success\",\"socau\":25,\"thoigianthi\":30,\"data\":[{\"macauhoi\":\"UI06-074\",\"tencauhoi\":\"C\\u1ea5u tr\\u00fac m\\u1ed9t \\u0111\\u1ecba ch\\u1ec9 th\\u01b0 \\u0111i\\u1ec7n t\\u1eed?\",\"A\":\".\",\"B\":\"@\",\"C\":\"@\",\"D\":\"T\\u1ea5t c\\u1ea3 \\u0111\\u1ec1u sai\",\"dapan\":\"C\"},{\"macauhoi\":\"UI02-099\",\"tencauhoi\":\"Mu\\u1ed1n xo\\u00e1 v\\u0129nh vi\\u1ec5n th\\u01b0 m\\u1ee5c ra kh\\u1ecfi m\\u00e1y, ta thao t\\u00e1c th\\u1ebf n\\u00e0o?\",\"A\":\"Nh\\u1ea5n t\\u1ed5 h\\u1ee3p ph\\u00edm Ctrl + A\",\"B\":\"Ch\\u1ecdn th\\u01b0 m\\u1ee5c v\\u00e0 nh\\u1ea5n t\\u1ed5 h\\u1ee3p ph\\u00edm Shift + Delete\",\"C\":\"Nh\\u1ea5n t\\u1ed5 h\\u1ee3p ph\\u00edm Ctrl +B\",\"D\":\"Nh\\u1ea5n t\\u1ed5 h\\u1ee3p ph\\u00edm Ctrl + Z\",\"dapan\":\"B\"},{\"macauhoi\":\"UI04-087\",\"tencauhoi\":\"Bi\\u1ec3u th\\u1ee9c sau cho k\\u1ebft qu\\u1ea3 l\\u00e0 bao nhi\\u00eau n\\u1ebfu DTB=9?\\r\\n=If(DTB>=5,\\\"TB\\\",If(DTB>=6.5,\\\"Kha\\\",If(DTB>=8,\\\"Gioi\\\",\\\"Yeu\\\")))\",\"A\":\"TB\",\"B\":\"Kha\",\"C\":\"Gioi\",\"D\":\"Yeu\",\"dapan\":\"C\"},{\"macauhoi\":\"UI06-108\",\"tencauhoi\":\"Trong khi g\\u1eedi Email, \\u0111\\u1ec3 ng\\u01b0\\u1eddi nh\\u1eadn kh\\u00f4ng th\\u1ec3 bi\\u1ebft nh\\u1eefng ai c\\u00f9ng nh\\u1eadn th\\u01b0 v\\u1edbi m\\u00ecnh ta c\\u1ea7n \\u0111i\\u1ec1n \\u0111\\u1ecba ch\\u1ec9 email c\\u1ee7a ng\\u01b0\\u1eddi \\u0111\\u00f3 v\\u00e0o m\\u1ee5c n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"TO\",\"B\":\"BCC\",\"C\":\"CC\",\"D\":\"SEND\",\"dapan\":\"B\"},{\"macauhoi\":\"UI03-102\",\"tencauhoi\":\"T\\u1ed5 h\\u1ee3p ph\\u00edm Ctrl+J d\\u00f9ng \\u0111\\u1ec3 l\\u00e0m g\\u00ec?\",\"A\":\"C\\u0103n l\\u1ec1 tr\\u00e1i v\\u0103n b\\u1ea3n \",\"B\":\"C\\u0103n v\\u0103n b\\u1ea3n \\u0111\\u1ec1u hai b\\u00ean\",\"C\":\"C\\u0103n l\\u1ec1 ph\\u1ea3i v\\u0103n b\\u1ea3n\",\"D\":\"C\\u0103n gi\\u1eefa v\\u0103n b\\u1ea3n \",\"dapan\":\"B\"},{\"macauhoi\":\"UI06-026\",\"tencauhoi\":\"Tr\\u00ecnh duy\\u1ec7t web kh\\u00f4ng th\\u1ec3 hi\\u1ec3n th\\u1ecb trang web y\\u00eau c\\u1ea7u, c\\u00f3 th\\u1ec3 do:\",\"A\":\"\\u0110\\u1ecba ch\\u1ec9 truy c\\u1eadp kh\\u00f4ng n\\u1eb1m tr\\u00ean m\\u00e1y ch\\u1ee7 c\\u1ee7a Vi\\u1ec7t Nam\",\"B\":\"\\u0110\\u1ecba ch\\u1ec9 truy c\\u1eadp kh\\u00f4ng \\u0111\\u01b0\\u1ee3c l\\u01b0u v\\u00e0o b\\u1ed9 nh\\u1edb \\u0111\\u1ec7m c\\u1ee7a tr\\u00ecnh duy\\u1ec7t\",\"C\":\"\\u0110\\u1ecba ch\\u1ec9 truy c\\u1eadp kh\\u00f4ng t\\u1ed3n t\\u1ea1i ho\\u1eb7c \\u0111\\u1ecba ch\\u1ec9 truy c\\u1eadp b\\u1ecb nh\\u00e0 qu\\u1ea3n tr\\u1ecb m\\u1ea1ng ng\\u0103n kh\\u00f4ng cho truy c\\u1eadp\",\"D\":\"\\u0110\\u1ecba ch\\u1ec9 truy c\\u1eadp l\\u00e0 m\\u1ed9t trang web c\\u00e1 nh\\u00e2n\",\"dapan\":\"C\"},{\"macauhoi\":\"UI05-082\",\"tencauhoi\":\"Mu\\u1ed1n ch\\u00e8n s\\u01a1 \\u0111\\u1ed3 t\\u1ed5 ch\\u1ee9c v\\u00e0o Slide ,ta th\\u1ef1c hi\\u1ec7n th\\u1ebf n\\u00e0o?\",\"A\":\"Tab Insert\\\\SmartArt\\\\ Picture, ch\\u1ecdn m\\u1ed9t d\\u1ea1ng s\\u01a1 \\u0111\\u1ed3 t\\u1ed5 ch\\u1ee9c ph\\u00f9 h\\u1ee3p\",\"B\":\"Tab Insert\\\\SmartArt\\\\ Hierarchy, ch\\u1ecdn m\\u1ed9t d\\u1ea1ng s\\u01a1 \\u0111\\u1ed3 t\\u1ed5 ch\\u1ee9c ph\\u00f9 h\\u1ee3p\",\"C\":\"Tab Insert\\\\Picture, sau \\u0111\\u00f3 ch\\u1ecdn s\\u01a1 \\u0111\\u1ed3 c\\u1ea7n ch\\u00e8n\",\"D\":\"Tab Insert\\\\Chart, ch\\u1ecdn m\\u1ed9t d\\u1ea1ng s\\u01a1 \\u0111\\u1ed3 t\\u1ed5 ch\\u1ee9c ph\\u00f9 h\\u1ee3p\",\"dapan\":\"B\"},{\"macauhoi\":\"UI03-106\",\"tencauhoi\":\"H\\u01b0\\u1edbng in m\\u1eb7c \\u0111\\u1ecbnh trong word l\\u00e0 h\\u01b0\\u1edbng g\\u00ec?\",\"A\":\"H\\u01b0\\u1edbng ngang\",\"B\":\"H\\u01b0\\u1edbng nghi\\u00eang 45 \\u0111\\u1ed9\",\"C\":\"H\\u01b0\\u1edbng k\\u1ebft h\\u1ee3p gi\\u1eefa d\\u1ecdc v\\u00e0 ngang\",\"D\":\"H\\u01b0\\u1edbng d\\u1ecdc\",\"dapan\":\"D\"},{\"macauhoi\":\"UI05-067\",\"tencauhoi\":\"Trong Powerpoint, \\u0111\\u00e1nh d\\u1ea5u 1 \\u0111o\\u1ea1n v\\u0103n b\\u1ea3n sau \\u0111\\u00f3 nh\\u1ea5n t\\u1ed5 h\\u1ee3p ph\\u00edm CTRL + E, thao t\\u00e1c n\\u00e0y t\\u01b0\\u01a1ng \\u1ee9ng v\\u1edbi l\\u1ef1a ch\\u1ecdn n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"Canh gi\\u1eefa \\u0111o\\u1ea1n v\\u0103n \\u0111\\u01b0\\u1ee3c ch\\u1ecdn\",\"B\":\"X\\u00f3a \\u0111o\\u1ea1n v\\u0103n \\u0111\\u01b0\\u1ee3c ch\\u1ecdn\",\"C\":\"Sao ch\\u00e9p \\u0111o\\u1ea1n v\\u0103n \\u0111\\u01b0\\u1ee3c ch\\u1ecdn\",\"D\":\"Canh tr\\u00e1i \\u0111o\\u1ea1n v\\u0103n \\u0111\\u01b0\\u1ee3c ch\\u1ecdn\",\"dapan\":\"A\"},{\"macauhoi\":\"UI03-120\",\"tencauhoi\":\"Font ch\\u1eef y\\u00eau c\\u1ea7u theo th\\u00f4ng t\\u01b0 01 v\\u1ec1 th\\u1ec3 th\\u1ee9c tr\\u00ecnh b\\u00e0y v\\u0103n b\\u1ea3n l\\u00e0?\",\"A\":\"Times New Roman\",\"B\":\"Arial\",\"C\":\"Tahoma\",\"D\":\"VNTime\",\"dapan\":\"A\"},{\"macauhoi\":\"UI04-059\",\"tencauhoi\":\"Trong Microsoft Excel, c\\u00f4ng th\\u1ee9c =MAX(30,10,65,5) nh\\u1eadn \\u0111\\u01b0\\u1ee3c k\\u1ebft qu\\u1ea3 l\\u00e0 bao nhi\\u00eau?\",\"A\":\"65\",\"B\":\"110\",\"C\":\"120\",\"D\":\"135\",\"dapan\":\"A\"},{\"macauhoi\":\"UI02-058\",\"tencauhoi\":\"Trong MS Windows, bi\\u1ec3u t\\u01b0\\u1ee3ng c\\u1ee7a th\\u01b0 m\\u1ee5c th\\u01b0\\u1eddng c\\u00f3 m\\u00e0u g\\u00ec?\",\"A\":\"V\\u00e0ng\",\"B\":\"Xanh\",\"C\":\"T\\u00edm\",\"D\":\"\\u0110\\u1ecf\",\"dapan\":\"A\"},{\"macauhoi\":\"UI01-094\",\"tencauhoi\":\"H\\u00e3y ch\\u1ecdn ph\\u01b0\\u01a1ng \\u00e1n \\u0111\\u00fang \\u0111\\u1ec3 \\u0111i\\u1ec1n v\\u00e0o d\\u1ea5u 3 ch\\u1ea5m: \\\"... l\\u00e0 m\\u1ed9t h\\u1ec7 th\\u1ed1ng th\\u00f4ng tin \\u0111\\u01b0\\u1ee3c s\\u1eed d\\u1ee5ng \\u0111\\u1ec3 t\\u1ea1o ra m\\u1ed9t ho\\u1eb7c nhi\\u1ec1u trang c\\u00f3 th\\u00f4ng tin \\u0111\\u01b0\\u1ee3c tr\\u00ecnh b\\u00e0y d\\u01b0\\u1edbi c\\u00e1c ch\\u1eef vi\\u1ebft, ch\\u1eef s\\u1ed1, h\\u00ecnh \\u1ea3nh, \\u00e2m thanh hay \\u0111\\u1eb7c bi\\u1ec7t h\\u01a1n l\\u00e0 c\\u00e1c k\\u00fd hi\\u1ec7u \\u0111\\u1ec3 ph\\u1ee5c v\\u1ee5 cho vi\\u1ec7c cung c\\u1ea5p th\\u00f4ng tin tr\\u00ean internet\\\"\",\"A\":\"Trang tin \\u0111i\\u1ec7n t\\u1eed\",\"B\":\"C\\u1ed5ng th\\u00f4ng tin \\u0111i\\u1ec7n t\\u1eed\",\"C\":\"B\\u00e1o\",\"D\":\"Tin n\\u1ed9i b\\u1ed9\",\"dapan\":\"A\"},{\"macauhoi\":\"UI05-046\",\"tencauhoi\":\"Trong Powerpoint 2010, ch\\u1ecdn nh\\u00f3m thao t\\u00e1c n\\u00e0o \\u0111\\u1ec3 t\\u1ea1o m\\u00e0u n\\u1ec1n cho to\\u00e0n b\\u1ed9 Slide ?\",\"A\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i v\\u00e0o Slide\\/Format Background\\/ ch\\u1ecdn m\\u00e0u trong Fill\\/ Close\",\"B\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i v\\u00e0o Slide\\/Format Background\\/ ch\\u1ecdn m\\u00e0u trong Fill\\/ Reset Background\",\"C\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i v\\u00e0o Slide\\/Format Background\\/ ch\\u1ecdn m\\u00e0u trong Fill\\/ Apply to All\\/ Close\",\"D\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i v\\u00e0o Slide\\/Format Background\\/ ch\\u1ecdn m\\u00e0u trong Fill\\/ Apply\",\"dapan\":\"C\"},{\"macauhoi\":\"UI01-049\",\"tencauhoi\":\"H\\u1ec7 th\\u1ed1ng n\\u00e0o l\\u00e0 t\\u1eadp h\\u1ee3p c\\u00e1c ch\\u01b0\\u01a1ng tr\\u00ecnh ph\\u1ea7n m\\u1ec1m ch\\u1ea1y tr\\u00ean m\\u00e1y t\\u00ednh, d\\u00f9ng \\u0111\\u1ec3\\u00a0\\u0111i\\u1ec1u h\\u00e0nh, qu\\u1ea3n l\\u00fd c\\u00e1c thi\\u1ebft b\\u1ecb ph\\u1ea7n c\\u1ee9ng v\\u00e0 t\\u00e0i nguy\\u00ean ph\\u1ea7n m\\u1ec1m tr\\u00ean m\\u00e1y t\\u00ednh?\",\"A\":\"Ph\\u1ea7n m\\u1ec1m \\u1ee9ng d\\u1ee5ng\",\"B\":\"H\\u1ec7 \\u0111i\\u1ec1u h\\u00e0nh\",\"C\":\"Ph\\u1ea7n c\\u1ee9ng\",\"D\":\"C\\u00e1c lo\\u1ea1i tr\\u00ecnh d\\u1ecbch trung gian\",\"dapan\":\"B\"},{\"macauhoi\":\"UI02-026\",\"tencauhoi\":\"Ph\\u1ea7n m\\u1ec1m n\\u00e0o d\\u01b0\\u1edbi \\u0111\\u00e2y n\\u1ebfu kh\\u00f4ng \\u0111\\u01b0\\u1ee3c c\\u00e0i \\u0111\\u1eb7t m\\u00e1y t\\u00ednh s\\u1ebd kh\\u00f4ng ho\\u1ea1t \\u0111\\u1ed9ng?\",\"A\":\"Chrome\",\"B\":\"Vietkey\",\"C\":\"Windows\",\"D\":\"Norton AntiVirus\",\"dapan\":\"C\"},{\"macauhoi\":\"UI01-025\",\"tencauhoi\":\"Ph\\u00e1t bi\\u1ec3u n\\u00e0o l\\u00e0 \\u0111\\u00fang khi n\\u00f3i \\u0111\\u1ebfn b\\u1ed9 nh\\u1edb RAM v\\u00e0 ROM??\",\"A\":\"B\\u1ed9 nh\\u1edb ROM th\\u01b0\\u1eddng \\u0111\\u01b0\\u1ee3c d\\u00f9ng b\\u1edfi c\\u00e1c ch\\u01b0\\u01a1ng tr\\u00ecnh \\u1ee9ng d\\u1ee5ng \\u0111\\u1ec3 l\\u01b0u tr\\u1eef t\\u1ea1m th\\u1eddi. B\\u1ed9 nh\\u1edb RAM \\u0111\\u01b0\\u1ee3c d\\u00f9ng \\u0111\\u1ec3 l\\u01b0u c\\u00e1c file d\\u1eef li\\u1ec7u\",\"B\":\"B\\u1ed9 nh\\u1edb RAM l\\u00e0 b\\u1ed9 nh\\u1edb \\u0111\\u1ecdc v\\u00e0 ghi, trong khi ROM l\\u00e0 b\\u1ed9 nh\\u1edb ch\\u1ec9 \\u0111\\u1ecdc\",\"C\":\"B\\u1ed9 nh\\u1edb RAM kh\\u00f4ng b\\u1ecb xo\\u00e1 khi m\\u00e1y t\\u00ednh \\u0111\\u00e3 t\\u1eaft. B\\u1ed9 nh\\u1edb ROM b\\u1ecb xo\\u00e1 khi m\\u00e1y t\\u00ednh t\\u1eaft\",\"D\":\"RAM v\\u00e0 ROM l\\u00e0 hai b\\u1ed9 nh\\u1edb truy c\\u1eadp ng\\u1eabu nhi\\u00ean\",\"dapan\":\"B\"},{\"macauhoi\":\"UI06-086\",\"tencauhoi\":\"\\u0110\\u1ec3 tr\\u00e1nh l\\u00e2y lan virus qua email, ng\\u01b0\\u1eddi d\\u00f9ng n\\u00ean l\\u00e0m g\\u00ec?\",\"A\":\"Qu\\u00e9t virus tr\\u01b0\\u1edbc khi m\\u1edf file g\\u1eafn k\\u00e8m trong email (1)\",\"B\":\"Kh\\u00f4ng m\\u1edf email c\\u1ee7a ng\\u01b0\\u1eddi l\\u1ea1 (2)\",\"C\":\"Kh\\u00f4ng t\\u1ea3i t\\u1eadp tin t\\u1eeb email khi kh\\u00f4ng c\\u1ea7n thi\\u1ebft (3)\",\"D\":\"T\\u1ea5t c\\u1ea3 \\u0111\\u00e1p \\u00e1n (1), (2) v\\u00e0 (3) \\u0111\\u1ec1u \\u0111\\u00fang\",\"dapan\":\"D\"},{\"macauhoi\":\"UI01-027\",\"tencauhoi\":\"\\u0110\\u00e2u l\\u00e0 thi\\u1ebft b\\u1ecb l\\u01b0u tr\\u1eef d\\u1eef li\\u1ec7u?\",\"A\":\"USB\",\"B\":\"M\\u00e1y in\",\"C\":\"Webcam\",\"D\":\"C\\u1ea7n \\u0111i\\u1ec3u khi\\u1ec3n\",\"dapan\":\"A\"},{\"macauhoi\":\"UI04-112\",\"tencauhoi\":\"Trong Microsoft Excel, c\\u00e1ch n\\u00e0o \\u0111\\u1ec3 m\\u1edf h\\u1ed9p tho\\u1ea1i thi\\u1ebft l\\u1eadp c\\u00e1c th\\u00f4ng s\\u1ed1 in b\\u1ea3ng t\\u00ednh?\",\"A\":\"Atl + P\",\"B\":\"Ctrl + P\",\"C\":\"Ctrl + Print Screen\\u00a0\",\"D\":\"Print Screen\\u00a0\",\"dapan\":\"B\"},{\"macauhoi\":\"UI04-066\",\"tencauhoi\":\"Trong b\\u1ea3ng t\\u00ednh MS Excel 2010, c\\u00f4ng th\\u1ee9c =Mod(26,7) cho k\\u1ebft qu\\u1ea3:\",\"A\":\"6\",\"B\":\"4\",\"C\":\"5\",\"D\":\"3\",\"dapan\":\"C\"},{\"macauhoi\":\"UI02-118\",\"tencauhoi\":\"\\u0110\\u1ec3 ch\\u1ecdn m\\u1ed9t m\\u00e1y in s\\u1eed d\\u1ee5ng th\\u01b0\\u1eddng xuy\\u00ean trong danh s\\u00e1ch c\\u00e1c m\\u00e1y in \\u0111\\u00e3 c\\u00e0i \\u0111\\u1eb7t, th\\u1ef1c hi\\u1ec7n thao t\\u00e1c n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i t\\u1ea1i bi\\u1ec3u t\\u01b0\\u1ee3ng m\\u00e1y in, ch\\u1ecdn Set as Default Printer\",\"B\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i t\\u1ea1i bi\\u1ec3u t\\u01b0\\u1ee3ng m\\u00e1y in, ch\\u1ecdn Use Printer\",\"C\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i t\\u1ea1i bi\\u1ec3u t\\u01b0\\u1ee3ng m\\u00e1y in, ch\\u1ecdn Always Use Printer\",\"D\":\"K\\u00edch chu\\u1ed9t ph\\u1ea3i t\\u1ea1i bi\\u1ec3u t\\u01b0\\u1ee3ng m\\u00e1y tin, ch\\u1ecdn Defaut Printer\",\"dapan\":\"A\"},{\"macauhoi\":\"UI03-019\",\"tencauhoi\":\"\\u0110\\u1ec3 s\\u1eafp x\\u1ebfp c\\u00e1c c\\u1eeda s\\u1ed5 v\\u0103n b\\u1ea3n tr\\u00ean m\\u00e0n h\\u00ecnh Word theo th\\u1ee9 t\\u1ef1 ta s\\u1eed d\\u1ee5ng Tab n\\u00e0o?\",\"A\":\"View\",\"B\":\"Home\",\"C\":\"File\",\"D\":\"Page Layout\",\"dapan\":\"A\"},{\"macauhoi\":\"UI05-001\",\"tencauhoi\":\"\\u0110\\u1ec3 m\\u1ed9t b\\u00e0i thuy\\u1ebft tr\\u00ecnh \\u0111\\u1ea1t hi\\u1ec7u qu\\u1ea3 cao, ch\\u00fang ta c\\u1ea7n quan t\\u00e2m t\\u1edbi v\\u1ea5n \\u0111\\u1ec1 n\\u00e0o \\u0111\\u1ea7u ti\\u00ean ?\",\"A\":\"\\u0110\\u1ed1i t\\u01b0\\u1ee3ng nghe b\\u00e0i thuy\\u1ebft tr\\u00ecnh\",\"B\":\"Ph\\u01b0\\u01a1ng ph\\u00e1p thuy\\u1ebft tr\\u00ecnh\",\"C\":\"N\\u1eafm v\\u1eefng n\\u1ed9i dung c\\u1ea7n thuy\\u1ebft tr\\u00ecnh\",\"D\":\"H\\u00ecnh th\\u1ee9c thuy\\u1ebft tr\\u00ecnh\",\"dapan\":\"C\"},{\"macauhoi\":\"UI04-074\",\"tencauhoi\":\"Trong Excel bi\\u1ec3u th\\u1ee9c =SUM(9,1,MAX(3,5,7)) s\\u1ebd tr\\u1ea3 v\\u1ec1 k\\u1ebft qu\\u1ea3 l\\u00e0 bao nhi\\u00eau?\",\"A\":\"13\",\"B\":\"15\",\"C\":\"19\",\"D\":\"17\",\"dapan\":\"D\"}]}";
        try {
            JSONObject jsonObject=new JSONObject(api);
            thoigian=jsonObject.getInt("thoigianthi");
            Log.d("thoigian",thoigian+"");
            JSONArray DATA=jsonObject.getJSONArray("data");
            for(int i=0;i<DATA.length();i++){
                JSONObject objectquestion=DATA.getJSONObject(i);
                String macauhoi=objectquestion.getString("macauhoi");
                String tencauhoi=objectquestion.getString("tencauhoi");
                String cauhoia=objectquestion.getString("A");
                String cauhoib=objectquestion.getString("B");
                String cauhoic=objectquestion.getString("C");
                String cauhoid=objectquestion.getString("D");
                String dapan=objectquestion.getString("dapan");
                Question question=new Question();
                RadioButton radioButtona=new RadioButton(getActivity());
                radioButtona.setText(cauhoia);
                RadioButton radioButtonb=new RadioButton(getActivity());
                radioButtonb.setText(cauhoib);
                RadioButton radioButtonc=new RadioButton(getActivity());
                radioButtonc.setText(cauhoic);
                RadioButton radioButtond=new RadioButton(getActivity());
                radioButtond.setText(cauhoid);
                questionArrayList.add(new Question(macauhoi,tencauhoi,radioButtona,radioButtonb,radioButtonc,radioButtond,dapan));
                stringArrayListdapan.add(dapan);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void chuyendoithoigian() {
        hou = "";
        min = "";
        sec = "";
        gio = thoigian / 3600;
        phut = (thoigian - 3600 * gio) / 60;
        giay = (thoigian - 3600 * gio - 60 * phut);
        if (gio < 10) {
            hou += "0" + gio;
        } else {
            hou += "" + gio;
        }
        if (phut < 10) {
            min += "0" + phut;
        } else {
            min += "" + phut;
        }
        if (giay < 10) {
            sec += "0" + giay;
        } else {
            sec += "" + giay;
        }
    }

    private void initDataFake() {
        for (int i = 0; i < questionArrayList.size(); i++) {
            questionArrayList.get(i).getRadioButtona().setChecked(false);
            questionArrayList.get(i).getRadioButtonb().setChecked(false);
            questionArrayList.get(i).getRadioButtonc().setChecked(false);
            questionArrayList.get(i).getRadioButtond().setChecked(false);
        }
    }

    private void batSuKien() {
        imgbtnLamBaiThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutlambaithi.openDrawer(GravityCompat.START);
            }
        });
        btnNopbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                ketQuaThiFragment = new KetQuaThiFragment();
                if(ketQuaThiFragment.isAdded()){
                    fragmentTransaction.show(ketQuaThiFragment);
                }
                else{
                    fragmentTransaction.add(R.id.framelayout, ketQuaThiFragment);
                    fragmentTransaction.addToBackStack("ketquathi");
                }
                if(DanhSachMonThiFragment.lamBaiThiFragment.isAdded()){
                    fragmentTransaction.hide(DanhSachMonThiFragment.lamBaiThiFragment);
                }
                rblambaithia.setEnabled(false);
                rblambaithib.setEnabled(false);
                rblambaithic.setEnabled(false);
                rblambaithid.setEnabled(false);
                fragmentTransaction.commit();
            }
        });
        btnlambaithicautiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kt = 0;
                stt++;
                if (stt >= questionArrayList.size()) {
                    stt = questionArrayList.size() - 1;
                } else {
                    kt++;
                    cauHoiKeTiep();
                    rblambaithia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(stringArrayListdapan.get(0).equals("A")){
                                tongdiem+=4;
                            }
                            questionArrayList.get(stt).getRadioButtona().setChecked(true);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                            questionArrayList.get(stt).getRadioButtond().setChecked(false);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "a");
                        }
                    });
                    rblambaithib.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(stringArrayListdapan.get(0).equals("B")){
                                tongdiem+=4;
                            }
                            questionArrayList.get(stt).getRadioButtona().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(true);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                            questionArrayList.get(stt).getRadioButtond().setChecked(false);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "b");
                        }
                    });
                    rblambaithic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(stringArrayListdapan.get(0).equals("C")){
                                tongdiem+=4;
                            }
                            questionArrayList.get(stt).getRadioButtona().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(true);
                            questionArrayList.get(stt).getRadioButtond().setChecked(false);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "c");
                        }
                    });
                    rblambaithid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(stringArrayListdapan.get(0).equals("D")){
                                tongdiem+=4;
                            }
                            questionArrayList.get(stt).getRadioButtona().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                            questionArrayList.get(stt).getRadioButtond().setChecked(true);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "d");
                        }
                    });
                }
            }
        });
        btnlambaithiquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kt = 0;
                stt--;
                if (stt < 0) {
                    stt = 0;
                } else {
                    kt++;
                    cauHoiTruoc();
                    rblambaithia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            questionArrayList.get(stt).getRadioButtona().setChecked(true);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                            questionArrayList.get(stt).getRadioButtond().setChecked(false);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "a");
                        }
                    });
                    rblambaithib.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            questionArrayList.get(stt).getRadioButtona().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(true);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                            questionArrayList.get(stt).getRadioButtond().setChecked(false);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "b");
                        }
                    });
                    rblambaithic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            questionArrayList.get(stt).getRadioButtona().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(true);
                            questionArrayList.get(stt).getRadioButtond().setChecked(false);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "c");
                        }
                    });
                    rblambaithid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            questionArrayList.get(stt).getRadioButtona().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonb().setChecked(false);
                            questionArrayList.get(stt).getRadioButtonc().setChecked(false);
                            questionArrayList.get(stt).getRadioButtond().setChecked(true);
                            soArrayList.get(stt).setMausac(getResources().getColor(R.color.mauxanh));
                            soAdapter.notifyDataSetChanged();
                            Log.d("kiemtra", "d");
                        }
                    });
                }
            }
        });
    }

    private void cauHoiTruoc() {
        rblambaithia.setChecked(questionArrayList.get(stt).getRadioButtona().isChecked());
        rblambaithib.setChecked(questionArrayList.get(stt).getRadioButtonb().isChecked());
        rblambaithic.setChecked(questionArrayList.get(stt).getRadioButtonc().isChecked());
        rblambaithid.setChecked(questionArrayList.get(stt).getRadioButtond().isChecked());
        txtlambaithimacauhoi.setText(questionArrayList.get(stt).getMacauhoi());
        txtlambaithicauhoi.setText(questionArrayList.get(stt).getTencauhoi());
        rblambaithia.setText("A. "+questionArrayList.get(stt).getRadioButtona().getText());
        rblambaithib.setText("B. "+questionArrayList.get(stt).getRadioButtonb().getText());
        rblambaithic.setText("C. "+questionArrayList.get(stt).getRadioButtonc().getText());
        rblambaithid.setText("D. "+questionArrayList.get(stt).getRadioButtond().getText());
    }

    private void cauHoiKeTiep() {
        rblambaithia.setChecked(questionArrayList.get(stt).getRadioButtona().isChecked());
        rblambaithib.setChecked(questionArrayList.get(stt).getRadioButtonb().isChecked());
        rblambaithic.setChecked(questionArrayList.get(stt).getRadioButtonc().isChecked());
        rblambaithid.setChecked(questionArrayList.get(stt).getRadioButtond().isChecked());
        int dem = stt + 1;
        txtlambaithimacauhoi.setText(questionArrayList.get(stt).getMacauhoi());
        txtlambaithicauhoi.setText(questionArrayList.get(stt).getTencauhoi());
        rblambaithia.setText("A. "+questionArrayList.get(stt).getRadioButtona().getText());
        rblambaithib.setText("B. "+questionArrayList.get(stt).getRadioButtonb().getText());
        rblambaithic.setText("C. "+questionArrayList.get(stt).getRadioButtonc().getText());
        rblambaithid.setText("D. "+questionArrayList.get(stt).getRadioButtond().getText());
    }

    private void anhXa() {
        stringArrayListdapan=new ArrayList<>();
        txtlambaithingaysinh=view.findViewById(R.id.txt_lambaithingaysinh);
        txtlambaithihoten=view.findViewById(R.id.txt_lambaithihoten);
        txtlambaithisobaodanh=view.findViewById(R.id.txt_lambaithisobaodanh);
        questionArrayList=new ArrayList<>();
        txtlambaithithoigian = view.findViewById(R.id.txt_lambaithithoigian);
        soArrayList = new ArrayList<>();
        txtlambaithichuthich = view.findViewById(R.id.txt_lambaithi_chuthich);
        txtlambaithicauhoi = view.findViewById(R.id.txt_lambaithi_cauhoi);
        txtlambaithimacauhoi = view.findViewById(R.id.txt_lambaithi_macauhoi);
        rblambaithia = view.findViewById(R.id.rb_lambaithi_a);
        rblambaithib = view.findViewById(R.id.rb_lambaithi_b);
        rblambaithic = view.findViewById(R.id.rb_lambaithi_c);
        rblambaithid = view.findViewById(R.id.rb_lambaithi_d);
        btnlambaithicautiep = view.findViewById(R.id.btn_lambaithi_cautiep);
        btnlambaithiquaylai = view.findViewById(R.id.btn_lambaithi_quaylai);
        btnNopbai = view.findViewById(R.id.btn_nopbai);
        loaiMenuArrayList = new ArrayList<>();
        imgbtnLamBaiThi = view.findViewById(R.id.imgbtn_lambaithi);
        drawerLayoutlambaithi = view.findViewById(R.id.drawerlayout_lambaithi);
        navigationView = view.findViewById(R.id.navigationview_lambaithi);
        questionArrayList = new ArrayList<>();
        radioGroup=view.findViewById(R.id.radio);
        gridViewlambaithi = view.findViewById(R.id.gridview_lambaithi);
        cauHoiArrayList=new ArrayList<>();
        datum1ArrayList=new ArrayList<>();
    }

}
