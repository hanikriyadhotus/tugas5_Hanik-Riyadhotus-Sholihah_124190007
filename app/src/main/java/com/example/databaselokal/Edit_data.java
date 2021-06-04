package com.example.databaselokal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databaselokal.adapter.KaryawanAdapter;
import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataKaryawan;
import com.example.databaselokal.main.LihatDataActivity;
import com.example.databaselokal.main.MainContact;
import com.example.databaselokal.presenter.KaryawanPresenter;

public class Edit_data extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private KaryawanPresenter karyawanPresenter;
    private KaryawanAdapter karyawanAdapter;
    private EditText etNama, etAlamat, etUsia ;
    private Button btnSubmit;
    private String setNama, setAlamat, setUsia ;
    private boolean edit = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etUsia = findViewById(R.id.et_usia);

        btnSubmit = findViewById(R.id.btn_submit);
        karyawanPresenter = new KaryawanPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setNama = getIntent().getStringExtra("nama");
        setAlamat = getIntent().getStringExtra("alamat");
        setUsia = getIntent().getStringExtra("usia");

        id = getIntent().getIntExtra("id", 99);

        etNama.setText(setNama);
        etAlamat.setText(setAlamat);
        etUsia.setText(setUsia);

        btnSubmit.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void resetForm() {
        etUsia.setText("");
        etNama.setText("");
        etAlamat.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }

    @Override
    public void editData(DataKaryawan item) {
        etNama.setText(item.getNama());
        etAlamat.setText(item.getAlamat());
        etUsia.setText(item.getUsia());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String Usia, Nama, Alamat;
        Usia = etUsia.getText().toString();
        Nama = etNama.getText().toString();
        Alamat= etAlamat.getText().toString();
        if(v ==  btnSubmit){
            if(Usia.equals("") ||  Nama.equals("") || Alamat.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                MainContact.datapresenter.editData(Usia,  Nama, Alamat, id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}
