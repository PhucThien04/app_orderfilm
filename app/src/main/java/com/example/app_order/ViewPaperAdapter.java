package com.example.app_order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPaperAdapter extends FragmentStateAdapter {
    public ViewPaperAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Now_Showing();
            case 1:
                return new Upcoming();
            default:
                return new Now_Showing();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
