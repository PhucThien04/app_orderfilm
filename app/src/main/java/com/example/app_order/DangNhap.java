package com.example.app_order;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhap extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button btnDangNhap, btnQuenMatKhau, btnDangKy;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    // Ánh xạ các View
    private void AnhXa() {
        editTextEmail = findViewById(R.id.editTextEmail_Login);
        editTextPassword = findViewById(R.id.editTextPass_Login);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnQuenMatKhau = findViewById(R.id.btnQuenMatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý...");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        // Khởi tạo Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Ánh xạ các View
        AnhXa();

        // Sự kiện click nút Quên mật khẩu
        btnQuenMatKhau.setOnClickListener(v -> {
            Intent intent = new Intent(DangNhap.this, QuenMatKhau.class);
            startActivity(intent);
        });

        // Sự kiện click nút Đăng ký
        btnDangKy.setOnClickListener(v -> {
            Intent intent = new Intent(DangNhap.this, DangKy.class);
            startActivity(intent);
        });

        // Sự kiện click nút Đăng nhập
        btnDangNhap.setOnClickListener(v -> signIn());
    }

    // Hàm xử lý đăng nhập
    private void signIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Kiểm tra rỗng
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Vui lòng nhập Email");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email không hợp lệ");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Vui lòng nhập mật khẩu");
            editTextPassword.requestFocus();
            return;
        }

        // Hiển thị ProgressDialog
        progressDialog.show();

        // Vô hiệu hóa nút đăng nhập
        btnDangNhap.setEnabled(false);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    // Bật lại nút đăng nhập
                    btnDangNhap.setEnabled(true);

                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        // Đăng nhập thành công
                        Intent intent = new Intent(DangNhap.this, TrangChu.class);
                        startActivity(intent);
                        finishAffinity(); // Đóng toàn bộ Activity trước đó
                    } else {
                        // Đăng nhập thất bại
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Đăng nhập thất bại!";
                        Toast.makeText(DangNhap.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi chung
                    progressDialog.dismiss();
                    btnDangNhap.setEnabled(true);
                    Toast.makeText(DangNhap.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
