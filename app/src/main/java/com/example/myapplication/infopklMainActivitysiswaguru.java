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

public class infopklMainActivitysiswaguru extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTL.php";
    public static final String URLDELETE = "https://monitoring11a.000webhostapp.com/PROJEK/DELETEL.php";
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITL.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTL.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Datainfopkl> itemList = new ArrayList<Datainfopkl>();
    Adapterinfopkl adapter;
    LayoutInflater inflater;
    EditText tidinfo,tketinfo,tdetinfo;
    String vidinfo,vketinfo,vdetinfo;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopklactivity_main);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapterinfopkl(infopklMainActivitysiswaguru.this, itemList);
        list.setAdapter(adapter);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
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
//        fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //statement jika fab diklik
//                dialogForm("","","","Tambah");
//            }
//        });
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                final String idx = itemList.get(position).getIdinfo();
//                final CharSequence[] pilihanAksi = {};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(infopklMainActivitysiswaguru.this);
//                dialog.setItems(pilihanAksi, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case 0:
//                                //jika dipilih hapus
//                                ubahData(idx);
//                                break;
//
//                            case 1:
//                                //jika memilih edit/ubah
//                                hapusData(idx);
//                                break;
//
//                        }
//
//                    }
//                }).show();
//                return false;
//            }
//        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String idx = itemList.get(i).getIdinfo();
                detailpenempatan(idx);
            }
        });
    }

    private void detailpenempatan(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idinfox = jObj.getString("idinfo");
                            String ketinfox = jObj.getString("ketinfo");
                            String detinfox = jObj.getString("detinfo");

                            Intent intent = new Intent(infopklMainActivitysiswaguru.this, detailinfopklMainActivity.class);
                            intent.putExtra("idinfo",idinfox);
                            intent.putExtra("ketinfo",ketinfox);
                            intent.putExtra("detinfo",detinfox);
                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(infopklMainActivitysiswaguru.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idinfo", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }


    public void ubahData(String idp){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idinfox = jObj.getString("idinfo");
                            String ketinfox = jObj.getString("ketinfo");
                            String detinfox = jObj.getString("detinfo");

                            dialogForm(idinfox,ketinfox, detinfox, "Ubah ");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(infopklMainActivitysiswaguru.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idinfo", idp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void dialogForm(String idinfo,String ketinfo,String detinfo,String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(infopklMainActivitysiswaguru.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_infopkl, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Form Data Guru");

        tidinfo = (EditText) viewDialog.findViewById(R.id.inidinfo);
        tketinfo = (EditText) viewDialog.findViewById(R.id.inketinfo);
        tdetinfo = (EditText) viewDialog.findViewById(R.id.indetinfo);

        if (idinfo.isEmpty()){
            tidinfo.setText(null);
            tketinfo.setText(null);
            tdetinfo.setText(null);
        }else{
            tidinfo.setText(idinfo);
            tketinfo.setText(ketinfo);
            tdetinfo.setText(detinfo);
        }

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vidinfo = tidinfo.getText().toString();
                vketinfo = tketinfo.getText().toString();
                vdetinfo = tdetinfo.getText().toString();


                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tidinfo.setText(null);
                tketinfo.setText(null);
                tdetinfo.setText(null);

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
                        Toast.makeText(infopklMainActivitysiswaguru.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(infopklMainActivitysiswaguru.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vidinfo.isEmpty()) {
                    params.put("ketinfo", vketinfo);
                    params.put("detinfo", vdetinfo);
                    return params;
                }else{
                    params.put("idinfo", vidinfo);
                    params.put("ketinfo", vketinfo);
                    params.put("detinfo", vdetinfo);
                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
    public void hapusData(String idp){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(infopklMainActivitysiswaguru.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(infopklMainActivitysiswaguru.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idinfo", idp);

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

                        Datainfopkl item = new Datainfopkl();

                        item.setIdinfo(obj.getString("idinfo"));
                        item.setKetinfo(obj.getString("ketinfo"));
                        item.setDetinfo(obj.getString("detinfo"));

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
