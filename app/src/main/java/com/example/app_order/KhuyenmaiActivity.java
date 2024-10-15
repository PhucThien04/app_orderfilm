package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class KhuyenmaiActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khuyenmai);

        tabLayout = findViewById(R.id.tab_khuyenmai);
        viewPager = findViewById(R.id.khuyenmai_viewpager);

        KhuyenmaiViewPagerAdapter viewPagerAdapter = new KhuyenmaiViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(KhuyenmaiActivity.this, TrangChu.class);
            startActivity(intent);
        });
    }
}