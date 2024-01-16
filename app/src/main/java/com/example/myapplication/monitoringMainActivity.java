package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class monitoringMainActivity extends AppCompatActivity {
    public static final String url = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTM.php";
    EditText idm, tglm, nis, jam, kegiatan, user;
    TextView tket, lokasi;
    Button binput, btnlokasi;
    //    Bitmap bitmap;
    private ImageView imageView;
    private Bitmap bitmap;
    private Byte aByte;
    String imageString;
    SessionManager sessionManager;

    double currentLong = 0.0;
    double currentLat = 0.0;
    String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoringa_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        idm = (EditText) findViewById(R.id.idn);
        tglm = (EditText) findViewById(R.id.nis);
        nis = (EditText) findViewById(R.id.nis2);
        jam = (EditText) findViewById(R.id.nis3);
        kegiatan = (EditText) findViewById(R.id.nis4);
        lokasi = (TextView) findViewById(R.id.nis5);
        user = (EditText) findViewById(R.id.nis6);
        binput = (Button) findViewById(R.id.btnnilai);
        btnlokasi = (Button) findViewById(R.id.button2);

        SessionManagerguru sessionManager = new SessionManagerguru(monitoringMainActivity.this);
        String username = sessionManager.getUserDetail().get(SessionManagerguru.USERNAME);
        user.setText(username);
        binput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();
            }
        });

        imageView = findViewById(R.id.imageview);

        if (ContextCompat.checkSelfPermission(monitoringMainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(monitoringMainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        btnlokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });

    }

    public boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )) {
                getLongLat();
                return true;
            } requestPermission();
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint("MissingPermission")
    private void getLongLat() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest locationRequest = LocationRequest.create().setInterval(0).setPriority(LocationRequest.PRIORITY_LOW_POWER);


        LocationCallback locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    //Update Long Lat
                    currentLong = location.getLongitude();
                    currentLat = location.getLatitude();

                    getCurrentLocation();

                    fusedLocationClient.removeLocationUpdates(this);
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
        );
    }

    private void getCurrentLocation(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        Address addr;

        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(currentLat, currentLong, 1);
            if (!addresses.isEmpty()) {
                addr = addresses.get(0);
                address = addr.getAddressLine(0);
                lokasi.setText(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }


    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    getLongLat();
                } else {
                    Toast.makeText(this, "Permission ditolak", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageString = getStringImage(bitmap);
            Log.d("image", imageString);
            //passing the image to volley
//            SendImage(image);


        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        String encode = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
        return encodedImage;

    }


    void inputdata() {
//        idm = (EditText) findViewById(R.id.idn);
//        tglm = (EditText) findViewById(R.id.nis);
//        nis = (EditText) findViewById(R.id.nis2);
//        jam = (EditText) findViewById(R.id.nis3);
//        kegiatan = (EditText) findViewById(R.id.nis4);
//        lokasi = (EditText) findViewById(R.id.nis5);
//        user = (EditText) findViewById(R.id.nis6);
//        imageView = (ImageView) findViewById(R.id.imageview);


        String idmm = idm.getText().toString();
//        String tglmm = tglm.getText().toString();

        String nism = nis.getText().toString();
        String jamm = jam.getText().toString();
        String kegiatanm = kegiatan.getText().toString();
        String lokasim = lokasi.getText().toString();
        String userm = user.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
//                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(monitoringMainActivity.this, s , Toast.LENGTH_LONG).show();
//                        send data when data success
//                        Intent intent = new Intent(monitoringMainActivity.this, detailmonitoringMainActivity.class);
//                        intent.putExtra("gambar", getStringImage(bitmap));
//                        intent.putExtra("tgl",tglmm);
//                        intent.putExtra("nis",nism);
//                        intent.putExtra("jam",jamm);
//                        intent.putExtra("kegiatan",kegiatanm);
//                        intent.putExtra("lokasi",lokasim);
//                        intent.putExtra("user", userm);
//                        startActivity(intent);

                        Log.d("TAG", "onResponse: "+s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("TAG", "onErrorResponse: "+volleyError);

                        Toast.makeText(monitoringMainActivity.this, "Masukkan hasil foto", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
//                String name = editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters

                params.put("idm", idmm);
//                params.put("tglm", tglmm);
                params.put("nis", nism);
//                params.put("jam", jamm);
                params.put("kegiatan", kegiatanm);
                params.put("lokasi", address);
                params.put("user", userm);
                params.put("name", getStringImage(bitmap));

//
//                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }



    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void SendImage(final String image) {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("uploade", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                        } catch (JSONException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//        Toast.makeText(Edit_Profile.this, "No internet connection", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();

                params.put("image", image);
                return params;
            }
        };

        {
            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

}