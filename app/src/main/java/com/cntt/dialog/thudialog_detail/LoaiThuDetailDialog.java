package com.cntt.dialog.thudialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cntt.R;
import com.cntt.entity.LoaiThu;
import com.cntt.ui.thu.LoaiThuFragment;
import com.cntt.ui.thu.LoaiThuViewModel;


public class LoaiThuDetailDialog {

    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView etId;
    private TextView etName;


    public LoaiThuDetailDialog(Context context, LoaiThuFragment fragment, LoaiThu loaiThus) {
        
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.loai_thu_detail_dialog,null);
        etId = view.findViewById(R.id.tvID);
        etName = view.findViewById(R.id.tvName);

        etId.setText(""+loaiThus.lid);
        etName.setText(loaiThus.ten);

//        Xử lý người dùng xem thông tin chi tiết
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view).setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
    }

    public void Show(){
        mDialog.show();
    }
}
