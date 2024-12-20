package com.example.app_order;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import com.example.app_order.model.KhachHang;

public class DangKy extends AppCompatActivity {
    private EditText verifyPass, edit_dateOfBird, edit_fullName, edit_phoneNumber, edit_email, edit_pass, edit_verifyPass;
    private Button btn_forgotPass, btn_signIn, btn_signUp;
    boolean passVisible;
    private FirebaseAuth auth;

    private void AnhXa() {
        verifyPass = findViewById(R.id.editText_verifyPass);
        edit_dateOfBird = findViewById(R.id.editText_dateOfBirth);
        edit_fullName = findViewById(R.id.editText_fullName);
        edit_phoneNumber = findViewById(R.id.editText_phoneNumber);
        edit_email = findViewById(R.id.editText_email);
        edit_pass = findViewById(R.id.editText_password);
        edit_verifyPass = findViewById(R.id.editText_verifyPass);
        btn_forgotPass = findViewById(R.id.btnQuenMatKhau);
        btn_signIn = findViewById(R.id.btnDangNhap);
        btn_signUp = findViewById(R.id.button_signUp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();
        edit_dateOfBird.setOnClickListener(v -> {
            // Lấy ngày hiện tại
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            // Hiển thị DatePickerDialog
            DatePickerDialog picker = new DatePickerDialog(DangKy.this, (view, selectedYear, selectedMonth, selectedDay) -> {
                // Đặt ngày đã chọn vào EditText
                edit_dateOfBird.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
            }, year, month, day);

            picker.show();
        });

        btn_forgotPass.setOnClickListener(v -> {
            Intent intent = new Intent(DangKy.this, QuenMatKhau.class);
            startActivity(intent);
        });

        btn_signIn.setOnClickListener(v -> {
            Intent intent = new Intent(DangKy.this, DangNhap.class);
            startActivity(intent);
        });

        btn_signUp.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edit_fullName.getText().toString()) || TextUtils.isEmpty(edit_email.getText().toString()) || TextUtils.isEmpty(edit_phoneNumber.getText().toString())
                    || TextUtils.isEmpty(edit_dateOfBird.getText().toString()) || TextUtils.isEmpty(edit_pass.getText().toString()) || TextUtils.isEmpty(edit_verifyPass.getText().toString())) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else if (!edit_pass.getText().toString().equals(edit_verifyPass.getText().toString())) {
                Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            } else {
                SignUp();
            }

        });

        edit_pass.setOnTouchListener((view, motionEvent) -> {
            final int Right = 2;
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX() >= edit_pass.getRight() - edit_pass.getCompoundDrawables()[Right].getBounds().width()) {
                    int select = edit_pass.getSelectionEnd();
                    if (passVisible) {
                        edit_pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                        edit_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passVisible = false;
                    } else {
                        edit_pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                        edit_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passVisible = true;
                    }
                    edit_pass.setSelection(select);
                    return true;
                }
            }
            return false;
        });

        verifyPass.setOnTouchListener((view, motionEvent) -> {
            final int Right = 2;
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX() >= verifyPass.getRight() - verifyPass.getCompoundDrawables()[Right].getBounds().width()) {
                    int select = verifyPass.getSelectionEnd();
                    if (passVisible) {
                        verifyPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                        verifyPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passVisible = false;
                    } else {
                        verifyPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                        verifyPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passVisible = true;
                    }
                    verifyPass.setSelection(select);
                    return true;
                }
            }
            return false;
        });
    }

    private void SignUp() {
        String email = edit_email.getText().toString().trim();
        String pass = edit_pass.getText().toString().trim();
        String fullName = edit_fullName.getText().toString().trim();
        String dateOfBirth = edit_dateOfBird.getText().toString().trim();
        String phoneNumber = edit_phoneNumber.getText().toString().trim();

        auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = auth.getCurrentUser().getUid();

                        KhachHang user = new KhachHang(userId, fullName, dateOfBirth, phoneNumber, email, pass);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference usersRef = database.getReference("KhachHang");

                        usersRef.child(userId)
                                .setValue(user)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(DangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(DangKy.this, TrangChu.class);
                                    startActivity(intent);

                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(DangKy.this, "Lỗi lưu thông tin: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });

                    } else {
                        Toast.makeText(DangKy.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}