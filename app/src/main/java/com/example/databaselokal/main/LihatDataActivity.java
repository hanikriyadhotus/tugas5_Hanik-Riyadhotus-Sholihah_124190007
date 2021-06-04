package com.example.databaselokal.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselokal.adapter.KaryawanAdapter;
import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataKaryawan;
import com.example.databaselokal.presenter.KaryawanPresenter;
import com.example.databaselokal.R;

import java.util.List;

import static android.R.attr;
import static android.R.drawable;
import static android.R.id;
import static android.R.id.*;
import static android.R.string;
import static android.R.style;

/**
 *
 */
public class LihatDataActivity extends AppCompatActivity implements MainContact.hapus {
    private final AppDatabase appDatabase;
    private KaryawanAdapter karyawanAdapter;
    private KaryawanPresenter karyawanPresenter;
    View view;
    RecyclerView recyclerView;
    private Object KaryawanPresenter;

    public LihatDataActivity(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_submit);
        KaryawanPresenter = new KaryawanPresenter(this);
        int rc_main = 0;
        recyclerView = findViewById(rc_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    readData (appDatabase);
    }

    public void readData(AppDatabase database){
        List<DataKaryawan> list;
        list = database.dao().getData();

        karyawanAdapter = new KaryawanAdapter(getApplicationContext(), list , this);
        recyclerView.setAdapter(karyawanAdapter);
    }
    @Override
    public void sukses(){
        Toast.makeText(getApplicationContext(), "Data Berhasil di hapus", Toast.LENGTH_SHORT).show();
        startActivity(new Intent((getApplicationContext()), LihatDataActivity.class));
    }
    @Override
    public void deleteData(final DataKaryawan item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder = new AlertDialog.Builder(this, style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data");
        builder.setMessage("Anda yakin ingin menghapus data ini?");
        builder.setPositiveButton(string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // resetForm();
                MainContact.datapresenter.deleteData(item, appDatabase);
            }
        }).setNegativeButton(string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setIcon(drawable.ic_dialog_alert);
        builder.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
