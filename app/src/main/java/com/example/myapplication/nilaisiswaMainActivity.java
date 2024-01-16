package com.example.myapplication;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class nilaisiswaMainActivity extends AppCompatActivity {
    public static final String url = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTNILAI.php";
    EditText tidn,tnis,tnama,tindikator1,tindikator2,tindikator3,tindikator4,tindikator5,tindikator6,tindikator7,tindikator8,tindikator9,tindikator10,tindikator11,tindikator12,tindikator13,tindikator14;
    TextView tket;
    Button binput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilaisiswa_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        tidn = (EditText) findViewById(R.id.idn);
        tnis = (EditText) findViewById(R.id.nis);
        tnama = (EditText) findViewById(R.id.nis2);
        tindikator1 = (EditText) findViewById(R.id.nis3);
        tindikator2 = (EditText) findViewById(R.id.nis4);
        tindikator3 = (EditText) findViewById(R.id.nis5);
        tindikator4 = (EditText) findViewById(R.id.nis6);
        tindikator5 = (EditText) findViewById(R.id.nis7);
        tindikator6 = (EditText) findViewById(R.id.nis8);
        tindikator7 = (EditText) findViewById(R.id.nis9);
        tindikator8 = (EditText) findViewById(R.id.nis10);
        tindikator9 = (EditText) findViewById(R.id.nis11);
        tindikator10 = (EditText) findViewById(R.id.nis12);
        tindikator11 = (EditText) findViewById(R.id.nis13);
        tindikator12 = (EditText) findViewById(R.id.nis14);
        tindikator13 = (EditText) findViewById(R.id.nis15);
        tindikator14 = (EditText) findViewById(R.id.nis16);
        binput=(Button) findViewById(R.id.btnnilai);
        tket = (TextView) findViewById(R.id.tket);



        binput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();
            }
        });
    }

    void inputdata() {
        tidn = (EditText) findViewById(R.id.idn);
        tnis = (EditText) findViewById(R.id.nis);
        tnama = (EditText) findViewById(R.id.nis2);
        tindikator1 = (EditText) findViewById(R.id.nis3);
        tindikator2 = (EditText) findViewById(R.id.nis4);
        tindikator3 = (EditText) findViewById(R.id.nis5);
        tindikator4 = (EditText) findViewById(R.id.nis6);
        tindikator5 = (EditText) findViewById(R.id.nis7);
        tindikator6 = (EditText) findViewById(R.id.nis8);
        tindikator7 = (EditText) findViewById(R.id.nis9);
        tindikator8 = (EditText) findViewById(R.id.nis10);
        tindikator9 = (EditText) findViewById(R.id.nis11);
        tindikator10 = (EditText) findViewById(R.id.nis12);
        tindikator11 = (EditText) findViewById(R.id.nis13);
        tindikator12 = (EditText) findViewById(R.id.nis14);
        tindikator13 = (EditText) findViewById(R.id.nis15);
        tindikator14 = (EditText) findViewById(R.id.nis16);
        tket = (TextView) findViewById(R.id.tket);

        String idn = tidn.getText().toString();
        String nis = tnis.getText().toString();
        String nama = tnama.getText().toString();
        String indikator1 = tindikator1.getText().toString();
        String indikator2 = tindikator2.getText().toString();
        String indikator3 = tindikator3.getText().toString();
        String indikator4 = tindikator4.getText().toString();
        String indikator5 = tindikator5.getText().toString();
        String indikator6 = tindikator6.getText().toString();
        String indikator7 = tindikator7.getText().toString();
        String indikator8 = tindikator8.getText().toString();
        String indikator9 = tindikator9.getText().toString();
        String indikator10 = tindikator10.getText().toString();
        String indikator11 = tindikator11.getText().toString();
        String indikator12 = tindikator12.getText().toString();
        String indikator13 = tindikator13.getText().toString();
//        String indikator14 = tindikator14.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tket.setText(response);
                        Toast.makeText(nilaisiswaMainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tket.setText("error");

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("idn", idn);
                params.put("nis", nis);
                params.put("nama", nama);
                params.put("indikator1", indikator1);
                params.put("indikator2", indikator2);
                params.put("indikator3", indikator3);
                params.put("indikator4", indikator4);
                params.put("indikator5", indikator5);
                params.put("indikator6", indikator6);
                params.put("indikator7", indikator7);
                params.put("indikator8", indikator8);
                params.put("indikator9", indikator9);
                params.put("indikator10", indikator10);
                params.put("indikator11", indikator11);
                params.put("indikator12", indikator12);
                params.put("indikator13", indikator13);
//                params.put("indikator14", indikator14);

                return params;
            }
        }  ;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
