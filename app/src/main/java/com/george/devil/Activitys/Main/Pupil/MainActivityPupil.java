package com.george.devil.Activitys.Main.Pupil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.george.devil.Activitys.Main.LoginActivity;
import com.george.devil.Fragments.Pupil.fragmentExplore;
import com.george.devil.Fragments.Pupil.fragmentHome;
import com.george.devil.Fragments.Pupil.fragmentMessege;
import com.george.devil.Fragments.Pupil.fragmentNote;
import com.george.devil.Fragments.Pupil.fragmentProfile;
import com.george.devil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityPupil extends AppCompatActivity {

    double chekable_data, start_seesion, load_component;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ProjectDevil);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        String name_user = sharedPreferences.getString("full_name", "empty_user_name");
//        if(name_user.equals("empty_user_name"))
//            startActivity(new Intent(MainActivityPupil.this, LoginActivity.class));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_pupil_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, new fragmentHome()).commit();

        start_seesion = 4231.1;
        load_component = 322;

    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        chekable_data = start_seesion / load_component;
                        Log.i(TAG, "Fragment home started Davy! current speed: " + Math.log(chekable_data));
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, selectedFragment).commit();

                return true;
            };

    @Override
    public void onBackPressed() {

    }
}