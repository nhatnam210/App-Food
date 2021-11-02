package com.example.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.appfood.activity.ChiTietMonActivity;
import com.example.lib.InterfaceResponsitory.ItemClickOptions;
import com.example.lib.model.Mon;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietDanhMucAdapter extends RecyclerView.Adapter<ChiTietDanhMucAdapter.GetViewChiTietDanhMuc> {
    Context context;
    List<Mon.Result> list;


    public ChiTietDanhMucAdapter(Context context, List<Mon.Result> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public GetViewChiTietDanhMuc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tiet_danh_muc, parent, false);
        GetViewChiTietDanhMuc getViewChiTietDanhMuc = new GetViewChiTietDanhMuc(view);
        return getViewChiTietDanhMuc;
    }

    @Override
    public void onBindViewHolder(@NonNull GetViewChiTietDanhMuc holder, int position) {
        Mon.Result monResult = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.gia_danhmuc.setText(decimalFormat.format(Double.parseDouble(monResult.getGia()))+" đ");
        holder.tenmon_danhmuc.setText(monResult.getTenmon());
        holder.mota_danhmuc.setText(monResult.getMota());

        Glide.with(context).load(monResult.getHinhmon())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(holder.hinhmon_danhmuc);
        holder.setItemClickOptions(new ItemClickOptions() {
            @Override
            public void onClickOptions(View view, int pos, int value) {
                    Intent intent = new Intent(context,ChiTietMonActivity.class);
                    intent.putExtra("chitietmon",monResult);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public class LoadingView extends RecyclerView.ViewHolder {
//        ProgressBar progressBar;
//
//        public LoadingView(@NonNull View itemView) {
//            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBar);
//        }
//    }

    public class GetViewChiTietDanhMuc extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gia_danhmuc, tenmon_danhmuc, mota_danhmuc;
        ImageView hinhmon_danhmuc;
        private ItemClickOptions itemClickOptions;

        public GetViewChiTietDanhMuc(@NonNull View itemView) {
            super(itemView);
            gia_danhmuc = itemView.findViewById(R.id.gia_danhmuc);
            tenmon_danhmuc = itemView.findViewById(R.id.tenmon_danhmuc);
            mota_danhmuc = itemView.findViewById(R.id.mota_danhmuc);
            hinhmon_danhmuc = itemView.findViewById(R.id.hinhmon_danhmuc);
            itemView.setOnClickListener(this);
        }

        public void setItemClickOptions(ItemClickOptions itemClickOptions) {
            this.itemClickOptions = itemClickOptions;
        }

        @Override
        public void onClick(View view) {
            itemClickOptions.onClickOptions(view, getAbsoluteAdapterPosition(),0);
        }
    }
}
