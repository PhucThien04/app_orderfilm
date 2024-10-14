package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BookingHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(BookingHistoryActivity.this, TransactionsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnBookNow).setOnClickListener(v -> {
            Intent intent = new Intent(BookingHistoryActivity.this, Cinema_Lineup.class);
            startActivity(intent);
        });
    }
}
