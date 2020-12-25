package com.george.devil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity implements BottomSheetNotes.BottomSheetListener{

    EditText nameBox;
    EditText noteBox;

    NotesDatabase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long notesId = 0;

    TextView dateView;
    String dateFull;

    private static final String TAG = "addNote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_add_note);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> save());


        nameBox = findViewById(R.id.name_note);
        noteBox = findViewById(R.id.note_text);
        dateView = findViewById(R.id.date_textView);


        sqlHelper = new NotesDatabase(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            notesId = extras.getLong("id");
        }

        ImageView more = findViewById(R.id.more_notes);
        more.setOnClickListener(v -> {
            BottomSheetNotes bottomSheetNotes = new BottomSheetNotes();
            bottomSheetNotes.show(getSupportFragmentManager(), "BottomSheetNotes");
        });

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        dateFull = "Последнее изменение:" + " " + dateText + " " + timeText;
        dateView.setText(dateFull);

        // если 0, то добавление
        if (notesId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + NotesDatabase.TABLE + " where " +
                    NotesDatabase.COLUMN_ID + "=?", new String[]{String.valueOf(notesId)});
            userCursor.moveToFirst();

            nameBox.setText(userCursor.getString(1));
            noteBox.setText(userCursor.getString(2));
            dateView.setText(userCursor.getString(3));

            userCursor.close();
        }
    }

    public void save() {
        if(!validateNameNote()){
            /*
            Сюда нужно было впихнуть заглушку, а то не работало бы.
             */

            return;
        } else {

            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            dateFull = "Последнее изменение:" + " " + dateText + " " + timeText;

            ContentValues cv = new ContentValues();
            cv.put(NotesDatabase.COLUMN_NAME_NOTE, nameBox.getText().toString());
            cv.put(NotesDatabase.COLUMN_NOTE, noteBox.getText().toString());
            cv.put(NotesDatabase.COLUMN_DATE, dateFull);

            if (notesId > 0)
                db.update(NotesDatabase.TABLE, cv, NotesDatabase.COLUMN_ID + "=" + String.valueOf(notesId), null);
            else
                db.insert(NotesDatabase.TABLE, null, cv);

            goHome();
        }

    }

    @Override
    public void onButtonClicked(String text) {
        Log.i(TAG, ""+text);
        CoordinatorLayout coordinatorLayout = findViewById(R.id.chak);


        if(text.equals("Button delete clicked")) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            boolean confirmDelet = preferences.getBoolean("delet_bool", true);

            if(confirmDelet){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                builder.setTitle("Внимание");
                builder.setMessage("Вы действительно хотите удалить заметку?");

                builder.setPositiveButton("Ок", (dialog, id) -> {
                    delete();
                    dialog.dismiss();
                });

                builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());

                builder.show();

            }else {
                delete();
            }

        }

        if(text.equals("Button copy clicked")){
            String copy = noteBox.getText().toString();

            if(copy.isEmpty()){
                Snackbar.make(coordinatorLayout, "Empty Note cant copied!", Snackbar.LENGTH_SHORT).setAction("done", null).show();
            } else {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", copy);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Snackbar.make(coordinatorLayout, "Note copied!", Snackbar.LENGTH_SHORT).setAction("done", null).show();
            }
        }

        if(text.equals("Button share clicked")) {
            String copy = noteBox.getText().toString();

            if (copy.isEmpty()) {
                Snackbar.make(coordinatorLayout, "Empty Note cant shared!", Snackbar.LENGTH_SHORT).setAction("done", null).show();
            } else {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, copy + "");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        }


    }

    public void delete() {
        db.delete(NotesDatabase.TABLE, "_id = ?", new String[]{String.valueOf(notesId)});
        goHome();
    }

    public void goHome(){
        db.close();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        save();
    }

    public boolean validateNameNote() {
        String check_name = nameBox.getText().toString().trim();
        String check_note = noteBox.getText().toString().trim();

        if(check_name.isEmpty() && check_note.isEmpty()) {
            String empty = "Empty boxes";
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("empty", empty);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            return false;
        } else {
            return true;
        }

    }


}