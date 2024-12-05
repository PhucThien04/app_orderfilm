package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RapDetailActivity extends AppCompatActivity {

    private TextView tvTieude, btnThongtin;
    private ImageView btnBack;
    private Button btnDay1, btnDay2, btnDay3, btnDay4, btnDay5, btnDay6, btnDay7;

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
        btnDay1 = findViewById(R.id.btnDay1);
        btnDay2 = findViewById(R.id.btnDay2);
        btnDay3 = findViewById(R.id.btnDay3);
        btnDay4 = findViewById(R.id.btnDay4);
        btnDay5 = findViewById(R.id.btnDay5);
        btnDay6 = findViewById(R.id.btnDay6);
        btnDay7 = findViewById(R.id.btnDay7);

        // Nhận đối tượng Rap từ Intent
        Rap selectedRap = (Rap) getIntent().getSerializableExtra("selectedRap");

        if (selectedRap != null) {
            // Hiển thị tên rạp từ đối tượng Rap
            tvTieude.setText(selectedRap.getTenRap());
        }

        btnThongtin.setOnClickListener(v -> {
            // Truyền đối tượng Rap vào Intent
            Rap infoRap = (Rap) getIntent().getSerializableExtra("selectedRap");

            if (infoRap != null) {
                Intent intent = new Intent(RapDetailActivity.this, RapInfoActivity.class);
                intent.putExtra("selectedRap", infoRap); // Truyền đối tượng Rap
                startActivity(intent);
            } else {
                Toast.makeText(RapDetailActivity.this, "Thông tin rạp không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });

        // Cập nhật các nút ngày
        updateDateButtons();
    }

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
}
