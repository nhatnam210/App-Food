package com.example.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.lib.model.GioHang;
import com.example.lib.model.Mon;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GetViewGioHang> {
    Context context;
    List<GioHang> list;

    public GioHangAdapter(Context context, List<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GetViewGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        GetViewGioHang getViewGioHang= new GetViewGioHang(view);
        return getViewGioHang;
    }

    @Override
    public void onBindViewHolder(@NonNull GetViewGioHang holder, int position) {
        GioHang giohang = list.get(position);

        holder.tenmon_giohang.setText(giohang.getTenmon());
        holder.soluong_mon.setText(giohang.getSoluong() +" ");
        Glide.with(context).load(giohang.getHinhmon())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(holder.hinhmon_giohang);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.gia_giohang.setText(decimalFormat.format(giohang.getGia())+" đ");

        long thanhtien = giohang.getGia() * giohang.getSoluong();
        holder.thanhtien_giohang.setText(decimalFormat.format(thanhtien)+" đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GetViewGioHang extends  RecyclerView.ViewHolder {
        ImageView hinhmon_giohang;
        TextView tenmon_giohang, gia_giohang,thanhtien_giohang,soluong_mon;
        public GetViewGioHang(@NonNull View itemView) {
            super(itemView);
            hinhmon_giohang = itemView.findViewById(R.id.hinhmon_giohang);
            tenmon_giohang = itemView.findViewById(R.id.tenmon_giohang);
            gia_giohang = itemView.findViewById(R.id.gia_giohang);
            thanhtien_giohang = itemView.findViewById(R.id.thanhtien_giohang);
            soluong_mon = itemView.findViewById(R.id.soluong_mon);
        }
    }
}
