package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuenMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quen_mat_khau);

        findViewById(R.id.btnDangNhap).setOnClickListener(v -> {
            Intent intent = new Intent(QuenMatKhau.this, DangNhap.class);
            startActivity(intent);
        });

        findViewById(R.id.btnDangKy).setOnClickListener(v -> {
            Intent intent = new Intent(QuenMatKhau.this, DangKy.class);
            startActivity(intent);
        });
    }
}