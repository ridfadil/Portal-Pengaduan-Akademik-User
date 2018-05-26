package com.kelompok1.portalpengaduanuser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kelompok1.portalpengaduanuser.R;
import com.kelompok1.portalpengaduanuser.api.UtilsApi;
import com.kelompok1.portalpengaduanuser.modelapi.FormAddPengaduan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganisasiActivity extends AppCompatActivity {

    @BindView(R.id.nama_organisasi)
    EditText namaOrganisasi;
    @BindView(R.id.keluhan_organisasi)
    EditText keluhanOrganisasi;
    @BindView(R.id.saran_organisasi)
    EditText saranOrganisasi;
    @BindView(R.id.kirim)
    Button kirim;

    String namaOrg,keluhan,saran,jenis="organisasi",NIM="1157050094";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisasi);
        ButterKnife.bind(this);

        //Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaduan Organisasi");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void kirimAduan(String mJudul,String mNim, String mKeluhan,String mSaran,String mJenis) {

        FormAddPengaduan pengaduan = new FormAddPengaduan(mJudul,mNim,mKeluhan,mSaran,mJenis);
        Call<ResponseBody> call = UtilsApi.getAPIService().addPengaduan(pengaduan);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(OrganisasiActivity.this,"Berhasil Mengajukan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(OrganisasiActivity.this,MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(OrganisasiActivity.this, "Silakan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.kirim)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.kirim:
                namaOrg = namaOrganisasi.getText().toString();
                keluhan = keluhanOrganisasi.getText().toString();
                saran = saranOrganisasi.getText().toString();
                if (namaOrg.isEmpty()|| keluhan.isEmpty()|| saran.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Form masih ada yang kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    kirimAduan(namaOrg,NIM,keluhan,saran,jenis);
                }
                break;
        }
    }
}