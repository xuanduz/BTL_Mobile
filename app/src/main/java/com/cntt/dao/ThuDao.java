package com.cntt.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.cntt.entity.ThongKeLoaiThu;
import com.cntt.entity.Thu;

import java.util.List;

@Dao
public interface ThuDao {

    @Query("SELECT * FROM Thu")
    public LiveData<List<Thu>> findAll();

    @Query("SELECT sum(So_Tien) FROM Thu")
    public LiveData<Float> TongThu();

    @Query("SELECT lt.lid,lt.Ten_Loai_Thu as ten,sum(So_Tien) as tong FROM Thu t INNER JOIN LoaiThu lt ON t.Loai_Thu_ID = lt.lid " +
            "GROUP BY lt.lid,lt.Ten_Loai_Thu")
    public LiveData<List<ThongKeLoaiThu>> ThongKeTheoLoai();
    @Insert
    public void insertThu(Thu thus);

    @Update
    public void updateThu(Thu thus);

    @Delete
    public void deleteThu(Thu thus);
}
