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
import com.kelompok1.portalpengaduanuser.modelapi.FormAddUser;
import com.kelompok1.portalpengaduanuser.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdministrasiActivity extends AppCompatActivity {

    @BindView(R.id.et_jenis_administrasi)
    EditText etJenisAdministrasi;
    @BindView(R.id.et_keluhan_administrasi)
    EditText etKeluhanAdministrasi;
    @BindView(R.id.et_saran_administrasi)
    EditText etSaranAdministrasi;
    @BindView(R.id.btn_kirim_administrasi)
    Button btnKirimAdministrasi;
    SessionManager session;

    String jenis ="administrasi",keluhan,saran,judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrasi);
        ButterKnife.bind(this);

        //Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaduan Administrasi");
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
        Call<ResponseBody> call = UtilsApi.getAPIService().addAdministrasi(pengaduan);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AdministrasiActivity.this,"Berhasil Mengajukan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AdministrasiActivity.this,MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(AdministrasiActivity.this, "Silakan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_kirim_administrasi)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_kirim_administrasi:
                judul = etJenisAdministrasi.getText().toString();
                keluhan = etKeluhanAdministrasi.getText().toString();
                saran = etSaranAdministrasi.getText().toString();
                if (jenis.isEmpty()  || saran.isEmpty() || keluhan.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Form Masih ada yang Kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    session = new SessionManager(getApplicationContext());
                    String nim= session.getNim();
                    Toast.makeText(AdministrasiActivity.this,"Nim : "+nim,Toast.LENGTH_SHORT).show();
                    kirimAduan(judul,nim,keluhan,saran,jenis);
                }
                break;
        }
    }
}
