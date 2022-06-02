package com.example.demo_day1.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.demo_day1.Model.Bao;
import com.example.demo_day1.R;

public class AdapterBao extends BaseAdapter {
    IRSS irss;

    public AdapterBao(IRSS irss) {
        this.irss = irss;
    }

    @Override
    public int getCount() {
        return irss.getCount();
    }

    @Override
    public Object getItem(int position) {
        return irss.getData(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.item_danh_sach_bao,parent,false);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);
        TextView txtNoiDung = view.findViewById(R.id.txtNoiDung);
        Bao bao = irss.getData(position);
        txtTieuDe.setText(bao.getTitle());
        txtNoiDung.setText(bao.getDescription());
        view.setOnClickListener(v -> irss.setOnClick(position));
        return view;
    }
    public interface IRSS {
        int getCount();
        Bao getData (int possition);
        void setOnClick(int possition);
    }
}
