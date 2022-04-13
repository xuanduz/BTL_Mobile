package com.cntt.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cntt.entity.Chi;
import com.cntt.entity.LoaiChi;
import com.cntt.repository.ChiRepository;
import com.cntt.repository.LoaiChiRepository;

import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {
    private ChiRepository repository;
    private LoaiChiRepository repositoryLC;
    private LiveData<List<Chi>> mChi;
    private LiveData<List<LoaiChi>> mLoaiChi;

    public KhoanChiViewModel(@NonNull Application application) {
        super(application);

        repository = new ChiRepository(application);
        mChi = repository.getAllChi();
        repositoryLC = new LoaiChiRepository(application);
        mLoaiChi = repositoryLC.getAllLoaiChi();
    }


    public LiveData<List<Chi>> getChi() {
        return mChi;
    }

    public LiveData<List<LoaiChi>> getLoaiChi() {
        return mLoaiChi;
    }

    public void insertC(Chi chi){
        repository.insertLC(chi);
    }

    public void updateC(Chi chi){
        repository.updateLC(chi);
    }

    public void deleteC(Chi chi){
        repository.deleteLC(chi);
    }
}