package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

public class editprofilguru extends AppCompatActivity {
    TextView idg;
    TextView namag,nipg, telpg, keling, keling1, keling2, keling3, keling4, keling5, keling6;
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadguru/";
    public static final String url = "https://monitoring11a.000webhostapp.com/PROJEK/INSERT.php";
    String idginfo, namaginfo,nipginfo, telpginfo, kelinginfo, kelinginfo1, kelinginfo2, kelinginfo3, kelinginfo4, kelinginfo5, kelinginfo6, sivguru;
    ImageView ivguruu;
    Button btnsimpan;
    private Bitmap bitmap;
    String imageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofilguru);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        idg = findViewById(R.id.idg);
        namag = findViewById(R.id.etnamag);
        nipg = findViewById(R.id.etnipg);
        telpg = findViewById(R.id.ettelpg);
        keling = findViewById(R.id.etkeling);
        keling1 = findViewById(R.id.etkeling1);
        keling2 = findViewById(R.id.etkeling2);
        keling3 = findViewById(R.id.etkeling3);
        keling4 = findViewById(R.id.etkeling4);
        keling5 = findViewById(R.id.etkeling5);
        keling6 = findViewById(R.id.etkeling6);
        ivguruu = findViewById(R.id.ivguru);
        btnsimpan = findViewById(R.id.button22);

        idginfo = getIntent().getStringExtra("idg");
        namaginfo = getIntent().getStringExtra("namag");
        nipginfo = getIntent().getStringExtra("nipg");
        telpginfo = getIntent().getStringExtra("telpg");
        kelinginfo = getIntent().getStringExtra("keling");
        kelinginfo1 = getIntent().getStringExtra("keling1");
        kelinginfo2 = getIntent().getStringExtra("keling2");
        kelinginfo3 = getIntent().getStringExtra("keling3");
        kelinginfo4 = getIntent().getStringExtra("keling4");
        kelinginfo5 = getIntent().getStringExtra("keling5");
        kelinginfo6 = getIntent().getStringExtra("keling6");
        sivguru = getIntent().getStringExtra("fotog");

        idg.setText(idginfo);
        namag.setText(namaginfo);
        nipg.setText(nipginfo);
        telpg.setText(telpginfo);
        keling.setText(kelinginfo);
        keling1.setText(kelinginfo1);
        keling2.setText(kelinginfo2);
        keling3.setText(kelinginfo3);
        keling4.setText(kelinginfo4);
        keling5.setText(kelinginfo5);
        keling6.setText(kelinginfo6);

        Glide.with(this)

                .load(tampil+sivguru)

                .override(300, 200)

                .into(ivguruu);


        if (ContextCompat.checkSelfPermission(editprofilguru.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(editprofilguru.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        ivguruu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            bitmap = (Bitmap) data.getExtras().get("data");
            ivguruu.setImageBitmap(bitmap);
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


        String idgg = idg.getText().toString();
        String namagg = namag.getText().toString();
        String nipgg = nipg.getText().toString();
        String telpgg = telpg.getText().toString();
        String kelingg = keling.getText().toString();
        String kelingg1 = keling1.getText().toString();
        String kelingg2 = keling2.getText().toString();
        String kelingg3 = keling3.getText().toString();
        String kelingg4 = keling4.getText().toString();
        String kelingg5 = keling5.getText().toString();
        String kelingg6 = keling6.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
//                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(editprofilguru.this, s , Toast.LENGTH_LONG).show();

                        Log.d("TAG", "onResponse: "+s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("TAG", "onErrorResponse: "+volleyError);

                        Toast.makeText(editprofilguru.this, "Mohon perbarui kembali foto profil", Toast.LENGTH_LONG).show();
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

                params.put("idg", idgg);
                params.put("namag", namagg);
                params.put("nipg", nipgg);
                params.put("telpg", telpgg);
                params.put("keling", kelingg);
                params.put("keling1", kelingg1);
                params.put("keling2", kelingg2);
                params.put("keling3", kelingg3);
                params.put("keling4", kelingg4);
                params.put("keling5", kelingg5);
                params.put("keling6", kelingg6);
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