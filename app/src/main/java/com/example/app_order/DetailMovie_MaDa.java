package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailMovie_MaDa extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_mada);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(DetailMovie_MaDa.this, Cinema_Lineup.class);
            startActivity(intent);
        });

        findViewById(R.id.btnBookTicket).setOnClickListener(v -> {
            Intent intent = new Intent(DetailMovie_MaDa.this, RapActivity.class);
            startActivity(intent);
        });
    }
}
