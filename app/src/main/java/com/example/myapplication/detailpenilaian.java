package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class detailpenilaian extends AppCompatActivity {
    TextView idntext, nistext, namatext, indikatortext1, indikatortext2, indikatortext3, indikatortext4, indikatortext5, indikatortext6, indikatortext7, indikatortext8, indikatortext9, indikatortext10, indikatortext11, indikatortext12, indikatortext13, indikatortext14;
    String iidntext, inistext, inamatext, iindikatortext1, iindikatortext2, iindikatortext3, iindikatortext4, iindikatortext5, iindikatortext6, iindikatortext7, iindikatortext8, iindikatortext9, iindikatortext10, iindikatortext11, iindikatortext12, iindikatortext13, iindikatortext14;
    Button home;
    public static final String URLEDITnilai = "https://monitoring11a.000webhostapp.com/PROJEK/EDITpenilaian.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpenilaian);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        SessionManager sessionManager = new SessionManager(detailpenilaian.this);

        String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        detailpenilaian(username);

        idntext = findViewById(R.id.idnilai);
        nistext = findViewById(R.id.nisnilai);
        namatext = findViewById(R.id.nis2nilai);
        indikatortext1 = findViewById(R.id.nis3nilai);
        indikatortext2 = findViewById(R.id.nis4nilai);
        indikatortext3 = findViewById(R.id.nis5nilai);
        indikatortext4 = findViewById(R.id.nis6nilai);
        indikatortext5 = findViewById(R.id.nis7nilai);
        indikatortext6 = findViewById(R.id.nis8nilai);
        indikatortext7 = findViewById(R.id.nis9nilai);
        indikatortext8 = findViewById(R.id.nis10nilai);
        indikatortext9 = findViewById(R.id.nis11nilai);
        indikatortext10 = findViewById(R.id.nis12nilai);
        indikatortext11 = findViewById(R.id.nis13nilai);
        indikatortext12 = findViewById(R.id.nis14nilai);
        indikatortext13 = findViewById(R.id.nis15nilai);
        home = findViewById(R.id.button24);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        iidntext = getIntent().getStringExtra("idn");
        inistext = getIntent().getStringExtra("nis");
        inamatext = getIntent().getStringExtra("nama");
        iindikatortext1 = getIntent().getStringExtra("indikator1");
        iindikatortext2 = getIntent().getStringExtra("indikator2");
        iindikatortext3 = getIntent().getStringExtra("indikator3");
        iindikatortext4 = getIntent().getStringExtra("indikator4");
        iindikatortext5 = getIntent().getStringExtra("indikator5");
        iindikatortext6 = getIntent().getStringExtra("indikator6");
        iindikatortext7 = getIntent().getStringExtra("indikator7");
        iindikatortext8 = getIntent().getStringExtra("indikator8");
        iindikatortext9 = getIntent().getStringExtra("indikator9");
        iindikatortext10 = getIntent().getStringExtra("indikator10");
        iindikatortext11 = getIntent().getStringExtra("indikator11");
        iindikatortext12 = getIntent().getStringExtra("indikator12");
        iindikatortext13 = getIntent().getStringExtra("indikator13");



    }

    private void detailpenilaian(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDITnilai,
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


                            idntext.setText(idnx);
                            nistext.setText(nisx);
                            namatext.setText(namax);
                            indikatortext1.setText(indikatorx1);
                            indikatortext2.setText(indikatorx2);
                            indikatortext3.setText(indikatorx3);
                            indikatortext4.setText(indikatorx4);
                            indikatortext5.setText(indikatorx5);
                            indikatortext6.setText(indikatorx6);
                            indikatortext7.setText(indikatorx7);
                            indikatortext8.setText(indikatorx8);
                            indikatortext9.setText(indikatorx9);
                            indikatortext10.setText(indikatorx10);
                            indikatortext11.setText(indikatorx11);
                            indikatortext12.setText(indikatorx12);
                            indikatortext13.setText(indikatorx13);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detailpenilaian.this, "Gagal terhubung ke server", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("nama", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}