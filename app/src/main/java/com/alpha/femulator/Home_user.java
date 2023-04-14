package com.alpha.femulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alpha.mylibrary.views.DuoDrawerLayout;
import com.alpha.mylibrary.views.DuoMenuView;
import com.alpha.mylibrary.widgets.DuoDrawerToggle;

import java.util.ArrayList;
import java.util.Arrays;

public class Home_user extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {

    private menuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));

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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, mainFragment,"mainFrag").commit();
    }

    @Override
    public void onFooterClicked() {

    }

    @Override
    public void onHeaderClicked() {

    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {

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