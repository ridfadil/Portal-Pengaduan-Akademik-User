package com.kelompok1.portalpengaduanuser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok1.portalpengaduanuser.R;
import com.kelompok1.portalpengaduanuser.api.UtilsApi;
import com.kelompok1.portalpengaduanuser.modelapi.FormLogin;
import com.kelompok1.portalpengaduanuser.modelapi.ResponseLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_nim_username)
    EditText etNimUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_registrasi)
    TextView tvRegistrasi;
    @BindView(R.id.btn_login)
    Button btnLogin;

    String nim,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    private void saveLogin(String username, String password) {

        FormLogin login = new FormLogin(username, password);
        Call<ResponseLogin> call = UtilsApi.getAPIService().addLogin(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    //String status=ResponseLogin.getStatus();
/*                    ResponseLogin responsesStatus = response.body().getStatus();
                    User responsesNama = response.body().getUser();
                    User responsesPosisi = response.body().getUser();
                    User responsesNoHp = response.body().getUser();
                    Token responsesToken = response.body().getToken();
                    String token = responsesToken.getAccessToken();
                    String id = responsesId.getId();
                    String email = responsesId.getEmail();
                    String noHp = responsesNoHp.getNoHp();
                    String nama = responsesNama.getNama();
                    String posisi = responsesPosisi.getPosisi();
                    session.createPosisiSession(posisi);
                    session.createIdSession(id);
                    session.createEmailSession(email);
                    session.createLoginSession(token);
                    session.createNamaSession(nama);
                    session.createNoHpSession(noHp);
                    *//*Snackbar.make(findViewById(R.id.rootView), "No internet connection", Snackbar.LENGTH_SHORT).show();*//*
*//*                  Toast.makeText(LoginActivity.this, id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, nama, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, noHp, Toast.LENGTH_SHORT).show();*/
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "check your Email or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @butterknife.OnClick({R.id.tv_registrasi, R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_registrasi:
                Intent i = new Intent(LoginActivity.this,RegistrasiActivity.class);
                startActivity(i);
                break;
            case R.id.btn_login:
                nim = etNimUsername.getText().toString();
                password = etPassword.getText().toString();
                if (nim != null && password != null){
                    saveLogin(nim,password);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Form masih ada yang kosong",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
