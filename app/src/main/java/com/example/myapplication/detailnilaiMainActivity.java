package com.example.myapplication;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class detailnilaiMainActivity extends AppCompatActivity {
    public static final String URLSELECTINDI = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTINDI.php";
    TextView idtextA, indikatortext1A,idntext, nistext, namatext, indikatortext1, indikatortext2, indikatortext3, indikatortext4, indikatortext5, indikatortext6, indikatortext7, indikatortext8, indikatortext9, indikatortext10, indikatortext11, indikatortext12, indikatortext13, indikatortext14;
    String iidtextA, iindikatortext1A,iidntext, inistext, inamatext, iindikatortext1, iindikatortext2, iindikatortext3, iindikatortext4, iindikatortext5, iindikatortext6, iindikatortext7, iindikatortext8, iindikatortext9, iindikatortext10, iindikatortext11, iindikatortext12, iindikatortext13, iindikatortext14;

    ListView list;
    SwipeRefreshLayout swipe;
    List<Datanilai> itemList = new ArrayList<Datanilai>();
    Adapternilai adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
//        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
//        list=(ListView) findViewById(R.id.activity_in);
//
//        adapter=new indikatorAdapter(detailinfopklMainActivity.this, itemList);
//        list.setAdapter(adapter);
//
//        swipe.setOnRefreshListener(this);
//
//        swipe.post(new Runnable() {
//                       @Override
//                       public void run() {
//                           swipe.setRefreshing(true);
//                           itemList.clear();
//                           adapter.notifyDataSetChanged();
//                           callVolley();
//                       }
//                   }
//        );

//        indikatortext1A = findViewById(R.id.nama2);
        idntext = findViewById(R.id.idnilai);
        nistext = findViewById(R.id.nisnilai);
        namatext = findViewById(R.id.nis2nilai);
        indikatortext1 = findViewById(R.id.indikator1);
        indikatortext2 = findViewById(R.id.indikator2);
        indikatortext3 = findViewById(R.id.indikator3);
        indikatortext4 = findViewById(R.id.indikator4);
        indikatortext5 = findViewById(R.id.indikator5);
        indikatortext6 = findViewById(R.id.indikator6);
        indikatortext7 = findViewById(R.id.indikator7);
        indikatortext8 = findViewById(R.id.indikator8);
        indikatortext9 = findViewById(R.id.indikator9);
        indikatortext10 = findViewById(R.id.indikator10);
        indikatortext11 = findViewById(R.id.indikator11);
        indikatortext12 = findViewById(R.id.indikator12);
        indikatortext13 = findViewById(R.id.indikator13);

        iindikatortext1A = getIntent().getStringExtra("ketindi");
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
        iindikatortext14 = getIntent().getStringExtra("indikator14");

//        indikatortext1A.setText(iindikatortext1A);
        idntext.setText(iidntext);
        nistext.setText(inistext);
        namatext.setText(inamatext);
        indikatortext1.setText(iindikatortext1);
        indikatortext2.setText(iindikatortext2);
        indikatortext3.setText(iindikatortext3);
        indikatortext4.setText(iindikatortext4);
        indikatortext5.setText(iindikatortext5);
        indikatortext6.setText(iindikatortext6);
        indikatortext7.setText(iindikatortext7);
        indikatortext8.setText(iindikatortext8);
        indikatortext9.setText(iindikatortext9);
        indikatortext10.setText(iindikatortext10);
        indikatortext11.setText(iindikatortext11);
        indikatortext12.setText(iindikatortext12);
        indikatortext13.setText(iindikatortext13);



    }

//    public void onRefresh() {
//        itemList.clear();
//        adapter.notifyDataSetChanged();
//        callVolley();
//
//    }
//    private void callVolley() {
//        itemList.clear();
//        adapter.notifyDataSetChanged();
//        swipe.setRefreshing(true);
//
//        // membuat request JSON
//        JsonArrayRequest jArr = new JsonArrayRequest(URLSELECTINDI, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                // Parsing json
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject obj = response.getJSONObject(i);
//
//                        Dataindikator item = new Dataindikator();
//
//
//                        item.setIndikator1a(obj.getString("ketindi"));
//                        item.setIndikator2a(obj.getString("ketindi"));
//                        item.setIndikator3a(obj.getString("ketindi"));
//                        item.setIndikator4a(obj.getString("ketindi"));
//
//                        // menambah item ke array
//                        itemList.add(item);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                // notifikasi adanya perubahan data pada adapter
//                adapter.notifyDataSetChanged();
//
//                swipe.setRefreshing(false);
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                swipe.setRefreshing(false);
//            }
//        });
//
//        // menambah request ke request queue
//        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        mRequestQueue.add(jArr);

}


