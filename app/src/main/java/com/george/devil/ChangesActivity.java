package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChangesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changes);

        FloatingActionButton add = findViewById(R.id.add_chage);
        add.setOnClickListener(v -> startActivity(new Intent(ChangesActivity.this, AddChange.class)));
    }
}