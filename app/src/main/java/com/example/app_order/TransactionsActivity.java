package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TransactionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        findViewById(R.id.btnBookingHistory).setOnClickListener(v -> {
            Intent intent = new Intent(TransactionsActivity.this, BookingHistoryActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnRefundHistory).setOnClickListener(v -> {
            Intent intent = new Intent(TransactionsActivity.this, RefundHistoryActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(TransactionsActivity.this, Information_Account.class);
            startActivity(intent);
        });
    }
}
