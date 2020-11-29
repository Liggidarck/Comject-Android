package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;

public class NewProjectImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_images);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_new_project2);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button done = findViewById(R.id.done_btn);
        done.setOnClickListener(v -> startActivity(new Intent(NewProjectImagesActivity.this, MainActivity.class)));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}