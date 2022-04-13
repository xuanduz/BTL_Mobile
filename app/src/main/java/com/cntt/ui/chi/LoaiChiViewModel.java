package com.cntt.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cntt.dao.LoaiThuDao;
import com.cntt.entity.LoaiChi;
import com.cntt.repository.LoaiChiRepository;
import com.cntt.repository.LoaiThuRepository;

import java.util.List;


public class LoaiChiViewModel extends AndroidViewModel {

    private LoaiChiRepository repository;
    private LiveData<List<LoaiChi>> mLoaiChi;

    public LoaiChiViewModel(@NonNull Application application) {
        super(application);

        repository = new LoaiChiRepository(application);
        mLoaiChi = repository.getAllLoaiChi();
    }

    public LiveData<List<LoaiChi>> getLoaiChi() {
        return mLoaiChi;
    }

    public void insertLC(LoaiChi loaiChi){
        repository.insertLC(loaiChi);
    }

    public void updateLC(LoaiChi loaiChi){
        repository.updateLC(loaiChi);
    }

    public void deleteLC(LoaiChi loaiChi){
        repository.deleteLC(loaiChi);
    }
}