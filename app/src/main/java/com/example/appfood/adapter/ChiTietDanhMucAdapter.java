package com.example.appfood.adapter;

import android.content.Context;
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
import com.example.lib.model.Mon;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietDanhMucAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Mon.Result> list;
    private static  final int data_type = 0;
    private static  final int loading_type = 1;

    public ChiTietDanhMucAdapter(Context context, List<Mon.Result> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == data_type) {
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tiet_danh_muc,parent,false);
            GetViewChiTietDanhMuc getViewChiTietDanhMuc = new GetViewChiTietDanhMuc(view);
            return getViewChiTietDanhMuc;
        }else{
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            LoadingView loadingView = new LoadingView(view);
            return loadingView;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof GetViewChiTietDanhMuc) {
            GetViewChiTietDanhMuc getViewChiTietDanhMuc = (GetViewChiTietDanhMuc) holder;
            Mon.Result monResult = list.get(position);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            getViewChiTietDanhMuc.gia_danhmuc.setText(decimalFormat.format(Double.parseDouble(monResult.getGia()))+" đ");
            getViewChiTietDanhMuc.tenmon_danhmuc.setText(monResult.getTenmon());
            getViewChiTietDanhMuc.mota_danhmuc.setText(monResult.getMota());
            Glide.with(context).load(monResult.getHinhmon())
                    .placeholder(R.drawable.img_default)
                    .error(R.drawable.img_error)
                    .into(getViewChiTietDanhMuc.hinhmon_danhmuc);
        }else{
            LoadingView loadingView = (LoadingView) holder;
            loadingView.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position) == null ? loading_type:data_type;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LoadingView extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingView(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public class GetViewChiTietDanhMuc extends RecyclerView.ViewHolder {
        TextView gia_danhmuc,tenmon_danhmuc,mota_danhmuc;
        ImageView hinhmon_danhmuc;
        public GetViewChiTietDanhMuc(@NonNull View itemView) {
            super(itemView);
            gia_danhmuc = itemView.findViewById(R.id.gia_danhmuc);
            tenmon_danhmuc = itemView.findViewById(R.id.tenmon_danhmuc);
            mota_danhmuc = itemView.findViewById(R.id.mota_danhmuc);
            hinhmon_danhmuc = itemView.findViewById(R.id.hinhmon_danhmuc);
        }
    }
}
