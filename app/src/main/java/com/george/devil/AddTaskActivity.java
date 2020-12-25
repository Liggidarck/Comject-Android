package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        MaterialCardView addNote = findViewById(R.id.add_note_task_layout);
        addNote.setOnClickListener(v -> startActivity(new Intent(AddTaskActivity.this, AddTaskNoteActivity.class)));

    }
}