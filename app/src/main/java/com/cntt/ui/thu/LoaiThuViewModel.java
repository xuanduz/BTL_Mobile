package com.cntt.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cntt.dao.LoaiThuDao;
import com.cntt.entity.LoaiThu;
import com.cntt.repository.LoaiThuRepository;

import java.util.List;

public class LoaiThuViewModel extends AndroidViewModel {

    private LoaiThuRepository repository;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuViewModel(@NonNull Application application) {
        super(application);

        repository = new LoaiThuRepository(application);
        mAllLoaiThu = repository.getAllLoaiThu();

    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }

    public void insertLT(LoaiThu loaiThu){
        repository.insertLT(loaiThu);
    }

    public void updateLT(LoaiThu loaiThu){
        repository.updateLT(loaiThu);
    }
    public void deleteLT(LoaiThu loaiThu){
        repository.deleteLT(loaiThu);
    }
}