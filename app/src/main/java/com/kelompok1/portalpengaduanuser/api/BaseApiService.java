package com.kelompok1.portalpengaduanuser.api;

import com.kelompok1.portalpengaduanuser.modelapi.FormAddUser;
import com.kelompok1.portalpengaduanuser.modelapi.FormLogin;
import com.kelompok1.portalpengaduanuser.modelapi.ResponseLogin;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BaseApiService {
    @POST("loginUser/")
    Call<ResponseLogin> addLogin(@Body FormLogin login);

    @POST("addUser/")
    Call<ResponseBody> addUser(@Body FormAddUser addUser);

    /*@GET("api/company/companyprofil")
    Call<List<ResponseCompanyProfile>>getCompanyProfile(@Header("Authorization") String Authorization);

    @PATCH("api/users/{id}")
    Call<ResponseUpdateProfil> updateProfil(@Header("Authorization") String Authorization,@Path("id") String id, @Body ProfileUpdateForm profil);*/
}
