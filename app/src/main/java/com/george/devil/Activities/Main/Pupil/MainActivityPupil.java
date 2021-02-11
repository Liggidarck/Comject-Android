package com.george.devil.Activities.Main.Pupil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.george.devil.Fragments.Common.fragmentExplore;
import com.george.devil.Fragments.Pupil.fragmentHome;
import com.george.devil.Fragments.Pupil.fragmentMessege;
import com.george.devil.Fragments.Pupil.fragmentNote;
import com.george.devil.Fragments.Pupil.fragmentProfile;
import com.george.devil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityPupil extends AppCompatActivity {

    /**
     * Главный класс для отрисовки фрагментов ученика.
     */
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ProjectDevil);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_pupil_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Дефолтный запущенный фрагмент home.
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, new fragmentHome()).commit();

    }

    /**
     Метод для работы {@link BottomNavigationView}.
     Метод отслеживает нажатие пользователя и запускает фрагмент. */
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
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
                        Log.i(TAG, "Fragment message started");
                        selectedFragment = new fragmentMessege();
                        break;
                    case R.id.nav_profile:
                        Log.i(TAG, "Fragment profile started");
                        selectedFragment = new fragmentProfile();
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, selectedFragment).commit();

                return true;
            };


    @Override
    public void onBackPressed() {
        Log.i(TAG, "No");
    }
}