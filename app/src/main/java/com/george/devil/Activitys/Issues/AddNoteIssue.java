package com.george.devil.Activitys.Issues;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class AddNoteIssue extends AppCompatActivity {

    EditText note_issue_text;
    String nameIssue, noteText;

    private static final String TAG = "AddNoteIssue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_issue);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_add_note_issue_new);
        toolbar.setTitle("Isuue name");

        note_issue_text = findViewById(R.id.note_issue_text);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            nameIssue = extras.getString("name_issue");

        toolbar.setTitle(nameIssue);

        toolbar.setNavigationOnClickListener(v ->{
            noteText = note_issue_text.getText().toString();
            Intent goBack = new Intent(AddNoteIssue.this, AddIssue.class);
            goBack.putExtra("noteIssue", noteText);
            startActivity(goBack);
        });


    }


}