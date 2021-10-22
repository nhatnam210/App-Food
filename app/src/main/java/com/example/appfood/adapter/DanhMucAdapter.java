package com.example.appfood.adapter;

import static retrofit2.Response.error;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.lib.model.DanhMuc;

import java.util.ArrayList;

public class DanhMucAdapter extends BaseAdapter {
    ArrayList<DanhMuc> arrayListDanhMuc;
    Context context;

    public DanhMucAdapter(ArrayList<DanhMuc> arrayListDanhMuc, Context context) {
        this.arrayListDanhMuc = arrayListDanhMuc;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListDanhMuc.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListDanhMuc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class GetCustomView {
        ImageView hinhdanhmuc;
        TextView tendanhmuc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GetCustomView getCustomView = null;
        //nếu chưa có giá trị id thì gán
        if(view == null) {
            getCustomView = new GetCustomView();
            //get layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_grid_danh_muc,null);
            getCustomView.hinhdanhmuc = (ImageView) view.findViewById(R.id.hinhdanhmuc);
            getCustomView.tendanhmuc = (TextView) view.findViewById(R.id.tendanhmuc);
            //gán các thuộc tính vào getView
            view.setTag(getCustomView);
        }else{
            getCustomView = (GetCustomView) view.getTag();
            getCustomView.tendanhmuc.setText(arrayListDanhMuc.get(i).getTendanhmuc());
            Glide.with(context).load(arrayListDanhMuc.get(i).getHinhanhdanhmuc())
                    .placeholder(R.drawable.img_default)
                    .error(R.drawable.img_fail)
                    .into(getCustomView.hinhdanhmuc);
        }
        return view;
    }
}
