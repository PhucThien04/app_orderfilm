package com.example.app_order;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RapphimAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private int selectedPosition = -1; // Vị trí item được chọn
    private boolean isRapList; // Để xác định đây là ListView rạp hay không

    public RapphimAdapter(Context context, String[] values, boolean isRapList) {
        super(context, R.layout.activity_itemrapphim, values);
        this.context = context;
        this.values = values;
        this.isRapList = isRapList;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Cập nhật UI
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_itemrapphim, parent, false);
        TextView textView = rowView.findViewById(R.id.tvItem);
        textView.setText(values[position]);

        // Thay đổi nền và màu chữ dựa trên vị trí được chọn
        if (isRapList) {
            if (position == selectedPosition) {
                rowView.setBackgroundColor(Color.parseColor("#1E1E2A")); // Nền cho rạp được chọn
                textView.setTextColor(Color.parseColor("#FFFFFF")); // Chữ trắng
            } else {
                rowView.setBackgroundColor(Color.TRANSPARENT); // Nền mặc định
                textView.setTextColor(Color.parseColor("#000000")); // Chữ đen
            }
        } else {
            if (position == selectedPosition) {
                rowView.setBackgroundColor(Color.parseColor("#FFF8E1")); // Nền cho vùng được chọn
                textView.setTextColor(Color.parseColor("#000000")); // Chữ đen
            } else {
                rowView.setBackgroundColor(Color.TRANSPARENT); // Nền mặc định
                textView.setTextColor(Color.parseColor("#000000")); // Chữ đen
            }
        }

        return rowView;
    }
}
