package com.example.app_order;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChitietrapphimActivity extends AppCompatActivity {

    private Button selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietrapphim);

        // Thiết lập nút mặc định được chọn
        selectedButton = findViewById(R.id.btnMonday);
        selectedButton.setBackgroundColor(Color.BLACK);
        selectedButton.setTextColor(Color.WHITE);

        // Lắng nghe sự kiện click cho các nút
        setUpButtonClickListener(R.id.btnMonday);
        setUpButtonClickListener(R.id.btnTuesday);
        setUpButtonClickListener(R.id.btnWednesday);
        setUpButtonClickListener(R.id.btnThursday);
        setUpButtonClickListener(R.id.btnFriday);
        setUpButtonClickListener(R.id.btnSaturday);
        setUpButtonClickListener(R.id.btnSunday);
    }

    private void setUpButtonClickListener(int buttonId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Đặt lại màu cho nút đã chọn trước đó
            if (selectedButton != null) {
                selectedButton.setBackgroundColor(Color.WHITE);
                selectedButton.setTextColor(Color.BLACK);
            }
            // Cập nhật nút được chọn mới
            selectedButton = button;
            selectedButton.setBackgroundColor(Color.BLACK);
            selectedButton.setTextColor(Color.WHITE);
        });
    }
}