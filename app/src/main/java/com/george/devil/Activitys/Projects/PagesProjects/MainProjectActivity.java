package com.george.devil.Activitys.Projects.PagesProjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.george.devil.Activitys.Changes.ChangesActivity;
import com.george.devil.Activitys.Main.Pupil.CommentsActivity;
import com.george.devil.Activitys.Issues.IsuuesActivity;
import com.george.devil.Activitys.Main.Pupil.MainActivityPupil;
import com.george.devil.Activitys.Projects.EditsProject.EditProjectActivity;
import com.george.devil.Activitys.Projects.InformationFromTeacherActivity;
import com.george.devil.BottomSheets.BottomSheetHastags;
import com.george.devil.Fragments.Pupil.likes_fragment;
import com.george.devil.Fragments.Pupil.lock_fragment;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MainProjectActivity extends AppCompatActivity implements BottomSheetHastags.StateListenerHastags {

    TextView main_title_project_full, main_description_project_full, main_hastags, main_title_prokect_secondaty, main_description_proj;

    static final String TAG = "MainProjectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_project);

        main_title_project_full        =  findViewById(R.id.main_title_project_full);
        main_description_project_full  =  findViewById(R.id.main_description_project_full);
        main_hastags                   =  findViewById(R.id.main_hastags);
        main_title_prokect_secondaty   =  findViewById(R.id.main_title_prokect_secondaty);
        main_description_proj          =  findViewById(R.id.main_description_proj);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_main_proj);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, MainActivityPupil.class)));

        Button edit_main_project = findViewById(R.id.edit_main_project);
        edit_main_project.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, EditProjectActivity.class)));

        RelativeLayout issues = findViewById(R.id.issues_layou);
        issues.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, IsuuesActivity.class)));

        RelativeLayout changes = findViewById(R.id.changes_layout);
        changes.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, ChangesActivity.class)));

        RelativeLayout teacher = findViewById(R.id.information_teather_layout);
        teacher.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, InformationFromTeacherActivity.class)));

        RelativeLayout commets = findViewById(R.id.comments_layout);
        commets.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, CommentsActivity.class)));

        RelativeLayout hast = findViewById(R.id.hashtah_layour);
        hast.setOnClickListener(v -> {
            BottomSheetHastags hastags = new BottomSheetHastags();
            hastags.show(getSupportFragmentManager(), "BottomSheetHas");
        });

        Button publish_main_prokect = findViewById(R.id.publish_main_prokect);
        publish_main_prokect.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, PublishProjectActivity.class)));

        SharedPreferences sharedPrefsMainProject = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String titleMain = sharedPrefsMainProject.getString("nameMainProject", "empty_main_project");
        String description = sharedPrefsMainProject.getString("descriptionMainProject", "empty_absic_description");
        String topic = sharedPrefsMainProject.getString("topicMainProject", "topic_main_proj_empty");
        String main_descroption = sharedPrefsMainProject.getString("description_main", "empty_description_main" );
        String hastags = sharedPrefsMainProject.getString("hastags", "Edit project hastags!");
        boolean private_public_pr = sharedPrefsMainProject.getBoolean("private_public_mail_proj", false);

        main_title_project_full.setText(titleMain);
        main_description_project_full.setText(description);

        main_hastags.setText(hastags);

        main_title_prokect_secondaty.setText(titleMain);
        main_description_proj.setText(main_descroption);

        Fragment public_private_proj;
        if(private_public_pr)
            public_private_proj = new lock_fragment();
        else
            public_private_proj = new likes_fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.private_public_fragme_main_proj, public_private_proj).commit();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainProjectActivity.this, MainActivityPupil.class));
    }

    @Override
    public void hastagsText(String textHast) {
        Log.i(TAG, textHast);
        main_hastags.setText(textHast);
    }
}