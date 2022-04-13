package com.cntt.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cntt.entity.ThongKeLoaiChi;
import com.cntt.entity.ThongKeLoaiThu;
import com.cntt.repository.ChiRepository;
import com.cntt.repository.ThuRepository;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuRepository thuRepository;
    private ChiRepository chiRepository;
    private LiveData<Float> mTongChi;
    private LiveData<Float> mTongThu;
    private LiveData<List<ThongKeLoaiThu>> mListThu;
    private LiveData<List<ThongKeLoaiChi>> mListChi;




    public ThongKeViewModel(@NonNull Application application) {
        super(application);

//        Chi
        chiRepository = new ChiRepository(application);
        mTongChi = chiRepository.tongChi();
        mListChi = chiRepository.ThongKeChi();

//        Thu
        thuRepository = new ThuRepository(application);
        mTongThu = thuRepository.TongThu();
        mListThu = thuRepository.sumByType();
    }

    public LiveData<Float> getTongThu() {
        return mTongThu;
    }
    public LiveData<Float> getTongChi() {
        return mTongChi;
    }

    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThu(){
        return mListThu;
    }

    public LiveData<List<ThongKeLoaiChi>> getThongKeLoaiChi() {
        return mListChi;
    }
}
