package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RapDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvTenRap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_detail);

        tvTenRap = findViewById(R.id.tvTieude);
        // Nhận tên rạp từ Intent
        String tenRap = getIntent().getStringExtra("tenRap");
        if (tenRap != null) {
            tvTenRap.setText(tenRap);// Hiển thị tên rạp
        } else {
            tvTenRap.setText("LỖI! LỖI! LỖI!");// Nếu không có tên rạp, hiển thị thông báo lỗi
        }

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(RapDetailActivity.this, RapActivity.class);
                startActivity(Back);
            }
        });
    }
}
