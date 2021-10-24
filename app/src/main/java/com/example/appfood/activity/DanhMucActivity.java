package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    RecyclerView recycleViewDanhMuc;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppFoodMethods appFoodMethods;

    List<DanhMuc> listDanhMuc;
    DanhMucAdapter danhMucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        listDanhMuc = new ArrayList<>();
        recycleViewDanhMuc = findViewById(R.id.recycleViewDanhMuc);

        //set layout 2 cột
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recycleViewDanhMuc.setLayoutManager(layoutManager);
        recycleViewDanhMuc.setHasFixedSize(true);

        appFoodMethods = RetrofitClient.getRetrofit(Url.AppFood_Url).create(AppFoodMethods.class);

        if(NetworkConnection.isConnected(this)) {
//            ShowToast.Notify(this,"Internet thành công!");
            GetDanhMuc();
        }else{
            Show.Notify(this,"Không có kết nối Internet!");
//            finish();
        }
    }

    private void GetDanhMuc() {
        compositeDisposable.add(appFoodMethods.GET_DanhMuc()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                danhMucModel -> {
                    if(danhMucModel.isSuccess()) {
//                        ShowToast.Notify(getApplicationContext(),danhMucModel.getResult().get(0).getTendanhmuc());
                        listDanhMuc = danhMucModel.getResult();
                        danhMucAdapter = new DanhMucAdapter(this, listDanhMuc);
                        recycleViewDanhMuc.setAdapter(danhMucAdapter);
                    }
                },
                throwable -> {
                    Show.Notify(this,"Không thể kết nối với Server! ");
                }
        ));
    }
}