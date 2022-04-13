package com.cntt.adapter.ThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cntt.R;
import com.cntt.entity.ThongKeLoaiThu;

import java.util.List;

public class ThongKeLoaiThuRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeLoaiThuRecyclerViewAdapter.ThongKeLoaiThuViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<ThongKeLoaiThu> mList;

    public ThongKeLoaiThuRecyclerViewAdapter(Context context) {
        mLayoutInflater =LayoutInflater.from(context);

    }

//    Create view
    @NonNull
    @Override
    public ThongKeLoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_thong_ke_loai_thu,parent,false);

        return new ThongKeLoaiThuViewHolder(view);
    }

//    Check list
    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiThuViewHolder holder, int position) {
        if(mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.etSum.setText("" + mList.get(position).tong);
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0;
        return mList.size();
    }

//    Khi thay dữ liệu thay đôi
    public void setList(List<ThongKeLoaiThu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ThongKeLoaiThuViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public EditText etSum;
        public ThongKeLoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvLoaiThu);
            etSum = itemView.findViewById(R.id.etTongLoaiThu);

        }
    }
}
