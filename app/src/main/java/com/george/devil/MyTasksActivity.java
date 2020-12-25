package com.george.devil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_tasks);
        toolbar.setTitleTextColor(Color.WHITE);

        FloatingActionButton addTask = findViewById(R.id.add_task);
        addTask.setOnClickListener(v -> startActivity(new Intent(MyTasksActivity.this, AddTaskActivity.class)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit_theme_task) {
            BottomSheetEditThemeTasks themes = new BottomSheetEditThemeTasks();
            themes.show(getSupportFragmentManager(), "BottomSheetThemes");
        }

        return super.onOptionsItemSelected(item);
    }
}