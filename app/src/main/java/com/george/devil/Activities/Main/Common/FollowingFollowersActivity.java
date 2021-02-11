package com.george.devil.Activities.Main.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.george.devil.Fragments.Common.fragmentFollowers;
import com.george.devil.Fragments.Common.fragmentFollowing;
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
        String name_teacher = sharedPreferences.getString("full_name_teather", "full_name_teather_empty");

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_following_followers);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if(!name_user.equals("empty_user_name"))
            toolbar.setTitle(name_user);

        if(!name_teacher.equals("full_name_teather_empty"))
            toolbar.setTitle(name_teacher);

        TabAdapterFollowingFollowers adapter = new TabAdapterFollowingFollowers(getSupportFragmentManager());
        adapter.addFragment(new fragmentFollowers(), (String) getText(R.string.followers));
        adapter.addFragment(new fragmentFollowing(), (String) getText(R.string.following));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}