package com.george.devil.Activities.Projects.EditsProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.george.devil.Activities.Projects.PagesProjects.MainProjectActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

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

        Objects.requireNonNull(textField_name_proj_edit.getEditText()).setText(name_project);
        Objects.requireNonNull(textField_description_proj_edit.getEditText()).setText(description);
        Objects.requireNonNull(textField_topic_edit.getEditText()).setText(topik);
        switchPrivate_proj_EDIT.setChecked(private_public_proj_edit);

        String[] items = getResources().getStringArray(R.array.categories_of_predmeti);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                EditProjectActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        autoCompleteTextView.setAdapter(adapter);

        Button next = findViewById(R.id.next_edit_btn);
        next.setOnClickListener(v -> BIG_DATA(1));

        MaterialToolbar topAppBar_edit_proj1 = findViewById(R.id.topAppBar_edit_proj1);
        topAppBar_edit_proj1.setNavigationOnClickListener(v -> BIG_DATA(2));

        ExtendedFloatingActionButton fab_save_edit_proj = findViewById(R.id.fab_save_edit_proj);
        fab_save_edit_proj.setOnClickListener(v -> BIG_DATA(3));

    }

    public void BIG_DATA(int id_btn) {
        String nameProj = Objects.requireNonNull(textField_name_proj_edit.getEditText()).getText().toString();
        String descriptionProj = Objects.requireNonNull(textField_description_proj_edit.getEditText()).getText().toString();
        String topicPjaj = Objects.requireNonNull(textField_topic_edit.getEditText()).getText().toString();
        boolean private_public_proj;

        private_public_proj = switchPrivate_proj_EDIT.isChecked();

        validateSave(nameProj, descriptionProj, topicPjaj, private_public_proj, id_btn);
    }

    public void validateSave (String name, String description, String topic, boolean private_public_proj, int id_bt) {

        if(!vaildateNameProject(name) | !validateDescriptionProject(description) | !validateTopicProject(topic)){
            return;
        } else {
            Log.i(TAG, "проверка прошла успешно!");
            saveData(name, description, topic, private_public_proj, id_bt);
        }

    }

    public void saveData(String nameProject, String descriptionProject, String topicProject, boolean private_public_pr, int id) {
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

        if(id == 1) {
            Log.i(TAG, topicProject);
            Intent intet = new Intent(EditProjectActivity.this, EditProjectImagesActivity.class);
            intet.putExtra("topic_image", topicProject);
            startActivity(intet);
        }

        if(id == 2 || id == 3 || id == 4)
            startActivity(new Intent(EditProjectActivity.this, MainProjectActivity.class));

    }

    public boolean vaildateNameProject(String checkNameProject) {
        if(checkNameProject.isEmpty()){
            textField_name_proj_edit.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_name_proj_edit.setError(null);
            return true;
        }
    }

    public boolean validateDescriptionProject(String chrckDescriptionProj) {
        if(chrckDescriptionProj.isEmpty()){
            textField_description_proj_edit.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_description_proj_edit.setError(null);
            return true;
        }

    }

    public boolean validateTopicProject(String TopicProj) {
        if(TopicProj.isEmpty()) {
            textField_topic_edit.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_topic_edit.setError(null);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        BIG_DATA(4);
    }
}