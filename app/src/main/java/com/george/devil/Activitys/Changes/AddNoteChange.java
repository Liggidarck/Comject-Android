package com.george.devil.Activitys.Changes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class AddNoteChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_change);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_add_note_change_new);
        toolbar.setTitle("Change Name");

    }
}