package com.example.app_order;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RapInfoActivity extends AppCompatActivity {

    private TextView tvTieude, tvDiaChi, tvNoiDoXe, tvTienIch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_info);

        tvTieude = findViewById(R.id.tvTieude);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvNoiDoXe = findViewById(R.id.tvNoiDoXe);
        tvTienIch = findViewById(R.id.tvTienIch);

        // Nhận đối tượng Rap từ Intent
        Rap infoRap = (Rap) getIntent().getSerializableExtra("selectedRap");

        if (infoRap != null) {
            // Hiển thị thông tin rạp
            tvTieude.setText(infoRap.getTenRap());
            tvDiaChi.setText("Địa chỉ: " + infoRap.getDiaChi());
            tvNoiDoXe.setText("Nơi đỗ xe: " + infoRap.getNoiDoXe());
            tvTienIch.setText("Tiện ích: " + infoRap.getTienIch());
        }
    }
}
