package com.alpha.femulator.Onboarding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alpha.femulator.Home_user;
import com.alpha.femulator.MainActivity;
import com.alpha.femulator.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import soup.neumorphism.NeumorphCardView;

public class login extends AppCompatActivity {

    NeumorphCardView neumorphCardView;
    FirebaseUser user;
    FirebaseAuth auth;
    Dialog google_dialog;
    GoogleSignInClient agooglesigninclient;
    private static final int RC_SIGN_IN = 101;
    DatabaseReference user_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        user_reference= FirebaseDatabase.getInstance().getReference().child("users");

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        agooglesigninclient = GoogleSignIn.getClient(this,gso);

        neumorphCardView=findViewById(R.id.neumorphCardView);
        neumorphCardView.setOnClickListener(v->{
            signIn_Google();
        });
    }
    private void signIn_Google() {
        Intent SignInIntent = agooglesigninclient.getSignInIntent();
        startActivityForResult(SignInIntent,RC_SIGN_IN);
        google_dialog = new Dialog(login.this);
        google_dialog.setCancelable(true);
        google_dialog.setContentView(R.layout.loading);
        google_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        google_dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account);


            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                Log.e("exception ",e+"");
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {

                    if(task.isSuccessful()){

                        if (google_dialog != null && google_dialog.isShowing()) {
                            google_dialog.dismiss();
                        }

                        user = auth.getCurrentUser();

                        assert user != null;

                        Toast.makeText(this, "Login Successfully!!!", Toast.LENGTH_SHORT).show();

                        user_reference.child(user.getUid()).child("email").setValue(user.getEmail());
                        user_reference.child(user.getUid()).child("name").setValue(user.getDisplayName());
                        user_reference.child(user.getUid()).child("uid").setValue(user.getUid());
                        updateUI();

                    }
                    else{
                        Toast.makeText(login.this, "Login failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void updateUI() {

        Intent intent=new Intent(login.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}