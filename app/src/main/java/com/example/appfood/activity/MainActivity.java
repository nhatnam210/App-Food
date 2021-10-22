package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.example.appfood.adapter.DanhMucAdapter;
import com.example.lib.model.DanhMuc;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarHome;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewNavHome;
    DrawerLayout drawerLayout;
    ArrayList<DanhMuc> danhMucs;
    DanhMucAdapter danhMucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViewId();
        OpenNav();
        Slider();
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

    private void OpenNav() {
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
        recyclerView = findViewById(R.id.recycleView);
        navigationView = findViewById(R.id.navigationView);
        listViewNavHome = findViewById(R.id.listViewNavHome);
        drawerLayout = findViewById(R.id.drawerLayout);
        //custom view danh muc
        danhMucs = new ArrayList<>();
        danhMucAdapter = new DanhMucAdapter(danhMucs,getApplicationContext());
        //...
    }
}