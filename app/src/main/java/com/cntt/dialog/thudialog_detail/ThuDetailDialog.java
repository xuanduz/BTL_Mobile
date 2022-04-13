package com.cntt.dialog.thudialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cntt.R;
import com.cntt.entity.Thu;
import com.cntt.ui.thu.KhoanThuFragment;
import com.cntt.ui.thu.KhoanThuViewModel;


public class ThuDetailDialog {

    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextView etId,etAmount,etNote,etName,etType;




    public ThuDetailDialog(Context context, KhoanThuFragment fragment, Thu  thu) {
        
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.thu_detail_dialog,null);
//        Null
        etId = view.findViewById(R.id.tvID);
        etName = view.findViewById(R.id.tvName);
        etAmount = view.findViewById(R.id.tvAmount);
        etNote = view.findViewById(R.id.tvNote);
        etType = view.findViewById(R.id.tvType);

//        set giá tri chi view
        etId.setText(""+thu.tId);
        etName.setText(thu.ten);
        etAmount.setText("" + thu.soTien + "$");
        etNote.setText(thu.ghiChu);
//        Suy nghĩ cách log ra value từ key của type
        etType.setText(""+thu.ltID);


        
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
