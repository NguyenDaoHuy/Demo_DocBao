package com.example.demo_day1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_day1.Model.Bao;
import com.example.demo_day1.R;

public class DocBaoActivity extends AppCompatActivity {

    TextView tvTitle,txtDescription;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);
        tvTitle = findViewById(R.id.tvTitle);
        txtDescription = findViewById(R.id.txtDescription);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
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