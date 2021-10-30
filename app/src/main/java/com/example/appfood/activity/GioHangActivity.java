package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.appfood.R;
import com.example.appfood.adapter.GioHangAdapter;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;
import com.example.lib.model.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    Toolbar toolbar_Giohang;
    RecyclerView recycleView_Giohang;
    TextView textview_tongtien;
    Button btn_thanhtoan;
    FrameLayout frame_giohangtrong;

    GioHangAdapter gioHangAdapter;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    int tongtien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        getViewId();
        actionToolbar();
        khoitao();
        if(NetworkConnection.isConnected(this)) {
            getGiohang();
            tinhTongTien();
        }else{
            Show.Notify(this,getString(R.string.error_network));
            finish();
        }
    }

    private void tinhTongTien() {
        for(int i = 0; i< Show.listGiohang.size();i++) {
            tongtien += Show.listGiohang.get(i).getGia() * Show.listGiohang.get(i).getSoluong();
        }

        textview_tongtien.setText(decimalFormat.format(tongtien)+" đ");
    }

    private void khoitao() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView_Giohang.setHasFixedSize(true);
        recycleView_Giohang.setLayoutManager(layoutManager);
    }

    private void getGiohang() {
        if(Show.listGiohang.size() == 0) {
            frame_giohangtrong.setVisibility(View.VISIBLE);
        }else{
            gioHangAdapter = new GioHangAdapter(getApplicationContext(),Show.listGiohang);
            recycleView_Giohang.setAdapter(gioHangAdapter);
        }

        toolbar_Giohang.setTitle(getString(R.string.cart)+" (" + Show.demSoLuongGioHang(2) +")");
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar_Giohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_Giohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getViewId() {
        toolbar_Giohang = findViewById(R.id.toolbarGiohang);
        recycleView_Giohang = findViewById(R.id.recycleView_Giohang);
        textview_tongtien = findViewById(R.id.textview_tongtien);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        frame_giohangtrong = findViewById(R.id.frame_giohangtrong);
    }

    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }
}