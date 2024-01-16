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

public class siswaMainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTS.php";
    public static final String URLDELETE = "https://monitoring11a.000webhostapp.com/PROJEK/DELETES.php";
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITS.php";
    public static final String URLEDITsiswa = "https://monitoring11a.000webhostapp.com/PROJEK/EDITSISWA.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTS.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Datasiswa> itemList = new ArrayList<Datasiswa>();
    Adaptersiswa adapter;
    LayoutInflater inflater;
    EditText tids, tnis,tnamas, tkelas,ttelps,tpembimbing,tindustri;
    String vids, vnis,vnamas, vkelas,vtelps,vpembimbing,vindustri;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siswaactivity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adaptersiswa(siswaMainActivity.this, itemList);
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
                dialogForm("","","","","","","","Tambah");
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getIds();
                final CharSequence[] pilihanAksi = {"Ubah", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(siswaMainActivity.this);
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
                final String idx = itemList.get(i).getIds();
                detailsiswa(idx);
            }
        });


    }

    private void detailsiswa(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idpx = jObj.getString("ids");
                            String kodepx = jObj.getString("nis");
                            String kodeix = jObj.getString("namas");
                            String namapx = jObj.getString("kelas");
                            String siswa1x = jObj.getString("telps");
                            String siswa2x = jObj.getString("pembimbing");
                            String siswa3x = jObj.getString("industri");
                            String siswa4x = jObj.getString("foto");

                            Intent intent = new Intent(siswaMainActivity.this, detailsiswa.class);
                            intent.putExtra("ids",idpx);
                            intent.putExtra("nis",kodepx);
                            intent.putExtra("namas",kodeix);
                            intent.putExtra("kelas",namapx);
                            intent.putExtra("telps",siswa1x);
                            intent.putExtra("pembimbing",siswa2x);
                            intent.putExtra("industri",siswa3x);
                            intent.putExtra("foto",siswa4x);
                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(siswaMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("ids", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void ubahData(String ids){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idsx = jObj.getString("ids");
                            String nisx = jObj.getString("nis");
                            String namasx = jObj.getString("namas");
                            String kelasx = jObj.getString("kelas");
                            String telpsx = jObj.getString("telps");
                            String pembimbingx = jObj.getString("pembimbing");
                            String industrix = jObj.getString("industri");

                            dialogForm(idsx, nisx, namasx, kelasx, telpsx, pembimbingx, industrix, "Ubah Profil");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(siswaMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("ids", ids);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void dialogForm(String ids,String nis, String namas, String kelas, String telps, String pembimbing, String industri, String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(siswaMainActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_siswa, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Data Siswa");

        tids = (EditText) viewDialog.findViewById(R.id.inids);
        tnis = (EditText) viewDialog.findViewById(R.id.inNis);
        tnamas = (EditText) viewDialog.findViewById(R.id.inNamas);
        tkelas = (EditText) viewDialog.findViewById(R.id.inKelas);
        ttelps = (EditText) viewDialog.findViewById(R.id.inTelps);
        tpembimbing = (EditText) viewDialog.findViewById(R.id.inPembimbing);
        tindustri = (EditText) viewDialog.findViewById(R.id.inIndustri);

        if (ids.isEmpty()){
            tids.setText(null);
            tnis.setText(null);
            tnamas.setText(null);
            tkelas.setText(null);
            ttelps.setText(null);
            tpembimbing.setText(null);
            tindustri.setText(null);
        }else{
            tids.setText(ids);
            tnis.setText(nis);
            tnamas.setText(namas);
            tkelas.setText(kelas);
            ttelps.setText(telps);
            tpembimbing.setText(pembimbing);
            tindustri.setText(industri);
        }

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vids = tids.getText().toString();
                vnis = tnis.getText().toString();
                vnamas = tnamas.getText().toString();
                vkelas = tkelas.getText().toString();
                vtelps = ttelps.getText().toString();
                vpembimbing = tpembimbing.getText().toString();
                vindustri = tindustri.getText().toString();

                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tids.setText(null);
                tnis.setText(null);
                tnamas.setText(null);
                tkelas.setText(null);
                ttelps.setText(null);
                tpembimbing.setText(null);
                tindustri.setText(null);

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
                        Toast.makeText(siswaMainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(siswaMainActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vids.isEmpty()) {
                    params.put("nis", vnis);
                    params.put("namas", vnamas);
                    params.put("kelas", vkelas);
                    params.put("telps", vtelps);
                    params.put("pembimbing", vpembimbing);
                    params.put("industri", vindustri);
                    params.put("foto", "profile.png");
                    return params;
                }else{
                    params.put("ids", vids);
                    params.put("nis", vnis);
                    params.put("namas", vnamas);
                    params.put("kelas", vkelas);
                    params.put("telps", vtelps);
                    params.put("pembimbing", vpembimbing);
                    params.put("industri", vindustri);
                    params.put("foto", "profile.png");
                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
    public void hapusData(String ids){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(siswaMainActivity.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(siswaMainActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("ids", ids);

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

                        Datasiswa item = new Datasiswa();

                        item.setIds(obj.getString("ids"));
                        item.setNis(obj.getString("nis"));
                        item.setNamas(obj.getString("namas"));
                        item.setKelas(obj.getString("kelas"));
                        item.setTelps(obj.getString("telps"));
                        item.setPembimbing(obj.getString("pembimbing"));
                        item.setIndustri(obj.getString("industri"));

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
