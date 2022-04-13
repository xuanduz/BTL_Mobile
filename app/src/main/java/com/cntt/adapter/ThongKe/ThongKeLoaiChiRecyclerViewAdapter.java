package com.cntt.adapter.ThongKe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cntt.R;
import com.cntt.entity.ThongKeLoaiChi;

import java.util.List;

public class ThongKeLoaiChiRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeLoaiChiRecyclerViewAdapter.ThongKeLoaiChiViewHolder>{

    private LayoutInflater inflater;
    private List<ThongKeLoaiChi> mListLoaiChi;

//    tao ra layout cho adapter
    public ThongKeLoaiChiRecyclerViewAdapter(Context context) {

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ThongKeLoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Create view
        View view = inflater.inflate(R.layout.recylerview_thong_ke_loai_chi,parent,false);
        return new ThongKeLoaiChiViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiChiViewHolder holder, int position) {
        if (mListLoaiChi != null){
            holder.tvTenLoaiChi.setText(mListLoaiChi.get(position).tenChi);
            holder.etSoTien.setText("" + mListLoaiChi.get(position).tongTien);
        }
    }

    @Override
    public int getItemCount() {
        if (mListLoaiChi == null)
            return 0;
        return mListLoaiChi.size();
    }

    public void setListLoaiChi(List<ThongKeLoaiChi> mListLoaiChi) {
        this.mListLoaiChi = mListLoaiChi;
        notifyDataSetChanged(); //cap nhat data
    }

    public static class ThongKeLoaiChiViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTenLoaiChi;
        private EditText etSoTien;

        public ThongKeLoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenLoaiChi = itemView.findViewById(R.id.tvTenLoaiChi);
            etSoTien = itemView.findViewById(R.id.etSoTien);

        }
    }
}
