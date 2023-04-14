package com.alpha.femulator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView submit;
    EditText criminal_name_edt,lop,age_of_menarche,height,weight,BMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit=findViewById(R.id.submit_txt);
        height=findViewById(R.id.height);
        criminal_name_edt=findViewById(R.id.criminal_name_edt);
        age_of_menarche=findViewById(R.id.age_of_menarche);
        BMI=findViewById(R.id.BMI);
        weight=findViewById(R.id.weight);
        lop=findViewById(R.id.lop);
        submit.setOnClickListener(v->{
            get_prediction();
        });
    }

    private void get_prediction() {

        String URL = "https://d23a-2405-201-3030-68a6-190e-366b-bfb5-3caf.ngrok-free.app/?length_of_cycle="+
                criminal_name_edt.getText().toString()+"&length_of_period="+lop.getText().toString()+
                "&age_at_menarche="+age_of_menarche.getText().toString()+"&height="+height.getText().toString()
                +"&weight="+weight.getText().toString()
                +"&bmi="+BMI.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("BULK code", response +"");
                        Log.e("BULK response",response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
                Log.e("Status of code = ","Wrong");

            }
        });

        queue.add(stringRequest);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
    }
}