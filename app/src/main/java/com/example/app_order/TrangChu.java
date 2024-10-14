package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class TrangChu extends AppCompatActivity {
    private ViewPager viewPager, viewPagerFilm;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private SliderPhimAdapter slidephimAdapter;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu);

        findViewById(R.id.btnFilms).setOnClickListener(v -> {
            Intent intent = new Intent(TrangChu.this, Cinema_Lineup.class);
            startActivity(intent);
        });

        findViewById(R.id.btnPromotion).setOnClickListener(v -> {
            Intent intent = new Intent(TrangChu.this, KhuyenmaiActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnCinema).setOnClickListener(v -> {
            Intent intent = new Intent(TrangChu.this, RapphimActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnAccount).setOnClickListener(v -> {
            Intent intent = new Intent(TrangChu.this, Information_Account.class);
            startActivity(intent);
        });

        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circle_indicator);

        photoAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());



        viewPagerFilm = findViewById(R.id.viewPager_film);
        slidephimAdapter = new SliderPhimAdapter(this, getListPhim());
        viewPagerFilm.setAdapter(slidephimAdapter);
        viewPagerFilm.setPadding(20, 0, 20, 0);

        }
    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.thobaymau));
        list.add(new Photo(R.drawable.ly));
        list.add(new Photo(R.drawable.khuyenmai));

        return list;
        }

    private List<Phim> getListPhim() {
        List<Phim> ds = new ArrayList<>();
        ds.add(new Phim(R.drawable.doanhcongduoctoi));
        ds.add(new Phim(R.drawable.hanhtrinhsolo));
        ds.add(new Phim(R.drawable.lookback));
        ds.add(new Phim(R.drawable.modomdom));
        ds.add(new Phim(R.drawable.lamgiauvoima));
        ds.add(new Phim(R.drawable.treuroiyeu));
        ds.add(new Phim(R.drawable.robothoangda));
        ds.add(new Phim(R.drawable.mada));
        ds.add(new Phim(R.drawable.caubecaheo));

        return ds;
    }

}
