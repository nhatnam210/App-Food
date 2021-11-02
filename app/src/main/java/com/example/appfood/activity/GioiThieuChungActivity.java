package com.example.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appfood.R;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;

public class GioiThieuChungActivity extends AppCompatActivity {
    Toolbar toolbar_Gioithieuchung;
    TextView thongbao_soluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu_chung);
        getViewId();
        actionToolbar();

        //check network
        if (NetworkConnection.isConnected(this)) {
            Show.thayDoiSoLuongGioHangNho(thongbao_soluong);
        } else {
            Show.Notify(this, getString(R.string.error_network));
            finish();
        }
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar_Gioithieuchung);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_Gioithieuchung.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getViewId() {
        toolbar_Gioithieuchung = findViewById(R.id.toolbar_Gioithieuchung);
        thongbao_soluong = findViewById(R.id.thongbao_soluong);
    }

    public void openCart(View view) {
        Intent giohang = new Intent(getApplicationContext(),GioHangActivity.class);
        startActivity(giohang);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Show.thayDoiSoLuongGioHangNho(thongbao_soluong);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Show.thayDoiSoLuongGioHangNho(thongbao_soluong);
    }


    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }

    public void ToLienHe(View view) {
        Intent lienhe = new Intent(getApplicationContext(),LienHeActivity.class);
        startActivity(lienhe);
    }
}