package com.george.devil.Activities.Main.Pupil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_comments_main);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }
}