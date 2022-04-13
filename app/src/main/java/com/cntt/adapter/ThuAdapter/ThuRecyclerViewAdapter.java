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
import com.cntt.entity.Thu;

import java.util.List;

public class ThuRecyclerViewAdapter extends RecyclerView.Adapter<ThuRecyclerViewAdapter.ThuViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<Thu> mList;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public ThuRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

//    Phương thức khi click edit và view

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ThuRecyclerViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ThuRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_thu_item,parent,false);
        return new ThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmount.setText("" +mList.get(position).soTien + "$");
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

    public Thu getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<Thu> mList) {
        this.mList = mList;
        notifyDataSetChanged(); // Cập nhật lại dữ liệu được hiển thị trong ui
    }

    public static class ThuViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName,tvAmount;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;
        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvID);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivView = itemView.findViewById(R.id.ivV);

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
