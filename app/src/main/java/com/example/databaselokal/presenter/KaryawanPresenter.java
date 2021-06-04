package com.example.databaselokal.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.databaselokal.Edit_data;
import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataKaryawan;
import com.example.databaselokal.main.MainContact;

public class KaryawanPresenter implements MainContact.datapresenter {
    MainContact.view view;
    MainContact.hapus viewH;
    public KaryawanPresenter(MainContact.view view) {
        this.view = view;
    }

    public KaryawanPresenter (MainContact.hapus viewH) {
        this.viewH = viewH;
    }

    static class EditData extends AsyncTask<Void, Void, Integer> {
        private final DataKaryawan dataKaryawan;
        private AppDatabase database;
        private DataKaryawan dataSekolah;
        private Edit_data view;

        public EditData(AppDatabase database, DataKaryawan dataKaryawan) {
            this.database = database;
            this.dataKaryawan = dataKaryawan;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataKaryawan);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.sukses();
        }
    }

    public static class DeleteData extends AsyncTask<Void, Void, Void>{
        private final DataKaryawan dataKaryawan;
        private AppDatabase database;
        private DataKaryawan dataSekolah;
        Context context;
        private Edit_data viewH;

        public DeleteData(AppDatabase database, DataKaryawan dataKaryawan) {
            this.database = database;
            this.dataKaryawan = dataKaryawan;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataKaryawan);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewH.sukses();
        }

    }

    @Override
    public void editData(String usia, String nama, String alamat, int id, AppDatabase database) {

    }

}