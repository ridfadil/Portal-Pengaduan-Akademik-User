package com.kelompok1.portalpengaduanuser.modelapi;

public class FormLogin {
    private String NIM;
    private String password;

    public FormLogin(String NIM, String password) {
        this.NIM = NIM;
        this.password = password;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
