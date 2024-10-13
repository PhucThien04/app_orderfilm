package com.example.app_order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Set;

public class DatgheActivity extends AppCompatActivity {

    private Button btnNext;
    private ImageView btnBack;

    private GridLayout gridLayoutSeats;
    private TextView tvTotalPrice;
    private Set<String> selectedSeats = new HashSet<>();
    private int totalPrice = 0;

    private final int STANDARD_SEAT_PRICE = 100000; // Giá ghế tiêu chuẩn
    private final int VIP_SEAT_PRICE = 110000; // Giá ghế VIP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datghe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNext = findViewById(R.id.btnTieptuc);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Next = new Intent(DatgheActivity.this, ThanhtoanActivity.class);
                startActivity(Next);
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Next = new Intent(DatgheActivity.this, ChitietrapphimActivity.class);
                startActivity(Next);
            }
        });

        gridLayoutSeats = findViewById(R.id.gridGhe);
        tvTotalPrice = findViewById(R.id.tvTien);

        // Tạo các ghế
        createSeats();
    }

    private void createSeats() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                String seatId = String.valueOf((char) ('A' + i)) + (j + 1); // Ghế A1, A2, B1, B2,...

                TextView seat = new TextView(this);
                seat.setText(seatId);
                seat.setPadding(16, 16, 16, 16);
                seat.setGravity(View.TEXT_ALIGNMENT_CENTER);

                // Đặt màu cho ghế
                if (i < 2) { // Hàng 1, 2, 3 là ghế tiêu chuẩn
                    seat.setBackgroundColor(Color.LTGRAY); // Màu ghế tiêu chuẩn
                } else { // Hàng 4, 5, 6 là ghế VIP
                    seat.setBackgroundColor(Color.YELLOW); // Màu ghế VIP
                }

                seat.setTextColor(Color.BLACK);

                // Gán sự kiện click cho mỗi ghế
                final int rowIndex = i; // Khai báo là final
                seat.setOnClickListener(v -> {
                    onSeatClick(seat, rowIndex);
                });

                // Thêm ghế vào GridLayout
                gridLayoutSeats.addView(seat);
            }
        }
    }

    private void onSeatClick(TextView seat, int rowIndex) {
        String seatId = seat.getText().toString(); // Lấy ID ghế

        // Kiểm tra xem ghế đã được chọn chưa
        if (selectedSeats.contains(seatId)) {
            // Nếu ghế đã chọn, khôi phục lại màu sắc ban đầu
            if (rowIndex < 2) { // Ghế tiêu chuẩn
                seat.setBackgroundColor(Color.LTGRAY); // Màu ghế tiêu chuẩn
            } else { // Ghế VIP
                seat.setBackgroundColor(Color.YELLOW); // Màu ghế VIP
            }
            seat.setTextColor(Color.BLACK); // Đặt lại màu chữ
            selectedSeats.remove(seatId); // Bỏ ghế khỏi danh sách chọn

            // Giảm giá nếu ghế đã chọn
            int price = (rowIndex < 2) ? STANDARD_SEAT_PRICE : VIP_SEAT_PRICE; // Giá ghế tiêu chuẩn hoặc VIP
            totalPrice -= price; // Giảm giá từ tổng
        } else {
            // Nếu ghế chưa chọn, thay đổi màu sắc
            seat.setBackgroundColor(Color.RED); // Màu ghế khi chọn
            seat.setTextColor(Color.WHITE); // Đặt màu chữ thành trắng
            // Cập nhật giá vé
            updateTotalPrice(rowIndex);
            selectedSeats.add(seatId); // Thêm ghế vào danh sách chọn
        }
    }

    private void updateTotalPrice(int rowIndex) {
        int price = (rowIndex < 2) ? STANDARD_SEAT_PRICE : VIP_SEAT_PRICE; // Giá ghế tiêu chuẩn hoặc VIP
        totalPrice += price; // Cộng thêm vào tổng giá
        tvTotalPrice.setText("Tổng: " + totalPrice + " VND"); // Cập nhật TextView hiển thị tổng giá
    }
}