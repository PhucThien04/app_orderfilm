package com.example.app_order;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RapActivity extends AppCompatActivity {

    private ListView listViewKhuvuc, listViewRap;
    private Button btnTieptuc;

    private ArrayList<String> khuVucList, rapList;
    private ArrayAdapter<String> khuVucAdapter, rapAdapter;
    private HashMap<String, ArrayList<String>> khuVucRapMap;

    private int selectedKhuVucIndex = 0; // Mặc định chọn khu vực đầu tiên
    private String selectedRap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewKhuvuc = findViewById(R.id.listViewKhuvuc);
        listViewRap = findViewById(R.id.listViewRap);
        btnTieptuc = findViewById(R.id.btnTieptuc);

        khuVucList = new ArrayList<>();
        rapList = new ArrayList<>();
        khuVucRapMap = new HashMap<>();

        // Tạo adapter
        khuVucAdapter = new ArrayAdapter<String>(this, R.layout.item_rap, R.id.tvItem, khuVucList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if (position == selectedKhuVucIndex) {
                    textView.setBackgroundColor(Color.parseColor("#FFF8E1"));
                } else {
                    textView.setBackgroundColor(Color.WHITE);
                }
                return textView;
            }
        };

        rapAdapter = new ArrayAdapter<String>(this, R.layout.item_rap, R.id.tvItem, rapList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if (rapList.get(position).equals(selectedRap)) {
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                } else {
                    textView.setBackgroundColor(Color.parseColor("#FFF8E1"));
                    textView.setTextColor(Color.BLACK);
                }
                return textView;
            }
        };

        listViewKhuvuc.setAdapter(khuVucAdapter);
        listViewRap.setAdapter(rapAdapter);

        // Tải dữ liệu từ Firebase
        loadFirebaseData();

        // Sự kiện chọn khu vực
        listViewKhuvuc.setOnItemClickListener((adapterView, view, position, id) -> {
            selectedKhuVucIndex = position;
            String selectedKhuVuc = khuVucList.get(position);
            rapList.clear();
            rapList.addAll(khuVucRapMap.getOrDefault(selectedKhuVuc, new ArrayList<>()));
            rapAdapter.notifyDataSetChanged();
            khuVucAdapter.notifyDataSetChanged();
        });

        // Sự kiện chọn rạp
        listViewRap.setOnItemClickListener((adapterView, view, position, id) -> {
            selectedRap = rapList.get(position);
            rapAdapter.notifyDataSetChanged();
        });

        // Nút tiếp tục
        btnTieptuc.setOnClickListener(v -> {
            if (selectedRap != null) {
                Toast.makeText(this, "Đã chọn rạp: " + selectedRap, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng chọn một rạp!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFirebaseData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Lưu trữ danh sách khu vực
        databaseReference.child("KhuVuc").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot khuVucSnapshot) {
                khuVucList.clear();
                for (DataSnapshot khuVuc : khuVucSnapshot.getChildren()) {
                    String tenKV = khuVuc.child("TenKV").getValue(String.class);
                    khuVucList.add(tenKV);
                }
                khuVucAdapter.notifyDataSetChanged();

                // Mặc định chọn khu vực đầu tiên
                if (!khuVucList.isEmpty()) {
                    listViewKhuvuc.performItemClick(null, 0, 0);
                }

                // Tiếp tục tải danh sách rạp
                databaseReference.child("Rap").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot rapSnapshot) {
                        khuVucRapMap.clear();
                        for (DataSnapshot rap : rapSnapshot.getChildren()) {
                            String tenRap = rap.child("TenRap").getValue(String.class);
                            Long idKhuVuc = rap.child("IDKhuVuc").getValue(Long.class);

                            if (idKhuVuc != null) {
                                String khuVucName = findKhuVucById(khuVucSnapshot, idKhuVuc.intValue());
                                if (khuVucName != null) {
                                    if (!khuVucRapMap.containsKey(khuVucName)) {
                                        khuVucRapMap.put(khuVucName, new ArrayList<>());
                                    }
                                    khuVucRapMap.get(khuVucName).add(tenRap);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RapActivity.this, "Lỗi khi tải rạp", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RapActivity.this, "Lỗi khi tải khu vực", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String findKhuVucById(DataSnapshot khuVucSnapshot, int idKhuVuc) {
        for (DataSnapshot khuVuc : khuVucSnapshot.getChildren()) {
            Long id = khuVuc.child("IDKhuVuc").getValue(Long.class);
            if (id != null && id == idKhuVuc) {
                return khuVuc.child("TenKV").getValue(String.class);
            }
        }
        return null;
    }
}
