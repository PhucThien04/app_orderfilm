package com.example.app_order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class KhuyenmaiViewPagerAdapter extends FragmentStatePagerAdapter {

    public KhuyenmaiViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KmPhimFragment();
            case 1:
                return new KmRapFragment();
            case 2:
                return new KmDoitacFragment();
            default:
                return new KmPhimFragment();
        }
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Phim";
            case 1:
                return "Rạp";
            case 2:
                return "Đối tác";
            default:
                return "Phim";
        }
    }
}