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
import android.widget.TextView;

import com.example.hoangbao.apptracnghiem.R;
import com.example.hoangbao.apptracnghiem.adapter.SoAdapter;
import com.example.hoangbao.apptracnghiem.model.LoaiMenu;
import com.example.hoangbao.apptracnghiem.model.Question;
import com.example.hoangbao.apptracnghiem.model.So;
import com.example.hoangbao.apptracnghiem.uis.fragment.KetQuaThiFragment;

import java.util.ArrayList;

public class LamBaiThiFragment extends Fragment {
    public static ArrayList<String> stringArrayListdapan = new ArrayList<>();
    public static int tongdiem = 0;
    String hou = "", min = "", sec = "";
    int gio, phut, giay;
    int thoigian = 3700;
    TextView txtlambaithithoigian;
    SoAdapter soAdapter;
    GridView gridViewlambaithi;
    ArrayList<So> soArrayList;
    int stt = 0;
    public static ArrayList<Question> questionArrayList;
    java.text.SimpleDateFormat simpleDateFormat;
    TextView txtlambaithistt, txtlambaithicauhoi, txtlambaithichuthich;
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
        stringArrayListdapan.add("A");
        stringArrayListdapan.add("B");
        stringArrayListdapan.add("C");
        stringArrayListdapan.add("D");
        stringArrayListdapan.add("A");
        thoigian = 6;
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
        txtlambaithistt.setText("Câu hỏi " + 1);
        txtlambaithicauhoi.setText(questionArrayList.get(0).getCauhoi());
        rblambaithia.setText(questionArrayList.get(0).getRadioButtona().getText());
        rblambaithib.setText(questionArrayList.get(0).getRadioButtonb().getText());
        rblambaithic.setText(questionArrayList.get(0).getRadioButtonc().getText());
        rblambaithid.setText(questionArrayList.get(0).getRadioButtond().getText());
        rblambaithia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("A".equals(stringArrayListdapan.get(0))) {
                    tongdiem += 10;
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
                if ("B".equals(stringArrayListdapan.get(0))) {
                    tongdiem += 10;
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
                if ("C".equals(stringArrayListdapan.get(0))) {
                    tongdiem += 10;
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
                if ("D".equals(stringArrayListdapan.get(0))) {
                    tongdiem += 10;
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
        questionArrayList.add(new Question(1, "tại sao ?", new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity())));
        questionArrayList.add(new Question(2, "vì sao ?", new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity())));
        questionArrayList.add(new Question(3, "làm sao ?", new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity())));
        questionArrayList.add(new Question(4, "làm thế nào ?", new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity())));
        questionArrayList.add(new Question(5, "ở đâu ?", new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity()), new RadioButton(getActivity())));
        for (int i = 0; i < questionArrayList.size(); i++) {
            questionArrayList.get(i).getRadioButtona().setChecked(false);
            questionArrayList.get(i).getRadioButtonb().setChecked(false);
            questionArrayList.get(i).getRadioButtonc().setChecked(false);
            questionArrayList.get(i).getRadioButtond().setChecked(false);
            questionArrayList.get(i).getRadioButtona().setText("hghghghg");
            questionArrayList.get(i).getRadioButtonb().setText("jfjfj");
            questionArrayList.get(i).getRadioButtonc().setText("khkhkh");
            questionArrayList.get(i).getRadioButtond().setText("ggdddd");
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
                            if ("A".equals(stringArrayListdapan.get(stt))) {
                                tongdiem += 10;
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
                            if ("B".equals(stringArrayListdapan.get(stt))) {
                                tongdiem += 10;
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
                            if ("C".equals(stringArrayListdapan.get(stt))) {
                                tongdiem += 10;
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
                            if ("D".equals(stringArrayListdapan.get(stt))) {
                                tongdiem += 10;
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
        txtlambaithistt.setText("Câu hỏi " + (stt + 1));
        txtlambaithicauhoi.setText(questionArrayList.get(stt).getCauhoi());
        rblambaithia.setText(questionArrayList.get(stt).getRadioButtona().getText());
        rblambaithib.setText(questionArrayList.get(stt).getRadioButtonb().getText());
        rblambaithic.setText(questionArrayList.get(stt).getRadioButtonc().getText());
        rblambaithid.setText(questionArrayList.get(stt).getRadioButtond().getText());
    }

    private void cauHoiKeTiep() {
        rblambaithia.setChecked(questionArrayList.get(stt).getRadioButtona().isChecked());
        rblambaithib.setChecked(questionArrayList.get(stt).getRadioButtonb().isChecked());
        rblambaithic.setChecked(questionArrayList.get(stt).getRadioButtonc().isChecked());
        rblambaithid.setChecked(questionArrayList.get(stt).getRadioButtond().isChecked());
        int dem = stt + 1;
        txtlambaithistt.setText("Câu hỏi " + dem);
        txtlambaithicauhoi.setText(questionArrayList.get(stt).getCauhoi());
        rblambaithia.setText(questionArrayList.get(stt).getRadioButtona().getText());
        rblambaithib.setText(questionArrayList.get(stt).getRadioButtonb().getText());
        rblambaithic.setText(questionArrayList.get(stt).getRadioButtonc().getText());
        rblambaithid.setText(questionArrayList.get(stt).getRadioButtond().getText());
    }

    private void anhXa() {
        txtlambaithithoigian = view.findViewById(R.id.txt_lambaithithoigian);
        soArrayList = new ArrayList<>();
        txtlambaithichuthich = view.findViewById(R.id.txt_lambaithi_chuthich);
        txtlambaithicauhoi = view.findViewById(R.id.txt_lambaithi_cauhoi);
        txtlambaithistt = view.findViewById(R.id.txt_lambaithi_stt);
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
        gridViewlambaithi = view.findViewById(R.id.gridview_lambaithi);
    }
}
