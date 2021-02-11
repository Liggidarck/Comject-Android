package com.george.devil.Fragments.Pupil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.george.devil.Activities.Notebook.AddNoteActivity;
import com.george.devil.DataBases.NotesDatabase;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragmentNote extends Fragment {

    NotesDatabase databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;


    ListView notesList;
    MaterialToolbar toolbar;
    FloatingActionButton add;
    View empty;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        toolbar = view.findViewById(R.id.toolbar_notebook);
        add = view.findViewById(R.id.add_note);
        notesList = view.findViewById(R.id.notebook_list);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        add.setOnClickListener(v -> startActivity(new Intent(fragmentNote.this.getActivity(), AddNoteActivity.class)));

        notesList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(requireActivity().getApplicationContext(), AddNoteActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        databaseHelper = new NotesDatabase(requireContext().getApplicationContext());

        empty = view.findViewById(R.id.empty_layout);
        notesList.setEmptyView(empty);

        return view;
    }

    /**
     * Подключаемся к {@link NotesDatabase} для отрисовки сохраненых данных в базе данных
     */
    @Override
    public void onResume(){
        super.onResume();

        db = databaseHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "+
                NotesDatabase.TABLE, null);

        String[] headers = new String[] {NotesDatabase.COLUMN_NAME_NOTE,
                NotesDatabase.COLUMN_NOTE};

        userAdapter = new SimpleCursorAdapter(fragmentNote.this.getActivity(), android.R.layout.simple_list_item_2, userCursor, headers,
                new int[] {android.R.id.text1, android.R.id.text2}, 0);
        notesList.setAdapter(userAdapter);

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

}
