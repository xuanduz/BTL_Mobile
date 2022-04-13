package com.cntt.dialog.chidialog_detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cntt.R;
import com.cntt.entity.Chi;
import com.cntt.ui.chi.KhoanChiFragment;
import com.cntt.ui.chi.KhoanChiViewModel;


public class ChiDetailDialog {

    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextView etId,etAmount,etNote,etName,etType;




    public ChiDetailDialog(Context context, KhoanChiFragment fragment, Chi  chi) {
        
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.chi_detail_dialog,null);
//        Null
        etId = view.findViewById(R.id.tvIDChi);
        etName = view.findViewById(R.id.tvNameChi);
        etAmount = view.findViewById(R.id.tvAmountChi);
        etNote = view.findViewById(R.id.tvNoteChi);
        etType = view.findViewById(R.id.tvTypeChi);

//        set giá tri chi view
        etId.setText(""+chi.cId);
        etName.setText(chi.cName);
        etAmount.setText("" + chi.soTien + "$");
        etNote.setText(chi.note);
//        Suy nghĩ cách log ra value từ key của type
        etType.setText(""+chi.lcId);


        
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
