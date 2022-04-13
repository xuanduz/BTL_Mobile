package com.cntt.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cntt.dao.AppDatabase;
import com.cntt.dao.ChiDao;
import com.cntt.entity.Chi;
import com.cntt.entity.ThongKeLoaiChi;

import java.util.List;

public class ChiRepository {

    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public ChiRepository(Application application) {
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi = mChiDao.findAll();
    }

    //  Trả về các loại Chi
    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }

//    Tong chi
    public LiveData<Float> tongChi() {
        return mChiDao.tongChi();
    }

//    Loai chi
    public LiveData<List<ThongKeLoaiChi>> ThongKeChi() {
        return mChiDao.ThongKeChi();
    }

    public void insertLC(Chi chis){
        new ChiRepository.InsertAsyncTask(mChiDao).execute(chis);
    }

    public void deleteLC(Chi chis){
        new ChiRepository.DeleteAsyncTask(mChiDao).execute(chis);
    }

    public void updateLC(Chi chis){
        new ChiRepository.UpdateAsyncTask(mChiDao).execute(chis);
    }

    // Lớp bất đồng bộ để insert vào database
    class InsertAsyncTask extends AsyncTask<Chi,Void,Void> {

        private ChiDao mChiDao;

        public InsertAsyncTask(ChiDao chiDao){
            this.mChiDao = chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.insertC(chis[0]);
            return null;
        }
    }

    //    Thực hiện xóa một item
    class DeleteAsyncTask extends AsyncTask<Chi,Void,Void>{

        private ChiDao mChiDao;

        public DeleteAsyncTask(ChiDao chiDao){
            this.mChiDao = chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.deleteC(chis[0]);
            return null;
        }
    }

    //    Thực hiện update một item
    class UpdateAsyncTask extends AsyncTask<Chi,Void,Void>{

        private ChiDao mChiDao;

        public UpdateAsyncTask(ChiDao chiDao){
            this.mChiDao = chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.updateC(chis[0]);
            return null;
        }
    }
}
