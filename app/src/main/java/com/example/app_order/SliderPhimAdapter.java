package com.example.app_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class SliderPhimAdapter extends RecyclerView.Adapter<SliderPhimAdapter.PhotoViewHolder> {
    public SliderPhimAdapter(List<Phim> mListPhim) {
        this.mListPhim = mListPhim;
    }

    private final List<Phim> mListPhim;

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliderfilm, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Phim phim = mListPhim.get(position);
        if (phim == null){
            return;
        }
        holder.imgFilm.setImageResource(phim.getResourceID());
    }

    @Override
    public int getItemCount() {
        if(mListPhim!=null){
            return mListPhim.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends  RecyclerView.ViewHolder{
        private final ImageView imgFilm;

        public PhotoViewHolder (@NonNull View itemview){
            super(itemview);
            imgFilm = itemview.findViewById(R.id.img_film);
        }
    }


}
