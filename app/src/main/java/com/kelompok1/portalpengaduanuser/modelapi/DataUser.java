package com.kelompok1.portalpengaduanuser.modelapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {

/*    {
        "status": "success",
            "login": "1",
            "data": [
        {
            "nim": "1157050094",
                "nama": "fadil",
                "password": "farid123",
                "role": "user"
        }
    ]
    }*/

    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("role")
    @Expose
    private String role;

    public DataUser(String nim, String nama, String password, String role) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
        this.role = role;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}