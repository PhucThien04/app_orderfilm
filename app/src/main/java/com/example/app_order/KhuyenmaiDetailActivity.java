package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KhuyenmaiDetailActivity extends AppCompatActivity {

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyenmai_detail);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(KhuyenmaiDetailActivity.this, KhuyenmaiActivity.class);
                startActivity(Back);
            }
        });

        // Ánh xạ view
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);

        // Lấy dữ liệu từ Intent
        String tieude = getIntent().getStringExtra("tieude");
        String mota = getIntent().getStringExtra("mota");
        // Xử lý ký tự xuống dòng
        if (mota != null) {
            mota = mota.replace("\\n", "\n");
        }

        // Hiển thị dữ liệu
        tvTitle.setText(tieude);
        tvDescription.setText(mota);
    }
}