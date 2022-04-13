package com.cntt.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cntt.R;
import com.cntt.adapter.ChiAdapter.ChiRecycleViewAdapter;
import com.cntt.adapter.ItemClickListener;


import com.cntt.dialog.chidialog_detail.ChiDetailDialog;
import com.cntt.dialog.chidialog_detail.ChiDialog;
import com.cntt.entity.Chi;


import java.util.List;


public class KhoanChiFragment extends Fragment {

    private KhoanChiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecycleViewAdapter adapter;

    public static KhoanChiFragment newInstance() {
        return new KhoanChiFragment();
    }

    //    Tìm kiếm và thiết lập các thành phần trong recyclerView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        adapter = new ChiRecycleViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(adapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_chi_fragment, container, false);
    }

    public KhoanChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanChiViewModel.class);

        mViewModel.getChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                adapter.setLst(chis);
            }
        });


        //    Bắt sự kiện khi người dùng click vào edit và view
        final KhoanChiFragment currentFragment= this; // tạo đối tượng
        adapter.setOnItemEditCClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = adapter.getItem(position);
                ChiDialog dialog = new ChiDialog(getActivity(),currentFragment,chi);
                dialog.Show();
            }
        });
        adapter.setOnItemInfoClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = adapter.getItem(position);
                ChiDetailDialog dialog = new ChiDetailDialog(getActivity(),currentFragment,chi);
                dialog.Show();
            }
        });

//        Sử lý sự kiện người dùng xóa bằng cách trượt trái hoạc phải
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Chi chi = adapter.getItem(position);
                Toast.makeText(getActivity(), "Đã Xóa Thành Công!!", Toast.LENGTH_SHORT).show();
                mViewModel.deleteC(chi);
            }
        });
        helper.attachToRecyclerView(mRv);
    }


}