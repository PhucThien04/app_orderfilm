package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentPolicyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_policy);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(PaymentPolicyActivity.this, Information_Account.class);
            startActivity(intent);
        });
    }
}
