package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChitietdatveActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnHoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chitietdatve);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(ChitietdatveActivity.this, LichsudatveActivity.class);
                startActivity(Back);
            }
        });

        btnHoan = findViewById(R.id.btnHoanve);
        btnHoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Hoan = new Intent(ChitietdatveActivity.this, HoanvethanhcongActivity.class);
                startActivity(Hoan);
            }
        });
    }
}