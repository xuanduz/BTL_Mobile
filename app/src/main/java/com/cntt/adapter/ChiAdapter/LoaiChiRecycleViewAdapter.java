package com.cntt.adapter.ChiAdapter;

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
import com.cntt.entity.LoaiChi;

import java.util.List;

// Tạo layout cho chi
public class LoaiChiRecycleViewAdapter extends RecyclerView.Adapter<LoaiChiRecycleViewAdapter.LoaiChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<LoaiChi> mLst;

    public static ItemClickListener itemEditLCClickListener;
    public static ItemClickListener itemInfoClickListener;

    public LoaiChiRecycleViewAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);
    }
//    Phương thức khi click edit và view


    public void setOnItemEditLCClickListener(ItemClickListener itemEditLCClickListener) {
        LoaiChiRecycleViewAdapter.itemEditLCClickListener = itemEditLCClickListener;
    }

    public void setOnItemInfoClickListener(ItemClickListener itemInfoClickListener) {
        LoaiChiRecycleViewAdapter.itemInfoClickListener = itemInfoClickListener;
    }

    //    Tạo view
    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.recyclerview_loai_chi_item,parent,false);
        return new LoaiChiViewHolder(view);
    }

//    Cập nhật nội dung hiển thị trong textView
    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mLst != null){
            holder.tvName.setText(mLst.get(position).tenLoaiChi);
            holder.position = position;
        }
    }

//    Trả về số lượng item trong mLst
    @Override
    public int getItemCount() {
        if(mLst == null){
            return 0;
        }
        return mLst.size();
    }

    public LoaiChi getItem(int position){
        if (mLst == null || position > mLst.size()){
            return null;
        }
        return mLst.get(position);
    }

    public void setLst(List<LoaiChi> mLst) {
        this.mLst = mLst;
        notifyDataSetChanged();
    }

//    Tìm kiếm theo id và gán cho các giá trị
    public static  class LoaiChiViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public ImageView ivEdit,ivInfo;
        public CardView cardView;
        public int position;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivE);
            ivInfo = itemView.findViewById(R.id.ivV);

            cardView = (CardView) itemView;

//            Sử lý click biểu tượng
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemEditLCClickListener != null){
                        itemEditLCClickListener.onItemClick(position);
                    }
                }
            });

            ivInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemInfoClickListener != null){
                        itemInfoClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
