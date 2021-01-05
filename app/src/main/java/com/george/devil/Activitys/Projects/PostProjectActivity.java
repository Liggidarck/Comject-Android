package com.george.devil.Activitys.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.george.devil.Activitys.Tasks.MyTasksActivity;
import com.george.devil.R;

public class PostProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_project);

        RelativeLayout my_tasks_layout_post = findViewById(R.id.my_tasks_layout_post);
        my_tasks_layout_post.setOnClickListener(v -> startActivity(new Intent(PostProjectActivity.this, MyTasksActivity.class)));

    }
}