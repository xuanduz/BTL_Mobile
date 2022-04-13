package com.cntt.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cntt.dao.AppDatabase;
import com.cntt.dao.LoaiChiDao;
import com.cntt.dao.LoaiThuDao;
import com.cntt.entity.LoaiChi;
import com.cntt.entity.LoaiThu;

import java.util.List;

public class LoaiChiRepository {

    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiRepository(Application application) {
        this.mLoaiChiDao = AppDatabase.getDatabase(application).loaiChiDao();
        mAllLoaiChi = mLoaiChiDao.findAll();
    }

    //  Trả về các loại thu
    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }


    public void insertLC(LoaiChi loaiChi){
        new InsertAsyncTask(mLoaiChiDao).execute(loaiChi);
    }

    public void deleteLC(LoaiChi loaiChi){
        new DeleteAsyncTask(mLoaiChiDao).execute(loaiChi);
    }

    public void updateLC(LoaiChi loaiChi){
        new UpdateAsyncTask(mLoaiChiDao).execute(loaiChi);
    }

    // Lớp bất đồng bộ để insert vào database
    class InsertAsyncTask extends AsyncTask<LoaiChi,Void,Void> {

        private LoaiChiDao mLoaiChiDao;

        public InsertAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao = loaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.insertLC(loaiChis[0]);
            return null;
        }
    }

    //    Thực hiện xóa một item
    class DeleteAsyncTask extends AsyncTask<LoaiChi,Void,Void>{

        private LoaiChiDao mLoaiChiDao;

        public DeleteAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao = loaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.deleteLC(loaiChis[0]);
            return null;
        }
    }

    //    Thực hiện update một item
    class UpdateAsyncTask extends AsyncTask<LoaiChi,Void,Void>{

        private LoaiChiDao mLoaiChiDao;

        public UpdateAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao = loaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.updateLC(loaiChis[0]);
            return null;
        }
    }
}
