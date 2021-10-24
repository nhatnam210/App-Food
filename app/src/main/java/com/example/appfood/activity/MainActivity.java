package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.appfood.adapter.MonNgauNhienAdapter;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;
import com.example.lib.InterfaceResponsitory.AppFoodMethods;
import com.example.lib.RetrofitClient;
import com.example.lib.common.Url;
import com.example.lib.model.Mon;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarHome;
    ViewFlipper viewFlipper;
    RecyclerView recycleViewMonNgauNhien;
    NavigationView navigationView;
    ListView listViewNavHome;
    DrawerLayout drawerLayout;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppFoodMethods appFoodMethods;

    List<Mon> listMonNgauNhien;
    MonNgauNhienAdapter monNgauNhienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewId();
        Toolbar();

        listMonNgauNhien = new ArrayList<>();

        //set layout 2 cột
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recycleViewMonNgauNhien.setLayoutManager(layoutManager);
        recycleViewMonNgauNhien.setHasFixedSize(true);

        appFoodMethods = RetrofitClient.getRetrofit(Url.AppFood_Url).create(AppFoodMethods.class);

        if(NetworkConnection.isConnected(this)) {
            Slider();
            GetMonNgauNhien();
        }else{
            Show.Notify(this,"Không có Internet! Vui lòng thử lại!");
//            finish();
        }
    }

    private void GetMonNgauNhien() {
        compositeDisposable.add(appFoodMethods.GET_MonNgauNhien()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                monModel -> {
                    if(monModel.isSuccess()) {
                        listMonNgauNhien = monModel.getResult();
                        monNgauNhienAdapter = new MonNgauNhienAdapter(this,listMonNgauNhien);
                        recycleViewMonNgauNhien.setAdapter(monNgauNhienAdapter);
                    }
                },
                throwable -> {
                    Show.Notify(this,"Không thể kết nối với Server! ");
                }
        ));
    }

    private void Slider() {
        List<String> slider = new ArrayList<>();
        slider.add(getString(R.string.slide_1));
        slider.add(getString(R.string.slide_2));
        slider.add(getString(R.string.slide_3));
        slider.add(getString(R.string.slide_4));
        for (int i = 0; i< slider.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(slider.get(i)).into(imageView);

            //fix imageView vào ViewFlipper
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(10000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_step_1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slider_step_1);
        Animation animation_slide_step_2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slider_step_2);

        viewFlipper.setInAnimation(animation_slide_step_1);
        viewFlipper.setOutAnimation(animation_slide_step_2);
    }

    private void Toolbar() {
        setSupportActionBar(toolbarHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void getViewId() {
        toolbarHome = findViewById(R.id.toolbarHome);
        viewFlipper = findViewById(R.id.viewFlipper);
        recycleViewMonNgauNhien = findViewById(R.id.recycleViewMonNgauNhien);
        navigationView = findViewById(R.id.navigationView);
        listViewNavHome = findViewById(R.id.listViewNavHome);
        drawerLayout = findViewById(R.id.drawerLayout);
        //custom view danh muc
//        danhMucs = new ArrayList<>();
//        danhMucAdapter = new DanhMucAdapter(danhMucs,getApplicationContext());
        //...
    }
}