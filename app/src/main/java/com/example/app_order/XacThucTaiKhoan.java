package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class XacThucTaiKhoan extends AppCompatActivity {
    private Button btn_back, btn_verify;

    private void AnhXa(){
        btn_back = findViewById(R.id.btnQuayLai);
        btn_verify = findViewById(R.id.btnXacNhan);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xac_thuc_tai_khoan);

        AnhXa();

        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(XacThucTaiKhoan.this, DangKy.class);
            startActivity(intent);
        });
        btn_verify.setOnClickListener(v -> {
            showRegistrationSuccessDialog();
        });


    }


    private void showRegistrationSuccessDialog() {
        RegistrationSuccessDialog registrationSuccessDialog = new RegistrationSuccessDialog();
        registrationSuccessDialog.show(getSupportFragmentManager(), "registrationSuccessDialog");

    }


}