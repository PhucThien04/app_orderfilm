package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailMovie_Modomdom extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_modomdom);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(DetailMovie_Modomdom.this, Cinema_Lineup.class);
            startActivity(intent);
        });

        findViewById(R.id.btnBookTicket).setOnClickListener(v -> {
            Intent intent = new Intent(DetailMovie_Modomdom.this, RapphimActivity.class);
            startActivity(intent);
        });
    }
}
