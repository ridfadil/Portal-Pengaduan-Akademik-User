package com.kelompok1.portalpengaduanuser.modelapi;

public class FormAddPengaduan {
    private String judul;
    private String NIM;
    private String keluhan;
    private String saran;
    private String jenis_keluhan;

    public FormAddPengaduan(String judul, String NIM, String keluhan, String saran, String jenis_keluhan) {
        this.judul = judul;
        this.NIM = NIM;
        this.keluhan = keluhan;
        this.saran = saran;
        this.jenis_keluhan = jenis_keluhan;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public String getJenis_keluhan() {
        return jenis_keluhan;
    }

    public void setJenis_keluhan(String jenis_keluhan) {
        this.jenis_keluhan = jenis_keluhan;
    }
}
