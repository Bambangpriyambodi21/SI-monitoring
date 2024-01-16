package com.example.myapplication;


import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class guruMainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECT.php";
    public static final String URLDELETE = "https://monitoring11a.000webhostapp.com/PROJEK/DELETE.php";
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDIT.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERT.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Dataguru> itemList = new ArrayList<Dataguru>();
    Adapterguru adapter;
    LayoutInflater inflater;
    EditText tidg,tnipg,tnamag,ttelpg,tkeling,tkeling1,tkeling2,tkeling3,tkeling4,tkeling5,tkeling6;
    String vidg,vnipg,vnamag,vtelpg,vkeling,vkeling1,vkeling2,vkeling3,vkeling4,vkeling5,vkeling6;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guruactivity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapterguru(guruMainActivity.this, itemList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //statement jika fab diklik
                dialogForm("","","","","","","","","","","","Tambah");
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getIdg();
                final CharSequence[] pilihanAksi = {"Ubah", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(guruMainActivity.this);
                dialog.setItems(pilihanAksi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                //jika dipilih hapus
                                ubahData(idx);
                                break;

                            case 1:
                                //jika memilih edit/ubah
                                hapusData(idx);
                                break;

                        }

                    }
                }).show();
                return false;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String idx = itemList.get(i).getIdg();
//                startActivity(new Intent(guruMainActivity.this, detailguru.class));
                detailgurua(idx);
            }
        });


    }

    private void detailgurua(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idpx = jObj.getString("idg");
                            String kodeix = jObj.getString("namag");
                            String kodepx = jObj.getString("nipg");
                            String namapx = jObj.getString("telpg");
                            String siswa8x = jObj.getString("fotog");
                            String siswa1x = jObj.getString("keling");
                            String siswa2x = jObj.getString("keling1");
                            String siswa3x = jObj.getString("keling2");
                            String siswa4x = jObj.getString("keling3");
                            String siswa5x = jObj.getString("keling4");
                            String siswa6x = jObj.getString("keling5");
                            String siswa7x = jObj.getString("keling6");

                            Intent intent = new Intent(guruMainActivity.this, detailguru.class);
                            intent.putExtra("idg",idpx);
                            intent.putExtra("namag",kodeix);
                            intent.putExtra("nipg",kodepx);
                            intent.putExtra("telpg",namapx);
                            intent.putExtra("fotog",siswa8x);
                            intent.putExtra("keling",siswa1x);
                            intent.putExtra("keling1",siswa2x);
                            intent.putExtra("keling2",siswa3x);
                            intent.putExtra("keling3",siswa4x);
                            intent.putExtra("keling4",siswa5x);
                            intent.putExtra("keling5",siswa6x);
                            intent.putExtra("keling6",siswa7x);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(guruMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idg", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void ubahData(String idg){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idgx = jObj.getString("idg");
                            String namagx = jObj.getString("namag");
                            String nipgx = jObj.getString("nipg");
                            String telpgx = jObj.getString("telpg");
                            String kelingx = jObj.getString("keling");
                            String keling1x = jObj.getString("keling1");
                            String keling2x = jObj.getString("keling2");
                            String keling3x = jObj.getString("keling3");
                            String keling4x = jObj.getString("keling4");
                            String keling5x = jObj.getString("keling5");
                            String keling6x = jObj.getString("keling6");

                            dialogForm(idgx, namagx, nipgx, telpgx, kelingx, keling1x, keling2x, keling3x, keling4x,keling5x,keling6x, "Ubah Profil");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(guruMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idg", idg);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void dialogForm(String id, String nama, String nip, String telp, String kelin, String kelin1, String kelin2, String kelin3, String kelin4, String kelin5, String kelin6, String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(guruMainActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_guru, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Data Guru");

        tidg = (EditText) viewDialog.findViewById(R.id.inIdg);
        tnamag = (EditText) viewDialog.findViewById(R.id.inNamag);
        tnipg = (EditText) viewDialog.findViewById(R.id.inNIPg);
        ttelpg = (EditText) viewDialog.findViewById(R.id.inTelpg);
        tkeling = (EditText) viewDialog.findViewById(R.id.inKelIng);
        tkeling1 = (EditText) viewDialog.findViewById(R.id.inKelIng1);
        tkeling2 = (EditText) viewDialog.findViewById(R.id.inKelIng2);
        tkeling3 = (EditText) viewDialog.findViewById(R.id.inKelIng3);
        tkeling4 = (EditText) viewDialog.findViewById(R.id.inKelIng4);
        tkeling5 = (EditText) viewDialog.findViewById(R.id.inKelIng5);
        tkeling6 = (EditText) viewDialog.findViewById(R.id.inKelIng6);

        if (id.isEmpty()){
            tidg.setText(null);
            tnamag.setText(null);
            tnipg.setText(null);
            ttelpg.setText(null);
            tkeling.setText(null);
            tkeling1.setText(null);
            tkeling2.setText(null);
            tkeling3.setText(null);
            tkeling4.setText(null);
            tkeling5.setText(null);
            tkeling6.setText(null);
        }else{
            tidg.setText(id);
            tnamag.setText(nama);
            tnipg.setText(nip);
            ttelpg.setText(telp);
            tkeling.setText(kelin);
            tkeling1.setText(kelin1);
            tkeling2.setText(kelin2);
            tkeling3.setText(kelin3);
            tkeling4.setText(kelin4);
            tkeling5.setText(kelin5);
            tkeling6.setText(kelin6);
        }

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vidg = tidg.getText().toString();
                vnamag = tnamag.getText().toString();
                vnipg = tnipg.getText().toString();
                vtelpg = ttelpg.getText().toString();
                vkeling = tkeling.getText().toString();
                vkeling1 = tkeling1.getText().toString();
                vkeling2 = tkeling2.getText().toString();
                vkeling3 = tkeling3.getText().toString();
                vkeling4 = tkeling4.getText().toString();
                vkeling5 = tkeling5.getText().toString();
                vkeling6 = tkeling6.getText().toString();

                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tidg.setText(null);
                tnamag.setText(null);
                tnipg.setText(null);
                ttelpg.setText(null);
                tkeling.setText(null);
                tkeling1.setText(null);
                tkeling2.setText(null);
                tkeling3.setText(null);
                tkeling4.setText(null);
                tkeling5.setText(null);
                tkeling6.setText(null);

            }
        });
        dialogForm.show();

    }
    public void simpan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLINSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(guruMainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(guruMainActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vidg.isEmpty()) {
                    params.put("idg", vidg);
                    params.put("namag", vnamag);
                    params.put("nipg", vnipg);
                    params.put("telpg", vtelpg);
                    params.put("fotog", "profilee.png");
                    params.put("keling", vkeling);
                    params.put("keling1", vkeling1);
                    params.put("keling2", vkeling2);
                    params.put("keling3", vkeling3);
                    params.put("keling4", vkeling4);
                    params.put("keling5", vkeling5);
                    params.put("keling6", vkeling6);
                    return params;
                }else{
                    params.put("idg", vidg);
                    params.put("namag", vnamag);
                    params.put("nipg", vnipg);
                    params.put("telpg", vtelpg);
                    params.put("fotog", "profilee.png");
                    params.put("keling", vkeling);
                    params.put("keling1", vkeling1);
                    params.put("keling2", vkeling2);
                    params.put("keling3", vkeling3);
                    params.put("keling4", vkeling4);
                    params.put("keling5", vkeling5);
                    params.put("keling6", vkeling6);
                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
    public void hapusData(String idg){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(guruMainActivity.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(guruMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idg", idg);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();

    }
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(URLSELECT, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Dataguru item = new Dataguru();

                        item.setIdg(obj.getString("idg"));
                        item.setNamag(obj.getString("namag"));
                        item.setNipg(obj.getString("nipg"));
                        item.setTelpg(obj.getString("telpg"));
                        item.setKeling(obj.getString("keling"));
                        item.setKeling1(obj.getString("keling1"));
                        item.setKeling2(obj.getString("keling2"));
                        item.setKeling3(obj.getString("keling3"));
                        item.setKeling4(obj.getString("keling4"));
                        item.setKeling4(obj.getString("keling5"));
                        item.setKeling4(obj.getString("keling6"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }
}