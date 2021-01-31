package com.george.devil.Activities.Projects.PagesProjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.george.devil.Activities.Projects.AddInformationTeacher;
import com.george.devil.R;

public class ProjectTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_teacher);

        RelativeLayout information_layout_post = findViewById(R.id.information_layout_post);
        information_layout_post.setOnClickListener(v -> startActivity(new Intent(ProjectTeacher.this, AddInformationTeacher.class)));

    }
}