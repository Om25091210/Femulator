package com.alpha.femulator.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alpha.femulator.R;

import soup.neumorphism.NeumorphCardView;

public class joinus extends AppCompatActivity {

    NeumorphCardView neumorphCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinus);

        neumorphCardView=findViewById(R.id.neumorphCardView);
        neumorphCardView.setOnClickListener(v->{
            Intent mainActivity = new Intent(getApplicationContext(), login.class);
            startActivity(mainActivity);
            finish();
        });
    }
}