package com.cntt.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.cntt.entity.LoaiThu;
import com.cntt.entity.Thu;
import com.cntt.repository.LoaiThuRepository;
import com.cntt.repository.ThuRepository;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel {
    private ThuRepository repositoryThu;
    private LoaiThuRepository repositoryLT;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;



    public KhoanThuViewModel(@NonNull Application application) {
        super(application);
        repositoryThu = new ThuRepository(application);
        mAllThu = repositoryThu.getAllThu();
        repositoryLT = new LoaiThuRepository(application);
        mAllLoaiThu = repositoryLT.getAllLoaiThu();


    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }

    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }

    public void insertThu(Thu thu){
        repositoryThu.insertThu(thu);
    }

    public void updateThu(Thu thu){
        repositoryThu.updateThu(thu);
    }
    public void deleteThu(Thu thu){
        repositoryThu.deleteThu(thu);
    }
}