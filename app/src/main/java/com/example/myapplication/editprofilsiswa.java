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

public class editprofilsiswa extends AppCompatActivity {
    TextView ids;
    TextView nissiswa, namas, kelas, telps, pembimbing, industri;
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadsiswa/";
    public static final String url = "https://monitoring11a.000webhostapp.com/PROJEK/INSERTS.php";
    String idsinfo, nissiswainfo, namasinfo, kelasinfo, telpsinfo, pembimbinginfo, industriinfo, sivsiswa;
    private ImageView ivsiswa;
    private Byte aByte;
    Button btnsimpan;
    private Bitmap bitmap;
    String imageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofilsiswa);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
            ids = findViewById(R.id.ids);
            nissiswa = findViewById(R.id.etnis);
            namas = findViewById(R.id.etnamas);
            kelas = findViewById(R.id.etkelas);
            telps = findViewById(R.id.ettelps);
            pembimbing = findViewById(R.id.etpembimbing);
            industri = findViewById(R.id.etindustri);
            ivsiswa = findViewById(R.id.ivsiswa);
            btnsimpan = findViewById(R.id.button20);

            idsinfo = getIntent().getStringExtra("ids");
            nissiswainfo = getIntent().getStringExtra("nis");
            namasinfo = getIntent().getStringExtra("namas");
            kelasinfo = getIntent().getStringExtra("kelas");
            telpsinfo = getIntent().getStringExtra("telps");
            pembimbinginfo = getIntent().getStringExtra("pembimbing");
            industriinfo = getIntent().getStringExtra("industri");
            sivsiswa = getIntent().getStringExtra("foto");

            ids.setText(idsinfo);
            nissiswa.setText(nissiswainfo);
            namas.setText(namasinfo);
            kelas.setText(kelasinfo);
            telps.setText(telpsinfo);
            pembimbing.setText(pembimbinginfo);
            industri.setText(industriinfo);

            Glide.with(this)

                    .load(tampil+sivsiswa)

                    .override(300, 200)

                    .into(ivsiswa);


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();
            }
        });



        if (ContextCompat.checkSelfPermission(editprofilsiswa.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(editprofilsiswa.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        ivsiswa.setOnClickListener(new View.OnClickListener() {
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
            ivsiswa.setImageBitmap(bitmap);
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


        String idss = ids.getText().toString();
        String niss = nissiswa.getText().toString();
        String namass = namas.getText().toString();
        String kelass = kelas.getText().toString();
        String telpss = telps.getText().toString();
        String pembimbings = pembimbing.getText().toString();
        String industris = industri.getText().toString();
        String siv = sivsiswa.getBytes().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
//                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(editprofilsiswa.this, s , Toast.LENGTH_LONG).show();
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

                        Toast.makeText(editprofilsiswa.this, "Mohon perbarui kembali foto profil", Toast.LENGTH_LONG).show();
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



                    params.put("ids", idss);
                    params.put("nis", niss);
                    params.put("namas", namass);
                    params.put("kelas", kelass);
                    params.put("telps", telpss);
                    params.put("pembimbing", pembimbings);
                    params.put("industri", industris);
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

//    private void SendImage(final String image) {
//        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("uploade", response);
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                        } catch (JSONException ex) {
//                            throw new RuntimeException(ex);
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//        Toast.makeText(editprofilsiswa.this, "No internet connection", Toast.LENGTH_LONG).show();
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new Hashtable<String, String>();
//
//                params.put("image", image);
//                return params;
//            }
//        };
//
//        {
//            int socketTimeout = 30000;
//            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//            stringRequest.setRetryPolicy(policy);
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(stringRequest);
//        }
//    }
}