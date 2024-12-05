package com.example.app_order;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.MovieViewHolder> {
    private List<Phimchieu> phimchieuList;

    public PhimAdapter(List<Phimchieu> phimchieuList) {
        this.phimchieuList = phimchieuList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phimchieu, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Phimchieu phimchieu = phimchieuList.get(position);
        holder.tvMovieTitle.setText(phimchieu.getTitle());
        holder.tvMovieType.setText(phimchieu.getType());

        // Thêm các phòng chiếu vào GridLayout
        holder.gridLayoutRooms.removeAllViews(); // Xóa các view cũ

        for (Phongchieu phongchieu : phimchieu.getRooms()) {
            // Inflate layout item_room.xml
            View roomView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.item_phongchieu, holder.gridLayoutRooms, false);

            // Lấy các TextView trong roomView
            TextView tvRoomName = roomView.findViewById(R.id.tvPhong);
            TextView tvStartTime = roomView.findViewById(R.id.tvGio);
            TextView tvAvailableSeats = roomView.findViewById(R.id.tvGhe);

            // Cập nhật dữ liệu cho các TextView
            tvRoomName.setText(phongchieu.getName());
            tvStartTime.setText(phongchieu.getStartTime());
            tvAvailableSeats.setText("Ghế còn: " + phongchieu.getAvailableSeats());

            //Chuyển trang
            roomView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DatgheActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });

            // Thêm roomView vào GridLayout
            holder.gridLayoutRooms.addView(roomView);
        }
    }

    @Override
    public int getItemCount() {
        return phimchieuList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieTitle;
        TextView tvMovieType;
        GridLayout gridLayoutRooms;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tvTenphim);
            tvMovieType = itemView.findViewById(R.id.tvLoaiphim);
            gridLayoutRooms = itemView.findViewById(R.id.gridPhong);
        }

        void populateRoomButtons(List<Phongchieu> phongchieuList) {
            gridLayoutRooms.removeAllViews(); // Xóa các nút cũ
            for (Phongchieu phongchieu : phongchieuList) {
                View roomView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_phongchieu, gridLayoutRooms, false);
                TextView tvRoomName = roomView.findViewById(R.id.tvPhong);
                TextView tvStartTime = roomView.findViewById(R.id.tvGio);
                TextView tvAvailableSeats = roomView.findViewById(R.id.tvGhe);

                tvRoomName.setText(phongchieu.getName());
                tvStartTime.setText(phongchieu.getStartTime());
                tvAvailableSeats.setText("Ghế còn: " + phongchieu.getAvailableSeats());

                gridLayoutRooms.addView(roomView);
            }
        }
    }
}
