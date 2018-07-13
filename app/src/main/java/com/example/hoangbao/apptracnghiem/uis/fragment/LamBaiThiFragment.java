package com.example.hoangbao.apptracnghiem.uis.fragment;

import android.content.Intent;
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
import com.example.hoangbao.apptracnghiem.adapter.QuestionAdapter;
import com.example.hoangbao.apptracnghiem.adapter.SoAdapter;
import com.example.hoangbao.apptracnghiem.model.ExamListRespone;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;
import com.example.hoangbao.apptracnghiem.model.LoaiMonThi;
import com.example.hoangbao.apptracnghiem.model.Question;
import com.example.hoangbao.apptracnghiem.model.So;
import com.example.hoangbao.apptracnghiem.rest.RestClient;
import com.example.hoangbao.apptracnghiem.uis.activity.LoginActivity;
import com.example.hoangbao.apptracnghiem.uis.fragment.KetQuaThiFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LamBaiThiFragment extends Fragment {
    public static Calendar calendar;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lam_bai_thi, container, false);
        anhXa();
//        retrofit2.Call<LoaiMonThi> callLoaiMonThi= RestClient.getAPIs().daymamonlenserver(DanhSachMonThiFragment.mamon);
//        callLoaiMonThi.enqueue(new Callback<LoaiMonThi>() {
//            @Override
//            public void onResponse(Call<LoaiMonThi> call, Response<LoaiMonThi> response) {
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<LoaiMonThi> call, Throwable t) {
//                Toast.makeText(getActivity(), "loi", Toast.LENGTH_SHORT).show();
//            }
//        });
        getDuLieu();
        Log.d("mamon",DanhSachMonThiFragment.mamon);
        Log.d("kiemtra",LoginActivity.sobaodanh);
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
                        KetQuaThiFragment ketQuaThiFragment = new KetQuaThiFragment();
                        fragmentTransaction.replace(R.id.framelayout, ketQuaThiFragment);
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
        rblambaithic.setText("C."+questionArrayList.get(0).getRadioButtonc().getText());
        rblambaithid.setText("D."+questionArrayList.get(0).getRadioButtond().getText());
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
        return view;
    }

    private void getDuLieu() {
//        String url="http://14.160.93.98:8672/danhsachcauhoi.php";
//        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String>hashMap=new HashMap<>();
//                hashMap.put("mamon",DanhSachMonThiFragment.mamon);
//                return hashMap;
//            }
//        };
//        requestQueue.add(stringRequest);
        String s="{\"code\":200,\"message\":\"Success\",\"socau\":25,\"thoigianthi\":30,\"data\":[{\"macauhoi\":\"UI04-087\",\"tencauhoi\":\"Bi\\u1ec3u th\\u1ee9c sau cho k\\u1ebft qu\\u1ea3 l\\u00e0 bao nhi\\u00eau n\\u1ebfu DTB=9?\\r\\n=If(DTB>=5,\\\"TB\\\",If(DTB>=6.5,\\\"Kha\\\",If(DTB>=8,\\\"Gioi\\\",\\\"Yeu\\\")))\",\"A\":\"TB\",\"B\":\"Gioi\",\"C\":\"Kha\",\"D\":\"Yeu\",\"dapan\":\"B\"},{\"macauhoi\":\"UI05-055\",\"tencauhoi\":\"Trong Powerpoint 2010, \\u0111\\u1ec3 th\\u00eam 1 slide gi\\u1ed1ng slide \\u0111\\u01b0\\u1ee3c ch\\u1ecdn ta d\\u00f9ng t\\u1ed1 h\\u1ee3p ph\\u00edm l\\u1ec7nh n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"ALT + H + E + D\",\"B\":\"ALT + H + I + D\",\"C\":\"ALT + H + D + I\",\"D\":\"ALT + H + E + I\",\"dapan\":\"B\"},{\"macauhoi\":\"UI04-049\",\"tencauhoi\":\"Trong Microsoft Excel, t\\u1ea1i \\u00f4 A2 c\\u00f3 gi\\u00e1 tr\\u1ecb l\\u00e0 s\\u1ed1 kh\\u00f4ng (0); T\\u1ea1i \\u00f4 B2 g\\u00f5 v\\u00e0o c\\u00f4ng th\\u1ee9c =5\\/A2 nh\\u1eadn \\u0111\\u01b0\\u1ee3c k\\u1ebft qu\\u1ea3 n\\u00e0o?\",\"A\":\"#VALUE!\",\"B\":\"#DIV\\/0!\",\"C\":\"0\",\"D\":\"#NAME?\",\"dapan\":\"B\"},{\"macauhoi\":\"UI05-117\",\"tencauhoi\":\"Trong Powerpoint 2010, c\\u00e1ch n\\u00e0o d\\u00f9ng \\u0111\\u1ec3 tr\\u00ecnh chi\\u1ebfu Slide?\",\"A\":\" View\\/ From Beginning\",\"B\":\" Insert\\/ From Beginning\",\"C\":\" File\\/ From Beginning\",\"D\":\" Slide Show\\/ From Beginning\",\"dapan\":\"D\"},{\"macauhoi\":\"UI03-081\",\"tencauhoi\":\"\\u0110\\u1ec3 \\u00e1p d\\u1ee5ng Style cho m\\u1ed9t \\u0111o\\u1ea1n v\\u0103n b\\u1ea3n b\\u1ea5t k\\u00ec ta th\\u1ef1c hi\\u1ec7n nh\\u01b0 sau: \",\"A\":\"Ch\\u1ecdn \\u0111o\\u1ea1n v\\u0103n b\\u1ea3n c\\u1ea7n \\u00e1p d\\u1ee5ng Style. V\\u00e0o Tab View\\/ Trong nh\\u00f3m Styles\\/ ch\\u1ecdn Style mu\\u1ed1n \\u00e1p d\\u1ee5ng\",\"B\":\"Ch\\u1ecdn \\u0111o\\u1ea1n v\\u0103n b\\u1ea3n c\\u1ea7n \\u00e1p d\\u1ee5ng Style. V\\u00e0o Tab Review\\/ Trong nh\\u00f3m Styles\\/ ch\\u1ecdn Style mu\\u1ed1n \\u00e1p d\\u1ee5ng\",\"C\":\"Ch\\u1ecdn \\u0111o\\u1ea1n v\\u0103n b\\u1ea3n c\\u1ea7n \\u00e1p d\\u1ee5ng Style. V\\u00e0o Tab Home\\/ Trong nh\\u00f3m Styles\\/ ch\\u1ecdn Style mu\\u1ed1n \\u00e1p d\\u1ee5ng\",\"D\":\"Ch\\u1ecdn \\u0111o\\u1ea1n v\\u0103n b\\u1ea3n c\\u1ea7n \\u00e1p d\\u1ee5ng Style. V\\u00e0o Tab Insert\\/ Trong nh\\u00f3m Styles\\/ ch\\u1ecdn Style mu\\u1ed1n \\u00e1p d\\u1ee5ng\",\"dapan\":\"C\"},{\"macauhoi\":\"UI04-031\",\"tencauhoi\":\"\\u0110\\u1ec3 xo\\u00e1 m\\u1ed9t c\\u1ed9t, b\\u00f4i \\u0111en c\\u1ed9t v\\u00e0 ch\\u1ecdn?\",\"A\":\"Insert\\\\Delete\\\\Delete Sheet Rows \",\"B\":\"Home\\\\Delete\\\\Delete Sheet Columns\",\"C\":\"Insert\\\\Delete\\\\Worksheet\",\"D\":\"Home\\\\Delete\\\\Clear\",\"dapan\":\"B\"},{\"macauhoi\":\"UI06-097\",\"tencauhoi\":\"Mu\\u1ed1n m\\u1edf h\\u1ed9p th\\u01b0 \\u0111\\u00e3 l\\u1eadp b\\u1ea1n ph\\u1ea3i l\\u00e0m g\\u00ec?\",\"A\":\"\\u0110\\u0103ng k\\u00fd m\\u1ed9t t\\u00e0i kho\\u1ea3n th\\u01b0 \\u0111i\\u1ec7n t\\u1eed\",\"B\":\"C\\u00f3 ch\\u01b0\\u01a1ng tr\\u00ecnh nh\\u1eadn\\/g\\u1eedi th\\u01b0 \\u0111i\\u1ec7n t\\u1eed\",\"C\":\"Cung c\\u1ea5p t\\u00e0i kho\\u1ea3n s\\u1eed d\\u1ee5ng Internet\",\"D\":\"Cung c\\u1ea5p ch\\u00ednh x\\u00e1c User v\\u00e0 Password \\u0111\\u00e3 \\u0111\\u0103ng k\\u00fd cho m\\u00e1y ch\\u1ee7 th\\u01b0 \\u0111i\\u1ec7n t\\u1eed\",\"dapan\":\"D\"},{\"macauhoi\":\"UI03-052\",\"tencauhoi\":\"Trong Micrsoft Word \\u0111\\u1ec3 t\\u0103ng c\\u1ee1 ch\\u1eef ta s\\u1eed d\\u1ee5ng t\\u1ed5 h\\u1ee3p ph\\u00edm\",\"A\":\"Ctrl + B\",\"B\":\"Ctrl + Shitf + ]\",\"C\":\"Ctrl + ]\",\"D\":\"Ctrl + [\",\"dapan\":\"C\"},{\"macauhoi\":\"UI01-117\",\"tencauhoi\":\"Khi m\\u1ed9t c\\u01a1 quan mua m\\u1ed9t ch\\u01b0\\u01a1ng tr\\u00ecnh m\\u00e1y t\\u00ednh, trong nh\\u1eefng tr\\u01b0\\u1eddng h\\u1ee3p n\\u00e0o ch\\u01b0\\u01a1ng tr\\u00ecnh n\\u00e0y c\\u00f3 th\\u1ec3 \\u0111\\u01b0\\u1ee3c c\\u00e0i \\u0111\\u1eb7t mi\\u1ec5n ph\\u00ed trong c\\u01a1 quan \\u0111\\u00f3?\",\"A\":\"Khi c\\u01a1 quan \\u0111\\u00f3 c\\u00f3 d\\u01b0\\u1edbi 10 nh\\u00e2n vi\\u00ean\",\"B\":\"Khi ch\\u01b0\\u01a1ng tr\\u00ecnh n\\u00e0y c\\u00f3 gi\\u00e1 d\\u01b0\\u1edbi 100 ngh\\u00ecn \\u0111\\u1ed3ng\",\"C\":\"Khi c\\u00f3 s\\u1ef1 \\u0111\\u1ed3ng \\u00fd b\\u1ea3n quy\\u1ec1n cho ph\\u00e9p \\u0111i\\u1ec1u n\\u00e0y\",\"D\":\"Khi n\\u00f3 \\u0111\\u01b0\\u1ee3c s\\u1eed d\\u1ee5ng t\\u1ed1i \\u0111a cho 3 m\\u00e1y t\\u00ednh m\\u1ed9t l\\u00fac\",\"dapan\":\"C\"},{\"macauhoi\":\"UI04-016\",\"tencauhoi\":\"Trong MS Excel 2010, gi\\u1ea3 s\\u1eed t\\u1ea1i \\u00f4 B5 c\\u00f3 c\\u00f4ng th\\u1ee9c = A1+$B$2+C$3. Sau khi sao ch\\u00e9p c\\u00f4ng th\\u1ee9c \\u0111\\u00f3 t\\u1eeb \\u00f4 B5 \\u0111\\u1ebfn \\u00f4 D8 s\\u1ebd c\\u00f3 c\\u00f4ng th\\u1ee9c:\",\"A\":\"= C1 + $B$2 + E$3\",\"B\":\"= A4 + $B$2 + C$3\",\"C\":\"B\\u00e1o l\\u1ed7i\",\"D\":\"= C4 + $B$2 + E$3\",\"dapan\":\"D\"},{\"macauhoi\":\"UI06-032\",\"tencauhoi\":\"Trong Internet Explorer phi\\u00ean b\\u1ea3n 7 ho\\u1eb7c m\\u1edbi h\\u01a1n, ph\\u00edm t\\u1eaft \\u0111\\u1ec3 th\\u00eam m\\u1ed9t Tab?\",\"A\":\"Ctrl+N\",\"B\":\"Alt+N\",\"C\":\"Alt+H\",\"D\":\"Ctrl+T\",\"dapan\":\"D\"},{\"macauhoi\":\"UI01-022\",\"tencauhoi\":\"C\\u00e1c thi\\u1ebft b\\u1ecb n\\u00e0o c\\u00f3 th\\u1ec3 thi\\u1ebfu trong m\\u1ed9t b\\u1ed9 m\\u00e1y t\\u00ednh?\",\"A\":\"\\u1ed4 \\u0111\\u0129a m\\u1ec1m\",\"B\":\"B\\u1ed9 ngu\\u1ed3n\",\"C\":\"B\\u1ed9 nh\\u1edb RAM\",\"D\":\"M\\u00e0n h\\u00ecnh\",\"dapan\":\"A\"},{\"macauhoi\":\"UI05-107\",\"tencauhoi\":\"Trong PowerPoint 2010, \\u0111\\u1ec3 th\\u00eam ph\\u1ea7n ghi ch\\u00fa cho trang thuy\\u1ebft tr\\u00ecnh, ta s\\u1eed d\\u1ee5ng ki\\u1ec3u hi\\u1ec3n th\\u1ecb n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"Notes Page v\\u00e0 Normal\",\"B\":\"Notes Page v\\u00e0 Slide Sorter\",\"C\":\"Notes Page v\\u00e0 Reading View\",\"D\":\"Slide Sorter v\\u00e0 Normal\",\"dapan\":\"A\"},{\"macauhoi\":\"UI03-114\",\"tencauhoi\":\"\\u0110\\u1ec3 xem t\\u00e0i li\\u1ec7u tr\\u01b0\\u1edbc khi in, ta ch\\u1ecdn l\\u1ec7nh\",\"A\":\"Print Preview\",\"B\":\"Print Demo\",\"C\":\"Print Layout\",\"D\":\"Print Test\",\"dapan\":\"A\"},{\"macauhoi\":\"UI02-085\",\"tencauhoi\":\"Trong h\\u1ec7 \\u0111i\\u1ec1u h\\u00e0nh Windows, ph\\u1ea3i nh\\u1ea5n gi\\u1eef ph\\u00edm n\\u00e0o khi ch\\u1ecdn nhi\\u1ec1u t\\u1ec7p ho\\u1eb7c th\\u01b0 m\\u1ee5c kh\\u00f4ng li\\u1ec1n nhau?\",\"A\":\"Ctrl\",\"B\":\"Alt\",\"C\":\"Shift\",\"D\":\"Enter\",\"dapan\":\"A\"},{\"macauhoi\":\"UI02-120\",\"tencauhoi\":\"\\u0110\\u1ec3 in m\\u1ed9t t\\u1ec7p th\\u00ec nh\\u1ea5n t\\u1ed5 h\\u1ee3p ph\\u00edm n\\u00e0o?\",\"A\":\"Ctrl+P\",\"B\":\"Ctrl+R\",\"C\":\"Ctrl+I\",\"D\":\"Ctrl+Shift+F11\",\"dapan\":\"A\"},{\"macauhoi\":\"UI01-102\",\"tencauhoi\":\"\\u0110\\u00e2u l\\u00e0 v\\u00ed d\\u1ee5 c\\u1ee7a m\\u1eadt kh\\u1ea9u t\\u1ed1t?\",\"A\":\"Ch\\u1ecdn m\\u1ed9t m\\u1eadt kh\\u1ea9u c\\u00f3 \\u00edt h\\u01a1n 6 k\\u00fd t\\u1ef1\",\"B\":\"M\\u1eadt kh\\u1ea9u nhi\\u1ec1u h\\u01a1n 7 k\\u00fd t\\u1ef1 g\\u1ed3m c\\u1ea3 ch\\u1eef hoa, s\\u1ed1, k\\u00fd t\\u1ef1 \\u0111\\u1eb7c bi\\u1ec7t\",\"C\":\"M\\u1eadt kh\\u1ea9u ph\\u1ea3i gi\\u1ed1ng l\\u00fd l\\u1ecbch ng\\u01b0\\u1eddi s\\u1eed d\\u1ee5ng (ng\\u00e0y th\\u00e1ng n\\u0103m sinh)\",\"D\":\"M\\u1eadt kh\\u1ea9u ch\\u1ec9 g\\u1ed3m ch\\u1eef ho\\u1eb7c s\\u1ed1\",\"dapan\":\"B\"},{\"macauhoi\":\"UI01-030\",\"tencauhoi\":\"M\\u00e1y Scanner \\u0111\\u01b0\\u1ee3c k\\u1ebft n\\u1ed1i v\\u1edbi m\\u00e1y t\\u00ednh th\\u00f4ng qua c\\u1ed5ng n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"RJ 45\",\"B\":\"Jack 3.5\",\"C\":\"RJ 11\",\"D\":\"USB\",\"dapan\":\"D\"},{\"macauhoi\":\"UI05-003\",\"tencauhoi\":\"Trong Powerpoint 2010, ph\\u1ea7n m\\u1edf r\\u1ed9ng c\\u1ee7a t\\u00ean file l\\u00e0 nh\\u00f3m k\\u00fd t\\u1ef1 n\\u00e0o?\",\"A\":\"ppts\",\"B\":\"ppt\",\"C\":\"pptt\",\"D\":\"pptx\",\"dapan\":\"D\"},{\"macauhoi\":\"UI03-075\",\"tencauhoi\":\"\\u0110\\u1ec3 \\u0111\\u00e1nh d\\u1ea5u \\u0111\\u1ec1 m\\u1ee5c, ta th\\u1ef1c hi\\u1ec7n thao t\\u00e1c\",\"A\":\"Tab Home\\/ nh\\u00f3m Font\\/ Numbering\",\"B\":\"Tab Home\\/ nh\\u00f3m Font\\/ Bullets\",\"C\":\"Tab Home\\/ nh\\u00f3m Paragraph\\/ Bullets\",\"D\":\"Tab Home\\/ nh\\u00f3m Clipboard\\/ Bullets\",\"dapan\":\"C\"},{\"macauhoi\":\"UI04-093\",\"tencauhoi\":\"H\\u00e3y cho bi\\u1ebft h\\u00e0m d\\u00f2 t\\u00ecm l\\u00e0 h\\u00e0m n\\u00e0o?\",\"A\":\"Product\",\"B\":\"Sumif\",\"C\":\"Counta\",\"D\":\"Vlookup\",\"dapan\":\"D\"},{\"macauhoi\":\"UI02-030\",\"tencauhoi\":\"\\u0110\\u1ec3 g\\u1ee1 b\\u1ecf m\\u1ed9t m\\u00e1y in \\u0111\\u00e3 c\\u00e0i \\u0111\\u1eb7t, ta th\\u1ef1c hi\\u1ec7n theo c\\u00e1ch n\\u00e0o sau \\u0111\\u00e2y?\",\"A\":\"Ch\\u1ecdn Add\\/Remove Hardware sau \\u0111\\u00f3 ch\\u1ecdn bi\\u1ec3u t\\u01b0\\u1ee3ng m\\u00e1y in c\\u1ea7n g\\u1ee1 b\\u1ecf\",\"B\":\"Ch\\u1ecdn Device Manager sau \\u0111\\u00f3 ch\\u1ecdn bi\\u1ec3u t\\u01b0\\u1ee3ng m\\u00e1y in c\\u1ea7n g\\u1ee1 b\\u1ecf\",\"C\":\"T\\u1ea1i c\\u1eeda s\\u1ed5 Printers and Faxes, nh\\u1ea5p ph\\u1ea3i chu\\u1ed9t v\\u00e0o m\\u00e1y in v\\u00e0 ch\\u1ecdn Remove Device\",\"D\":\"G\\u1ee1 b\\u1ecf m\\u00e1y tin trong Control Panenl\",\"dapan\":\"C\"},{\"macauhoi\":\"UI06-115\",\"tencauhoi\":\"N\\u01a1i c\\u1ed9ng \\u0111\\u1ed3ng m\\u1ea1ng th\\u01b0\\u1eddng trao \\u0111\\u1ed5i g\\u1ecdi l\\u00e0 g\\u00ec?\",\"A\":\"Forum\",\"B\":\"Blog\",\"C\":\"Community\",\"D\":\"Portal\",\"dapan\":\"A\"},{\"macauhoi\":\"UI02-091\",\"tencauhoi\":\"Ph\\u00e1t bi\\u1ec3u n\\u00e0o v\\u1ec1 ch\\u1ee9c n\\u0103ng c\\u1ee7a th\\u00f9ng r\\u00e1c (Recycle Bin) l\\u00e0 \\u0111\\u00fang?\",\"A\":\"Ch\\u1ec9 l\\u01b0u c\\u00e1c t\\u1ec7p tin b\\u1ecb xo\\u00e1 t\\u1eeb \\u1ed5 \\u0111\\u0129a c\\u1ee9ng\",\"B\":\"L\\u01b0u tr\\u1eef t\\u1ea5t c\\u1ea3 c\\u00e1c t\\u1ec7p b\\u1ecb xo\\u00e1 b\\u1edfi ng\\u01b0\\u1eddi d\\u00f9ng\",\"C\":\"Ch\\u1ec9 l\\u01b0u tr\\u1eef c\\u00e1c t\\u1ec7p tin b\\u1ecb xo\\u00e1 t\\u1eeb \\u1ed5 c\\u1ee9ng v\\u00e0 c\\u00e1c thi\\u1ebft b\\u1ecb l\\u01b0u tr\\u1eef di \\u0111\\u1ed9ng\",\"D\":\"L\\u01b0u c\\u00e1c t\\u1ec7p tin b\\u1ecb xo\\u00e1 t\\u1eeb \\u1ed5 c\\u1ee9ng v\\u00e0 \\u1ed5 \\u0111\\u0129a m\\u1ea1ng \\u0111\\u01b0\\u1ee3c \\u0111\\u1eb7t \\u1edf n\\u01a1i kh\\u00e1c\",\"dapan\":\"A\"},{\"macauhoi\":\"UI06-080\",\"tencauhoi\":\"\\u0110\\u1ecba ch\\u1ec9 n\\u00e0o kh\\u00f4ng ph\\u1ea3i l\\u00e0 \\u0111\\u1ecba ch\\u1ec9 email?\",\"A\":\"ninhbinh@yahoo.com\",\"B\":\"vnexpress.net\",\"C\":\"tranvana@gmail.com\",\"D\":\"nguyenvanb@hanoi.gov.vn\",\"dapan\":\"B\"}]}";
        try {
            JSONObject jsonObject=new JSONObject(s);
            thoigian=jsonObject.getInt("thoigianthi");
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
                KetQuaThiFragment ketQuaThiFragment = new KetQuaThiFragment();
                fragmentTransaction.replace(R.id.framelayout, ketQuaThiFragment);
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
        rblambaithib.setText("B."+questionArrayList.get(stt).getRadioButtonb().getText());
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
        rblambaithib.setText("B."+questionArrayList.get(stt).getRadioButtonb().getText());
        rblambaithic.setText("C. "+questionArrayList.get(stt).getRadioButtonc().getText());
        rblambaithid.setText("D. "+questionArrayList.get(stt).getRadioButtond().getText());
    }

    private void anhXa() {
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
    }
}
