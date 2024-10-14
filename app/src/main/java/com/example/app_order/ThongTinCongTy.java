package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestBuilder;
//import com.bumptech.glide.load.resource.bitmap.CenterCrop;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;

public class ThongTinCongTy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_cong_ty);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        ImageView imgView = findViewById(R.id.imgCinema);
//
//        Glide.with(this).load(R.drawable.lotte_cinema_pico)
//                .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(50)))
//                .into(imgView);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(ThongTinCongTy.this, Information_Account.class);
            startActivity(intent);
        });
    }
}