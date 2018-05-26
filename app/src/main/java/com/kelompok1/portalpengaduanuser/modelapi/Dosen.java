package com.kelompok1.portalpengaduanuser.modelapi;

public class Dosen {
    private String id,nama,noHp,kuliah,longit,latid;

    public Dosen(String id, String nama, String noHp, String kuliah, String longit, String latid) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.kuliah = kuliah;
        this.longit = longit;
        this.latid = latid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getKuliah() {
        return kuliah;
    }

    public void setKuliah(String kuliah) {
        this.kuliah = kuliah;
    }

    public String getLongit() {
        return longit;
    }

    public void setLongit(String longit) {
        this.longit = longit;
    }

    public String getLatid() {
        return latid;
    }

    public void setLatid(String latid) {
        this.latid = latid;
    }
}
