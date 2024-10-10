package com.example.app_order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class Upcoming extends Fragment {
    private ViewPager2 viewPager;
    private int[] images = {R.drawable.modomdom, R.drawable.mada, R.drawable.lamgiauvoima, R.drawable.robothoangda, R.drawable.treuroiyeu};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        viewPager = view.findViewById(R.id.viewPagerSlider_upcoming);
        ImageSliderAdapter adapter = new ImageSliderAdapter(getActivity(), images);
        viewPager.setAdapter(adapter);

        // Tự động chuyển ảnh nếu cần (không bắt buộc)
        autoSlideImages();
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