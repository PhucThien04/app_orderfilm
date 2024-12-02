package com.example.app_order;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class KhuyenmaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyenmai);

        TabLayout tabLayout = findViewById(R.id.tab_khuyenmai);
        ViewPager viewPager = findViewById(R.id.khuyenmai_viewpager);

        KhuyenmaiPagerAdapter adapter = new KhuyenmaiPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}