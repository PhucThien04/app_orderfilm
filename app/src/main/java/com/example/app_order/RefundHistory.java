package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RefundHistory extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_history);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(RefundHistory.this, TransactionsActivity.class);
            startActivity(intent);
        });
    }
}
