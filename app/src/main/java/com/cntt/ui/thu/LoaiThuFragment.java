package com.cntt.ui.thu;

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
import com.cntt.adapter.ThuAdapter.LoaiThuRecyclerViewAdapter;
import com.cntt.dialog.thudialog_detail.LoaiThuDetailDialog;
import com.cntt.dialog.thudialog_detail.LoaiThuDialog;
import com.cntt.entity.LoaiThu;

import java.util.List;

public class LoaiThuFragment extends Fragment {

    private RecyclerView mRv;
    private LoaiThuRecyclerViewAdapter adapter;
    private LoaiThuViewModel mViewModel;

    public static LoaiThuFragment newInstance() {
        return new LoaiThuFragment();
    }

    public LoaiThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loai_thu_fragment, container, false);
    }

    //    Tìm kiếm và thiết lập các thuộc tính cho recyclerView và thiết lập Adapter cho recyclerView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRv = view.findViewById(R.id.recyclerView);
        adapter = new LoaiThuRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager( new LinearLayoutManager(getActivity()));
        mRv.setAdapter(adapter);

        //    Bắt sự kiện khi người dùng click vào edit và view
//        create obj loai thu
        final LoaiThuFragment currentFragment = this;
        adapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = adapter.getItem(position);
                LoaiThuDialog dialog = new LoaiThuDialog(getActivity(),currentFragment,loaiThu);
                dialog.Show();
            }
        });
        adapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = adapter.getItem(position);
                LoaiThuDetailDialog dialog = new LoaiThuDetailDialog(getActivity(),currentFragment,loaiThu);
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

                LoaiThu lt = adapter.getItem(position);

                Toast.makeText(getActivity(),"Đã xóa thành công",Toast.LENGTH_LONG).show();
                mViewModel.deleteLT(lt);
            }
        });
        helper.attachToRecyclerView(mRv);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
//      Nếu dữ liệu thay đổi tự động cập nhật lại adapter
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                adapter.setList(loaiThus);
            }
        });
    }

}