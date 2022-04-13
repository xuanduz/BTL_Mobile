package com.cntt.dialog.thudialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.cntt.R;
import com.cntt.entity.LoaiThu;
import com.cntt.repository.LoaiThuRepository;
import com.cntt.ui.thu.LoaiThuFragment;
import com.cntt.ui.thu.LoaiThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class LoaiThuDialog {
    
    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    
    private TextInputEditText etId;
    private TextInputEditText etName;
    private boolean checkMode; //xem là sửa hay là input


    public LoaiThuDialog(Context context, LoaiThuFragment fragment,LoaiThu ... loaiThus) {
        
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.loai_thu_dialog,null);
        etId = view.findViewById(R.id.editTextID);
        etName = view.findViewById(R.id.editTextName);

        if(loaiThus != null && loaiThus.length > 0){
            etId.setText(""+loaiThus[0].lid);
            etName.setText(loaiThus[0].ten);
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
//                Validate and add item in list
//                Trường hợp trùng nhau (Thiếu validate) và là số
                LoaiThu lt = new LoaiThu();
                lt.ten = etName.getText().toString();

                if(checkMode){
                    lt.lid = Integer.parseInt(etId.getText().toString());
                    mViewModel.updateLT(lt);
                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                }else {
                    if (lt.ten.isEmpty()){
                        Toast.makeText(context,"Chưa nhập tên cho loại thu",Toast.LENGTH_LONG).show();
                    }else {

                        mViewModel.insertLT(lt);
                        Toast.makeText(context,"Loại thu đã được lưu",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        mDialog = builder.create();
    }

    public void Show(){
        mDialog.show();
    }
}
