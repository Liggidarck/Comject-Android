package com.george.devil.Activities.Main.Pupil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.george.devil.R;


public class ProfileActivityPupil extends AppCompatActivity {

    Button profile_sub, profile_subed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pupil);

        profile_sub = findViewById(R.id.profile_sub);
        profile_subed = findViewById(R.id.profile_subed);

        profile_sub.setOnClickListener(v -> {
            profile_sub.setVisibility(View.INVISIBLE);
            profile_subed.setVisibility(View.VISIBLE);
        });

        profile_subed.setOnClickListener(v -> {
            profile_sub.setVisibility(View.VISIBLE);
            profile_subed.setVisibility(View.INVISIBLE);
        });

    }
}