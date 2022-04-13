package com.cntt.adapter.ChiAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cntt.ui.chi.KhoanChiFragment;
import com.cntt.ui.chi.LoaiChiFragment;

public class ChiViewPager2Adapter extends FragmentStateAdapter {

    public ChiViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment ;
        if(position == 0){
            fragment = KhoanChiFragment.newInstance();
        }else {
            fragment = LoaiChiFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
