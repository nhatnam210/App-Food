package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appfood.R;
import com.example.appfood.adapter.DanhMucAdapter;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;
import com.example.lib.InterfaceResponsitory.AppFoodMethods;
import com.example.lib.RetrofitClient;
import com.example.lib.common.Url;
import com.example.lib.model.DanhMuc;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DanhMucActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recycleViewDanhMuc;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppFoodMethods appFoodMethods;

    List<DanhMuc.Result> listDanhMucResult;
    DanhMucAdapter danhMucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        getViewId();
        actionToolbar();
        khoitao();

        if(NetworkConnection.isConnected(this)) {
//            ShowToast.Notify(this,"Internet thành công!");
            GetDanhMuc();
        }else{
            Show.Notify(this,"Không có kết nối Internet!");
            finish();
        }
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(trangchu);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetDanhMuc();
    }

    private void khoitao() {
        listDanhMucResult = new ArrayList<>();
        appFoodMethods = RetrofitClient.getRetrofit(Url.AppFood_Url).create(AppFoodMethods.class);

        //set layout 2 cột
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recycleViewDanhMuc.setLayoutManager(layoutManager);
        recycleViewDanhMuc.setHasFixedSize(true);
    }

    private void GetDanhMuc() {
        compositeDisposable.add(appFoodMethods.GET_DanhMuc()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                danhMuc -> {
                    if(danhMuc.isSuccess()) {
                        listDanhMucResult = danhMuc.getResult();
                        danhMucAdapter = new DanhMucAdapter(this,listDanhMucResult);
                        recycleViewDanhMuc.setAdapter(danhMucAdapter);
                    }
                },
                throwable -> {
                    Show.Notify(this,"Không thể kết nối với Server! ");
                }
        ));
    }

    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }

    private void getViewId() {
        toolbar = findViewById(R.id.toolbar);
        recycleViewDanhMuc = findViewById(R.id.recycleViewDanhMuc);
    }
}