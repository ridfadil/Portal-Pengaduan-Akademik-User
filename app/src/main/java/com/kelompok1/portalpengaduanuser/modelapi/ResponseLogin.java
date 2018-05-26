package com.kelompok1.portalpengaduanuser.modelapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/*{
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

public class ResponseLogin {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DataUser> data;

    public ResponseLogin(String status, List<DataUser> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataUser> getData() {
        return data;
    }

    public void setData(List<DataUser> data) {
        this.data = data;
    }
}