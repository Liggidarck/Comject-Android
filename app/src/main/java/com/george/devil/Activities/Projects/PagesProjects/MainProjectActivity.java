package com.george.devil.Activities.Projects.PagesProjects;

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

import com.george.devil.Activities.Changes.ChangesActivity;
import com.george.devil.Activities.Main.Pupil.CommentsActivity;
import com.george.devil.Activities.Issues.IssuesActivity;
import com.george.devil.Activities.Main.Pupil.MainActivityPupil;
import com.george.devil.Activities.Projects.EditsProject.EditProjectActivity;
import com.george.devil.Activities.Projects.InformationFromTeacherActivity;
import com.george.devil.BottomSheets.BottomSheetHastags;
import com.george.devil.Fragments.Pupil.likes_fragment;
import com.george.devil.Fragments.Pupil.lock_fragment;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MainProjectActivity extends AppCompatActivity implements BottomSheetHastags.StateListenerHashtag {

    TextView main_title_project_full, main_description_project_full, main_hastags, main_title_prokect_secondaty, main_description_proj;
    MaterialToolbar toolbar;

    Button edit_main_project, publish_main_prokect;
    RelativeLayout issues, changes, teacher, commets, hast ;

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
        toolbar                        = findViewById(R.id.topAppBar_main_proj);
        edit_main_project              = findViewById(R.id.edit_main_project);
        issues                         = findViewById(R.id.issues_layou);
        changes                        = findViewById(R.id.changes_layout);
        teacher                        = findViewById(R.id.information_teather_layout);
        commets                        = findViewById(R.id.comments_layout);
        hast                           = findViewById(R.id.hashtah_layour);
        publish_main_prokect           = findViewById(R.id.publish_main_prokect);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, MainActivityPupil.class)));
        edit_main_project.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, EditProjectActivity.class)));
        issues.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, IssuesActivity.class)));
        changes.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, ChangesActivity.class)));
        teacher.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, InformationFromTeacherActivity.class)));
        commets.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, CommentsActivity.class)));
        publish_main_prokect.setOnClickListener(v -> startActivity(new Intent(MainProjectActivity.this, PublishProjectActivity.class)));

        hast.setOnClickListener(v -> {
            BottomSheetHastags hastags = new BottomSheetHastags();
            hastags.show(getSupportFragmentManager(), "BottomSheetHas");
        });

        SharedPreferences sharedPrefsMainProject = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String titleMain = sharedPrefsMainProject.getString("nameMainProject", "empty_main_project");
        String description = sharedPrefsMainProject.getString("descriptionMainProject", "empty_absic_description");
        String main_descroption = sharedPrefsMainProject.getString("description_main", "empty_description_main" );
        String hastags = sharedPrefsMainProject.getString("hastags", "Edit project hastags!");
        boolean private_public_pr = sharedPrefsMainProject.getBoolean("private_public_mail_proj", false);

        Fragment public_private_proj;
        if(private_public_pr)
            public_private_proj = new lock_fragment();
        else
            public_private_proj = new likes_fragment();

        main_title_project_full.setText(titleMain);
        main_description_project_full.setText(description);
        main_hastags.setText(hastags);
        main_title_prokect_secondaty.setText(titleMain);
        main_description_proj.setText(main_descroption);
        getSupportFragmentManager().beginTransaction().replace(R.id.private_public_fragme_main_proj, public_private_proj).commit();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainProjectActivity.this, MainActivityPupil.class));
    }

    /**
     * @param textHast получаем текст из {@link BottomSheetHastags} и устанавливаем в {@link TextView}
     */
    @Override
    public void hastagsText(String textHast) {
        Log.i(TAG, textHast);
        main_hastags.setText(textHast);
    }
}