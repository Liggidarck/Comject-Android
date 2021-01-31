package com.george.devil.Activities.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.george.devil.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddInformationTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information_teacher);

        FloatingActionButton add_info = findViewById(R.id.add_info);
        add_info.setOnClickListener(v -> startActivity(new Intent(AddInformationTeacher.this, AddInformationActivity.class) ));
    }
}