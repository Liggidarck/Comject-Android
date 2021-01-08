package com.george.devil.Activitys.Projects.PagesProjects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PublishProjectActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    TextView title_name_project_publish, description_title_project_publish, hastags_titile_project_publish;

    static final String TAG = "PublishProjectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_project);

        title_name_project_publish = findViewById(R.id.title_name_project_publish);
        description_title_project_publish = findViewById(R.id.description_title_project_publish);
        hastags_titile_project_publish = findViewById(R.id.hastags_titile_project_publish);

        SharedPreferences sharedPrefsPublishActivity = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String title_project = sharedPrefsPublishActivity.getString("nameMainProject", "empty_main_project");
        String description_project = sharedPrefsPublishActivity.getString("descriptionMainProject", "empty_absic_description");
        String hastags_projects = sharedPrefsPublishActivity.getString("hastags", "Edit project hastags!");

        title_name_project_publish.setText(title_project);
        description_title_project_publish.setText(description_project);
        hastags_titile_project_publish.setText(hastags_projects);

        toolbar = findViewById(R.id.topAppBar_new_post);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_publish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.item_share)
            Log.i(TAG, "Share Item Clicked!");

        return super.onOptionsItemSelected(item);
    }
}