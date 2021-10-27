package com.example.appfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appfood.R;
import com.example.lib.NavForm;

public class NavAdapter extends ArrayAdapter<NavForm> {

    Activity context;
    int resource;

    public NavAdapter(@NonNull Context context, int resource) {
        super(context, resource);

        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View customView = layoutInflater.inflate(this.resource,null);

        ImageView hinhminhhoa = customView.findViewById(R.id.hinhminhhoa);
        TextView noidung = customView.findViewById(R.id.noidung);

        NavForm nav = getItem(position);

        hinhminhhoa.setImageResource(nav.getHinhminhhoa());
        noidung.setText(nav.getNoidung());

        return customView;
    }
}
