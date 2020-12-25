package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class EditProjectImagesActivity extends AppCompatActivity {

    TextInputLayout textField_name_proj_edit_images;
    EditText main_description_proj_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project_images);

        textField_name_proj_edit_images = findViewById(R.id.textField_name_proj_edit_images);
        main_description_proj_edit = findViewById(R.id.main_description_proj_edit);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_project = sharedPreferences.getString("nameMainProject", "empty_main_project");
        String description = sharedPreferences.getString("description_main", "empty_description_main");

        textField_name_proj_edit_images.getEditText().setText(name_project);
        main_description_proj_edit.setText(description);

        ExtendedFloatingActionButton save_btn_images_edit = findViewById(R.id.save_btn_images_edit);
        save_btn_images_edit.setOnClickListener(v -> {
            saveData();
            startActivity(new Intent(EditProjectImagesActivity.this, MainProjectActivity.class));

        });

        MaterialToolbar topAppBar_edit_proj1 = findViewById(R.id.topAppBar_edit_proj1);
        topAppBar_edit_proj1.setNavigationOnClickListener(v -> {
            saveData();
            startActivity(new Intent(EditProjectImagesActivity.this, EditProjectActivity.class));
        });

    }

    @Override
    public void onBackPressed() {
        saveData();
        startActivity(new Intent(EditProjectImagesActivity.this, EditProjectActivity.class));
    }

    public void saveData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String description_main = main_description_proj_edit.getText().toString();

        if(description_main.isEmpty()){
            description_main = "This will be the most important text for your project";
        }

        sharedPreferences.edit().remove("description_main").apply();
        sharedPreferences.edit().putString("description_main", description_main).apply();
    }

}