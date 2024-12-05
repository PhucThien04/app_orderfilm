package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RapDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvTenRap;
    private LinearLayout llMovieList;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_detail);

        tvTenRap = findViewById(R.id.tvTieude);
        llMovieList = findViewById(R.id.llMovieList);
        btnBack = findViewById(R.id.btnBack);

        // Nhận tên rạp từ Intent
        String tenRap = getIntent().getStringExtra("tenRap");
        if (tenRap != null) {
            tvTenRap.setText(tenRap); // Hiển thị tên rạp
        } else {
            tvTenRap.setText("LỖI! LỖI! LỖI!"); // Nếu không có tên rạp, hiển thị thông báo lỗi
        }

        // Quay lại RapActivity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(RapDetailActivity.this, RapActivity.class);
                startActivity(backIntent);
            }
        });

        // Kết nối Firebase
        database = FirebaseDatabase.getInstance().getReference();

        // Hiển thị danh sách phim
        displayMovies();

        // Hiển thị ngày hiện tại cho Button ngày 1
        Button btnDay1 = findViewById(R.id.btnDay1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        btnDay1.setText(currentDate);
    }

    private void displayMovies() {
        database.child("Phim").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                llMovieList.removeAllViews(); // Xóa tất cả các view cũ trong danh sách phim

                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                    String movieName = movieSnapshot.child("TenPhim").getValue(String.class);
                    int movieAge = movieSnapshot.child("DoTuoi").getValue(Integer.class);

                    // Tạo một dòng cho mỗi phim
                    LinearLayout movieLayout = new LinearLayout(RapDetailActivity.this);
                    movieLayout.setOrientation(LinearLayout.VERTICAL);
                    movieLayout.setPadding(0, 20, 0, 20);

                    // Tạo TextView cho tên phim
                    TextView movieTitle = new TextView(RapDetailActivity.this);
                    movieTitle.setText(movieName);
                    movieTitle.setTextSize(18);
                    movieTitle.setPadding(10, 0, 10, 0);

                    // Tạo Button hiển thị độ tuổi cho phim
                    Button ageButton = new Button(RapDetailActivity.this);
                    ageButton.setText(String.valueOf(movieAge) + "+");
                    ageButton.setBackgroundResource(R.drawable.circle_button); // Giả sử bạn đã tạo drawable cho nút hình tròn
                    ageButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    // Thêm tên phim và độ tuổi vào layout của phim
                    movieLayout.addView(movieTitle);
                    movieLayout.addView(ageButton);

                    // Hiển thị các phòng chiếu của phim
                    displayScreenings(movieLayout, movieSnapshot.child("IDPhim").getValue(Integer.class));

                    // Thêm phim vào danh sách
                    llMovieList.addView(movieLayout);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RapDetailActivity.this, "Lỗi kết nối Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayScreenings(LinearLayout movieLayout, int movieId) {
        database.child("LichChieu").orderByChild("IDPhim").equalTo(movieId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LinearLayout screeningsLayout = new LinearLayout(RapDetailActivity.this);
                screeningsLayout.setOrientation(LinearLayout.VERTICAL);
                screeningsLayout.setPadding(10, 10, 10, 10);

                for (DataSnapshot screeningSnapshot : dataSnapshot.getChildren()) {
                    String startTime = screeningSnapshot.child("BatDau").getValue(String.class);
                    String screenName = screeningSnapshot.child("IDPhongChieu").getValue(Integer.class).toString(); // Bạn cần lấy tên phòng từ Firebase

                    // Tạo TextView cho mỗi phòng chiếu
                    TextView screeningText = new TextView(RapDetailActivity.this);
                    screeningText.setText("Phòng " + screenName + " - Giờ chiếu: " + startTime);
                    screeningText.setTextSize(16);

                    screeningsLayout.addView(screeningText);
                }

                // Thêm phòng chiếu vào layout phim
                movieLayout.addView(screeningsLayout);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RapDetailActivity.this, "Lỗi kết nối Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
}