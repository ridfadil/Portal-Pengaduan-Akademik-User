package com.kelompok1.portalpengaduanuser.modelapi;

public class FormAddUser {
    private String nim;
    private String pasword;
    private String nama;

    public FormAddUser(String nim, String pasword, String nama) {
        this.nim = nim;
        this.pasword = pasword;
        this.nama = nama;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
