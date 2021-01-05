package com.george.devil.Activitys.Main.Pupil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.george.devil.Fragments.Pupil.fragmentFollowers;
import com.george.devil.Fragments.Pupil.fragmentFollowing;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class FollowingFollowersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_followers);

        ViewPager viewPager =  findViewById(R.id.viewPager);
        TabLayout tabLayout =  findViewById(R.id.tab_layout);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_user = sharedPreferences.getString("full_name", "empty_user_name");

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_following_followers);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setTitle(name_user);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentFollowers(), "Followers");
        adapter.addFragment(new fragmentFollowing(), "Following");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}