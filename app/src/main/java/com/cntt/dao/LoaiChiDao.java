package com.cntt.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cntt.entity.LoaiChi;

import java.util.List;

@Dao
public interface LoaiChiDao {

    @Query("SELECT * FROM LoaiChi")
    public LiveData<List<LoaiChi>> findAll();

    @Insert
    public void insertLC(LoaiChi loaiChi);

    @Update
    public void updateLC(LoaiChi loaiChi);

    @Delete
    public void deleteLC(LoaiChi loaiChi);
}
