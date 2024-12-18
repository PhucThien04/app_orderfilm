package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XacThucTaiKhoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xac_thuc_tai_khoan);

        findViewById(R.id.btnXacNhan).setOnClickListener(v -> {
            showRegistrationSuccessDialog();
        });

    }

    private void showRegistrationSuccessDialog() {
        RegistrationSuccessDialog registrationSuccessDialog = new RegistrationSuccessDialog();
        registrationSuccessDialog.show(getSupportFragmentManager(), "registrationSuccessDialog");

    }


}