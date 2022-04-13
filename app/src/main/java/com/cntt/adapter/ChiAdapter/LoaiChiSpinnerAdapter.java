package com.cntt.adapter.ChiAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cntt.R;
import com.cntt.entity.LoaiChi;
import com.cntt.entity.LoaiThu;

import java.util.List;

public class LoaiChiSpinnerAdapter extends BaseAdapter {

    private List<LoaiChi> mList;
    private LayoutInflater mLayoutInflater;

    public LoaiChiSpinnerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

//    Gán giá trị cho danh sách
    public void setList(List<LoaiChi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();

    }

    @Override
    public Object getItem(int i) {
        if (mList == null){
            return null;
        }
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

//    Tạo view
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KhoanThuViewHolder holder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.spinner_chi_item,null,false);
            holder = new KhoanThuViewHolder(view);

            view.setTag(holder);
        }else {
            holder = (KhoanThuViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(i).tenLoaiChi);
        return view;
    }

    public static class KhoanThuViewHolder{
        public TextView tvName;

        public KhoanThuViewHolder(View view){
            tvName = view.findViewById(R.id.tvNameLC);
        }
    }
}
