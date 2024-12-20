package com.example.app_order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app_order.R;
import com.example.app_order.TrangChu;
import com.example.app_order.adapter.KhuyenmaiViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class KhuyenmaiActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khuyenmai);

        tabLayout = findViewById(R.id.tab_khuyenmai);
        viewPager = findViewById(R.id.khuyenmai_viewpager);
        btn_back = findViewById(R.id.btnBack);

        KhuyenmaiViewPagerAdapter viewPagerAdapter = new KhuyenmaiViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhuyenmaiActivity.this, TrangChu.class);
                startActivity(intent);
            }
        });
    }
}