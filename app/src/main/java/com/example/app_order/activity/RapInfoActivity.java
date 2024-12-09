package com.example.app_order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_order.R;
import com.example.app_order.model.Rap;

public class RapInfoActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvTieude, tvDiaChi, tvNoiDoXe, tvTienIch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_info);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(RapInfoActivity.this, RapDetailActivity.class);
                startActivity(Back);
            }
        });

        tvTieude = findViewById(R.id.tvTieude);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvNoiDoXe = findViewById(R.id.tvNoiDoXe);
        tvTienIch = findViewById(R.id.tvTienIch);

        // Nhận đối tượng Rap từ Intent
        Rap infoRap = (Rap) getIntent().getSerializableExtra("infoRap");

        if (infoRap != null) {
            String Tieude = infoRap.getTenRap();
            String DiaChi = infoRap.getDiaChi();
            String NoiDoXe = infoRap.getNoiDoXe();
            String TienIch = infoRap.getTienIch();

            if (NoiDoXe != null) {
                NoiDoXe = NoiDoXe.replace("\\n", "\n");
            }

            if (TienIch != null) {
                TienIch = TienIch.replace("\\n", "\n");
            }
            tvTieude.setText(Tieude);
            tvDiaChi.setText(DiaChi);
            tvNoiDoXe.setText(NoiDoXe);
            tvTienIch.setText(TienIch);
        }
    }
}
