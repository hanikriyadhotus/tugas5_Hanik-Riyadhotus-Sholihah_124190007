package com.example.databaselokal.entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao

public interface DataKaryawanDAO {
    @Update
    int updateData(DataKaryawan dataKaryawan);
    @Insert
    long insertData (DataKaryawan dataKaryawan);
    @Query("Select * from karyawan_db")
    List <DataKaryawan> getData();

    @Delete
    void deleteData(DataKaryawan dataKaryawan);
}
