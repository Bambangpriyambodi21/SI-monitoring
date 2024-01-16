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

public class nilaiMainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTNILAI.php";
    public static final String URLTAMPILINDI = "https://monitoring11a.000webhostapp.com/PROJEK/TAMPILINDI.php";
    public static final String URLDELETE = "https://monitoring11a.000webhostapp.com/PROJEK/DELETENILAI.php";
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITNILAI.php";

    ListView list;
    SwipeRefreshLayout swipe;
    List<Datanilai> itemList = new ArrayList<Datanilai>();
    Adapternilai adapter;
    LayoutInflater inflater;
    EditText tidn,tnis,tnama,tindikator1,tindikator2,tindikator3,tindikator4,tindikator5,tindikator6,tindikator7,tindikator8,tindikator9,tindikator10,tindikator11,tindikator12,tindikator13,tindikator14;
    String vidn,vnis,vnama,vindikator1,vindikator2,vindikator3,vindikator4,vindikator5,vindikator6,vindikator7,vindikator8,vindikator9,vindikator10,vindikator11,vindikator12,vindikator13,vindikator14;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nilaiactivity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapternilai(nilaiMainActivity.this, itemList);
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
                startActivity(new Intent(nilaiMainActivity.this, nilaisiswaMainActivity.class));
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getIdn();
                final CharSequence[] pilihanAksi = {"Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(nilaiMainActivity.this);
                dialog.setItems(pilihanAksi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                //jika dipilih hapus
                                hapusData(idx);
                                break;

                            case 1:
                                //jika memilih edit/ubah
//                                ubahData(idx);
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
                final String idx = itemList.get(i).getIdn();
                detailpenempatan(idx);
                detailindikator(idx);
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

                            String idnx = jObj.getString("idn");
                            String nisx = jObj.getString("nis");
                            String namax = jObj.getString("nama");
                            String indikatorx1 = jObj.getString("indikator1");
                            String indikatorx2 = jObj.getString("indikator2");
                            String indikatorx3 = jObj.getString("indikator3");
                            String indikatorx4 = jObj.getString("indikator4");
                            String indikatorx5 = jObj.getString("indikator5");
                            String indikatorx6 = jObj.getString("indikator6");
                            String indikatorx7 = jObj.getString("indikator7");
                            String indikatorx8 = jObj.getString("indikator8");
                            String indikatorx9 = jObj.getString("indikator9");
                            String indikatorx10 = jObj.getString("indikator10");
                            String indikatorx11 = jObj.getString("indikator11");
                            String indikatorx12 = jObj.getString("indikator12");
                            String indikatorx13 = jObj.getString("indikator13");


                            Intent intent = new Intent(nilaiMainActivity.this, detailnilaiMainActivity.class);
                            intent.putExtra("idn",idnx);
                            intent.putExtra("nis",nisx);
                            intent.putExtra("nama",namax);
                            intent.putExtra("indikator1",indikatorx1);
                            intent.putExtra("indikator2",indikatorx2);
                            intent.putExtra("indikator3",indikatorx3);
                            intent.putExtra("indikator4",indikatorx4);
                            intent.putExtra("indikator5",indikatorx5);
                            intent.putExtra("indikator6",indikatorx6);
                            intent.putExtra("indikator7",indikatorx7);
                            intent.putExtra("indikator8",indikatorx8);
                            intent.putExtra("indikator9",indikatorx9);
                            intent.putExtra("indikator10",indikatorx10);
                            intent.putExtra("indikator11",indikatorx11);
                            intent.putExtra("indikator12",indikatorx12);
                            intent.putExtra("indikator13",indikatorx13);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(nilaiMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idn", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    private void detailindikator(String idxx){
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLTAMPILINDI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idnxA = jObj.getString("id");
                            String indikatorx1A = jObj.getString("ketindi");



                            Intent intent = new Intent(nilaiMainActivity.this, detailnilaiMainActivity.class);
                            intent.putExtra("id",idnxA);
                            intent.putExtra("ketindi",indikatorx1A);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(nilaiMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", idxx);

                return params;
            }

        };
        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
        queue1.add(stringRequest1);
    }


    public void hapusData(String idp){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(nilaiMainActivity.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(nilaiMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idn", idp);

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

                        Datanilai item = new Datanilai();

                        item.setIdn(obj.getString("idn"));
                        item.setNis(obj.getString("nis"));

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
