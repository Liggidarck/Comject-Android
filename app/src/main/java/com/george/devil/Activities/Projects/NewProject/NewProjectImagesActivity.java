package com.george.devil.Activities.Projects.NewProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.george.devil.Activities.Main.Pupil.MainActivityPupil;
import com.george.devil.Activities.Projects.EditsProject.FindMoreImagesActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class NewProjectImagesActivity extends AppCompatActivity {

    TextView project_main_name_images;

    EditText description_main_text;

    public static final String TAG_1 = "NewProjectImages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_images);

        project_main_name_images = findViewById(R.id.project_main_name_images);
        description_main_text    = findViewById(R.id.description_main_text);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_new_project2);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        SharedPreferences sharedPr = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_project = sharedPr.getString("nameMainProject", "empty_main_project");
        boolean private_public_pr = sharedPr.getBoolean("private_public_mail_proj", false);

        SharedPreferences.Editor editor = sharedPr.edit();

        /** СПАСИБО, БЛЯТЬ, ВСЕМ БОГАМ ЗА ТО, ЧТО ЭТА ХУЙНЯ {@link private_public_pr} РАБОТАЕТ!!!*/
        Log.i(TAG_1, "BOOLEAN = " + private_public_pr);

        project_main_name_images.setText(name_project);

        Button done = findViewById(R.id.done_btn);
        done.setOnClickListener(v -> {
             String description_main = description_main_text.getText().toString();

             if(description_main.isEmpty()){
                 description_main = "This will be the most important text for your project";
             }

             editor.putString("description_main", description_main);
             editor.apply();

             startActivity(new Intent(NewProjectImagesActivity.this, MainActivityPupil.class));

        });

        Button find_images = findViewById(R.id.find_more_images_btn);
        find_images.setOnClickListener(v -> startActivity(new Intent(NewProjectImagesActivity.this, FindMoreImagesActivity.class)));
    }

}