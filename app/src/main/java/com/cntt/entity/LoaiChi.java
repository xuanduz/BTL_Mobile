package com.cntt.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiChi {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Ten_Loai_Chi")
    public String tenLoaiChi;
}
