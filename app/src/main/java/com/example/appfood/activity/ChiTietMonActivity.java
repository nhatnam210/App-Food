package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;
import com.example.lib.model.Mon;

import java.text.DecimalFormat;

public class ChiTietMonActivity extends AppCompatActivity {

    Toolbar toolbarChitietmon;
    ImageView hinhmon_chitiet;
    TextView tenmon_chitiet,gia_chitiet,mota_chitiet;
    Button btn_mua;
    Spinner spinner_soluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);

        getViewId();
        actionToolbar();
//        khoitao();

        //check network
        if(NetworkConnection.isConnected(this)) {
            getChiTietMon();
//                actionLoading();
        }else{
            Show.Notify(this,getString(R.string.error_network));
            finish();
        }
    }

    private void getChiTietMon() {
        Mon.Result monResult = (Mon.Result) getIntent().getSerializableExtra("chitietmon");
        tenmon_chitiet.setText(monResult.getTenmon());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia_chitiet.setText(decimalFormat.format(Double.parseDouble(monResult.getGia()))+" đ");
        mota_chitiet.setText(monResult.getMota());
        Glide.with(getApplicationContext()).load(monResult.getHinhmon())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(hinhmon_chitiet);

        Integer[] soluong = new Integer[]{1,5,10,15,20};
        ArrayAdapter<Integer> adapterSpin =
                new ArrayAdapter<>(this,R.layout.spinner_item,soluong);
        spinner_soluong.setAdapter(adapterSpin);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarChitietmon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitietmon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getViewId() {
        toolbarChitietmon = findViewById(R.id.toolbarChitietmon);
        hinhmon_chitiet = findViewById(R.id.hinhmon_chitiet);
        tenmon_chitiet = findViewById(R.id.tenmon_chitiet);
        gia_chitiet = findViewById(R.id.gia_chitiet);
        mota_chitiet = findViewById(R.id.mota_chitiet);
        spinner_soluong = findViewById(R.id.spinner_soluong);
        btn_mua = findViewById(R.id.btn_mua);
    }

    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }
}