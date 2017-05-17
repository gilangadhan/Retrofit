/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.codinate.appmahasiswa.apihelper;

/**
 * Created by Gilang Ramadhan on 17/05/2017.
 */

public class UtilsApi {
    // untuk koneksi localhost atau server
    public static final  String BASE_URL_API = "http://192.168.43.233/appandroid/";
    //mendeklarasiakn interface BaseApiServece
    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
