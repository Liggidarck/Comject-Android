package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IsuuesActivity extends AppCompatActivity {


    IssuesDataBase databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    ListView issuesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isuues);

        FloatingActionButton add = findViewById(R.id.add_issue);
        add.setOnClickListener(v -> startActivity(new Intent(IsuuesActivity.this, AddIssue.class)));

        issuesList = findViewById(R.id.issues_list);

        issuesList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), AddIssue.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        databaseHelper = new IssuesDataBase(getApplicationContext());

        View empty = findViewById(R.id.empty_issue_layout);
        issuesList.setEmptyView(empty);
    }

    @Override
    public void onResume(){
        super.onResume();

        db = databaseHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "+ IssuesDataBase.TABLE, null);

        String[] headers = new String[] {IssuesDataBase.COLUMN_NAME_ISSUE, IssuesDataBase.COLUMN_PRIORITY};

        userAdapter = new SimpleCursorAdapter(IsuuesActivity.this, android.R.layout.simple_list_item_2, userCursor, headers,
                new int[] {android.R.id.text1, android.R.id.text2}, 0);
        issuesList.setAdapter(userAdapter);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }


}