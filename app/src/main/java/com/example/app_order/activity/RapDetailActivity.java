package com.example.app_order.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_order.model.Phimchieu;
import com.example.app_order.adapter.PhimAdapter;
import com.example.app_order.R;
import com.example.app_order.model.LichChieu;
import com.example.app_order.model.Phim;
import com.example.app_order.model.PhongChieu;
import com.example.app_order.model.Rap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RapDetailActivity extends AppCompatActivity {

    private TextView tvTieude, btnThongtin;
    private ImageView btnBack;
    private Button btnDay1, btnDay2, btnDay3, btnDay4, btnDay5, btnDay6, btnDay7, selectedButton;
    private RecyclerView recyclerViewMovies;
    private PhimAdapter phimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_detail);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(RapDetailActivity.this, RapActivity.class);
                startActivity(Back);
            }
        });

        tvTieude = findViewById(R.id.tvTieude);
        btnThongtin = findViewById(R.id.btnThongtin);

        // Thiết lập nút mặc định được chọn
        btnDay1 = findViewById(R.id.btnDay1);
        btnDay1.setBackgroundResource(R.drawable.rounded_btnday_selected);
        btnDay1.setTextColor(Color.WHITE);
        btnDay2 = findViewById(R.id.btnDay2);
        btnDay3 = findViewById(R.id.btnDay3);
        btnDay4 = findViewById(R.id.btnDay4);
        btnDay5 = findViewById(R.id.btnDay5);
        btnDay6 = findViewById(R.id.btnDay6);
        btnDay7 = findViewById(R.id.btnDay7);

        // Cập nhật các nút ngày
        updateDateButtons();

        // Lắng nghe sự kiện click cho các nút
        setUpButtonClickListener(btnDay1);
        setUpButtonClickListener(btnDay2);
        setUpButtonClickListener(btnDay3);
        setUpButtonClickListener(btnDay4);
        setUpButtonClickListener(btnDay5);
        setUpButtonClickListener(btnDay6);
        setUpButtonClickListener(btnDay7);

        // Nhận đối tượng Rap từ Intent
        Rap selectedRap = (Rap) getIntent().getSerializableExtra("selectedRap");

        if (selectedRap != null) {
            // Hiển thị tên rạp từ đối tượng Rap
            tvTieude.setText(selectedRap.getTenRap());
        }

        // Sự kiện nút thông tin
        btnThongtin.setOnClickListener(v -> {
            if (selectedRap != null) {
                Intent intent = new Intent(RapDetailActivity.this, RapInfoActivity.class);
                intent.putExtra("infoRap", selectedRap); // Truyền đối tượng Rap
                startActivity(intent);
            } else {
                Toast.makeText(RapDetailActivity.this, "Thông tin rạp không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });

        // Cài đặt RecyclerView và Adapter
        recyclerViewMovies = findViewById(R.id.recyclerView);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        List<Phimchieu> phimchieuList = createMovieData();

        phimAdapter = new PhimAdapter(phimchieuList);
        recyclerViewMovies.setAdapter(phimAdapter);
    }

    // Update ngày cho nút
    private void updateDateButtons() {
        Calendar calendar = Calendar.getInstance(); // Lấy ngày hiện tại
        Date currentDate = calendar.getTime(); // Ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd"); // Định dạng ngày

        // Đặt ngày cho btnDay1 và các nút tiếp theo
        btnDay1.setText(dateFormat.format(currentDate)); // btnDay1 là ngày hôm nay

        for (int i = 1; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Thêm 1 ngày
            Button btn = (Button) findViewById(getResources().getIdentifier("btnDay" + (i+1), "id", getPackageName()));
            btn.setText(dateFormat.format(calendar.getTime()));
        }
    }

    // Hàm sự kiện cho các nút
    private void setUpButtonClickListener(Button button) {
        selectedButton = btnDay1;
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

        List<PhongChieu> rooms1 = new ArrayList<>();
        rooms1.add(new PhongChieu("Screen 2", "10:00"));
        rooms1.add(new PhongChieu("Screen 3", "12:00"));
        rooms1.add(new PhongChieu("Screen 4", "14:00"));
        rooms1.add(new PhongChieu("Screen 5", "13:00"));
        phimchieuList.add(new Phimchieu(18, "CÁM", rooms1));

        List<PhongChieu> rooms2 = new ArrayList<>();
        rooms2.add(new PhongChieu("Screen 2", "11:00"));
        rooms2.add(new PhongChieu("Screen 3", "13:00"));
        phimchieuList.add(new Phimchieu(16, "ĐỐ ANH CÒNG ĐƯỢC TÔI", rooms2));

        return phimchieuList;
    }

}