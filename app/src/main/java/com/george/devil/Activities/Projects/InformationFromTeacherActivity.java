package com.george.devil.Activities.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class InformationFromTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ProjectDevilToolbar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_from_teacher);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_information_teather_proj);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}