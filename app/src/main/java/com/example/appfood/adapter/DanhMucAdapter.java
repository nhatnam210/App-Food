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
import com.example.lib.model.DanhMuc;

import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.GetViewDanhMuc> {
    Context context;
    List<DanhMuc> list;

    public DanhMucAdapter(Context context, List<DanhMuc> list) {
        this.context = context;
        this.list = list;
    }

    //get form view
    @NonNull
    @Override
    public GetViewDanhMuc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_danh_muc, parent, false);
        GetViewDanhMuc getViewDanhMuc = new GetViewDanhMuc(v);
        return getViewDanhMuc;
    }

    //gán giá trị cho tương ứng cho từng id view theo position
    @Override
    public void onBindViewHolder(@NonNull GetViewDanhMuc holder, int position) {
        DanhMuc danhMuc = list.get(position);
        holder.tendanhmuc.setText(danhMuc.getTendanhmuc());
        Glide.with(context).load(danhMuc.getHinhdanhmuc())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(holder.hinhdanhmuc);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //get id view
    public class GetViewDanhMuc extends RecyclerView.ViewHolder {
        TextView tendanhmuc;
        ImageView hinhdanhmuc;
        public GetViewDanhMuc(@NonNull View itemView) {
            super(itemView);
            tendanhmuc = itemView.findViewById(R.id.tendanhmuc);
            hinhdanhmuc = itemView.findViewById(R.id.hinhdanhmuc);
        }
    }
}
