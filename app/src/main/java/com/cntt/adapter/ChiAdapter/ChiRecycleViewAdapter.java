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
import com.cntt.entity.Chi;

import java.util.List;

// Tạo layout cho chi
public class ChiRecycleViewAdapter extends RecyclerView.Adapter<ChiRecycleViewAdapter.ChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Chi> mLst;

    public static ItemClickListener itemEditCClickListener;
    public static ItemClickListener itemInfoCClickListener;

    public ChiRecycleViewAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);
    }
//    Phương thức khi click edit và view


    public void setOnItemEditCClickListener(ItemClickListener itemEditLCClickListener) {
        ChiRecycleViewAdapter.itemEditCClickListener = itemEditLCClickListener;
    }

    public void setOnItemInfoClickListener(ItemClickListener itemInfoClickListener) {
        ChiRecycleViewAdapter.itemInfoCClickListener = itemInfoClickListener;
    }

    //    Tạo view
    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.recyclerview_chi_item,parent,false);
        return new ChiViewHolder(view);
    }

//    Cập nhật nội dung hiển thị trong textView
    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mLst != null){
            holder.tvName.setText(mLst.get(position).cName);
            holder.tvChiAmount.setText(""+(mLst.get(position).soTien) + "$");
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

    public Chi getItem(int position){
        if (mLst == null || position > mLst.size()){
            return null;
        }
        return mLst.get(position);
    }

    public void setLst(List<Chi> mLst) {
        this.mLst = mLst;
        notifyDataSetChanged();
    }

//    Tìm kiếm theo id và gán cho các giá trị
    public static  class ChiViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName,tvChiAmount;
        public ImageView ivEdit,ivInfo;
        public CardView cardView;
        public int position;
        public ChiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivChiE);
            ivInfo = itemView.findViewById(R.id.ivChiV);
            tvChiAmount = itemView.findViewById(R.id.tvChiAmount);

            cardView = (CardView) itemView;

//            Sử lý click biểu tượng
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemEditCClickListener != null){
                        itemEditCClickListener.onItemClick(position);
                    }
                }
            });

            ivInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemInfoCClickListener != null){
                        itemInfoCClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
