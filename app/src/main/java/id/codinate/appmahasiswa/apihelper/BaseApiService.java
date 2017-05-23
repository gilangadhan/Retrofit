/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.codinate.appmahasiswa.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Gilang Ramadhan on 17/05/2017.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("nama") String nama);

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseBody> insertPohon(@Field("id") String id,
                                   @Field("uuid") String uuid,
                                   @Field("jenis_pohon") String jenis_pohon,
                                   @Field("usia_pohon") String usia_pohon,
                                   @Field("kondisi_pohon") String kondisi_pohon,
                                   @Field("latitude") String latitude,
                                   @Field("longitude") String longitude,
                                   @Field("foto_pohon") String foto_pohon,
                                   @Field("keterangan") String keterangan
    );


}
