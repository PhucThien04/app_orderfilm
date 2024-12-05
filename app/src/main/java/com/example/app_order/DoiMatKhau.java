package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DoiMatKhau extends AppCompatActivity {
    private EditText edit_oldPass, edit_newPass, edit_confirmPass;
    private Button btn_changePass;
    private ImageView btnBack;
    private DatabaseReference databaseReference;
    private String userEmail;
    private boolean passVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        // Initialize Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("KhachHang");

        // Get email from previous activity
        userEmail = getIntent().getStringExtra("EMAIL");

        // Initialize views
        edit_oldPass = findViewById(R.id.editText_OldPass);
        edit_newPass = findViewById(R.id.editText_NewPass);
        edit_confirmPass = findViewById(R.id.editText_VerifyPass);
        btn_changePass = findViewById(R.id.button_CapNhatMK);
        btnBack = findViewById(R.id.btnBack);

        // Back button listener
        btnBack.setOnClickListener(v -> onBackPressed());

        // Password visibility toggle methods
        setupPasswordVisibilityToggle(edit_oldPass);
        setupPasswordVisibilityToggle(edit_newPass);
        setupPasswordVisibilityToggle(edit_confirmPass);

        // Change password button click listener
        btn_changePass.setOnClickListener(v -> handleChangePassword());
    }

    private void handleChangePassword() {
        String oldPass = edit_oldPass.getText().toString().trim();
        String newPass = edit_newPass.getText().toString().trim();
        String confirmPass = edit_confirmPass.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(oldPass) || TextUtils.isEmpty(newPass) || TextUtils.isEmpty(confirmPass)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPass.equals(confirmPass)) {
            Toast.makeText(this, "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // Find the user and validate old password
        Query emailQuery = databaseReference.orderByChild("email").equalTo(userEmail);

        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean userFound = false;
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    userFound = true;
                    // Check if old password matches
                    String currentPassword = userSnapshot.child("matKhau").getValue(String.class);

                    if (oldPass.equals(currentPassword)) {
                        // Old password is correct, proceed with password update
                        userSnapshot.getRef().child("matKhau").setValue(newPass)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                    navigateToLoginScreen();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(DoiMatKhau.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        // Old password is incorrect
                        Toast.makeText(DoiMatKhau.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                if (!userFound) {
                    Toast.makeText(DoiMatKhau.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoiMatKhau.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToLoginScreen() {
        Intent intent = new Intent(DoiMatKhau.this, DangNhap.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    // Method to setup password visibility toggle
    private void setupPasswordVisibilityToggle(EditText editText) {
        editText.setOnTouchListener((view, motionEvent) -> {
            final int Right = 2;
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX() >= editText.getRight() - editText.getCompoundDrawables()[Right].getBounds().width()) {
                    int select = editText.getSelectionEnd();
                    if (passVisible) {
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passVisible = false;
                    } else {
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passVisible = true;
                    }
                    editText.setSelection(select);
                    return true;
                }
            }
            return false;
        });
    }
}
