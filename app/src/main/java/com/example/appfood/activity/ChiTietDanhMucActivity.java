package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appfood.R;
import com.example.appfood.adapter.ChiTietDanhMucAdapter;
import com.example.appfood.adapter.MonNgauNhienAdapter;
import com.example.lib.InterfaceResponsitory.AppFoodMethods;
import com.example.lib.RetrofitClient;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;
import com.example.lib.common.Url;
import com.example.lib.model.Mon;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChiTietDanhMucActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recycleViewChiTietDanhMuc;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppFoodMethods appFoodMethods;

    List<Mon.Result> listMonTheoDanhMuc;
    ChiTietDanhMucAdapter chiTietDanhMucAdapter;

    int page = 1;
    int madanhmuc = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_danh_muc);

        getViewId();
        actionToolbar();
        khoitao();
        
        //check network
        if(NetworkConnection.isConnected(this)) {
                getChiTietDanhMuc();
        }else{
            Show.Notify(this,"Không có Internet! Vui lòng thử lại!");
            finish();
        }
    }

    private void khoitao() {
        listMonTheoDanhMuc = new ArrayList<>();
        appFoodMethods = RetrofitClient.getRetrofit(Url.AppFood_Url).create(AppFoodMethods.class);

        //set kiểu layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleViewChiTietDanhMuc.setLayoutManager(layoutManager);
        recycleViewChiTietDanhMuc.setHasFixedSize(true);
    }

    private void getChiTietDanhMuc() {
  compositeDisposable.add(appFoodMethods.GET_MonTheoDanhMuc(page,madanhmuc)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
                  mon -> {
                      if(mon.isSuccess()) {
                          listMonTheoDanhMuc = mon.getResult();
                          chiTietDanhMucAdapter = new ChiTietDanhMucAdapter(this,listMonTheoDanhMuc);
                          recycleViewChiTietDanhMuc.setAdapter(chiTietDanhMucAdapter);
                      }
                  },
                  throwable -> {
                      Show.Notify(this,"Không thể kết nối với Server! ");
                  }
          ));
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getViewId() {
        toolbar = findViewById(R.id.toolbar);
        recycleViewChiTietDanhMuc = findViewById(R.id.recycleViewChiTietDanhMuc);
    }

    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }
}