package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityforum extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTF.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTF.php";
    public static final String notifikasig = "https://monitoring11a.000webhostapp.com/PROJEK/push_notification.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Dataforum> itemList = new ArrayList<Dataforum>();
    Adapterforum adapter;
    LayoutInflater inflater;
    EditText tidf,tnamaf, tjamf, tchat, tglf;
    String vidf,vnamaf, vjamf, vchat, vtglf;
    FloatingActionButton fab;
    SessionManagerguru sessionManagerguru;
    SessionManager sessionManager;

    double currentLong = 0.0;
    double currentLat = 0.0;
    String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainforum);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        FirebaseMessaging.getInstance().subscribeToTopic("notif")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "succes";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivityforum.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        adapter = new Adapterforum(MainActivityforum.this, itemList);
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
                dialogForm("","","","","","Tambah");
//                startActivity(new Intent(nilaiMainActivity.this, nilaisiswaMainActivity.class));
            }
        });
    }

    private void pushnotifikasig(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, notifikasig,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // handle response from server
                        // Toast.makeText(monitoringMainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error
                        Toast.makeText(MainActivityforum.this, ""+error, Toast.LENGTH_SHORT).show();                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                params.put("title", "Test Notification");//
//                params.put("message", "This is a test notification from Android App");
                return params;            }
        };
        // Add request to the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void dialogForm(String id,String nama,String chat,String jam, String tgl, String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(MainActivityforum.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_forum, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);

        tidf = (EditText) viewDialog.findViewById(R.id.inid);
        tchat = (EditText) viewDialog.findViewById(R.id.chat);
        tnamaf = (EditText) viewDialog.findViewById(R.id.namaf);
        tjamf = (EditText) viewDialog.findViewById(R.id.jamfo);
        tglf = (EditText) viewDialog.findViewById(R.id.tglff);



        if (id.isEmpty()){
            tidf.setText(null);
            tnamaf.setText(null);
            tchat.setText(null);
            tjamf.setText(null);
            tglf.setText(null);
        }else{
            tidf.setText(id);
            tnamaf.setText(nama);
            tchat.setText(chat);
            tjamf.setText(jam);
            tglf.setText(tgl);
        }
        String namaginfo = getIntent().getStringExtra("namag");

            tnamaf.setText(namaginfo);

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vidf = tidf.getText().toString();
                vnamaf = namaginfo;
                vchat = tchat.getText().toString();
                vjamf = "";
                vtglf = "";


                pushnotifikasig();
                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tidf.setText(null);
                tnamaf.setText(null);
                tchat.setText(null);
                tjamf.setText(null);
                tglf.setText(null);

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
                        Toast.makeText(MainActivityforum.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityforum.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vidf.isEmpty()) {
                    params.put("namaf", vnamaf);
                    params.put("chatf", vchat);
                    params.put("jamf", vjamf);
                    params.put("tglf", vtglf);
                    return params;
                }else{
                    params.put("idf", vidf);
                    params.put("namaf", vnamaf);
                    params.put("jamf", vjamf);
                    params.put("chatf", vchat);
                    params.put("tglf", vtglf);
                    return params;
                }
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

                        Dataforum item = new Dataforum();

                        item.setNamaf(obj.getString("namaf"));
                        item.setJamf(obj.getString("jamf"));
                        item.setChatf(obj.getString("chatf"));
                        item.setTglf(obj.getString("tglf"));

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