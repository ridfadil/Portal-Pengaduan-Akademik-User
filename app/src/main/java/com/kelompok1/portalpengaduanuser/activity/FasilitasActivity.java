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
import com.kelompok1.portalpengaduanuser.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FasilitasActivity extends AppCompatActivity {

    @BindView(R.id.et_nama_fasilitas)
    EditText etNamaFasilitas;
    @BindView(R.id.et_keluhan_fasilitas)
    EditText etKeluhanFasilitas;
    @BindView(R.id.saran_fasilitas)
    EditText saranFasilitas;
    @BindView(R.id.btn_kirim_fasilitas)
    Button btnKirimFasilitas;
    SessionManager session;

    String namaFasilitas, keluhan, saran, jenis = "fasilitas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        ButterKnife.bind(this);

        //Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaduan Fasilitas");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void kirimAduan(String mJudul, String mNim, String mKeluhan, String mSaran, String mJenis) {

        FormAddPengaduan pengaduan = new FormAddPengaduan(mJudul, mNim, mKeluhan, mSaran, mJenis);
        Call<ResponseBody> call = UtilsApi.getAPIService().addFasilitas(pengaduan);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FasilitasActivity.this, "Berhasil Mengajukan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(FasilitasActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(FasilitasActivity.this, "Silakan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_kirim_fasilitas)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_kirim_fasilitas:
                namaFasilitas = etNamaFasilitas.getText().toString();
                keluhan = etKeluhanFasilitas.getText().toString();
                saran = saranFasilitas.getText().toString();
                if (namaFasilitas.isEmpty() || keluhan.isEmpty()|| saran.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Form Masih Ada yang Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    session = new SessionManager(getApplicationContext());
                    String nim= session.getNim();
                    kirimAduan(namaFasilitas, nim, keluhan, saran, jenis);
                }
                break;
        }
    }
}
