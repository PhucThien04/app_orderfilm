package com.example.app_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class SliderPhimAdapter extends PagerAdapter {
    private Context mContext;
    private List<Phim> mListPhim;

    public SliderPhimAdapter(Context mContext, List<Phim> mListPhim) {
        this.mContext = mContext;
        this.mListPhim = mListPhim;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.sliderfilm, container, false);

        ImageView img = view.findViewById(R.id.img_film);

        Phim phim = mListPhim.get(position);

        if(phim != null){
            Glide.with(mContext).load(phim.getResourceID()).into(img);
        }
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(mListPhim != null){
            return mListPhim.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
