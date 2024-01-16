package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
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

public class MainActivityforumadmin extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTF.php";
    public static final String URLINSERT = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTF.php";
    public static final String notifikasia = "https://monitoring11a.000webhostapp.com/PROJEK/push_notification.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Dataforumadmin> itemList = new ArrayList<Dataforumadmin>();
    Adapterforumadmin adapter;
    LayoutInflater inflater;
    EditText tidf,tnamaf, tjamf, tchat;
    String vidf,vnamaf, vjamf, vchat, tnama;
    FloatingActionButton fab;
    SessionManagerguru sessionManagerguru;
    SessionManager sessionManager;

    double currentLong = 0.0;
    double currentLat = 0.0;
    String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainforumadmin);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapterforumadmin(MainActivityforumadmin.this, itemList);
        list.setAdapter(adapter);

        FirebaseMessaging.getInstance().subscribeToTopic("notif")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "succes";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivityforumadmin.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

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
                dialogForm("","","","","Tambah");
//                startActivity(new Intent(nilaiMainActivity.this, nilaisiswaMainActivity.class));
            }
        });
    }

    private void pushnotifikasia(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, notifikasia,
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
                        Toast.makeText(MainActivityforumadmin.this, ""+error, Toast.LENGTH_SHORT).show();                    }
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

    private void sendNotificationn(String messageBody) {
        Intent intent = new Intent(this, MainActivityforumadmin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_IMMUTABLE);

        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("SI Monitoring PKL")
                        .setSmallIcon(R.drawable.logo3)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    public void dialogForm(String id,String nama,String chat,String jam,String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(MainActivityforumadmin.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_forum, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);

        tidf = (EditText) viewDialog.findViewById(R.id.inid);
        tchat = (EditText) viewDialog.findViewById(R.id.chat);
        tnamaf = (EditText) viewDialog.findViewById(R.id.namaf);
        tjamf = (EditText) viewDialog.findViewById(R.id.jamfo);



        if (id.isEmpty()){
            tidf.setText(null);
            tnamaf.setText(null);
            tchat.setText(null);
            tjamf.setText(null);
        }else{
            tidf.setText(id);
            tnamaf.setText(nama);
            tchat.setText(chat);
            tjamf.setText(jam);
        }

        String alfaa = getIntent().getStringExtra("namaa");
        tnamaf.setText(alfaa);

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vidf = tidf.getText().toString();
                vnamaf = alfaa;
                vchat = tchat.getText().toString();
                vjamf = "";


//                sendNotificationn(vchat);
                pushnotifikasia();
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
                        Toast.makeText(MainActivityforumadmin.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityforumadmin.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
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
                    return params;
                }else{
                    params.put("idf", vidf);
                    params.put("namaf", vnamaf);
                    params.put("jamf", vjamf);
                    params.put("chatf", vchat);
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

                        Dataforumadmin item = new Dataforumadmin();

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