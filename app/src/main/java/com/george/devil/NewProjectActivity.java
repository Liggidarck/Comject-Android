package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;

public class NewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_new_project1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button next = findViewById(R.id.next_btn);
        next.setOnClickListener(v -> startActivity(new Intent(NewProjectActivity.this, NewProjectImagesActivity.class)));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}