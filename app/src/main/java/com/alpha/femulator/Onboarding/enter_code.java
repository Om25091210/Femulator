package com.alpha.femulator.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.alpha.femulator.Home_user;
import com.alpha.femulator.R;

public class enter_code extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);

        EditText link=findViewById(R.id.age_of_menarche);
        TextView proceed=findViewById(R.id.submit_txt);
        proceed.setOnClickListener(v->{
            Intent intent = new Intent(this, Home_user.class);
            intent.putExtra("DEEP_LINK_URI", Uri.parse(link.getText().toString()+""));
            startActivity(intent);
            finish();
        });
    }
}