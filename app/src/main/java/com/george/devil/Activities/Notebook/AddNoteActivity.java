package com.george.devil.Activities.Notebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.george.devil.Activities.Changes.ChangesActivity;
import com.george.devil.Activities.Main.Pupil.MainActivityPupil;
import com.george.devil.BottomSheets.BottomSheetNotes;
import com.george.devil.DataBases.ChangesDataBase;
import com.george.devil.DataBases.NotesDatabase;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity implements BottomSheetNotes.BottomSheetListener {

    EditText nameBox;
    EditText noteBox;

    NotesDatabase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long notesId = 0;

    TextView dateView;
    String dateFull;

    View theme_view_add_note;
    MaterialToolbar toolbar;
    CardView more_bottom;

    private static final String TAG = "addNote";
    String checkTheme = "Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = findViewById(R.id.topAppBar_add_note);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> save());

        nameBox = findViewById(R.id.name_note);
        noteBox = findViewById(R.id.note_text);
        dateView = findViewById(R.id.date_textView);
        theme_view_add_note = findViewById(R.id.theme_view_add_note);
        more_bottom = findViewById(R.id.more_bottom);

        sqlHelper = new NotesDatabase(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            notesId = extras.getLong("id");

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        dateFull = getText(R.string.last_modified) + ":" + " " + dateText + " " + timeText;
        dateView.setText(dateFull);

        if (notesId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + NotesDatabase.TABLE + " where " +
                    NotesDatabase.COLUMN_ID + "=?", new String[]{String.valueOf(notesId)});
            userCursor.moveToFirst();

            nameBox.setText(userCursor.getString(1));
            noteBox.setText(userCursor.getString(2));
            dateView.setText(userCursor.getString(3));
            checkTheme = userCursor.getString(4);
            Log.i(TAG, "получил значение темы - " + checkTheme);

            userCursor.close();
        }

        if(checkTheme.equals("Default")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FAFAFA"));
            toolbar.setBackgroundColor(Color.parseColor("#FAFAFA"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FAFAFA"));
        }

        if(checkTheme.equals("Red")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FF8C8C"));
            toolbar.setBackgroundColor(Color.parseColor("#FF8C8C"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FF8C8C"));
        }

        if(checkTheme.equals("Orange")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FFB661"));
            toolbar.setBackgroundColor(Color.parseColor("#FFB661"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FFB661"));
        }

        if(checkTheme.equals("Yellow")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FFD850"));
            toolbar.setBackgroundColor(Color.parseColor("#FFD850"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FFD850"));
        }

        if(checkTheme.equals("Green")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#7AE471"));
            toolbar.setBackgroundColor(Color.parseColor("#7AE471"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#7AE471"));
        }

        if(checkTheme.equals("Light Green")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#56E0C7"));
            toolbar.setBackgroundColor(Color.parseColor("#56E0C7"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#56E0C7"));
        }

        if(checkTheme.equals("Ligth Blue")){
            theme_view_add_note.setBackgroundColor(Color.parseColor("#6CD3FF"));
            toolbar.setBackgroundColor(Color.parseColor("#6CD3FF"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#6CD3FF"));
        }

        if(checkTheme.equals("Blue")) {
            theme_view_add_note.setBackgroundColor(Color.parseColor("#819CFF"));
            toolbar.setBackgroundColor(Color.parseColor("#819CFF"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#819CFF"));
        }

        if(checkTheme.equals("violet")) {
            theme_view_add_note.setBackgroundColor(Color.parseColor("#DD8BFA"));
            toolbar.setBackgroundColor(Color.parseColor("#DD8BFA"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#DD8BFA"));
        }

        if(checkTheme.equals("Pink")) {
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FF6CA1"));
            toolbar.setBackgroundColor(Color.parseColor("#FF6CA1"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FF6CA1"));
        }

        if(checkTheme.equals("Gray")) {
            theme_view_add_note.setBackgroundColor(Color.parseColor("#C4C4C4"));
            toolbar.setBackgroundColor(Color.parseColor("#C4C4C4"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#C4C4C4"));
        }


        ImageView more = findViewById(R.id.more_notes);
        more.setOnClickListener(v -> {
            BottomSheetNotes bottomSheetNotes = new BottomSheetNotes();

            Bundle bundle = new Bundle();
            bundle.putString("theme", checkTheme );
            bottomSheetNotes.setArguments(bundle);

            bottomSheetNotes.show(getSupportFragmentManager(), "BottomSheetNotes");
        });
    }

    /**
     * Метод для определения нажатой кнопки в {@link BottomSheetNotes}
     * @param text - полученые данные о кнопке от {@link BottomSheetNotes}
     */
    @Override
    public void onButtonClicked(String text) {
        Log.i(TAG, "" + text);
        CoordinatorLayout coordinatorLayout = findViewById(R.id.chak);

        if(text.equals("Button delete clicked")) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            boolean confirmDelet = preferences.getBoolean("delet_bool", true);

            if(confirmDelet){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                builder.setTitle(getText(R.string.attention));
                builder.setMessage(getText(R.string.confirm_delete_note));

                builder.setPositiveButton(getString(android.R.string.ok), (dialog, id) -> {
                    delete();
                    dialog.dismiss();
                });

                builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.dismiss());

                builder.show();

            }else {
                delete();
            }

        }

        if(text.equals("Button copy clicked")){
            String copy = noteBox.getText().toString();
            if(copy.isEmpty()){
                Snackbar.make(coordinatorLayout, getText(R.string.empty_note_cant_copied), Snackbar.LENGTH_SHORT).setAction("done", null).show();
            } else {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", copy);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Snackbar.make(coordinatorLayout, getText(R.string.note_copied), Snackbar.LENGTH_SHORT).setAction("done", null).show();
            }
        }

        if(text.equals("Button share clicked")) {
            String copy = noteBox.getText().toString();
            if (copy.isEmpty()) {
                Snackbar.make(coordinatorLayout, getText(R.string.empty_note_cant_shared), Snackbar.LENGTH_SHORT).setAction("done", null).show();
            } else {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, copy + "");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        }

        if(text.equals("Default")){
            checkTheme = "Default";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FAFAFA"));
            toolbar.setBackgroundColor(Color.parseColor("#FAFAFA"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FAFAFA"));
        }

        if(text.equals("Red")){
            checkTheme = "Red";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FF8C8C"));
            toolbar.setBackgroundColor(Color.parseColor("#FF8C8C"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FF8C8C"));
        }

        if(text.equals("Orange")){
            checkTheme = "Orange";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FFB661"));
            toolbar.setBackgroundColor(Color.parseColor("#FFB661"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FFB661"));
        }

        if(text.equals("Yellow")){
            checkTheme = "Yellow";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FFD850"));
            toolbar.setBackgroundColor(Color.parseColor("#FFD850"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FFD850"));
        }

        if(text.equals("Green")){
            checkTheme = "Green";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#7AE471"));
            toolbar.setBackgroundColor(Color.parseColor("#7AE471"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#7AE471"));
        }

        if(text.equals("Light Green")){
            checkTheme = "Light Green";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#56E0C7"));
            toolbar.setBackgroundColor(Color.parseColor("#56E0C7"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#56E0C7"));
        }

        if(text.equals("Ligth Blue")){
            checkTheme = "Ligth Blue";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#6CD3FF"));
            toolbar.setBackgroundColor(Color.parseColor("#6CD3FF"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#6CD3FF"));
        }

        if(text.equals("Blue")) {
            checkTheme = "Blue";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#819CFF"));
            toolbar.setBackgroundColor(Color.parseColor("#819CFF"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#819CFF"));
        }

        if(text.equals("violet")) {
            checkTheme = "violet";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#DD8BFA"));
            toolbar.setBackgroundColor(Color.parseColor("#DD8BFA"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#DD8BFA"));
        }

        if(text.equals("Pink")) {
            checkTheme = "Pink";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#FF6CA1"));
            toolbar.setBackgroundColor(Color.parseColor("#FF6CA1"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#FF6CA1"));
        }

        if(text.equals("Gray")) {
            checkTheme = "Gray";
            theme_view_add_note.setBackgroundColor(Color.parseColor("#C4C4C4"));
            toolbar.setBackgroundColor(Color.parseColor("#C4C4C4"));
            more_bottom.setCardBackgroundColor(Color.parseColor("#C4C4C4"));
        }

    }

    /**
     * Вызывается когда, нужно сохрнить в базу данных новую ячейку или для перезаписи текущего ячейки данных
     */
    public void save() {
        if(!validateNameNote()) {
            return;
        } else {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            dateFull = getText(R.string.last_modified) + ":" + " " + dateText + " " + timeText;

            ContentValues cv = new ContentValues();
            cv.put(NotesDatabase.COLUMN_NAME_NOTE, nameBox.getText().toString());
            cv.put(NotesDatabase.COLUMN_NOTE, noteBox.getText().toString());
            cv.put(NotesDatabase.COLUMN_DATE, dateFull);
            cv.put(NotesDatabase.COLUMN_THEME, checkTheme);

            if (notesId > 0)
                db.update(NotesDatabase.TABLE, cv, NotesDatabase.COLUMN_ID + "=" + notesId, null);
            else
                db.insert(NotesDatabase.TABLE, null, cv);

            goHome();
        }
    }

    /**
     * Выполням запрос на удадение ячейки из {@link NotesDatabase} и закрываем подключение
     */
    public void delete() {
        db.delete(NotesDatabase.TABLE, "_id = ?", new String[]{String.valueOf(notesId)});
        goHome();
    }

    /**
     * Закрывается подключение к {@link NotesDatabase} и запускается {@link ChangesActivity}
     */
    public void goHome(){
        db.close();

        Intent intent = new Intent(this, MainActivityPupil.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    public boolean validateNameNote() {
        String check_name = nameBox.getText().toString().trim();
        String check_note = noteBox.getText().toString().trim();

        if(check_name.isEmpty() && check_note.isEmpty()) {
            Intent intent = new Intent(this, MainActivityPupil.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        save();
    }
}