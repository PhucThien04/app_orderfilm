package com.example.app_order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KmDoitacFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> khuyenMaiList;
    private ArrayList<String> khuyenMaiMota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_km_doitac, container, false);

        listView = view.findViewById(R.id.listViewDoitac);
        khuyenMaiList = new ArrayList<>();
        khuyenMaiMota = new ArrayList<>();
        adapter = new ArrayAdapter<String>(requireContext(), R.layout.list_item_khuyenmai, R.id.textViewKhuyenMai, khuyenMaiList);
        listView.setAdapter(adapter);

        // Kết nối Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("khuyenmai/doitac");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                khuyenMaiList.clear();
                khuyenMaiMota.clear();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String tieude = childSnapshot.child("tieude").getValue(String.class);
                    String mota = childSnapshot.child("mota").getValue(String.class);

                    khuyenMaiList.add(tieude);
                    khuyenMaiMota.add(mota);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "Lỗi kết nối Firebase!", Toast.LENGTH_SHORT).show();
            }
        });

        // Hiển thị mô tả khuyến mãi khi nhấn vào item
        listView.setOnItemClickListener((AdapterView<?> parent, View view1, int position, long id) -> {
            // Lấy thông tin tiêu đề và mô tả
            String tieude = khuyenMaiList.get(position);
            String mota = khuyenMaiMota.get(position);

            // Chuyển sang KhuyenmaiDetailActivity
            Intent intent = new Intent(requireContext(), KhuyenmaiDetailActivity.class);
            intent.putExtra("tieude", tieude);
            intent.putExtra("mota", mota);
            startActivity(intent);
        });

        return view;
    }
}