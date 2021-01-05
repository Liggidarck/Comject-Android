package com.george.devil.Activitys.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class EditProjectActivity extends AppCompatActivity {

    TextInputLayout textField_name_proj_edit, textField_description_proj_edit, textField_topic_edit;

    private static final String TAG = "EditprojectActivity";

    SwitchMaterial switchPrivate_proj_EDIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        textField_name_proj_edit = findViewById(R.id.textField_name_proj_edit);
        textField_description_proj_edit = findViewById(R.id.textField_description_proj_edit);
        textField_topic_edit = findViewById(R.id.textField_topic_edit);
        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.topic_auto_edit_proj);
        switchPrivate_proj_EDIT = findViewById(R.id.switch_edit_proj);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_project = sharedPreferences.getString("nameMainProject", "empty_main_project");
        String description = sharedPreferences.getString("descriptionMainProject", "empty_absic_description");
        String topik = sharedPreferences.getString("topicMainProject", "topic_main_proj_empty");
        boolean private_public_proj_edit = sharedPreferences.getBoolean("private_public_mail_proj", false);

        textField_name_proj_edit.getEditText().setText(name_project);
        textField_description_proj_edit.getEditText().setText(description);
        textField_topic_edit.getEditText().setText(topik);
        switchPrivate_proj_EDIT.setChecked(private_public_proj_edit);

        String[] items = new String[] {
                "Biology", "Chemistry", "Economics", "English",
                "Engineering/Construction","Geography", "History",
                "IT", "Literature", "Math", "Physics", "Politics",
                "Sports", "Social studies", "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                EditProjectActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        autoCompleteTextView.setAdapter(adapter);

        Button next = findViewById(R.id.next_edit_btn);
        next.setOnClickListener(v -> {
            BIG_DATA();
            startActivity(new Intent(EditProjectActivity.this, EditProjectImagesActivity.class));
        });

        MaterialToolbar topAppBar_edit_proj1 = findViewById(R.id.topAppBar_edit_proj1);
        topAppBar_edit_proj1.setNavigationOnClickListener(v -> {
            BIG_DATA();
            startActivity(new Intent(EditProjectActivity.this, MainProjectActivity.class));
        });

        ExtendedFloatingActionButton fab_save_edit_proj = findViewById(R.id.fab_save_edit_proj);
        fab_save_edit_proj.setOnClickListener(v -> {
            BIG_DATA();
            startActivity(new Intent(EditProjectActivity.this, MainProjectActivity.class));
        });

    }

    public void BIG_DATA() {
        String nameProj = textField_name_proj_edit.getEditText().getText().toString();
        String descriptionProj = textField_description_proj_edit.getEditText().getText().toString();
        String topicPjaj = textField_topic_edit.getEditText().getText().toString();
        boolean private_public_proj;


        if(switchPrivate_proj_EDIT.isChecked()) {
            private_public_proj = true;
        } else {
            private_public_proj = false;
        }


        validateSave(nameProj, descriptionProj, topicPjaj, private_public_proj);
    }

    public boolean vaildateNameProject(String checkNameProject) {
//        String check = Objects.requireNonNull(textField_name_proj_edit.getEditText()).getText().toString().trim();
        if(checkNameProject.isEmpty()){
            textField_name_proj_edit.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_name_proj_edit.setError(null);
            return true;
        }
    }

    public boolean validateDescriptionProject(String chrckDescriptionProj) {
        //String check = Objects.requireNonNull(textField_description_proj_edit.getEditText()).getText().toString().trim();
        if(chrckDescriptionProj.isEmpty()){
            textField_description_proj_edit.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_description_proj_edit.setError(null);
            return true;
        }

    }

    public boolean validateTopicProject(String TopicProj) {
    //    String check = Objects.requireNonNull(textField_topic_edit.getEditText()).getText().toString().trim();
        if(TopicProj.isEmpty()){
            textField_topic_edit.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_topic_edit.setError(null);
            return true;
        }
    }

    public void validateSave (String name, String description, String topic, boolean private_public_proj) {

        if(!vaildateNameProject(name) | !validateDescriptionProject(description) | !validateTopicProject(topic)){
            return;
        } else {
            Log.i(TAG, "проверка прошла успешно!");
            saveData(name, description, topic, private_public_proj);
        }

    }

    public void saveData(String nameProject, String descriptionProject, String topicProject, boolean private_public_pr) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        sharedPreferences.edit().remove("nameMainProject").apply();
        sharedPreferences.edit().remove("descriptionMainProject").apply();
        sharedPreferences.edit().remove("topicMainProject").apply();
        sharedPreferences.edit().remove("private_public_mail_proj").apply();
        sharedPreferences.edit().remove("private_public_mail_proj").apply();

        sharedPreferences.edit().putString("nameMainProject", nameProject).apply();
        sharedPreferences.edit().putString("descriptionMainProject", descriptionProject).apply();
        sharedPreferences.edit().putString("topicMainProject", topicProject).apply();
        sharedPreferences.edit().putBoolean("private_public_mail_proj", private_public_pr).apply();
    }

    @Override
    public void onBackPressed() {
        BIG_DATA();
        startActivity(new Intent(EditProjectActivity.this, MainProjectActivity.class));
    }
}