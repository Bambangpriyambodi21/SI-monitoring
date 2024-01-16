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

public class penempatanMainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTP.php";
    public static final String URLDELETE = "https://monitoring11a.000webhostapp.com/PROJEK/DELETEP.php";
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITP.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTP.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Datapenempatan> itemList = new ArrayList<Datapenempatan>();
    Adapterpenempatan adapter;
    LayoutInflater inflater;
    EditText tidp,tkodep,tkodei,tnamap,tsiswa1,tsiswa2,tsiswa3,tsiswa4,tsiswa5,tsiswa6;
    String vidp,vkodep,vkodei,vnamap,vsiswa1,vsiswa2,vsiswa3,vsiswa4,vsiswa5,vsiswa6;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penempatanactivity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapterpenempatan(penempatanMainActivity.this, itemList);
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
                dialogForm("","","","","","","","","","","Tambah");
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getIdp();
                final CharSequence[] pilihanAksi = {"Ubah", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(penempatanMainActivity.this);
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
                final String idx = itemList.get(i).getIdp();
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

                            String idpx = jObj.getString("idp");
                            String kodepx = jObj.getString("kodep");
                            String kodeix = jObj.getString("kodei");
                            String namapx = jObj.getString("namapp");
                            String siswa1x = jObj.getString("siswa1");
                            String siswa2x = jObj.getString("siswa2");
                            String siswa3x = jObj.getString("siswa3");
                            String siswa4x = jObj.getString("siswa4");
                            String siswa5x = jObj.getString("siswa5");
                            String siswa6x = jObj.getString("siswa6");

                            Intent intent = new Intent(penempatanMainActivity.this, detailpenempatanMainActivity.class);
                            intent.putExtra("idp",idpx);
                            intent.putExtra("kodep",kodepx);
                            intent.putExtra("kodei",kodeix);
                            intent.putExtra("namapp",namapx);
                            intent.putExtra("siswa1",siswa1x);
                            intent.putExtra("siswa2",siswa2x);
                            intent.putExtra("siswa3",siswa3x);
                            intent.putExtra("siswa4",siswa4x);
                            intent.putExtra("siswa5",siswa5x);
                            intent.putExtra("siswa6",siswa6x);
                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(penempatanMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idp", idpp);

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

                            String idpx = jObj.getString("idp");
                            String kodepx = jObj.getString("kodep");
                            String kodeix = jObj.getString("kodei");
                            String namapx = jObj.getString("namapp");
                            String siswa1x = jObj.getString("siswa1");
                            String siswa2x = jObj.getString("siswa2");
                            String siswa3x = jObj.getString("siswa3");
                            String siswa4x = jObj.getString("siswa4");
                            String siswa5x = jObj.getString("siswa5");
                            String siswa6x = jObj.getString("siswa6");

                            dialogForm(idpx,kodepx, kodeix, namapx, siswa1x, siswa2x, siswa3x, siswa4x, siswa5x, siswa6x, "Ubah Profil");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(penempatanMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idp", idp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void dialogForm(String idp,String kodep,String kodei, String namap, String siswa1,String siswa2,String siswa3,String siswa4,String siswa5,String siswa6,String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(penempatanMainActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_penempatan, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Data Penempatan");

        tidp = (EditText) viewDialog.findViewById(R.id.inidp);
        tkodep = (EditText) viewDialog.findViewById(R.id.inkodep);
        tkodei = (EditText) viewDialog.findViewById(R.id.inkodei);
        tnamap = (EditText) viewDialog.findViewById(R.id.innamap);
        tsiswa1 = (EditText) viewDialog.findViewById(R.id.insiswa1);
        tsiswa2 = (EditText) viewDialog.findViewById(R.id.insiswa2);
        tsiswa3 = (EditText) viewDialog.findViewById(R.id.insiswa3);
        tsiswa4 = (EditText) viewDialog.findViewById(R.id.insiswa4);
        tsiswa5 = (EditText) viewDialog.findViewById(R.id.insiswa5);
        tsiswa6 = (EditText) viewDialog.findViewById(R.id.insiswa6);

        if (idp.isEmpty()){
            tidp.setText(null);
            tkodep.setText(null);
            tkodei.setText(null);
            tnamap.setText(null);
            tsiswa1.setText(null);
            tsiswa2.setText(null);
            tsiswa3.setText(null);
            tsiswa4.setText(null);
            tsiswa5.setText(null);
            tsiswa6.setText(null);
        }else{
            tidp.setText(idp);
            tkodep.setText(kodep);
            tkodei.setText(kodei);
            tnamap.setText(namap);
            tsiswa1.setText(siswa1);
            tsiswa2.setText(siswa2);
            tsiswa3.setText(siswa3);
            tsiswa4.setText(siswa4);
            tsiswa5.setText(siswa5);
            tsiswa6.setText(siswa6);
        }

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vidp = tidp.getText().toString();
                vkodep = tkodep.getText().toString();
                vkodei = tkodei.getText().toString();
                vnamap = tnamap.getText().toString();
                vsiswa1 = tsiswa1.getText().toString();
                vsiswa2 = tsiswa2.getText().toString();
                vsiswa3 = tsiswa3.getText().toString();
                vsiswa4 = tsiswa4.getText().toString();
                vsiswa5 = tsiswa5.getText().toString();
                vsiswa6 = tsiswa6.getText().toString();


                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tidp.setText(null);
                tkodep.setText(null);
                tkodei.setText(null);
                tnamap.setText(null);
                tsiswa1.setText(null);
                tsiswa2.setText(null);
                tsiswa3.setText(null);
                tsiswa4.setText(null);
                tsiswa5.setText(null);
                tsiswa6.setText(null);

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
                        Toast.makeText(penempatanMainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(penempatanMainActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vidp.isEmpty()) {
                    params.put("kodep", vkodep);
                    params.put("kodei", vkodei);
                    params.put("namapp", vnamap);
                    params.put("siswa1", vsiswa1);
                    params.put("siswa2", vsiswa2);
                    params.put("siswa3", vsiswa3);
                    params.put("siswa4", vsiswa4);
                    params.put("siswa5", vsiswa5);
                    params.put("siswa6", vsiswa6);
                    return params;
                }else{
                    params.put("idp", vidp);
                    params.put("kodep", vkodep);
                    params.put("kodei", vkodei);
                    params.put("namapp", vnamap);
                    params.put("siswa1", vsiswa1);
                    params.put("siswa2", vsiswa2);
                    params.put("siswa3", vsiswa3);
                    params.put("siswa4", vsiswa4);
                    params.put("siswa5", vsiswa5);
                    params.put("siswa6", vsiswa6);
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
                        Toast.makeText(penempatanMainActivity.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(penempatanMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("idp", idp);

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

                        Datapenempatan item = new Datapenempatan();

                        item.setIdp(obj.getString("idp"));
                        item.setKodep(obj.getString("kodep"));
                        item.setKodei(obj.getString("kodei"));
                        item.setNamap(obj.getString("namapp"));
                        item.setSiswa1(obj.getString("siswa1"));
                        item.setSiswa2(obj.getString("siswa2"));
                        item.setSiswa3(obj.getString("siswa3"));
                        item.setSiswa4(obj.getString("siswa4"));
                        item.setSiswa5(obj.getString("siswa5"));
                        item.setSiswa6(obj.getString("siswa6"));

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
