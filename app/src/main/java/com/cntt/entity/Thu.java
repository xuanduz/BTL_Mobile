package com.cntt.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Thu {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Thu")
    public int tId;

    @ColumnInfo(name = "Loai_Thu_ID")
    public int ltID;

    @ColumnInfo(name = "Ten_Loai_Thu")
    public String ten;

    @ColumnInfo(name = "So_Tien")
    public float soTien;

    @ColumnInfo(name = "Ghi_Chu")
    public String ghiChu;


}
