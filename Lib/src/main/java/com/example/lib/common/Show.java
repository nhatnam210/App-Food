package com.example.lib.common;

import android.content.Context;
import android.widget.Toast;

import com.example.lib.model.GioHang;

import java.util.List;

public class Show {
    public static void Notify(Context context, String notify) {
        Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
    }

    public static List<GioHang> listGiohang;

    public static int demSoLuongGioHang(int Options) {
        int dem = 0;
        if(Show.listGiohang != null) {
            // thongbao_soluong.setText(String.valueOf(Show.listGiohang.size())); // đếm theo loại
            for(int i = 0;i < Show.listGiohang.size(); i++ ) {
                dem += Show.listGiohang.get(i).getSoluong();
            }
            switch(Options) {
                case 1:
                    dem = dem >= 999 ? 999 : dem;
                    return dem;
                case 2:
                    return dem;
                default:
                    return 0;
            }
        }else{
            return 0;
        }
    }
}
