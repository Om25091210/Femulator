package com.alpha.femulator.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alpha.femulator.Home_user;
import com.alpha.femulator.R;

import soup.neumorphism.NeumorphCardView;

public class login extends AppCompatActivity {

    NeumorphCardView neumorphCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        neumorphCardView=findViewById(R.id.neumorphCardView);
        neumorphCardView.setOnClickListener(v->{
            Intent mainActivity = new Intent(getApplicationContext(), Home_user.class);
            startActivity(mainActivity);
            finish();
        });
    }
}