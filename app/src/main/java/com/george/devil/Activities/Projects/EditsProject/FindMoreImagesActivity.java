package com.george.devil.Activities.Projects.EditsProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.george.devil.Activities.Issues.AddIssue;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class FindMoreImagesActivity extends AppCompatActivity {

    private static final String TAG = "FidMore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more_images);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_pic_images);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        String[] categories = getResources().getStringArray(R.array.categories_of_predmeti);

        ListView lvMain = findViewById(R.id.categories_images_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);

        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, String.valueOf(id));
            Intent intent = new Intent(FindMoreImagesActivity.this, ImagesActivity.class);
            intent.putExtra("id_image", id);
            startActivity(intent);
        });


    }
}