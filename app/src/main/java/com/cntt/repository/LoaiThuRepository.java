package com.cntt.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cntt.dao.AppDatabase;
import com.cntt.dao.LoaiThuDao;
import com.cntt.entity.LoaiThu;

import java.util.List;

public class LoaiThuRepository {

    private LoaiThuDao mLoaiThuDao;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuRepository(Application application) {
        this.mLoaiThuDao = AppDatabase.getDatabase(application).loaiThuDao();
        mAllLoaiThu = mLoaiThuDao.findAll();
    }

    //  Trả về các loại thu
    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }


    public void insertLT(LoaiThu loaiThu){
        new InsertAsyncTask(mLoaiThuDao).execute(loaiThu);
    }

    public void deleteLT(LoaiThu loaiThu){
        new DeleteAsyncTask(mLoaiThuDao).execute(loaiThu);
    }

    public void updateLT(LoaiThu loaiThu){
        new UpdateAsyncTask(mLoaiThuDao).execute(loaiThu);
    }

    // Lớp bất đồng bộ để insert vào database
    class InsertAsyncTask extends AsyncTask<LoaiThu,Void,Void> {

        private LoaiThuDao mLoaiThuDao;

        public InsertAsyncTask(LoaiThuDao loaiThuDao){
            this.mLoaiThuDao = loaiThuDao;
        }
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.insertLT(loaiThus[0]);
            return null;
        }
    }

    //    Thực hiện xóa một item
    class DeleteAsyncTask extends AsyncTask<LoaiThu,Void,Void>{

        private LoaiThuDao mLoaiThuDao;

        public DeleteAsyncTask(LoaiThuDao loaiThuDao){
            this.mLoaiThuDao = loaiThuDao;
        }
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.deleteLT(loaiThus[0]);
            return null;
        }
    }

    //    Thực hiện update một item
    class UpdateAsyncTask extends AsyncTask<LoaiThu,Void,Void>{

        private LoaiThuDao mLoaiThuDao;

        public UpdateAsyncTask(LoaiThuDao loaiThuDao){
            this.mLoaiThuDao = loaiThuDao;
        }
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.updateLT(loaiThus[0]);
            return null;
        }
    }
}
