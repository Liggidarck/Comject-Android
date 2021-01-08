package com.george.devil.Activitys.Changes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.george.devil.DataBases.ChangesDataBase;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChangesActivity extends AppCompatActivity {

    ChangesDataBase databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    ListView changesList;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changes);

        changesList = findViewById(R.id.changes_list);
        toolbar = findViewById(R.id.toolbar_changes);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        changesList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), AddChange.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        databaseHelper = new ChangesDataBase(getApplicationContext());

        View empty = findViewById(R.id.empty_change_layout);
        changesList.setEmptyView(empty);

        FloatingActionButton add = findViewById(R.id.add_chage);
        add.setOnClickListener(v -> startActivity(new Intent(ChangesActivity.this, AddChange.class)));
    }


    @Override
    protected void onResume() {
        super.onResume();

        db = databaseHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "+ ChangesDataBase.TABLE, null);

        String[] headers = new String[] {ChangesDataBase.COLUMN_NAME_CHANGE, ChangesDataBase.COLUMN_DUE_DAY};

        userAdapter = new SimpleCursorAdapter(ChangesActivity.this, android.R.layout.simple_list_item_2, userCursor, headers,
                new int[] {android.R.id.text1, android.R.id.text2}, 0);
        changesList.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}