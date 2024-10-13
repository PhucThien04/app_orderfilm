package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RapphimActivity extends AppCompatActivity {

    // Nút sang trang mới
    private Button btnNext;
    // Khai báo các ListView và dữ liệu
    private ListView lvVung, lvRap;
    private String[] vungList = {"TPHCM (9)", "Hà Nội (4)"};
    private String[] rapHCM = {"Cantavil", "Cộng Hòa", "Gò Vấp", "Gold View", "Moonlight", "Nam Sài Gòn", "Nowzone", "Phú Thọ (Q11)", "Thủ Đức"};
    private String[] rapHaNoi = {"Hà Đông", "Kosmo", "Thăng Long", "West Lake"};

    // Khai báo adapter cho rạp
    private RapphimAdapter rapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rapphim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Nút sang trang tiếp
        btnNext = findViewById(R.id.btnTieptuc);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Next = new Intent(RapphimActivity.this, ChitietrapphimActivity.class);
                startActivity(Next);
            }
        });

        lvVung = findViewById(R.id.lvVung);
        lvRap = findViewById(R.id.lvRap);

        // Sử dụng CustomAdapter cho ListView Vùng
        RapphimAdapter vungAdapter = new RapphimAdapter(this, vungList, false);
        lvVung.setAdapter(vungAdapter);

        lvVung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vungAdapter.setSelectedPosition(position); // Đặt vị trí được chọn cho vùng

                // Cập nhật danh sách rạp tương ứng
                if (vungList[position].equals("Hà Nội (4)")) {
                    RapphimAdapter rapAdapter = new RapphimAdapter(RapphimActivity.this, rapHaNoi, true);
                    lvRap.setAdapter(rapAdapter);
                } else if (vungList[position].equals("TPHCM (9)")) {
                    RapphimAdapter rapAdapter = new RapphimAdapter(RapphimActivity.this, rapHCM, true);
                    lvRap.setAdapter(rapAdapter);
                }
            }
        });

        // Thêm OnItemClickListener cho ListView rạp
        lvRap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy adapter hiện tại của ListView rạp
                RapphimAdapter rapAdapter = (RapphimAdapter) lvRap.getAdapter();
                if (rapAdapter != null) {
                    rapAdapter.setSelectedPosition(position); // Đặt vị trí được chọn cho rạp
                }
            }
        });

        // Mô phỏng việc nhấp vào item đầu tiên của ListView
        lvVung.performItemClick(lvVung.getAdapter().getView(0, null, null), 0, lvVung.getAdapter().getItemId(0));
    }
}