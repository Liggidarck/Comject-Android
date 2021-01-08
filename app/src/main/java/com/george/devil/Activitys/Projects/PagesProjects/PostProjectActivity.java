package com.george.devil.Activitys.Projects.PagesProjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.george.devil.Activitys.Main.Pupil.ProfileActivityPupil;
import com.george.devil.Activitys.Tasks.MyTasksActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PostProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_project);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_post_proj);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        RelativeLayout my_tasks_layout_post = findViewById(R.id.my_tasks_layout_post);
        my_tasks_layout_post.setOnClickListener(v -> startActivity(new Intent(PostProjectActivity.this, MyTasksActivity.class)));

        RelativeLayout layout_profile = findViewById(R.id.layout_profile);
        layout_profile.setOnClickListener(v -> startActivity(new Intent(PostProjectActivity.this, ProfileActivityPupil.class)));
    }
}