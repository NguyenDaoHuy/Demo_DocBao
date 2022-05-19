package com.example.demo_day1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo_day1.Activity.DocBaoActivity;
import com.example.demo_day1.Activity.MainActivity;
import com.example.demo_day1.Model.Bao;
import com.example.demo_day1.R;

import java.io.Serializable;
import java.util.ArrayList;

public class AdapterBao extends BaseAdapter {
    ArrayList<Bao> baoList;
    MainActivity context;
    public AdapterBao(MainActivity context,ArrayList<Bao> baoList){
        this.context = context;
        this.baoList = baoList;
    }
    @Override
    public int getCount() {
        return baoList.size();
    }

    @Override
    public Object getItem(int position) {
        return baoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_danh_sach_bao,parent,false);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);
        TextView txtNoiDung = view.findViewById(R.id.txtNoiDung);
        Bao bao = baoList.get(position);
        txtTieuDe.setText(bao.getTitle());
        txtNoiDung.setText(bao.getDescription());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DocBaoActivity.class);
                intent.putExtra("thongtin", baoList.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
