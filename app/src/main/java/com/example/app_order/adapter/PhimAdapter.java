package com.example.app_order.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_order.model.Phim;
import com.example.app_order.R;
import com.example.app_order.model.PhongChieu;

import java.util.List;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.MovieViewHolder> {
    private List<Phim> phimList;

    public PhimAdapter(List<Phim> phimList) {
        this.phimList = phimList;
    }

    @NonNull
    @Override
    public PhimAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim, parent, false);
        return new PhimAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimAdapter.MovieViewHolder holder, int position) {
        Phim phim = phimList.get(position);
        holder.tvMovieAge.setText(String.valueOf(phim.getDoTuoi()));
        holder.tvMovieTitle.setText(phim.getTenPhim());


//        GridLayout gridLayout = holder.gridLayoutRooms;
//        if (gridLayout != null) {
//            gridLayout.removeAllViews();
//        }

//        for (PhongChieu phongchieu : phim.getRooms()) {
//            // Inflate layout item_room.xml
//            View roomView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.item_phongchieu, holder.gridLayoutRooms, false);
//
//            // Lấy các TextView trong roomView
//            TextView tvRoomName = roomView.findViewById(R.id.tvPhong);
//            TextView tvStartTime = roomView.findViewById(R.id.tvGio);
//
//            // Cập nhật dữ liệu cho các TextView
//            tvRoomName.setText(phongchieu.getTenPC());
//            tvStartTime.setText(phongchieu.getGioChieu());
//
//            // Chuyển trang
//            roomView.setOnClickListener(v -> {
//                Intent intent = new Intent(holder.itemView.getContext(), DatgheActivity.class);
//                holder.itemView.getContext().startActivity(intent);
//            });
//
//            // Thêm roomView vào GridLayout
//            gridLayout.addView(roomView);
//        }
    }

    @Override
    public int getItemCount() {
        return phimList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieAge, tvMovieTitle;
//        GridLayout gridLayoutRooms;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieAge = itemView.findViewById(R.id.doTuoi);
            tvMovieTitle = itemView.findViewById(R.id.tenPhim);
//            gridLayoutRooms = itemView.findViewById(R.id.gridPhongChieu); // Khởi tạo gridLayoutRooms ở đây
        }
    }
}
