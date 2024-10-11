package com.example.app_order;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RapphimActivity extends AppCompatActivity {

    // Khai báo các ListView và dữ liệu
    private ListView lvVung, lvRap;
    private String[] vungList = {"TPHCM (9)", "Hà Nội (4)"};
    private String[] rapHCM = {"Cantavil", "Cộng Hòa", "Gò Vấp", "Gold View", "Moonlight", "Nam Sài Gòn", "Nowzone", "Phú Thọ (Q11)", "Thủ Đức"};
    private String[] rapHaNoi = {"Hà Đông", "Kosmo", "Thăng Long", "West Lake"};

    // Khai báo adapter cho rạp
    private CustomAdapter rapAdapter;

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

        lvVung = findViewById(R.id.lvVung);
        lvRap = findViewById(R.id.lvRap);

        // Sử dụng CustomAdapter cho ListView Vùng
        CustomAdapter vungAdapter = new CustomAdapter(this, vungList, false);
        lvVung.setAdapter(vungAdapter);

        lvVung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vungAdapter.setSelectedPosition(position); // Đặt vị trí được chọn cho vùng

                // Cập nhật danh sách rạp tương ứng
                if (vungList[position].equals("Hà Nội (4)")) {
                    CustomAdapter rapAdapter = new CustomAdapter(RapphimActivity.this, rapHaNoi, true);
                    lvRap.setAdapter(rapAdapter);
                } else if (vungList[position].equals("TPHCM (9)")) {
                    CustomAdapter rapAdapter = new CustomAdapter(RapphimActivity.this, rapHCM, true);
                    lvRap.setAdapter(rapAdapter);
                }
            }
        });

        // Thêm OnItemClickListener cho ListView rạp
        lvRap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy adapter hiện tại của ListView rạp
                CustomAdapter rapAdapter = (CustomAdapter) lvRap.getAdapter();
                if (rapAdapter != null) {
                    rapAdapter.setSelectedPosition(position); // Đặt vị trí được chọn cho rạp
                }
            }
        });

        // Mô phỏng việc nhấp vào item đầu tiên của ListView
        lvVung.performItemClick(lvVung.getAdapter().getView(0, null, null), 0, lvVung.getAdapter().getItemId(0));
    }
}