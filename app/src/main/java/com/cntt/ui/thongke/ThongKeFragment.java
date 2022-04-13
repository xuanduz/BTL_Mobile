package com.cntt.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cntt.R;
import com.cntt.adapter.ThongKe.ThongKeLoaiChiRecyclerViewAdapter;
import com.cntt.adapter.ThongKe.ThongKeLoaiThuRecyclerViewAdapter;
import com.cntt.entity.ThongKeLoaiChi;
import com.cntt.entity.ThongKeLoaiThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {

    private ThongKeViewModel mThongKeViewModel;
    private EditText etTongThu;
    private EditText etTongChi;
    private RecyclerView rvThongKeLoaiThu;
    private RecyclerView rvThongKeLoaiChi;
    private ThongKeLoaiThuRecyclerViewAdapter thuRecyclerViewAdapter;
    private ThongKeLoaiChiRecyclerViewAdapter chiRecyclerViewAdapter;

    public ThongKeFragment() {
    }


    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

//        trả về sự thể hiện trên giao diện
//        Tong thu va loai thu
        etTongThu = view.findViewById(R.id.etTongThu);
        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeThu);

//        Tong chi va loai chi
        etTongChi = view.findViewById(R.id.etTongChi);
        rvThongKeLoaiChi = view.findViewById(R.id.rvThongKeChi);


        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);

//        Loai Thu
        thuRecyclerViewAdapter = new ThongKeLoaiThuRecyclerViewAdapter(getActivity());
        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiThu.setAdapter(thuRecyclerViewAdapter);

//        Loai chi
        chiRecyclerViewAdapter =  new ThongKeLoaiChiRecyclerViewAdapter(getActivity());
        rvThongKeLoaiChi.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiChi.setAdapter(chiRecyclerViewAdapter);

//        Lắng nghe sự thay đổi trong thu để cập nhật trên biểu đồ
//        Tong thu
        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tongThu) {
                etTongThu.setText("" + tongThu);
            }
        });
//        Tong chi
        mThongKeViewModel.getTongChi().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tongChi) {
                etTongChi.setText("" + tongChi);
            }
        });
        //        Lắng nghe sự thay đổi trong thống kê loại thu để cập nhật trên biểu đồ
//        Loai thu
        mThongKeViewModel.getThongKeLoaiThu().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                thuRecyclerViewAdapter.setList(thongKeLoaiThus);
            }
        });

        //        Loai chi
        mThongKeViewModel.getThongKeLoaiChi().observe(getActivity(), new Observer<List<ThongKeLoaiChi>>() {
            @Override
            public void onChanged(List<ThongKeLoaiChi> thongKeLoaiChis) {
                chiRecyclerViewAdapter.setListLoaiChi(thongKeLoaiChis);
            }
        });

        return view;
    }
}