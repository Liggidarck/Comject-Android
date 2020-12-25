package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;

public class AddChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change);

        MaterialCardView addNote = findViewById(R.id.add_note_change_layout);
        addNote.setOnClickListener(v -> startActivity(new Intent(AddChange.this, AddNoteChange.class)));

    }
}