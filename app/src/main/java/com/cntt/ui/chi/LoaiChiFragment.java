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
import com.cntt.adapter.ItemClickListener;
import com.cntt.adapter.ChiAdapter.LoaiChiRecycleViewAdapter;
import com.cntt.dialog.chidialog_detail.LoaiChiDetailDialog;
import com.cntt.dialog.chidialog_detail.LoaiChiDialog;
import com.cntt.entity.LoaiChi;

import java.util.List;

public class LoaiChiFragment extends Fragment {

    private LoaiChiViewModel mViewModel;
    private RecyclerView mRv;
    private LoaiChiRecycleViewAdapter adapter;

    public static LoaiChiFragment newInstance() {
        return new LoaiChiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loai_chi_fragment, container, false);
    }

    public LoaiChiViewModel getViewModel() {
        return mViewModel;
    }

    //    Tìm kiếm và thiết lập các thành phần trong recyclerView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRv = view.findViewById(R.id.recyclerView);

        adapter = new LoaiChiRecycleViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRv.setAdapter(adapter);

        //    Bắt sự kiện khi người dùng click vào edit và view
        final LoaiChiFragment currentFragment= this; // tạo đối tượng
        adapter.setOnItemEditLCClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = adapter.getItem(position);
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity(),currentFragment,loaiChi);
                dialog.Show();
            }
        });
        adapter.setOnItemInfoClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = adapter.getItem(position);
                LoaiChiDetailDialog dialog = new LoaiChiDetailDialog(getActivity(),currentFragment,loaiChi);
                dialog.Show();
            }
        });

        //        xử lý khi người dùng trượt trái hoạc phải
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                LoaiChi loaiChi = adapter.getItem(position);

                Toast.makeText(getActivity(),"Đã xóa thành công",Toast.LENGTH_LONG).show();
                mViewModel.deleteLC(loaiChi);
            }
        });
        helper.attachToRecyclerView(mRv);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiChiViewModel.class);

//        Quan sát dữ liệu và load lại
        mViewModel.getLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                adapter.setLst(loaiChis);
            }
        });
    }

}