/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.codinate.appmahasiswa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.codinate.appmahasiswa.apihelper.BaseApiService;
import id.codinate.appmahasiswa.apihelper.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText nama, email, password;
    String sNama, sEmail, sPassword;
    Button register;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        nama = (EditText) findViewById(R.id.edNama);
        email = (EditText) findViewById(R.id.edEmail);
        password = (EditText) findViewById(R.id.edPassword);
        register = (Button) findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Loading bro", true, false);
                requestRegister();
            }
        });
    }

    private void requestRegister() {
        sNama = nama.getText().toString();
        sEmail = email.getText().toString();
        sPassword = password.getText().toString();
        mApiService.registerRequest(sEmail, sPassword, sNama).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "On Respon : Berhasil ");
                    loading.dismiss();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("error").equals("false")) {
                            Toast.makeText(mContext, "Berhasil Register", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, LoginActivity.class));
                            finish();
                        } else {
                            String error = object.getString("error_msg");
                            Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("debug", "On Respon : Gagal");
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "OnFailure : Error > "  +t.getMessage());
            }
        });
    }
}
