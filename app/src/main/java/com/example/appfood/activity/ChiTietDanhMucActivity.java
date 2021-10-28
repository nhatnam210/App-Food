package com.example.appfood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    Toolbar toolbarChitietdanhmuc;
    RecyclerView recycleViewChiTietDanhMuc;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppFoodMethods appFoodMethods;

    List<Mon.Result> listMonTheoDanhMuc;
    ChiTietDanhMucAdapter chiTietDanhMucAdapter;

//    LinearLayoutManager linearLayoutManager;
//    Handler handler = new Handler();
//    boolean isLoading = false;

    int page = 1;

    int madanhmuc;
    int select = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_danh_muc);

        getViewId();
        actionToolbar();
        khoitao();
        
        //check network
        if(NetworkConnection.isConnected(this)) {
                getChiTietDanhMuc(page);
//                actionLoading();
        }else{
            Show.Notify(this,getString(R.string.error_network));
            finish();
        }
    }

//    private void actionLoading() {
//        recycleViewChiTietDanhMuc.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if(!isLoading) {
//                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == listMonTheoDanhMuc.size() -1) {
//                        isLoading = true;
//                        loadMore();
//                    }
//                }
//            }
//        });
//    }

//    private void loadMore() {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                listMonTheoDanhMuc.add(null);
//                chiTietDanhMucAdapter.notifyItemInserted(listMonTheoDanhMuc.size() - 1);
//            }
//        });
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                listMonTheoDanhMuc.remove(listMonTheoDanhMuc.size() - 1);
//                chiTietDanhMucAdapter.notifyItemRemoved(listMonTheoDanhMuc.size());
//                page += 1;
//                getChiTietDanhMuc(page);
//                chiTietDanhMucAdapter.notifyDataSetChanged();
//                isLoading = false;
//            }
//        },1500);
//    }

    private void khoitao() {
        listMonTheoDanhMuc = new ArrayList<>();
        appFoodMethods = RetrofitClient.getRetrofit(Url.AppFood_Url).create(AppFoodMethods.class);

        //set kiểu layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycleViewChiTietDanhMuc.setLayoutManager(layoutManager);
        recycleViewChiTietDanhMuc.setHasFixedSize(true);
    }

    private void getChiTietDanhMuc(int page) {
        madanhmuc = getIntent().getIntExtra("madanhmuc",1);
        compositeDisposable.add(appFoodMethods.GET_MonTheoDanhMuc(page,madanhmuc,select)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
                  mon -> {
                      if (mon.isSuccess()) {
                          listMonTheoDanhMuc = mon.getResult();
                          chiTietDanhMucAdapter = new ChiTietDanhMucAdapter(this, listMonTheoDanhMuc);
                          recycleViewChiTietDanhMuc.setAdapter(chiTietDanhMucAdapter);
                      }
                  },
                  throwable -> {
                      Show.Notify(this,getString(R.string.error_server));
                  }
          ));
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarChitietdanhmuc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitietdanhmuc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent danhmuc = new Intent(getApplicationContext(),DanhMucActivity.class);
//                startActivity(danhmuc);
                finish();
            }
        });
    }

    private void getViewId() {
        toolbarChitietdanhmuc = findViewById(R.id.toolbarChitietdanhmuc);
        recycleViewChiTietDanhMuc = findViewById(R.id.recycleViewChiTietDanhMuc);
    }

    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}