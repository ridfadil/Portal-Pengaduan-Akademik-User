package com.kelompok1.portalpengaduanuser.modelapi;

public class FormAddUser {
    private String NIM;
    private String nama;
    private String pasword;

    public FormAddUser(String NIM, String nama, String pasword) {
        this.NIM = NIM;
        this.nama = nama;
        this.pasword = pasword;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
