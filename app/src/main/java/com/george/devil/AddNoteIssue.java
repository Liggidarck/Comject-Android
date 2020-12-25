package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class AddNoteIssue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_issue);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_add_note_issue_new);
        toolbar.setTitle("Isuue name");

    }
}