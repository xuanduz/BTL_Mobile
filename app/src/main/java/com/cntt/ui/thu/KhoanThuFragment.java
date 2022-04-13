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
import com.cntt.adapter.ThuAdapter.ThuRecyclerViewAdapter;
import com.cntt.dialog.thudialog_detail.ThuDetailDialog;
import com.cntt.dialog.thudialog_detail.ThuDialog;
import com.cntt.entity.Thu;

import java.util.List;

public class KhoanThuFragment extends Fragment {

    private KhoanThuViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRecyclerViewAdapter adapter;

    public static KhoanThuFragment newInstance() {
        return new KhoanThuFragment();
    }

    public KhoanThuViewModel getViewModel() {
        return mViewModel;
    }

    //    Tìm kiếm và thiết lập các thuộc tính cho recyclerView và thiết lập Adapter cho recyclerView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRv = view.findViewById(R.id.recyclerView);
        adapter = new ThuRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(adapter);

        //    Các hàm sử lý chức năng
//    Xóa một phần tử bằng cách trượt sang trái hoặc phải
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                Thu thu = adapter.getItem(position);
                mViewModel.deleteThu(thu);
                Toast.makeText(getActivity(),"Đã xóa thành công",Toast.LENGTH_LONG).show();
            }
        });
        helper.attachToRecyclerView(mRv);

//        xử lý khi người dùng click và hai biểu tượng edit and view
//        Khởi tạo đói tượng KhoanThuFragment
        final KhoanThuFragment currentFragment = this;
        adapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = adapter.getItem(position);
                ThuDetailDialog dialog = new ThuDetailDialog(getActivity(),currentFragment,thu);
                dialog.Show();
            }
        });

        adapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = adapter.getItem(position);
                ThuDialog dialog = new ThuDialog(getActivity(),currentFragment,thu);
                dialog.Show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_thu_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanThuViewModel.class);

//        Xử lý khi có sự thay đổi dữ liệu và tự động load lại
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                adapter.setList(thus);
            }
        });
    }

}