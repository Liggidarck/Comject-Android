package com.george.devil.Activities.Tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.george.devil.BottomSheets.BottomSheetEditThemeTasks;
import com.george.devil.DataBases.ChangesDataBase;
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

    String themeChoosed = "Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        themeChoosed =  prefs.getString("themeData", "Default");
        Log.i(TAG, themeChoosed);

        TasksList = findViewById(R.id.list_tasks);
        theme_tasks = findViewById(R.id.theme_tasks);
        toolbar = findViewById(R.id.toolbar_tasks);
        FloatingActionButton addTask = findViewById(R.id.add_task);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ThemeChanged(themeChoosed);

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

    /**
     * Подключаемся к {@link ChangesDataBase} для отрисовки сохраненых данных в базе данных
     */
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

    /**
     * Когда фрагмент пониамет, что больше не используется, он закрывает подключение к базе данных
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }

    /**
     * Подключаемся к menu для отрисовки в {@link MaterialToolbar} кнопки Change Theme
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_menu, menu);

        return true;
    }

    /**
     * Метод для реализации нажатия на элементы {@link MaterialToolbar}
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit_theme_task) {
            BottomSheetEditThemeTasks themes = new BottomSheetEditThemeTasks();
            themes.show(getSupportFragmentManager(), "BottomSheetThemes");
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Вызывается когда пользователь меняет тему
     * @param theme_text полученое значение от {@link BottomSheetEditThemeTasks} для сохрания в памяти телефона
     */
    @Override
    public void ThemeChoose(String theme_text) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();

        if(theme_text.equals("Default")) {
            themeChoosed = "Default";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Red")) {
            themeChoosed = "Red";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Orange")){
            themeChoosed = "Orange";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Yellow")){
            themeChoosed = "Yellow";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Green")){
            themeChoosed = "Green";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Green Secondary")){
            themeChoosed = "Green Secondary";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Light Blue")) {
            themeChoosed = "Light Blue";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Blue")){
            themeChoosed = "Blue";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Violet")){
            themeChoosed = "Violet";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Pink")){
            themeChoosed = "Pink";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        if(theme_text.equals("Gray")){
            themeChoosed = "Gray";
            editor.putString("themeData", themeChoosed);
            ThemeChanged(themeChoosed);
        }

        editor.apply();

    }

    /**
     * Метод для отрисовки сохраненой темы в памяти телефона
     * @param theme  для отслеживая сохраненой темы
     */
    public void ThemeChanged(String theme) {

        if(theme.equals("Default")) {
            theme_tasks.setBackgroundColor(Color.parseColor("#FAFAFA"));
            toolbar.setBackgroundColor(Color.parseColor("#3C80E7"));
        }

        if(theme.equals("Red")) {
            theme_tasks.setBackgroundColor(Color.parseColor("#FF8C8C"));
            toolbar.setBackgroundColor(Color.parseColor("#FF8C8C"));
        }

        if(theme.equals("Orange")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FFB661"));
            toolbar.setBackgroundColor(Color.parseColor("#FFB661"));
        }

        if(theme.equals("Yellow")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FFD850"));
            toolbar.setBackgroundColor(Color.parseColor("#FFD850"));
        }

        if(theme.equals("Green")){
            theme_tasks.setBackgroundColor(Color.parseColor("#7AE471"));
            toolbar.setBackgroundColor(Color.parseColor("#7AE471"));
        }

        if(theme.equals("Green Secondary")){
            theme_tasks.setBackgroundColor(Color.parseColor("#56E0C7"));
            toolbar.setBackgroundColor(Color.parseColor("#56E0C7"));
        }

        if(theme.equals("Light Blue")) {
            theme_tasks.setBackgroundColor(Color.parseColor("#6CD3FF"));
            toolbar.setBackgroundColor(Color.parseColor("#6CD3FF"));
        }

        if(theme.equals("Blue")){
            theme_tasks.setBackgroundColor(Color.parseColor("#819CFF"));
            toolbar.setBackgroundColor(Color.parseColor("#819CFF"));
        }

        if(theme.equals("Violet")){
            theme_tasks.setBackgroundColor(Color.parseColor("#DD8BFA"));
            toolbar.setBackgroundColor(Color.parseColor("#DD8BFA"));
        }

        if(theme.equals("Pink")){
            theme_tasks.setBackgroundColor(Color.parseColor("#FF6CA1"));
            toolbar.setBackgroundColor(Color.parseColor("#FF6CA1"));
        }

        if(theme.equals("Gray")){
            theme_tasks.setBackgroundColor(Color.parseColor("#C4C4C4"));
            toolbar.setBackgroundColor(Color.parseColor("#C4C4C4"));
        }

    }
}