package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class Now_Showing extends Fragment {
    private ViewPager2 viewPager;
    private int[] images = {R.drawable.cam, R.drawable.doanhcongduoctoi, R.drawable.caubecaheo, R.drawable.hanhtrinhsolo, R.drawable.lookback};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_showing, container, false);
        viewPager = view.findViewById(R.id.viewPagerSlider_nowshowing);
        ImageSliderAdapter adapter = new ImageSliderAdapter(getActivity(), images);
        viewPager.setAdapter(adapter);

        // Tự động chuyển ảnh nếu cần (không bắt buộc)
        autoSlideImages();

        view.findViewById(R.id.imgMovie_1).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailMovie_Cam.class);
            startActivity(intent);
        });

        view.findViewById(R.id.imgMovie_2).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailMovie_DoAnhCongDuocToi.class);
            startActivity(intent);
        });
        view.findViewById(R.id.btnBuyTicket_1).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RapActivity.class);
            startActivity(intent);
        });
        view.findViewById(R.id.btnBuyTicket_2).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RapActivity.class);
            startActivity(intent);
        });
        return view;
    }

    // Phương thức tự động chuyển ảnh
    private void autoSlideImages() {
        final Handler sliderHandler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % images.length;
                viewPager.setCurrentItem(nextItem, true);
                sliderHandler.postDelayed(this, 3000); // Chuyển ảnh mỗi 3 giây
            }
        };
        sliderHandler.postDelayed(runnable, 3000);
    }
}