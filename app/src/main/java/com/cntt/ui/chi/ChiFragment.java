package com.cntt.ui.chi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cntt.R;
import com.cntt.adapter.ChiAdapter.ChiViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ChiFragment extends Fragment {

    private ViewPager2 mVp;
    private TabLayout mTl;

    public ChiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChiFragment newInstance(String param1, String param2) {
        ChiFragment fragment = new ChiFragment();
        return fragment;
    }
    //    Tìm kiếm theo id và tạo view cho ChiFragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVp = view.findViewById(R.id.viewPager2);
        mTl = view.findViewById(R.id.tabLayout);

        ChiViewPager2Adapter adapter = new ChiViewPager2Adapter((getActivity()));
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Khoản Chi");
                    tab.setIcon(R.drawable.ic_action_money);
                }else {
                    tab.setText("Loại Chi");
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
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }
}