package com.cntt.dialog.chidialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cntt.R;
import com.cntt.entity.LoaiChi;
import com.cntt.ui.chi.LoaiChiFragment;
import com.cntt.ui.chi.LoaiChiViewModel;

public class LoaiChiDetailDialog {

    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView etId;
    private TextView etName;


    public LoaiChiDetailDialog(Context context, LoaiChiFragment fragment, LoaiChi loaiChis) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.loai_chi_detail_dialog,null);

        etId = view.findViewById(R.id.tvLCId);
        etName = view.findViewById(R.id.tvLCTen);

        etId.setText(""+loaiChis.id);
        etName.setText(loaiChis.tenLoaiChi);

//        Sử lý khi người dùng click vào biểu tương add
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
