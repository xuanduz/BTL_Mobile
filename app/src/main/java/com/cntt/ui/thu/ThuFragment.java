package com.cntt.ui.thu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cntt.R;
import com.cntt.adapter.ThuAdapter.ThuViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

// Cho phép tìm kiếm ViewPager2 & TabLayout
public class ThuFragment extends Fragment {

    private ViewPager2 mVp;
    private TabLayout mTl;

    public ThuFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ThuFragment newInstance(String param1, String param2) {
        ThuFragment fragment = new ThuFragment();

        return fragment;
    }

//    Tìm kiếm theo id và tạo view cho ThuFragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVp =view.findViewById(R.id.viewPager2);
        mTl = view.findViewById(R.id.tabLayout);

        ThuViewPager2Adapter adapter = new ThuViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Khoản Thu");
                    tab.setIcon(R.drawable.ic_action_money);
                }else{
                    tab.setText("Loại Thu");
                    tab.setIcon(R.drawable.ic_action_money);
                }
            }
        }).attach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thu, container, false);
    }
}