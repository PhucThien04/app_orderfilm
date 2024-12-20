package com.example.app_order;

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

import com.example.app_order.activity.RapActivity;
import com.example.app_order.R;
import com.example.app_order.adapter.PhimAdapter;
import com.example.app_order.model.Phim;
import com.example.app_order.model.Rap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChitietrapphimActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView tvTieude, tvDoTuoi, tvTenPhim;
    private ImageView btnBack;
    private Button btnDay1, btnDay2, btnDay3, btnDay4, btnDay5, btnDay6, btnDay7, selectedButton;
    private RecyclerView recyclerViewMovies;
    private PhimAdapter phimAdapter;
    private List<Phim> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_detail);

        // Ánh xạ các view
        tvTieude = findViewById(R.id.tvTieude);
        btnBack = findViewById(R.id.btnBack);

        btnDay1 = findViewById(R.id.btnDay1);
        btnDay2 = findViewById(R.id.btnDay2);
        btnDay3 = findViewById(R.id.btnDay3);
        btnDay4 = findViewById(R.id.btnDay4);
        btnDay5 = findViewById(R.id.btnDay5);
        btnDay6 = findViewById(R.id.btnDay6);
        btnDay7 = findViewById(R.id.btnDay7);

        recyclerViewMovies = findViewById(R.id.recyclerView);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();
        //phimAdapter = new com.example.app_order.PhimAdapter(this, movieList);
        recyclerViewMovies.setAdapter(phimAdapter);

        // Nút quay lại
        btnBack.setOnClickListener(view -> {
            Intent backIntent = new Intent(ChitietrapphimActivity.this, RapActivity.class);
            startActivity(backIntent);
        });

        // Cập nhật các nút ngày
        updateDateButtons();

        // Lắng nghe sự kiện click cho các nút ngày
        setUpButtonClickListener(btnDay1);
        setUpButtonClickListener(btnDay2);
        setUpButtonClickListener(btnDay3);
        setUpButtonClickListener(btnDay4);
        setUpButtonClickListener(btnDay5);
        setUpButtonClickListener(btnDay6);
        setUpButtonClickListener(btnDay7);

        // Nhận dữ liệu từ Intent
        Rap selectedRap = (Rap) getIntent().getSerializableExtra("selectedRap");

        if (selectedRap != null) {
            // Hiển thị tên rạp
            tvTieude.setText(selectedRap.getTenRap());
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin rạp", Toast.LENGTH_SHORT).show();
        }

        // Tải dữ liệu phim từ Firebase
        loadMoviesFromFirebase();
    }

    // Hàm cập nhật các nút ngày
    private void updateDateButtons() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

        btnDay1.setText(dateFormat.format(calendar.getTime()));

        for (int i = 1; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Button btn = findViewById(getResources().getIdentifier("btnDay" + (i + 1), "id", getPackageName()));
            btn.setText(dateFormat.format(calendar.getTime()));
        }
    }

    // Cài đặt sự kiện cho các nút ngày
    private void setUpButtonClickListener(Button button) {
        if (selectedButton == null) {
            selectedButton = btnDay1;
        }

        button.setOnClickListener(v -> {
            // Đặt lại trạng thái nút trước đó
            if (selectedButton != null) {
                selectedButton.setBackgroundResource(R.drawable.rounded_btnday_default);
                selectedButton.setTextColor(Color.BLACK);
            }

            // Cập nhật trạng thái nút được chọn
            selectedButton = button;
            selectedButton.setBackgroundResource(R.drawable.rounded_btnday_selected);
            selectedButton.setTextColor(Color.WHITE);
        });
    }

    // Tải danh sách phim từ Firebase
    private void loadMoviesFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance("https://apporderfilm-14fd9-default-rtdb.firebaseio.com/").getReference("Phim");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movieList.clear();

                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    String tenPhim = movieSnapshot.child("tenPhim").getValue(String.class);
                    Integer doTuoi = movieSnapshot.child("doTuoi").getValue(Integer.class);

                    if (tenPhim != null && doTuoi != null) {
                        movieList.add(new Phim());
                    }
                }

                phimAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChitietrapphimActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
