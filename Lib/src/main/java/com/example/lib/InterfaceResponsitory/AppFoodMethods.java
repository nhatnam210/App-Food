package com.example.lib.InterfaceResponsitory;

import com.example.lib.model.DanhMuc;
import com.example.lib.model.Mon;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppFoodMethods {
    @GET("danhmuc.php")
    Observable<DanhMuc> GET_DanhMuc();

    @GET("monngaunhien.php")
    Observable<Mon> GET_MonNgauNhien();

    @POST("chitietdanhmuc.php")
    @FormUrlEncoded
    Observable<Mon> POST_MonTheoDanhMuc(
//            @Field("page") int page,
//            @Field("select") int select,
            @Field("madanhmuc") int madanhmuc
    );

}
