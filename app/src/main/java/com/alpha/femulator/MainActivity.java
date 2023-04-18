package com.alpha.femulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import com.alpha.femulator.Model.APIResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView submit;
    EditText criminal_name_edt,lop,age_of_menarche,height,weight;
    TextView perious;
    double bmi;
    String api_link="";
    DatabaseReference api_ref;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api_ref= FirebaseDatabase.getInstance().getReference().child("apilink");
        check();
        submit=findViewById(R.id.submit_txt);
        height=findViewById(R.id.height);
        criminal_name_edt=findViewById(R.id.criminal_name_edt);
        age_of_menarche=findViewById(R.id.age_of_menarche);
        perious=findViewById(R.id.BMI);
        weight=findViewById(R.id.weight);
        lop=findViewById(R.id.lop);
        perious.setOnClickListener(v->{
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    mDateSetListener,
                    year,month,day);
            dialog.show();
        });
        mDateSetListener = (datePicker, year, month, day) -> {

            String d=String.valueOf(day);
            String m=String.valueOf(month+1);
            Log.e("month",m+"");
            month = month + 1;
            Log.e("month",month+"");
            if(String.valueOf(day).length()==1)
                d="0"+ day;
            if(String.valueOf(month).length()==1)
                m="0"+ month;
            String date = year + "-" + m + "-" + d;
            perious.setText(date);

        };
        submit.setOnClickListener(v->{
            submit.setText("Predicting . . . ");
            get_prediction();
        });
    }

    private void get_prediction() {
        cal_bmi();
        if(!criminal_name_edt.getText().toString().equals("") &&
            !lop.getText().toString().equals("") &&
            !age_of_menarche.getText().toString().equals("") &&
            !weight.getText().toString().equals("") &&
            !weight.getText().toString().equals("") &&
            !perious.getText().toString().equals("Tap to select")) {
            String URL = api_link+"/?length_of_cycle=" +
                    criminal_name_edt.getText().toString() + "&length_of_period=" + lop.getText().toString() +
                    "&age_at_menarche=" + age_of_menarche.getText().toString() + "&height=" + Integer.parseInt(weight.getText().toString())
                    + "&weight=" + Integer.parseInt(weight.getText().toString())
                    + "&bmi=" + bmi;

            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("BULK code", response + "");
                            // Convert response object to JSON
                            Gson gson = new Gson();
                            APIResponse resp = gson.fromJson(response, APIResponse.class);
                            double prediction = Double.parseDouble(resp.getPrediction());
                            Log.e("prediction", prediction - 10 + "");
                            Intent intent = new Intent(MainActivity.this, Home_user.class);
                            intent.putExtra("prediction", prediction - 10 + "");
                            intent.putExtra("lop", lop.getText().toString());
                            intent.putExtra("prev_date", perious.getText().toString());
                            startActivity(intent);
                            finish();
                            getSharedPreferences("FORM", MODE_PRIVATE).edit()
                                    .putString("form_filled", "yes").apply();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // enjoy your error status
                    Log.e("Status of code = ", "Wrong");

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

    void check(){
        api_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                api_link=snapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    private void cal_bmi() {
        double heightInInches = Integer.parseInt(height.getText().toString()) / 2.54;
        double heightInMetres = heightInInches / 39.37;
        double heightInMetreSq = heightInMetres * heightInMetres;
        bmi = Integer.parseInt(weight.getText().toString()) / heightInMetreSq;
        Log.e("BMI",bmi+"");
    }
}