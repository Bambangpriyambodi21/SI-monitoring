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

public class industriMainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTI.php";
    public static final String URLDELETE = "https://monitoring11a.000webhostapp.com/PROJEK/DELETEI.php";
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITI.php";
    public static final String URLEDITindustri = "https://monitoring11a.000webhostapp.com/PROJEK/EDITindustri.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTI.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Dataindustri> itemList = new ArrayList<Dataindustri>();
    Adapterindustri adapter;
    LayoutInflater inflater;
    EditText tidi,tnamai, tbidangi,talamati,ttelpi,tnapemi;
    String vidi,vnamai, vbidangi,valamati,vtelpi,vnapemi;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industriactivity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapterindustri(industriMainActivity.this, itemList);
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
                dialogForm("","","","","","","Tambah");
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getIdi();
                final CharSequence[] pilihanAksi = {"Ubah", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(industriMainActivity.this);
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
                final String idx = itemList.get(i).getNamai();
                detailindustri(idx);
            }
        });
    }

    private void detailindustri(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDITindustri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String namaix = jObj.getString("namai");

                            Intent intent = new Intent(industriMainActivity.this, detailindustri.class);

                            intent.putExtra("industri",namaix);
                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(industriMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namai", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void ubahData(String idi){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idix = jObj.getString("idi");
                            String namaix = jObj.getString("namai");
                            String bidangix = jObj.getString("bidangi");
                            String alamatix = jObj.getString("alamati");
                            String telpix = jObj.getString("telpi");
                            String napemix = jObj.getString("napemi");

                            dialogForm(idix, namaix, bidangix, alamatix, telpix, napemix, "Ubah Profil");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(industriMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idi", idi);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void dialogForm(String id, String nama, String bidang, String alamat, String telp, String napemi, String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(industriMainActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_industri, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Data Industri");

        tidi = (EditText) viewDialog.findViewById(R.id.inIdi);
        tnamai = (EditText) viewDialog.findViewById(R.id.inNamai);
        tbidangi = (EditText) viewDialog.findViewById(R.id.inBidangi);
        talamati = (EditText) viewDialog.findViewById(R.id.inAlamati);
        ttelpi = (EditText) viewDialog.findViewById(R.id.inTelpi);
        tnapemi = (EditText) viewDialog.findViewById(R.id.inNapemi);

        if (id.isEmpty()){
            tidi.setText(null);
            tnamai.setText(null);
            tbidangi.setText(null);
            talamati.setText(null);
            ttelpi.setText(null);
            tnapemi.setText(null);
        }else{
            tidi.setText(id);
            tnamai.setText(nama);
            tbidangi.setText(bidang);
            talamati.setText(alamat);
            ttelpi.setText(telp);
            tnapemi.setText(napemi);
        }

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vidi = tidi.getText().toString();
                vnamai = tnamai.getText().toString();
                vbidangi = tbidangi.getText().toString();
                valamati = talamati.getText().toString();
                vtelpi = ttelpi.getText().toString();
                vnapemi = tnapemi.getText().toString();

                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tidi.setText(null);
                tnamai.setText(null);
                tbidangi.setText(null);
                talamati.setText(null);
                ttelpi.setText(null);
                tnapemi.setText(null);

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
                        Toast.makeText(industriMainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(industriMainActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vidi.isEmpty()) {
                    params.put("idi", vidi);
                    params.put("namai", vnamai);
                    params.put("bidangi", vbidangi);
                    params.put("alamati", valamati);
                    params.put("telpi", vtelpi);
                    params.put("napemi", vnapemi);
                    return params;
                }else{
                    params.put("idi", vidi);
                    params.put("namai", vnamai);
                    params.put("bidangi", vbidangi);
                    params.put("alamati", valamati);
                    params.put("telpi", vtelpi);
                    params.put("napemi", vnapemi);
                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
    public void hapusData(String idi){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(industriMainActivity.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(industriMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idi", idi);

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

                        Dataindustri item = new Dataindustri();

                        item.setIdi(obj.getString("idi"));
                        item.setNamai(obj.getString("namai"));
                        item.setBidangi(obj.getString("bidangi"));
                        item.setAlamati(obj.getString("alamati"));
                        item.setTelpi(obj.getString("telpi"));
                        item.setNapemi(obj.getString("napemi"));

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
