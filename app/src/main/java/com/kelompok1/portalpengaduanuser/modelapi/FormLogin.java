package com.kelompok1.portalpengaduanuser.modelapi;

public class FormLogin {
    private String nim;
    private String pasword;

    public FormLogin(String nim, String pasword) {
        this.nim = nim;
        this.pasword = pasword;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
