package com.example.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.lib.InterfaceResponsitory.ItemClickOptions;
import com.example.lib.common.Show;
import com.example.lib.model.EventBus.ActionEvent;
import com.example.lib.model.GioHang;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GetViewGioHang> {
    Context context;
    List<GioHang> list;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public GioHangAdapter(Context context, List<GioHang> list) {
        this.context = context;
        this.list = list;
    }
//    List<Mon.Result> listBeside;


    @NonNull
    @Override
    public GetViewGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        GetViewGioHang getViewGioHang= new GetViewGioHang(view);
        return getViewGioHang;
    }

    @Override
    public void onBindViewHolder(@NonNull GetViewGioHang holder, int position) {
//        Mon.Result monResult = listBeside.get(position);
        GioHang giohang = list.get(position);

        holder.tenmon_giohang.setText(giohang.getTenmon());
        holder.soluong_mon.setText(giohang.getSoluong() +" ");
        Glide.with(context).load(giohang.getHinhmon())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(holder.hinhmon_giohang);

        holder.gia_giohang.setText(decimalFormat.format(giohang.getGia())+" đ");

        long thanhtien = giohang.getGia() * giohang.getSoluong();
        holder.thanhtien_giohang.setText(decimalFormat.format(thanhtien)+" đ");

        holder.setItemClickOptions(new ItemClickOptions() {
            int soLuongMoi = 0;
            @Override
            public void onClickOptions(View view, int pos, int value) {
                switch (value) {
                    case -1:
                        if(list.get(pos).getSoluong() > 1) {
                            soLuongMoi = list.get(pos).getSoluong() - 1;
                            list.get(pos).setSoluong(soLuongMoi);
                        }
                        capNhatThongTin(holder,pos,soLuongMoi);
                        break;
                    case 1:
                        if(list.get(pos).getSoluong() < 200) {
                            soLuongMoi = list.get(pos).getSoluong() + 1;
                            list.get(pos).setSoluong(soLuongMoi);
                        }
                        capNhatThongTin(holder,pos,soLuongMoi);
                        break;
                    case -2:
                        Show.listGiohang.remove(pos);
                        break;
                }
                //post event
                EventBus.getDefault().postSticky(new ActionEvent());
            }
        });
        //ẩn hiện hút
        anHienNut(holder.tru_giohang,holder.cong_giohang,giohang.getSoluong());
    }

    public void capNhatThongTin(@NonNull GetViewGioHang holder,int pos,int value) {
        holder.soluong_mon.setText(list.get(pos).getSoluong() +" ");
        long thanhtien = list.get(pos).getGia() * list.get(pos).getSoluong();
        holder.thanhtien_giohang.setText(decimalFormat.format(thanhtien)+" đ");

        anHienNut(holder.tru_giohang,holder.cong_giohang,value);

        //post event
        EventBus.getDefault().postSticky(new ActionEvent());
    }

    public void anHienNut(ImageView view1,ImageView view2, int value) {
        if (value <= 1) {
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
        }else if (value >= 200){
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
        } else {
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
        }

    }

    public void hienThongBao(LinearLayout main, LinearLayout notify) {
        main.setVisibility(View.INVISIBLE);
        notify.setVisibility(View.VISIBLE);
    }

    public void anThongBao(LinearLayout main, LinearLayout notify) {
        main.setVisibility(View.VISIBLE);
        notify.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GetViewGioHang extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView hinhmon_giohang,cong_giohang,tru_giohang;
        FrameLayout xoa_giohang;
        LinearLayout linear_main,linear_notify;
        TextView btn_yes,btn_no;
        TextView tenmon_giohang, gia_giohang,thanhtien_giohang,soluong_mon;
        ItemClickOptions itemClickOptions;
        public GetViewGioHang(@NonNull View itemView) {
            super(itemView);
            hinhmon_giohang = itemView.findViewById(R.id.hinhmon_giohang);
            tenmon_giohang = itemView.findViewById(R.id.tenmon_giohang);
            gia_giohang = itemView.findViewById(R.id.gia_giohang);
            thanhtien_giohang = itemView.findViewById(R.id.thanhtien_giohang);
            soluong_mon = itemView.findViewById(R.id.soluong_mon);
            cong_giohang = itemView.findViewById(R.id.cong_giohang);
            tru_giohang = itemView.findViewById(R.id.tru_giohang);
            xoa_giohang = itemView.findViewById(R.id.xoa_giohang);
            linear_main = itemView.findViewById(R.id.linear_main);
            linear_notify = itemView.findViewById(R.id.linear_notify);
            btn_yes = itemView.findViewById(R.id.btn_yes);
            btn_no = itemView.findViewById(R.id.btn_no);

            //edit
            cong_giohang.setOnClickListener(this);
            tru_giohang.setOnClickListener(this);
            xoa_giohang.setOnClickListener(this);
            btn_yes.setOnClickListener(this);
            btn_no.setOnClickListener(this);
        }

        public void setItemClickOptions(ItemClickOptions itemClickOptions) {
            this.itemClickOptions = itemClickOptions;
        }

        @Override
        public void onClick(View view) {
            if(view == tru_giohang ) {
                itemClickOptions.onClickOptions(view, getAdapterPosition(),-1);
            }else if(view == cong_giohang ) {
                itemClickOptions.onClickOptions(view, getAdapterPosition(),1);
            }else if (view == xoa_giohang) {
                hienThongBao(linear_main,linear_notify);
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        anThongBao(linear_main,linear_notify);
                    }
                });

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        anThongBao(linear_main,linear_notify);
                        itemClickOptions.onClickOptions(view, getAdapterPosition(),-2);
                    }
                });

            }
        }
    }
}

