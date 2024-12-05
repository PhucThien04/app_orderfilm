package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class QuenMatKhau extends AppCompatActivity {
    private EditText edit_email;
    private Button btn_layLaiMatKhau, btn_dangNhap, btn_dangKy;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        // Initialize Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance("https://apporderfilm-14fd9-default-rtdb.firebaseio.com/").getReference("KhachHang");

        // Initialize views
        edit_email = findViewById(R.id.editText_email);
        btn_layLaiMatKhau = findViewById(R.id.btnLayLaiMatKhau);
        btn_dangNhap = findViewById(R.id.btnDangNhap);
        btn_dangKy = findViewById(R.id.btnDangKy);

        // Set click listener for "Lấy lại mật khẩu" (Retrieve Password) button
        btn_layLaiMatKhau.setOnClickListener(v -> {
            String email = edit_email.getText().toString().trim();

            // Validate email input
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(QuenMatKhau.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                return;
            }

            // Query to check if email exists in the database
            Query emailQuery = databaseReference.orderByChild("email").equalTo(email);

            emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Email found, navigate to password change screen
                        Intent intent = new Intent(QuenMatKhau.this, DoiMatKhau.class);
                        intent.putExtra("EMAIL", email);
                        startActivity(intent);
                    } else {
                        // Email not found in the database
                        Toast.makeText(QuenMatKhau.this, "Email chưa được đăng ký", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle potential errors
                    Toast.makeText(QuenMatKhau.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Set click listeners for navigation buttons
        btn_dangNhap.setOnClickListener(v -> {
            Intent intent = new Intent(QuenMatKhau.this, DangNhap.class);
            startActivity(intent);
        });

        btn_dangKy.setOnClickListener(v -> {
            Intent intent = new Intent(QuenMatKhau.this, DangKy.class);
            startActivity(intent);
        });
    }
}