package com.kelompok1.portalpengaduanuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kelompok1.portalpengaduanuser.R;
import com.kelompok1.portalpengaduanuser.modelapi.Dosen;

import java.util.List;

public class AdapterDosen extends RecyclerView.Adapter<AdapterDosen.ListDosenViewHolder> {
    //deklarasi global variabel
    private Context context;
    private final List<Dosen> listDosen;


    //konstruktor untuk menerima data adapter
    public AdapterDosen(Context context, List<Dosen> listDosen) {
        this.context = context;
        this.listDosen = listDosen;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListDosenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dosen, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListDosenViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListDosenViewHolder holder, int position) {
        final Dosen mCurrent = listDosen.get(position);
        final String longit = mCurrent.getLongit();
        final String latid = mCurrent.getLatid();
        final String nama = mCurrent.getNama();
        holder.tvNamaDosen.setText(mCurrent.getNama());
        holder.tvNoHp.setText(mCurrent.getNoHp());
        holder.tvMatkul.setText(mCurrent.getKuliah());
        holder.kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q="+longit+","+latid+" (" + nama + ")"));
                context.startActivity(intent);
            }
        });
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listDosen.size();
    }

    public class ListDosenViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaDosen, tvNoHp, tvMatkul;
        private Button kirim;

        final AdapterDosen mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListDosenViewHolder(View itemView, AdapterDosen adapter) {
            super(itemView);
            context = itemView.getContext();
            tvNamaDosen = itemView.findViewById(R.id.list_nama_dosen);
            tvMatkul = itemView.findViewById(R.id.list_matkul_dosen);
            tvNoHp = itemView.findViewById(R.id.list_no_telp);
            kirim = itemView.findViewById(R.id.btn_list_lokasi_dosen);
            this.mAdapter = adapter;
        }
    }
}

