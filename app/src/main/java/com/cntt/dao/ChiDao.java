package com.cntt.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cntt.entity.Chi;
import com.cntt.entity.ThongKeLoaiChi;

import java.util.List;

@Dao
public interface ChiDao {

    @Query("SELECT * FROM Chi")
    public LiveData<List<Chi>> findAll();

    @Query("SELECT SUM(soTien) FROM Chi")
    public LiveData<Float> tongChi();

    @Query("SELECT lc.id as lid,lc.Ten_Loai_Chi as tenChi, SUM(soTien) as tongTien FROM Chi c INNER JOIN LoaiChi lc WHERE c.lcId = lc.id " +
            "GROUP BY lc.id,lc.Ten_Loai_Chi")
    public LiveData<List<ThongKeLoaiChi>> ThongKeChi();

    @Insert
    public void insertC(Chi chi);

    @Update
    public void updateC(Chi chi);

    @Delete
    public void deleteC(Chi Chi);
}
