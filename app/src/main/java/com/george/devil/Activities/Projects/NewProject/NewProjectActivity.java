package com.george.devil.Activities.Projects.NewProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.george.devil.Activities.Main.Pupil.MainActivityPupil;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class NewProjectActivity extends AppCompatActivity {

    TextInputLayout textField_name_proj, textField_description_proj, textField_topic;
    SwitchMaterial switchPrivate_proj;

    Button next, skip;
    MaterialToolbar toolbar;
    MaterialAutoCompleteTextView topicAutoCompleteTextView;

    private static final String TAG = "newProjectActivity";

    /**
     * инициализируем активити, находим все фронтэнд элементы по id
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        toolbar = findViewById(R.id.topAppBar_new_project1);
        textField_name_proj = findViewById(R.id.textField_name_proj);
        textField_description_proj = findViewById(R.id.textField_description_proj);
        textField_topic = findViewById(R.id.textField_topic_new);
        switchPrivate_proj = findViewById(R.id.switchPrivate_proj);
        skip = findViewById(R.id.skip_btn);
        next = findViewById(R.id.next_btn);
        topicAutoCompleteTextView = findViewById(R.id.topic_auto_new_proj);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        cleanErrors();

        String[] items = getResources().getStringArray(R.array.categories_of_predmeti);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                NewProjectActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        final boolean[] private_piblic_project = {false};

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();

        switchPrivate_proj.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                private_piblic_project[0] = true;
                Log.i(TAG, "Переменная (true): " + true);
            } else {
                private_piblic_project[0] = false;
                Log.i(TAG, "Переменная (false): " + false);
            }
            editor.putBoolean("private_public_mail_proj", private_piblic_project[0]);

        });

        topicAutoCompleteTextView.setAdapter(adapter);

        next.setOnClickListener(v -> {

            if(!vaildateNameProject() | !validateDescriptionProject() | !validateTopicProject()) {
                return;
            } else {

                String nameProject = Objects.requireNonNull(textField_name_proj.getEditText()).getText().toString();
                String description = Objects.requireNonNull(textField_description_proj.getEditText()).getText().toString();
                String topic = Objects.requireNonNull(textField_topic.getEditText()).getText().toString();

                Log.i(TAG, "name = " + nameProject);
                Log.i(TAG, "description = " + description);
                Log.i(TAG, "topic = " + topic);

                editor.putString("nameMainProject", nameProject);
                editor.putString("descriptionMainProject", description);
                editor.putString("topicMainProject", topic);

                editor.apply();

                startActivity(new Intent(NewProjectActivity.this, NewProjectImagesActivity.class));

            }

        });
        skip.setOnClickListener(v -> startActivity(new Intent(this, MainActivityPupil.class)));
    }

    /**
     * @return
     * возвращает true/false для проверки поля {@link TextInputLayout} на пустоту. и отрисовывает ошибку.
     */
    public boolean vaildateNameProject() {
        String check = Objects.requireNonNull(textField_name_proj.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            textField_name_proj.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_name_proj.setError(null);
            return true;
        }

    }

    /**
     * @return
     * возвращает true/false для проверки поля {@link TextInputLayout} на пустоту. и отрисовывает ошибку.
     */
    public boolean validateDescriptionProject() {
        String check = Objects.requireNonNull(textField_description_proj.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            textField_description_proj.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_description_proj.setError(null);
            return true;
        }
    }

    /**
     * @return
     * возвращает true/false для проверки поля {@link TextInputLayout} на пустоту. и отрисовывает ошибку.
     */
    public boolean validateTopicProject() {
        String check = Objects.requireNonNull(textField_topic.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            textField_topic.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_topic.setError(null);
            return true;
        }
    }

    /**
     * Вызывается, когда нужно проверить {@link TextInputLayout} на пустоту и снять ошибку
     */
    public void cleanErrors() {
        Objects.requireNonNull(textField_name_proj.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_name_proj.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_description_proj.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_description_proj.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_topic.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_topic.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}