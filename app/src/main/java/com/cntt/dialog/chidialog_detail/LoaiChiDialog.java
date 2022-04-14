package com.cntt.dialog.chidialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.R;
import com.cntt.entity.LoaiChi;
import com.cntt.ui.chi.LoaiChiFragment;
import com.cntt.ui.chi.LoaiChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDialog {

    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId;
    private TextInputEditText etName;
    private TextView tvContent;
    private boolean checkMode; //xem là sửa hay là input


    public LoaiChiDialog(Context context, LoaiChiFragment fragment,LoaiChi ... loaiChis) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.loai_chi_dialog,null);

        etId = view.findViewById(R.id.edTId);
        etName = view.findViewById(R.id.edTName);
        tvContent = view.findViewById(R.id.tvContentLC);

        if (loaiChis != null && loaiChis.length > 0){
            etId.setText("" + loaiChis[0].id);
            etName.setText(loaiChis[0].tenLoaiChi);
            checkMode = true;
        }else {
            checkMode = false;
        }

//        Sử lý khi người dùng click vào biểu tương add
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view).setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDialog.dismiss();
            }
        }).setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoaiChi loaiChi = new LoaiChi();

                String editMaLoaiChi = etId.getText().toString();
                String editTenLoaiChi = etName.getText().toString();

                loaiChi.tenLoaiChi = etName.getText().toString();

                    if(editTenLoaiChi.isEmpty()){
                        Toast.makeText(mDialog.getContext(), "Bạn phải nhập tên loại chi",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!editTenLoaiChi.matches("[a-zA-Z0-9]*")){
                        Toast.makeText(context, "Loại chi không đươc chứa kí tự đặc biệt ", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        loaiChi.tenLoaiChi = editTenLoaiChi;
                    }
                    tvContent.setText("Sửa Loại Chi");
                    //loaiChi.id = Integer.parseInt(etId.getText().toString());
                    mViewModel.updateLC(loaiChi);
                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                //}else {
                    tvContent.setText("Thêm Loại Chi");
                    if(checkMode){
                        loaiChi.id = Integer.parseInt(etId.getText().toString());
                        mViewModel.updateLC(loaiChi);
                        Toast.makeText(context, "Sửa thành công loại chi", Toast.LENGTH_LONG).show();
                    }else{
                        mViewModel.insertLC(loaiChi);
                        Toast.makeText(context, "Loại chi đã được thêm", Toast.LENGTH_LONG).show();
                    }
            }
        });
        mDialog = builder.create();
    }

    public void Show(){
        mDialog.show();
    }
}
