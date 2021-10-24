package com.example.lib.InterfaceResponsitory;

import com.example.lib.model.DanhMucModel;
import com.example.lib.model.MonModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface AppFoodMethods {
    @GET("danhmuc.php")
    Observable<DanhMucModel> GET_DanhMuc();

    @GET("monngaunhien.php")
    Observable<MonModel> GET_MonNgauNhien();
}
