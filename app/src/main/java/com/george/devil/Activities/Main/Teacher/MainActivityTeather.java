package com.george.devil.Activities.Main.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.george.devil.Fragments.Common.fragmentExplore;
import com.george.devil.Fragments.Teacher.fragmentHomeTeacher;
import com.george.devil.Fragments.Teacher.fragmentMessageTeacher;
import com.george.devil.Fragments.Teacher.fragmentProfileTeacher;
import com.george.devil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityTeather extends AppCompatActivity {

    static final String TAG = "mainActivityTea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teather);

        BottomNavigationView navigationView = findViewById(R.id.bottom_teather_navigation);
        navigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_teather_container, new fragmentHomeTeacher()).commit();

    }

    /**
     * Метод для работы {@link BottomNavigationView}.
     * Метод отслеживает нажатие пользователя и запускает фрагмент.
     */
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home_teather:
                        Log.i(TAG, "Fragment home started");
                        selectedFragment = new fragmentHomeTeacher();
                        break;
                    case R.id.nav_explore_teather:
                        Log.i(TAG, "Fragment explore started");
                        selectedFragment = new fragmentExplore();
                        break;
                    case R.id.nav_message_teather:
                        Log.i(TAG, "Fragment message started");
                        selectedFragment = new fragmentMessageTeacher();
                        break;
                    case R.id.nav_profile_teather:
                        Log.i(TAG, "Fragment profile started");
                        selectedFragment = new fragmentProfileTeacher();
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_teather_container, selectedFragment).commit();

                return true;
            };


}