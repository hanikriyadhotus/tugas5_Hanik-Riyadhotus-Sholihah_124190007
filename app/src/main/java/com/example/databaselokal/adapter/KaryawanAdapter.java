package com.example.databaselokal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselokal.Edit_data;
import com.example.databaselokal.R;
import com.example.databaselokal.entity.DataKaryawan;
import com.example.databaselokal.main.MainContact;

import java.text.BreakIterator;
import java.util.List;
import java.util.Objects;

public class KaryawanAdapter extends RecyclerView.Adapter<KaryawanAdapter.ViewHolder>{
    Context context;
    List<DataKaryawan> list;
    MainContact.hapus view;

    public KaryawanAdapter (Context context, List<DataKaryawan> list, MainContact
                            .hapus view){
        this.view = view;
        this.context= context;
        this.list = list;
    }
    @NonNull
    @Override
    public KaryawanAdapter.ViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_lihat_data, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataKaryawan data = list.get(i);
        viewHolder.tvNama.setText(data.getNama());
        viewHolder.tvAlamat.setText(data.getAlamat());
        viewHolder.tvUsia.setText(data.getUsia());
        viewHolder.id.setText(String.valueOf(data.getId())); //disini berbeda karena id itu langsung autogenerate jadi selalu ada nilainya
        //membuat action hapus
        Objects.requireNonNull(viewHolder).btnHapus.setOnClickListener(v -> {
            view.deleteData(data); //terlempar ke class maincontact
            // return true;
        });
        //membuat action hapus
        viewHolder.btnEdit.setOnClickListener(v -> {
            Intent x = new Intent(context, Edit_data.class); //pertama disini kita melemparkan class dulu dengan menyimpan berbagai data
            x.putExtra("nama", data.getNama()); //data pertama dengan name valuenya nama, dan class data sekolah.ambil nilai dari get yg ada disana
            x.putExtra("alamat", data.getAlamat());
            x.putExtra("usia", data.getUsia());
            x.putExtra("id", data.getId());
            //dia mengirim ke class edit data bahwa akan memulai aktivitas dalam tugas baru yang harus dilakukan
            x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //proses perpindahan
            context.startActivity(x);
        });
        {
            return;
        } //mendapatkan ukuran set data yg akan ditampilkan

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View btnHapus;
        public BreakIterator tvAlamat;
        public BreakIterator tvNama;
        public BreakIterator id;
        public BreakIterator tvUsia;
        public View btnEdit;

        public ViewHolder() {
            this(KaryawanAdapter.this.view);
        }

        public ViewHolder(MainContact.hapus view) {
            super((View) view);

        }

        public ViewHolder(View view) {
            super(view);
        }
    }
}