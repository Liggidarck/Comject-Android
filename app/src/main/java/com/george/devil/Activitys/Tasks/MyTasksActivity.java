package com.george.devil.Activitys.Tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.george.devil.BottomSheets.BottomSheetEditThemeTasks;
import com.george.devil.DataBases.TasksDataBase;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyTasksActivity extends AppCompatActivity implements BottomSheetEditThemeTasks.StateListener {

    TasksDataBase databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    ListView TasksList;

    View theme_tasks;

    MaterialToolbar toolbar;
    private static final String TAG = "MyTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        //TODO: Доделать изменение темы. Нужно сделать сохранение состояния выбранной темы и отображение выбранной темы в BottomSheet

        TasksList = findViewById(R.id.list_tasks);
        theme_tasks = findViewById(R.id.theme_tasks);

        toolbar = findViewById(R.id.toolbar_tasks);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        FloatingActionButton addTask = findViewById(R.id.add_task);
        addTask.setOnClickListener(v -> startActivity(new Intent(MyTasksActivity.this, AddTaskActivity.class)));

        TasksList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        databaseHelper = new TasksDataBase(getApplicationContext());

        View empty = findViewById(R.id.tasks_empty);
        TasksList.setEmptyView(empty);

    }

    @Override
    public void onResume(){
        super.onResume();

        db = databaseHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "+ TasksDataBase.TABLE, null);

        String[] headers = new String[] {TasksDataBase.COLUMN_TASK};

        userAdapter = new SimpleCursorAdapter(MyTasksActivity.this, android.R.layout.simple_list_item_1,
                userCursor, headers, new int[] {android.R.id.text1}, 0);
        TasksList.setAdapter(userAdapter);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
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

    @Override
    public void ThemeChoose(String tex) {

        if(tex.equals("Default")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FAFAFA"));
            toolbar.setBackgroundColor(Color.parseColor("#3C80E7"));
        }

        if(tex.equals("Red")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FF8C8C"));
            toolbar.setBackgroundColor(Color.parseColor("#FF8C8C"));
        }

        if(tex.equals("Orange")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FFB661"));
            toolbar.setBackgroundColor(Color.parseColor("#FFB661"));
        }

        if(tex.equals("Yellow")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FFD850"));
            toolbar.setBackgroundColor(Color.parseColor("#FFD850"));
        }

        if(tex.equals("Green")){
            theme_tasks.setBackgroundColor(Color.parseColor("#7AE471"));
            toolbar.setBackgroundColor(Color.parseColor("#7AE471"));
        }

        if(tex.equals("Green Secondary")){
            theme_tasks.setBackgroundColor(Color.parseColor("#56E0C7"));
            toolbar.setBackgroundColor(Color.parseColor("#56E0C7"));
        }

        if(tex.equals("Light Blue")) {
            theme_tasks.setBackgroundColor(Color.parseColor("#6CD3FF"));
            toolbar.setBackgroundColor(Color.parseColor("#6CD3FF"));
        }

        if(tex.equals("Blue")){
            theme_tasks.setBackgroundColor(Color.parseColor("#819CFF"));
            toolbar.setBackgroundColor(Color.parseColor("#819CFF"));
        }

        if(tex.equals("Violet")){
            theme_tasks.setBackgroundColor(Color.parseColor("#DD8BFA"));
            toolbar.setBackgroundColor(Color.parseColor("#DD8BFA"));
        }

        if(tex.equals("Pink")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FF6CA1"));
            toolbar.setBackgroundColor(Color.parseColor("#FF6CA1"));
        }

        if(tex.equals("Gray")){
            theme_tasks.setBackgroundColor(Color.parseColor("#C4C4C4"));
            toolbar.setBackgroundColor(Color.parseColor("#C4C4C4"));
        }

    }
}