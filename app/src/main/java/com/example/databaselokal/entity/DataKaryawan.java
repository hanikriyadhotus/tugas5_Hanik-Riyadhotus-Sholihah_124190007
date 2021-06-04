package com.example.databaselokal.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "karyawan_db") //untuk nama tabelnya

public class DataKaryawan {
    @NonNull
    @PrimaryKey ( autoGenerate = true) //menandakan primary key yaitu id
    @ColumnInfo (name = "id") //info buat atribut dalam database
    private int id; // tipe data dari masing masin gatributnya

    @ColumnInfo(name = "usia")
    private String usia;


    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "alamat")
    private String alamat;

    //selanjutnya di alt enter untuk setter dan getternya
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) { this.id = id; }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
