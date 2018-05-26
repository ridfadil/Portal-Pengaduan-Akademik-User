package com.kelompok1.portalpengaduanuser.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.kelompok1.portalpengaduanuser.R;
import com.kelompok1.portalpengaduanuser.adapter.AdapterDosen;
import com.kelompok1.portalpengaduanuser.modelapi.Dosen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDataDosenActivity extends AppCompatActivity {

    private List<Dosen> listDosen;
    private RecyclerView mRecyclerView;
    private AdapterDosen mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("LIst Dosen");

        setContentView(R.layout.activity_list_data_dosen);
        listDosen = new ArrayList<>();

        listDosen.add(new Dosen("1","Bu Dian","089456786767","Logika Informatika","-6.904765","107.624700"));
        listDosen.add(new Dosen("2","Pak Cepi","08946567567","Sistem TErdistribusi","-6.803008","107.625794"));
        listDosen.add(new Dosen("3","Pak Aldy","08956666567","Aplikasi Flatform Khusus","-6.902008","107.625764"));
        listDosen.add(new Dosen("4","Pak Cecep","0877878767","Jaringan Komputer","-6.703048","107.6285664"));
        listDosen.add(new Dosen("5","Pak Irfan","0892223354","Dasar Pemrograman","-6.603028","107.6255622"));
        listDosen.add(new Dosen("6","Bu Diena","0891111122","Rekayasa Perangkat Lunak","-6.203548","107.6255604"));
        listDosen.add(new Dosen("7","Bu Rieke","0893431156","Proyek Perangkat Lunak","-6.303018","107.6755694"));
        listDosen.add(new Dosen("8","Pak ali","0834564556","Pengantar Informatika","-6.403021","107.6355674"));
        listDosen.add(new Dosen("9","Pak Indra","085223557564","Struktur Data","-6.103248","107.6278364"));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_dosen);

        mAdapter = new AdapterDosen(getApplicationContext(), listDosen);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


