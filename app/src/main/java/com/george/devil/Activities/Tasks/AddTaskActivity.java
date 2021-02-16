package com.george.devil.Activities.Tasks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.george.devil.Activities.Changes.ChangesActivity;
import com.george.devil.Activities.Issues.AddIssue;
import com.george.devil.DataBases.ChangesDataBase;
import com.george.devil.DataBases.IssuesDataBase;
import com.george.devil.R;
import com.george.devil.DataBases.TasksDataBase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    TasksDataBase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long taskId = 0;

    EditText name_task;
    TextView text_edit_task;
    String date;

    MaterialToolbar toolbar;
    MaterialCardView addNote;

    ImageView delete_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        name_task = findViewById(R.id.name_task);
        text_edit_task = findViewById(R.id.text_edit_task);
        toolbar = findViewById(R.id.topAppBar_add_task);
        addNote = findViewById(R.id.add_note_task_layout);
        delete_task = findViewById(R.id.delete_task);

        toolbar.setNavigationOnClickListener(v -> save());
        addNote.setOnClickListener(v -> startActivity(new Intent(AddTaskActivity.this, AddTaskNoteActivity.class)));

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        date = "Последнее изменение:" + " " + dateText + " " + timeText;
        text_edit_task.setText(date);

        delete_task.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            boolean confirmDelet = preferences.getBoolean("delet_bool", true);

            if(confirmDelet){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddTaskActivity.this);
                builder.setTitle(getText(R.string.attention));
                builder.setMessage(getText(R.string.confirm_delete_issue));

                builder.setPositiveButton(getString(android.R.string.ok), (dialog, id) -> {
                    delete();
                    dialog.dismiss();
                });

                builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.dismiss());

                builder.show();
            }else
                delete();
        });

        sqlHelper = new TasksDataBase(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            taskId = extras.getLong("id");

        if (taskId > 0) {
            userCursor = db.rawQuery("select * from " + TasksDataBase.TABLE + " where "
                    + TasksDataBase.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
            userCursor.moveToFirst();

            name_task.setText(userCursor.getString(1));
            text_edit_task.setText(userCursor.getString(2));

            userCursor.close();
        }
    }

    /**
     * Вызывается когда, нужно сохрнить в базу данных новую ячейку или для перезаписи текущего ячейки данных
     */
    public void save() {
        if(!validateTask()){
            return;
        } else {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            date = "Последнее изменение:" + " " + dateText + " " + timeText;

            ContentValues cv = new ContentValues();
            cv.put(TasksDataBase.COLUMN_TASK, name_task.getText().toString());
            cv.put(TasksDataBase.COLUMN_DATE_CREATE_TASK, date);

            if (taskId > 0)
                db.update(TasksDataBase.TABLE, cv, TasksDataBase.COLUMN_ID + "=" +
                        taskId, null);
            else
                db.insert(TasksDataBase.TABLE, null, cv);

            goHome();
        }
    }

    /**
     * Закрывается подключение к {@link TasksDataBase} и запускается {@link MyTasksActivity}
     */
    public void goHome() {
        db.close();
        Intent intent = new Intent(this, MyTasksActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /**
     *  @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateTask() {
        String check = name_task.getText().toString();
        if(check.isEmpty()){
            goHome();
            return false;
        } else
            return true;
    }

    /**
     * Выполням запрос на удадение ячейки из {@link TasksDataBase} и закрываем подключение
     */
    public void delete() {
        db.delete(TasksDataBase.TABLE, "_id = ?", new String[]{String.valueOf(taskId)});
        goHome();
    }

}