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

public class editprofiladmin extends AppCompatActivity {
    TextView ida;
    EditText usera, namaa, telpa;
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadadmin/";
    public static final String url = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTA.php";
    String idainfo, userainfo,namaainfo, telpainfo, sivadmin;
    ImageView ivadmin;
    Button btnsimpan;
    private Bitmap bitmap;
    String imageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofiladmin);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        ida = findViewById(R.id.ida);
        usera = findViewById(R.id.etusera);
        namaa = findViewById(R.id.etnamaa);
        telpa = findViewById(R.id.ettelpa);
        ivadmin = findViewById(R.id.ivadmin);
        btnsimpan = findViewById(R.id.button21);

        idainfo = getIntent().getStringExtra("ida");
        userainfo = getIntent().getStringExtra("usera");
        namaainfo = getIntent().getStringExtra("namaa");
        telpainfo = getIntent().getStringExtra("telpa");
        sivadmin = getIntent().getStringExtra("fotoa");

        ida.setText(idainfo);
        usera.setText(userainfo);
        namaa.setText(namaainfo);
        telpa.setText(telpainfo);

        Glide.with(this)

                .load(tampil+sivadmin)

                .override(300, 200)

                .into(ivadmin);


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();
            }
        });



        if (ContextCompat.checkSelfPermission(editprofiladmin.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(editprofiladmin.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        ivadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            bitmap = (Bitmap) data.getExtras().get("data");
            ivadmin.setImageBitmap(bitmap);
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


        String idaa = ida.getText().toString();
        String useraa = usera.getText().toString();
        String namaaa = namaa.getText().toString();
        String telpaa = telpa.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
//                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(editprofiladmin.this, s , Toast.LENGTH_LONG).show();
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

                        Toast.makeText(editprofiladmin.this, "Mohon perbarui kembali foto profil", Toast.LENGTH_LONG).show();
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

                params.put("ida", idaa);
                params.put("usera", useraa);
                params.put("namaa", namaaa);
                params.put("telpa", telpaa);
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