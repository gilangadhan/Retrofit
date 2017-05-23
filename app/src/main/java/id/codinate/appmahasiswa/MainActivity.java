/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.codinate.appmahasiswa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class MainActivity extends AppCompatActivity {
    EditText eid, ejenis_pohon, eusia_pohon, ekondisi_pohon, elatitude, elongitude, efoto_pohon, eketerangan;
    String sid, suuid, sjenis_pohon, susia_pohon, skondisi_pohon, slatitude, slongitude, sfoto_pohon, sketerangan;
    Button submit;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        suuid = getIntent().getStringExtra("uuid");
        String nama = getIntent().getStringExtra("nama");
        mContext = this;
        mApiService = UtilsApi.getApiService();

        submit = (Button) findViewById(R.id.buttonSUBMIT);
        eid = (EditText) findViewById(R.id.editID);
        ejenis_pohon = (EditText) findViewById(R.id.editjenis_pohon);
        eusia_pohon = (EditText) findViewById(R.id.editusia_pohon);
        ekondisi_pohon = (EditText) findViewById(R.id.editkondisi_pohon);
        elatitude = (EditText) findViewById(R.id.editlatitude);
        elongitude = (EditText) findViewById(R.id.editlongitude);
        efoto_pohon = (EditText) findViewById(R.id.editfoto_pohon);
        eketerangan = (EditText) findViewById(R.id.editketerangan);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Loading bro", true, false);
                submit();
            }
        });

        ArrayAdapter myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, POHON);

        final AutoCompleteTextView textView;
        textView = (AutoCompleteTextView)
                findViewById(R.id.editkondisi_pohon);
        textView.setAdapter(myAdapter);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                textView.showDropDown();
            }
        });


    }

    private void submit() {
        sid = eid.getText().toString();
        sjenis_pohon = ejenis_pohon.getText().toString();
        susia_pohon = eusia_pohon.getText().toString();
        skondisi_pohon = ekondisi_pohon.getText().toString();
        slatitude = elatitude.getText().toString();
        slongitude = elongitude.getText().toString();
        sfoto_pohon = efoto_pohon.getText().toString();
        sketerangan = eketerangan.getText().toString();
        mApiService.insertPohon(sid, suuid, sjenis_pohon, susia_pohon, skondisi_pohon, slatitude, slongitude, sfoto_pohon, sketerangan).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "On Respon : Berhasil ");
                    loading.dismiss();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("error").equals("false")) {
                            String id = object.getJSONObject("pohon").getString("id");
                            String uuid = object.getJSONObject("pohon").getString("uuid");
                            String jenis_pohon = object.getJSONObject("pohon").getString("jenis_pohon");
                            String usia_pohon = object.getJSONObject("pohon").getString("usia_pohon");
                            String latitude = object.getJSONObject("pohon").getString("latitude");
                            String longitude = object.getJSONObject("pohon").getString("longitude");
                            String foto_pohon = object.getJSONObject("pohon").getString("foto_pohon");
                            String tgl = object.getJSONObject("pohon").getString("tgl");
                            String keterangan = object.getJSONObject("pohon").getString("keterangan");
                            String kondisi_pohon = object.getJSONObject("pohon").getString("kondisi_pohon");

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Submit");
                            builder.setMessage(
                                    "ID Pohon:" + id + "\n" +
                                            "UUID:" + uuid + "\n" +
                                            "Jenis Pohon :" + jenis_pohon + "\n" +
                                            "Latitude :" + latitude + "\n" +
                                            "Longitude :" + longitude + "\n" +
                                            "Foto Pohon :" + foto_pohon + "\n" +
                                            "Tanggal :" + tgl + "\n" +
                                            "Keterangan :" + keterangan + "\n" +
                                            "Kondisi Pohon :" + kondisi_pohon + "\n" +
                                            "Usia Pohon :" + usia_pohon
                            );
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();
                            eid.setText("");
                            ejenis_pohon.setText("");
                            eusia_pohon.setText("");
                            ekondisi_pohon.setText("");
                            elatitude.setText("");
                            elongitude.setText("");
                            efoto_pohon.setText("");
                            eketerangan.setText("");

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
                Log.e("debug", "OnFailure : Error > " + t.getMessage());
            }
        });

    }

    private static final String[] POHON = new String[]{
            "sehat", "cukup", "keropos", "mati",
    };
}

