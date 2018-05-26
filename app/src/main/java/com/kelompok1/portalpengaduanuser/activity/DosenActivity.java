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

public class DosenActivity extends AppCompatActivity {

    @BindView(R.id.et_nama_dosen_pengaduan)
    EditText etNamaDosenPengaduan;
/*    @BindView(R.id.et_matakuliah_dosen)
    EditText etMatakuliahDosen;*/
    @BindView(R.id.et_keluhan_dosen)
    EditText etKeluhanDosen;
    @BindView(R.id.et_saran_dosen)
    EditText etSaranDosen;
    @BindView(R.id.btn_kirim_dosen)
    Button btnKirimDosen;

    String namaDosen,keluhan,saran,NIM="1157050094",jenis="dosen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);
        ButterKnife.bind(this);

        //Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaduan Dosen");
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
        Call<ResponseBody> call = UtilsApi.getAPIService().addPengaduan(pengaduan);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DosenActivity.this, "Berhasil Mengajukan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DosenActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(DosenActivity.this, "Silakan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_kirim_dosen)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_kirim_dosen:
                namaDosen = etNamaDosenPengaduan.getText().toString();
                keluhan = etKeluhanDosen.getText().toString();
                saran = etSaranDosen.getText().toString();

                if(namaDosen.isEmpty() || keluhan.isEmpty() || saran.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Form masih ada yang Kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    kirimAduan(namaDosen,NIM,keluhan,saran,jenis);
                }

                break;
        }
    }
}
