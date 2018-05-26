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
import com.kelompok1.portalpengaduanuser.modelapi.DataUser;
import com.kelompok1.portalpengaduanuser.modelapi.FormLogin;
import com.kelompok1.portalpengaduanuser.modelapi.ResponseLogin;
import com.kelompok1.portalpengaduanuser.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    List<DataUser> responData = new ArrayList<>();
    SessionManager session;

    private EditText etNimUsername;
    private EditText etPassword;
    private TextView tvRegistrasi;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim, password;
                nim = etNimUsername.getText().toString();
                password = etPassword.getText().toString();
                if (nim.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Form masih ada yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    saveLogin(nim, password);
                }
            }
        });
        tvRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistrasiActivity.class);
                startActivity(i);
            }
        });
    }


    private void saveLogin(String username, String password) {

        FormLogin login = new FormLogin(username, password);
        Call<ResponseLogin> call = UtilsApi.getAPIService().addLogin(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    session = new SessionManager(getApplicationContext());
                    responData = response.body().getData();
                    String Nim = responData.get(0).getNim();
                    String nama = responData.get(0).getNama();
                    String role = responData.get(0).getRole();
                    if (nama.isEmpty()&&Nim.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Login Salah cek email dan username", Toast.LENGTH_SHORT).show();
                    } else {
                        session.createNamaSession(nama);
                        session.createNimSession(Nim);
                        session.createRoleSession(role);
                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }
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

    private void initView() {
        etNimUsername = (EditText) findViewById(R.id.et_nim_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvRegistrasi = (TextView) findViewById(R.id.tv_registrasi);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
