package com.example.databaselokal.main;

import android.content.Context;
import android.view.View;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataKaryawan;
import com.example.databaselokal.presenter.KaryawanPresenter;

import android.view.View;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataKaryawan;

import java.util.List;

//untuk mengaktifkan fungsi edit dan delete
public interface MainContact {
    interface view extends View.OnClickListener{
        void resetForm();
        void sukses();
        void editData(DataKaryawan item);
        //void deleteData(DataKaryawam item);
    }
    interface datapresenter{
        //readData(AppDatabase database);
        static void editData(String usia, String nama, String alamat, int id, AppDatabase database);
        default void deleteData(DataKaryawan dataKaryawan, AppDatabase database) {
            new KaryawanPresenter.DeleteData(database,dataKaryawan).execute();
        }
    }
    interface Cetak extends View.OnClickListener{
        void getData(List<DataKaryawan> list);
    }
    interface hapus{
        // void resetForm();
        void sukses();
        void deleteData(DataKaryawan item);
    }
}
