package com.george.devil.Activities.Projects.EditsProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class FindMoreImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more_images);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_pic_images);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        String[] categories = { "Biology", "Chemistry", "Economics", "English ", "Engineering/Construction", "Geography", "History",
                "IT", "Literature", "Math", "Physics", "Politics", "Sports", "Social studies", "Other" };

            ListView lvMain = findViewById(R.id.categories_images_list);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, categories);

            lvMain.setAdapter(adapter);


    }
}