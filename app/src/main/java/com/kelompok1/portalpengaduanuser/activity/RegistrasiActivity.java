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
import com.kelompok1.portalpengaduanuser.modelapi.FormAddUser;
import com.kelompok1.portalpengaduanuser.modelapi.FormLogin;
import com.kelompok1.portalpengaduanuser.modelapi.ResponseLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {

    @BindView(R.id.et_nim_registrasi)
    EditText etNimRegistrasi;
    @BindView(R.id.et_nama_registrasi)
    EditText etNamaRegistrasi;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.btn_registrasi)
    Button btnRegistrasi;

    String nim,nama,repass,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        ButterKnife.bind(this);

        //Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registrasi");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveRegister(String mNim, String mNama,String mPass) {

        FormAddUser user = new FormAddUser(mNim,mNama,mPass);
        Call<ResponseBody> call = UtilsApi.getAPIService().addUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistrasiActivity.this,"Berhasil Register", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(RegistrasiActivity.this, "Silakan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_registrasi)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_registrasi:
                nim = etNimRegistrasi.getText().toString();
                pass = etPassword.getText().toString();
                nama = etNamaRegistrasi.getText().toString();
                repass = etRepassword.getText().toString();

                if(nim.isEmpty() || pass.isEmpty() || nama.isEmpty() || repass.isEmpty()){
                    if (pass.equals(repass)){
                        Toast.makeText(RegistrasiActivity.this, "Password Tidak sesuai, Silakan ulangi", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        saveRegister(nim,nama,pass);
                    }
                }
                else{
                    Toast.makeText(RegistrasiActivity.this, "Form masih ada yang Kosong", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
