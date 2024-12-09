package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_order.activity.RapActivity;

public class DetailMovie_DoAnhCongDuocToi extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_doanhcongduoctoi);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(DetailMovie_DoAnhCongDuocToi.this, Cinema_Lineup.class);
            startActivity(intent);
        });

        findViewById(R.id.btnBookTicket).setOnClickListener(v -> {
            Intent intent = new Intent(DetailMovie_DoAnhCongDuocToi.this, RapActivity.class);
            startActivity(intent);
        });
    }
}
