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
    int thoigian;
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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getDuLieu();
        //mảng questionArrayList không có dữ liệu ạ mà bên trong onResponse của Retrofit thì lại có dữ liệu là 25 đối tượng
        //em muốn mảng questionArrayList bên ngoài onResponse của Retrofit vẫn có được dữ liệu thì làm như thế nào vậy anh

//        thoigian *=60;
//        chuyendoithoigian();
//        txtlambaithithoigian.setText(hou + ":" + min + ":" + sec);
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                thoigian -= 1;
//                if (thoigian > 0) {
//                    chuyendoithoigian();
//                    txtlambaithithoigian.setText(hou + ":" + min + ":" + sec);
//                    handler.postDelayed(this, 1000);
//                } else {
//                    if(getActivity()!=null){
//                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        ketQuaThiFragment = new KetQuaThiFragment();
//                        if(ketQuaThiFragment.isAdded()){
//                            Log.d("chandoilamco","hhh");
//                            fragmentTransaction.show(ketQuaThiFragment);
//                        }
//                        else{
//                            fragmentTransaction.add(R.id.framelayout, ketQuaThiFragment);
//                            fragmentTransaction.addToBackStack("ketquathi");
//                        }
//                        if(DanhSachMonThiFragment.lamBaiThiFragment.isAdded()){
//                            fragmentTransaction.hide(DanhSachMonThiFragment.lamBaiThiFragment);
//                        }
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        }, 1000);
//        for (int i = 1; i <= 25; i++) {
//            soArrayList.add(new So(i + "", getResources().getColor(R.color.mauden)));
//        }
//        soAdapter = new SoAdapter(getActivity(), R.layout.dong_gridview, soArrayList);
//        //em demo roi anh
//        gridViewlambaithi.setAdapter(soAdapter);
//        initDataFake();
//        final Handler handler1 = new Handler();
//        handler1.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (laco == 1){
//                    handler1.removeCallbacks(this);
//                    txtlambaithimacauhoi.setText(questionArrayList.get(0).getMacauhoi());
//                    txtlambaithicauhoi.setText(questionArrayList.get(0).getTencauhoi());
//                    rblambaithia.setText("A. "+questionArrayList.get(0).getRadioButtona().getText());
//                    rblambaithib.setText("B. "+questionArrayList.get(0).getRadioButtonb().getText());
//                    rblambaithic.setText("C. "+questionArrayList.get(0).getRadioButtonc().getText());
//                    rblambaithid.setText("D. "+questionArrayList.get(0).getRadioButtond().getText());
//                }else {
//                    handler1.postDelayed(this,100);
//                }
//            }
//        },200);
////khó
//        rblambaithia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(stringArrayListdapan.get(0).equals("A")){
//                    tongdiem+=4;
//                }
//                questionArrayList.get(stt).getRadioButtona().setChecked(true);
//                questionArrayList.get(stt).getRadioButtonb().setChecked(false);
//                questionArrayList.get(stt).getRadioButtonc().setChecked(false);
//                questionArrayList.get(stt).getRadioButtond().setChecked(false);
//                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
//                soAdapter.notifyDataSetChanged();
//                Log.d("kiemtra", "a");
//            }
//        });
//        rblambaithib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(stringArrayListdapan.get(0).equals("B")){
//                    tongdiem+=4;
//                }
//                questionArrayList.get(stt).getRadioButtona().setChecked(false);
//                questionArrayList.get(stt).getRadioButtonb().setChecked(true);
//                questionArrayList.get(stt).getRadioButtonc().setChecked(false);
//                questionArrayList.get(stt).getRadioButtond().setChecked(false);
//                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
//                soAdapter.notifyDataSetChanged();
//                Log.d("kiemtra", "b");
//            }
//        });
//        rblambaithic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(stringArrayListdapan.get(0).equals("C")){
//                    tongdiem+=4;
//                }
//                questionArrayList.get(stt).getRadioButtona().setChecked(false);
//                questionArrayList.get(stt).getRadioButtonb().setChecked(false);
//                questionArrayList.get(stt).getRadioButtonc().setChecked(true);
//                questionArrayList.get(stt).getRadioButtond().setChecked(false);
//                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
//                soAdapter.notifyDataSetChanged();
//                Log.d("kiemtra", "c");
//            }
//        });
//        rblambaithid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(stringArrayListdapan.get(0).equals("D")){
//                    tongdiem+=4;
//                }
//                questionArrayList.get(stt).getRadioButtona().setChecked(false);
//                questionArrayList.get(stt).getRadioButtonb().setChecked(false);
//                questionArrayList.get(stt).getRadioButtonc().setChecked(false);
//                questionArrayList.get(stt).getRadioButtond().setChecked(true);
//                soArrayList.get(0).setMausac(getResources().getColor(R.color.mauxanh));
//                soAdapter.notifyDataSetChanged();
//                Log.d("kiemtra", "d");
//            }
//        });
//        txtlambaithichuthich.setPaintFlags(txtlambaithichuthich.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        batSuKien();
//        txtlambaithihoten.setText(LoginActivity.name);
//        txtlambaithisobaodanh.setText("SBD:"+LoginActivity.edtsobaodanh.getText().toString());
//        txtlambaithingaysinh.setText("Ngày sinh:"+LoginActivity.ngaysinh);
        super.onActivityCreated(savedInstanceState);
    }

    private void getDuLieu() {
        Call<DanhSachCauHoi>danhSachCauHoiCall= RestClient.getAPIs().getdanhsachcauhoi(LoginActivity.sobaodanh,DanhSachMonThiFragment.mamon);
        danhSachCauHoiCall.enqueue(new Callback<DanhSachCauHoi>() {
            @Override
            public void onResponse(Call<DanhSachCauHoi> call, Response<DanhSachCauHoi> response) {
                DanhSachCauHoi danhSachCauHoi=response.body();
                datum1ArrayList= (ArrayList<Datum1>) danhSachCauHoi.getData();
                for(int i=0;i<datum1ArrayList.size();i++) {
                    String macauhoi=datum1ArrayList.get(i).getMacauhoi();
                    String tencauhoi=datum1ArrayList.get(i).getTencauhoi();
                    String cauhoia=datum1ArrayList.get(i).getA();
                    String cauhoib=datum1ArrayList.get(i).getB();
                    String cauhoic=datum1ArrayList.get(i).getC();
                    String cauhoid=datum1ArrayList.get(i).getD();
                    String dapan=datum1ArrayList.get(i).getDapan();

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
                for (int i = 0 ; i< questionArrayList.size() ; i++){
                    Log.d("BBB","Dap an " + questionArrayList.get(i).getDapan());
                    Log.d("BBB","Macauhoi " + questionArrayList.get(i).getMacauhoi());
                    Log.d("BBB","Tencauhoi " + questionArrayList.get(i).getTencauhoi());
                    Log.d("BBB","Buttona " + questionArrayList.get(i).getRadioButtona());
                    Log.d("BBB","Buttonb " + questionArrayList.get(i).getRadioButtonb());
                    Log.d("BBB","Buttonc " + questionArrayList.get(i).getRadioButtonc());
                    Log.d("BBB","Buttond " + questionArrayList.get(i).getRadioButtond());

                }
                //Cai nay ban phai tim hieu ve RxJava trong retrofit
                //nó có tác dụng lấy dữ liệu bên ngoài retrofit ạ ch// cho em ví dụ là ví dụ em lấy được 25 đối tượng bên trong response thì bên ngoài response em vẫn có 25 đối tượng đún gkhoong anh đấy là tác dụng của rxxjava ạ ?
                //Khog phai do thằng retrofit no gọi onrespone 3 lần nhưng giá trị bạn chỉ gán lại 1 lần nên giá trị này chỉ hiểu đx 1 thằng rxjava nó sẽ cập nhật dữ liệu liên tục cho đối tượng khi có dữ liệu mới như bạn thấy cái log.d thấy thời gian nó gọi log ko
                //No goi lien tuc 3 lan trong log co nghia response dx goi lai 3 lan nhung gia tri ban chi gan 1 lan nen ko thuc thi được tình trạng này em sắp bị tống rồi thôi cảm ơn anh ạ

                //mảng questionArrayList bên trong onResponse thì có dữ liệu 25 đối tượng nhưng bên ngoài thì không có đối tượng nào ạ
                Toast.makeText(getActivity(), questionArrayList.size()+"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<DanhSachCauHoi> call, Throwable t) {

            }
        });

    }

    private void chuyendoithoigian() {
        //em xử lý thời gian 30 phút đúng rồi ạ do retrofit ạ
        //Cai nay phuong thuc xu ly rieng dau anh huong du lieu retrofit dau ban
        //giờ em tạo dữ liệu để demo ạ anh xem ạ

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
