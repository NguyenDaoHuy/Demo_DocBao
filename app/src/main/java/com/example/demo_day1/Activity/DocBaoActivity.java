package com.example.demo_day1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_day1.Model.Bao;
import com.example.demo_day1.R;

public class DocBaoActivity extends AppCompatActivity {

    private TextView tvTitle,txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);
        tvTitle = findViewById(R.id.tvTitle);
        txtDescription = findViewById(R.id.txtDescription);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
        });
        getDuLieu();
    }

    private void getDuLieu() {
        Bao bao = (Bao) getIntent().getSerializableExtra("thongtin");
        String title = bao.getTitle();
        String description = bao.getDescription();
        tvTitle.setText(title);
        txtDescription.setText(description);
    }
}