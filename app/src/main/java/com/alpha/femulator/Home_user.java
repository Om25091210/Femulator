package com.alpha.femulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.mylibrary.views.DuoDrawerLayout;
import com.alpha.mylibrary.views.DuoMenuView;
import com.alpha.mylibrary.widgets.DuoDrawerToggle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Home_user extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {

    private menuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    private ArrayList<String> mTitles = new ArrayList<>();
    Uri deep_link_uri;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth auth;
    FirebaseUser user;
    String val_pred,prev_date,lop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        deep_link_uri = getIntent().getData();//deep link value
        Uri deepLinkUri = getIntent().getParcelableExtra("DEEP_LINK_URI");

        TextView name=findViewById(R.id.textView2);
        Log.e("deep",deep_link_uri+"");
        if(user==null){
            deep_link_uri=deepLinkUri;
            name.setText("Partner");
        }else {
            name.setText(user.getDisplayName());
        }
        val_pred=getIntent().getStringExtra("prediction");
        prev_date=getIntent().getStringExtra("prev_date");
        lop=getIntent().getStringExtra("lop");

        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));

        // Initialize the views
        mViewHolder = new ViewHolder();

        // Handle menu actions
        handleMenu();

        // Handle drawer actions
        handleDrawer();

        goToFragment(new MainFragment());
    }

    private void goToFragment(MainFragment mainFragment) {
        if(deep_link_uri!=null){
            if (deep_link_uri.toString().equals("https://femulator.android")){
                Bundle bundle = new Bundle();
                bundle.putString("val_pred", val_pred);
                bundle.putString("prev_date", prev_date);
                bundle.putString("lop", lop);
                mainFragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, mainFragment, "mainFrag").commit();
            }
            else if(deep_link_uri.toString().equals("http://femulator.android")){
                Bundle bundle = new Bundle();
                bundle.putString("val_pred", val_pred);
                bundle.putString("prev_date", prev_date);
                bundle.putString("lop", lop);
                mainFragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, mainFragment, "mainFrag").commit();
            }
            else if(deep_link_uri.toString().equals("femulator.android")){
                Bundle bundle = new Bundle();
                bundle.putString("val_pred", val_pred);
                bundle.putString("prev_date", prev_date);
                bundle.putString("lop", lop);
                mainFragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, mainFragment, "mainFrag").commit();
            }
            else{
                // if the uri is not null then we are getting the
                // path segments and storing it in list.
                List<String> parameters = deep_link_uri.getPathSegments();
                // after that we are extracting string from that parameters.
                if(parameters!=null) {
                    if(parameters.size()>1) {
                        String check_profile=parameters.get(parameters.size()-2);
                        if(check_profile.trim().equals("profile")){

                            String name=parameters.get(parameters.size()-1);
                            String uid=parameters.get(parameters.size()-3);
                            //sending values to home_content frag for opening profile...
                            Bundle args = new Bundle();
                            args.putString("deep_link_name", name);
                            args.putString("deep_link_uid_value_profile", uid);
                            mainFragment.setArguments(args);
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.container, mainFragment, "mainFrag").commit();

                        }
                        else {
                            String param = parameters.get(parameters.size() - 1);
                            String uid = parameters.get(parameters.size() - 2);
                            // on below line we are setting
                            // that string to our text view
                            // which we got as params.
                            Log.e("deep_link_value", param + "");
                            Log.e("deep_link_value_uid", uid + "");
                            Bundle args = new Bundle();
                            args.putString("deep_link_value", param);
                            args.putString("deep_link_uid_value", uid);
                            mainFragment.setArguments(args);
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.container, mainFragment, "mainFrag").commit();
                        }
                    }
                    else{
                        Bundle bundle = new Bundle();
                        bundle.putString("val_pred", val_pred);
                        bundle.putString("prev_date", prev_date);
                        bundle.putString("lop", lop);
                        mainFragment.setArguments(bundle);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.add(R.id.container, mainFragment, "mainFrag").commit();
                    }
                }
            }
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putString("val_pred", val_pred);
            bundle.putString("prev_date", prev_date);
            bundle.putString("lop", lop);
            mainFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, mainFragment, "mainFrag").commit();
        }
    }

    @Override
    public void onFooterClicked() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(Home_user.this, gso);

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(Home_user.this, task -> Toast.makeText(this, "Sign out Successfully!", Toast.LENGTH_SHORT).show());
        auth.signOut();
        startActivity(new Intent(Home_user.this , Splash.class));
        finish();
    }

    @Override
    public void onHeaderClicked() {

    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
// Set the right options selected
        mMenuAdapter.setViewSelected(position);

        // Navigate to the right fragment
        if(position==1) {

            mMenuAdapter.setViewSelected(0);
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
        else if(position==2) {
            mMenuAdapter.setViewSelected(0);
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
        else if(position==3) {
            String title ="*Femulator - Female Menstrual Calculator*"+"\n\n"+"Download this app to stay updated with your cycles"; //Text to be shared
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, title+"\n\n"+"This is a playstore link to download.. " + "https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
            mMenuAdapter.setViewSelected(0);
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
        else if(position==4) {
            mMenuAdapter.setViewSelected(0);
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
        else if(position==5){
            mMenuAdapter.setViewSelected(0);
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
        else if(position==6){
            String str_title ="*"+"Femulator"+"*"+
                    "\n\n"+"*View* :"+"https://femulator.android/mosioco/"+user.getUid()+"/"+user.getUid() ; //Text to be shared
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, str_title+"\n\n"+"This is a playstore link to download.. " + "https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
            mMenuAdapter.setViewSelected(0);
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
        else {
            mViewHolder.mDuoDrawerLayout.closeDrawer();
        }
    }
    private void handleMenu() {
        mMenuAdapter = new menuAdapter(mTitles);

        mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }

    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                com.alpha.mylibrary.R.string.navigation_drawer_open,
                com.alpha.mylibrary.R.string.navigation_drawer_close);

        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();

    }
    private class ViewHolder {
        private final DuoDrawerLayout mDuoDrawerLayout;
        private final DuoMenuView mDuoMenuView;
        private final ImageView mToolbar;

        ViewHolder() {
            mDuoDrawerLayout =findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();
            mToolbar = findViewById(R.id.imageView5);
        }
    }
}