package com.cntt.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiThu {

    @PrimaryKey(autoGenerate = true)
    public int lid;

    @ColumnInfo(name = "Ten_Loai_Thu")
    public String ten;
}

