package com.george.devil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragmentHome()).commit();

    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Log.i(TAG, "Fragment home started");
                        selectedFragment = new fragmentHome();
                        break;
                    case R.id.nav_note:
                        Log.i(TAG, "Fragment note started");
                        selectedFragment = new fragmentNote();
                        break;
                    case R.id.nav_explore:
                        Log.i(TAG, "Fragment explore started");
                        selectedFragment = new fragmentExplore();
                        break;
                    case R.id.nav_messeg:
                        Log.i(TAG, "Fragment messege started");
                        selectedFragment = new fragmentMessege();
                        break;
                    case R.id.nav_profile:
                        Log.i(TAG, "Fragment porofile started");
                        selectedFragment = new fragmentProfile();
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                return true;
            };
}