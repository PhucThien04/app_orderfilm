package com.example.app_order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChitietrapphimActivity extends AppCompatActivity {

    // Nút
    private ImageView btnBack;
    private TextView btnDiachi;
    private TextView tvRap;

    private Button selectedButton;

    private RecyclerView recyclerViewMovies;
    private PhimAdapter phimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietrapphim);

        //Nút
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(ChitietrapphimActivity.this, RapActivity.class);
                startActivity(Back);
            }
        });
        btnDiachi = findViewById(R.id.btnDchi);
        btnDiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Dchi = new Intent(ChitietrapphimActivity.this, DiachiActivity.class);
                startActivity(Dchi);
            }
        });
        TextView textViewRap = findViewById(R.id.tvTieude);
        String tenRap = getIntent().getStringExtra("tenRap");

        if (tenRap != null) {
            textViewRap.setText(tenRap);
        }
        // Thiết lập nút mặc định được chọn
        selectedButton = findViewById(R.id.btnMonday);
        selectedButton.setBackgroundResource(R.drawable.rounded_btnday_selected);
        selectedButton.setTextColor(Color.WHITE);

        // Lắng nghe sự kiện click cho các nút
        setUpButtonClickListener(R.id.btnMonday);
        setUpButtonClickListener(R.id.btnTuesday);
        setUpButtonClickListener(R.id.btnWednesday);
        setUpButtonClickListener(R.id.btnThursday);
        setUpButtonClickListener(R.id.btnFriday);
        setUpButtonClickListener(R.id.btnSaturday);
        setUpButtonClickListener(R.id.btnSunday);

        // Thiết lập RecyclerView
        recyclerViewMovies = findViewById(R.id.revPhim);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách phim
        List<Phimchieu> phimchieuList = createMovieData();

        // Thiết lập adapter cho RecyclerView
        phimAdapter = new PhimAdapter(phimchieuList);
        recyclerViewMovies.setAdapter(phimAdapter);
    }

    private void setUpButtonClickListener(int buttonId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Đặt lại màu cho nút đã chọn trước đó
            if (selectedButton != null) {
                selectedButton.setBackgroundResource(R.drawable.rounded_btnday_default);
                selectedButton.setTextColor(Color.BLACK);
            }
            // Cập nhật nút được chọn mới
            selectedButton = button;
            selectedButton.setBackgroundResource(R.drawable.rounded_btnday_selected);
            selectedButton.setTextColor(Color.WHITE);
        });
    }

    // Phương thức tạo dữ liệu mẫu cho danh sách phim
    private List<Phimchieu> createMovieData() {
        List<Phimchieu> phimchieuList = new ArrayList<>();

        List<Phongchieu> rooms1 = new ArrayList<>();
        rooms1.add(new Phongchieu("Screen 2", "10:00", 30));
        rooms1.add(new Phongchieu("Screen 3", "12:00", 25));
        rooms1.add(new Phongchieu("Screen 4", "14:00", 10));
        rooms1.add(new Phongchieu("Screen 5", "13:00", 10));
        phimchieuList.add(new Phimchieu("CÁM", "2D Lồng Tiếng", rooms1)); // Thêm loại phim

        List<Phongchieu> rooms2 = new ArrayList<>();
        rooms2.add(new Phongchieu("Screen 5", "11:00", 5));
        rooms2.add(new Phongchieu("Screen 6", "13:00", 20));
        phimchieuList.add(new Phimchieu("ĐỐ ANH CÒNG ĐƯỢC TÔI", "2D Phụ Đề", rooms2)); // Thêm loại phim

        return phimchieuList;
    }
}
