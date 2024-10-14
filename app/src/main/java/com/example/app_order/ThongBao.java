package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThongBao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_bao);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(ThongBao.this, Information_Account.class);
            startActivity(intent);
        });
    }
}