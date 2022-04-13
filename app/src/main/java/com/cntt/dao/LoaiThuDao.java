package com.cntt.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cntt.entity.LoaiThu;

import java.util.List;

@Dao
public interface LoaiThuDao {

    @Query("SELECT * FROM LoaiThu")
    public LiveData<List<LoaiThu>> findAll();

    @Insert
    public void insertLT(LoaiThu loaiThu);

    @Update
    public void updateLT(LoaiThu loaiThu);

    @Delete
    public void deleteLT(LoaiThu loaiThu);
}
