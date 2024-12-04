package com.example.app_order;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

        // Khu vực adapter với layout tùy chỉnh
        khuVucAdapter = new ArrayAdapter<String>(this, R.layout.item_rap, R.id.tvItem, khuVucList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if (position == selectedKhuVucIndex) {
                    textView.setBackgroundColor(Color.parseColor("#FFF8E1"));
                    textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                } else {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                }
                return textView;
            }
        };

        // Rạp adapter với layout tùy chỉnh
        rapAdapter = new ArrayAdapter<String>(this, R.layout.item_rap, R.id.tvItem, rapList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if (rapList.get(position).equals(selectedRap)) {
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                    textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                } else {
                    textView.setBackgroundColor(Color.parseColor("#FFF8E1"));
                    textView.setTextColor(Color.BLACK);
                    textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                }
                return textView;
            }
        };

        listViewKhuvuc.setAdapter(khuVucAdapter);
        listViewRap.setAdapter(rapAdapter);

        loadKhuVucData();

        listViewKhuvuc.setOnItemClickListener((parent, view, position, id) -> {
            selectedKhuVucIndex = position;
            updateRapList();
            khuVucAdapter.notifyDataSetChanged();
        });

        listViewRap.setOnItemClickListener((parent, view, position, id) -> {
            selectedRap = rapList.get(position);
            rapAdapter.notifyDataSetChanged();
        });

        btnTieptuc.setOnClickListener(v -> {
            if (selectedRap == null) {
                Toast.makeText(RapActivity.this, "Vui lòng chọn một rạp!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(RapActivity.this, RapDetailActivity.class);
                intent.putExtra("tenRap", selectedRap);
                startActivity(intent);
            }
        });
    }

    private void loadKhuVucData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("khuvuc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                khuVucList.clear();
                khuVucRapMap.clear();
                for (DataSnapshot khuVucSnapshot : snapshot.getChildren()) {
                    String khuVucTen = khuVucSnapshot.child("ten").getValue(String.class);
                    if (khuVucTen != null) {
                        khuVucList.add(khuVucTen);

                        ArrayList<String> rapListTemp = new ArrayList<>();
                        for (DataSnapshot rapSnapshot : khuVucSnapshot.child("rap").getChildren()) {
                            rapListTemp.add(rapSnapshot.getValue(String.class));
                        }
                        khuVucRapMap.put(khuVucTen, rapListTemp);
                    }
                }
                khuVucAdapter.notifyDataSetChanged();
                updateRapList(); // Cập nhật danh sách rạp theo khu vực đầu tiên
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RapActivity.this, "Lỗi tải dữ liệu từ Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRapList() {
        rapList.clear();
        if (selectedKhuVucIndex < khuVucList.size()) {
            String selectedKhuVuc = khuVucList.get(selectedKhuVucIndex);
            if (khuVucRapMap.containsKey(selectedKhuVuc)) {
                rapList.addAll(khuVucRapMap.get(selectedKhuVuc));
            }
        }
        selectedRap = null; // Reset rạp được chọn
        rapAdapter.notifyDataSetChanged();
    }
}
