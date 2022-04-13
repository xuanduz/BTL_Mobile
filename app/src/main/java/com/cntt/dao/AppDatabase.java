package com.cntt.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cntt.entity.Chi;
import com.cntt.entity.LoaiChi;
import com.cntt.entity.LoaiThu;
import com.cntt.entity.Thu;

@Database(entities = {LoaiThu.class, Thu.class, LoaiChi.class,Chi.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LoaiThuDao loaiThuDao();
    public abstract ThuDao thuDao();
    public abstract LoaiChiDao loaiChiDao();
    public abstract ChiDao chiDao();


    public static AppDatabase INSTANCE ;

    //    Định nghĩa callback
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    //    Khởi tạo database
    public static  AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"personal")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback).build();
            }
        }
        return INSTANCE;
    }

    public static  class PopulateData extends AsyncTask<Void,Void,Void> {
        private LoaiThuDao loaiThuDao;
        private ThuDao thuDao;
        private LoaiChiDao loaiChiDao;
        private ChiDao chiDao;

        public PopulateData(AppDatabase db){
            loaiThuDao = db.loaiThuDao();
            thuDao = db.thuDao();
            loaiChiDao = db.loaiChiDao();
            chiDao = db.chiDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

//            Insert thu va loai thu
            String[] loaiThus = new String[]{"Lương","Thưởng","Cổ phiếu"};
            for(String item : loaiThus){
                LoaiThu loaiThu = new LoaiThu();
                loaiThu.ten = item;
                loaiThuDao.insertLT(loaiThu);
            }

            Thu thu = new Thu();
            thu.ten = "Lương Tháng 1";
            thu.soTien = 333;
            thu.ltID = 2;
            thu.ghiChu= "";
            thuDao.insertThu(thu);

//            Insert chi và loại chi
            String[] loaiChis = new String[]{"Cổ Phiếu","Tiền Mạng","Tiền Điện","Tiền Nước","Mua Sắm"};
            for (String item : loaiChis){
                LoaiChi loaiChi = new LoaiChi();
                loaiChi.tenLoaiChi = item;
                loaiChiDao.insertLC(loaiChi);
            }

            Chi chi = new Chi();
            chi.lcId = 1;
            chi.cName = "Mạng T1";
            chi.soTien = 12f;
            chi.note = "";
            chiDao.insertC(chi);

            return null;
        }
    }
}
