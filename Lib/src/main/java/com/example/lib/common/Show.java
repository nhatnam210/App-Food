package com.example.lib.common;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib.model.GioHang;

import java.util.List;

public class Show {
    public static void Notify(Context context, String notify) {
        Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
    }

    public static List<GioHang> listGiohang;
//    public static List<Mon.Result> listBeside;

    public static int demSoLuongGioHang(int Options) {
        int dem = 0;
        if(Show.listGiohang != null) {
            for(int i = 0;i < Show.listGiohang.size(); i++ ) {
                dem += Show.listGiohang.get(i).getSoluong();
            }
            switch(Options) {
                case 1:
                    dem = dem < 999 ? dem : (dem > 999 ? 1000 : 999);
                    return dem;
                case 2:
                    return dem;
                default:
                    return 0;
            }
        }
            return 0;
    }

    public static void thayDoiSoLuongGioHangNho(TextView view) {
        int check = Show.demSoLuongGioHang(1);
        if(check > 999) {
            view.setText("999+");
        }else if(check <= 0) {
            view.setVisibility(View.GONE);
        }else{
            view.setText(String.valueOf(demSoLuongGioHang(1)));
            view.setVisibility(View.VISIBLE);
        }
    }

    public static int tinhTongTien() {
        int tongtien = 0;
        if(listGiohang.size()>0) {
            for (int i = 0; i < Show.listGiohang.size(); i++) {
                tongtien += Show.listGiohang.get(i).getGia() * Show.listGiohang.get(i).getSoluong();
            }
        }
        return tongtien;
    }
}
