package com.cntt.dialog.thudialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.cntt.R;
import com.cntt.adapter.ThuAdapter.LoaiThuSpinnerAdapter;
import com.cntt.entity.LoaiThu;
import com.cntt.entity.Thu;
import com.cntt.ui.thu.KhoanThuFragment;
import com.cntt.ui.thu.KhoanThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class ThuDialog {
    
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    
    private TextInputEditText etId,etAmount,etNote;
    private TextInputEditText etName;
    private Spinner spType;
    private LoaiThuSpinnerAdapter adapter;
    private boolean checkMode; //xem là update hay là input


    public ThuDialog(Context context, KhoanThuFragment fragment, Thu ... thu) {
        
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.thu_dialog,null);
        etId = view.findViewById(R.id.editTextID);
        etName = view.findViewById(R.id.editTextName);
        etAmount = view.findViewById(R.id.edTextAmount);
        etNote = view.findViewById(R.id.edTextNote);
        spType = view.findViewById(R.id.spType);


//        Create hàm quan sát khi khoản thu thay đôi -> spinner cũng thay đổi
        adapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                adapter.setList(loaiThus);
            }
        });
        spType.setAdapter(adapter);

        if(thu != null && thu.length > 0){
            etId.setText(""+thu[0].tId);
            etName.setText(thu[0].ten);
            etAmount.setText(""+thu[0].soTien);
            etNote.setText(thu[0].ghiChu);

            checkMode = true;
        }else {
            checkMode = false;
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view).setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDialog.dismiss();
            }
        }).setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Validate and add item in list
//                Trường hợp trùng nhau (Thiếu validate)
                Thu t = new Thu();
                t.ten = etName.getText().toString();
                t.soTien = Float.parseFloat(etAmount.getText().toString());
                t.ghiChu = etNote.getText().toString();
                t.ltID = ((LoaiThu) adapter.getItem(spType.getSelectedItemPosition())).lid;

                if(checkMode){
                    t.tId = Integer.parseInt(etId.getText().toString());
                    mViewModel.updateThu(t);
                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                }else {
                    if (t.ten.isEmpty()){
                        Toast.makeText(context,"Chưa nhập tên cho khoản thu",Toast.LENGTH_LONG).show();
                    }else {

                        mViewModel.insertThu(t);
                        Toast.makeText(context,"Khoản thu đã được lưu",Toast.LENGTH_LONG).show();
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
