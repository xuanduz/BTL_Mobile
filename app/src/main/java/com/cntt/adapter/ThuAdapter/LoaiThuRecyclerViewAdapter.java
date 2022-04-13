package com.cntt.adapter.ThuAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cntt.R;
import com.cntt.adapter.ItemClickListener;
import com.cntt.entity.LoaiThu;

import java.util.List;
// tạo layout cho thu
public class LoaiThuRecyclerViewAdapter extends RecyclerView.Adapter<LoaiThuRecyclerViewAdapter.LoaiThuViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<LoaiThu> mList;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiThuRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

//    Phương thức khi click edit và view
    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiThuRecyclerViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiThuRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    //    Tạo view
    @NonNull
    @Override
    public LoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_loai_thu_item,parent,false);
        return new LoaiThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

    public LoaiThu getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<LoaiThu> mList) {
        this.mList = mList;
        notifyDataSetChanged(); // Cập nhật lại dữ liệu được hiển thị trong ui
    }

    public static class LoaiThuViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;
        public LoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvID);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivView = itemView.findViewById(R.id.ivView);

            cv = (CardView) itemView;

//            xử lý click biểu tượng
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemViewClickListener != null){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemEditClickListener != null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
