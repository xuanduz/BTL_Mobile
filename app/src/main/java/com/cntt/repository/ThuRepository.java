package com.cntt.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cntt.dao.AppDatabase;

import com.cntt.dao.ThuDao;
import com.cntt.entity.ThongKeLoaiThu;
import com.cntt.entity.Thu;

import java.util.List;

public class ThuRepository {

    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;

    public ThuRepository(Application application) {
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllThu = mThuDao.findAll();
    }

    //  Trả về các loại thu
    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }

//    Tổng Thu
    public LiveData<Float> TongThu() {
        return mThuDao.TongThu();
    }

//    Tổng thu theo type
    public LiveData<List<ThongKeLoaiThu>> sumByType() {
        return mThuDao.ThongKeTheoLoai();
    }

    public void insertThu(Thu thu){
        new InsertAsyncTask(mThuDao).execute(thu);
    }

    public void deleteThu(Thu thu){
        new DeleteAsyncTask(mThuDao).execute(thu);
    }

    public void updateThu(Thu thu){
        new UpdateAsyncTask(mThuDao).execute(thu);
    }

    // Lớp bất đồng bộ để insert vào database
    class InsertAsyncTask extends AsyncTask<Thu,Void,Void> {

        private ThuDao mThuDao;

        public InsertAsyncTask(ThuDao thuDao){
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.insertThu(thus[0]);
            return null;
        }
    }

    //    Thực hiện xóa một item
    class DeleteAsyncTask extends AsyncTask<Thu,Void,Void>{

        private ThuDao mThuDao;

        public DeleteAsyncTask(ThuDao thuDao){
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.deleteThu(thus[0]);
            return null;
        }
    }

    //    Thực hiện update một item
    class UpdateAsyncTask extends AsyncTask<Thu,Void,Void>{

        private ThuDao mThuDao;

        public UpdateAsyncTask(ThuDao thuDao){
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.updateThu(thus[0]);
            return null;
        }
    }

}
