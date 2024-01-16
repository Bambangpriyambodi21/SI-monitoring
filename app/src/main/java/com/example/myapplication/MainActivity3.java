package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITSISWA.php";
    public static final String URLEDITindustri = "https://monitoring11a.000webhostapp.com/PROJEK/EDITindustri.php";

    String idsinfo, nissiswainfo, namasinfo, kelasinfo, telpsinfo, pembimbinginfo, industriinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        SessionManager sessionManager = new SessionManager(MainActivity3.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        TextView user = findViewById(R.id.userrs);
        Button button = findViewById(R.id.buttonprofilsiswa);
        Button button2 = findViewById(R.id.button2);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button71 = findViewById(R.id.button71);

        String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        user.setText(username);
        button71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
                detailforum(username);
            }
        });


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, detailpenilaian.class));
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, infopklMainActivitysiswaguru.class));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
                index(username);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
                detailpenempatan(username);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, Monitorings.class));
            }
        });
    }



    private void index(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idsx = jObj.getString("ids");
                            String nisx = jObj.getString("nis");
                            String namasx = jObj.getString("namas");
                            String kelasx1 = jObj.getString("kelas");
                            String telpsx2 = jObj.getString("telps");
                            String pembimbingx3 = jObj.getString("pembimbing");
                            String industrix4 = jObj.getString("industri");


                            Intent intent = new Intent(MainActivity3.this, detailindustri.class);
                            intent.putExtra("ids",idsx);
                            intent.putExtra("nis",nisx);
                            intent.putExtra("namas",namasx);
                            intent.putExtra("kelas",kelasx1);
                            intent.putExtra("telps",telpsx2);
                            intent.putExtra("pembimbing",pembimbingx3);
                            intent.putExtra("industri",industrix4);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namas", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }


    private void detailindustri(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDITindustri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idix = jObj.getString("idi");
                            String namaix = jObj.getString("namai");
                            String bidangix = jObj.getString("bidangi");
                            String alamatix1 = jObj.getString("alamati");
                            String telpix2 = jObj.getString("telpi");
                            String napemix3 = jObj.getString("napemi");


                            Intent intent = new Intent(MainActivity3.this, detailindustri.class);
                            intent.putExtra("idi",idix);
                            intent.putExtra("namai",namaix);
                            intent.putExtra("bidangi",bidangix);
                            intent.putExtra("alamati",alamatix1);
                            intent.putExtra("telpi",telpix2);
                            intent.putExtra("napemi",napemix3);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
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

    private void detailpenempatan(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idsx = jObj.getString("ids");
                            String nisx = jObj.getString("nis");
                            String namasx = jObj.getString("namas");
                            String kelasx1 = jObj.getString("kelas");
                            String telpsx2 = jObj.getString("telps");
                            String pembimbingx3 = jObj.getString("pembimbing");
                            String industrix4 = jObj.getString("industri");
                            String profilx = jObj.getString("foto");


                            Intent intent = new Intent(MainActivity3.this, detailsiswa.class);
                            intent.putExtra("ids",idsx);
                            intent.putExtra("nis",nisx);
                            intent.putExtra("namas",namasx);
                            intent.putExtra("kelas",kelasx1);
                            intent.putExtra("telps",telpsx2);
                            intent.putExtra("pembimbing",pembimbingx3);
                            intent.putExtra("industri",industrix4);
                            intent.putExtra("foto", profilx);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namas", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    private void detailforum(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String namasx = jObj.getString("namas");


                            Intent intent = new Intent(MainActivity3.this, MainActivityforumsiswa.class);
                            intent.putExtra("namas",namasx);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namas", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity3.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                SessionManager.logoutSession();
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}