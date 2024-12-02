package com.example.app_order;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase {

    private static final String TAG = "Firebase";
    private FirebaseDatabase database;
    private DatabaseReference reference;

    // Constructor để khởi tạo Firebase Database
    public Firebase() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("message");
    }

    // Ghi dữ liệu vào Realtime Database
    public void writeData(String message) {
        reference.setValue(message)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Data written successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Failed to write data", e));
    }

    // Đọc dữ liệu từ Realtime Database
    public void readData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
