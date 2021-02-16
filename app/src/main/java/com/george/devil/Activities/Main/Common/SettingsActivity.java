package com.george.devil.Activities.Main.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.george.devil.Activities.Main.LoginActivity;
import com.george.devil.Activities.Main.Pupil.EditProfilePupil;
import com.george.devil.Activities.Main.Teacher.EditProfileTeacher;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class SettingsActivity extends AppCompatActivity {

    TextView name_profile_settings, email_profile_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        name_profile_settings = findViewById(R.id.name_profile_settings);
        email_profile_settings = findViewById(R.id.email_profile_settings);
        Button sign_out = findViewById(R.id.btn_sign_out);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_teather = sharedPreferences.getString("full_name_teather", "full_name_teather_empty");
        String email_teather = sharedPreferences.getString("email_teather", "password_teather_empty");

        String name_user = sharedPreferences.getString("full_name", "empty_user_name");
        String username = sharedPreferences.getString("username", "empty_correct_username");

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_setting);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (!name_teather.equals("full_name_teather_empty")) {
            name_profile_settings.setText(name_teather);
            email_profile_settings.setText(email_teather);
        }

        if (!name_user.equals("empty_user_name")) {
            name_profile_settings.setText(name_user);
            email_profile_settings.setText(username);
        }

        Button edit_account = findViewById(R.id.btn_edit_account);
        edit_account.setOnClickListener(v -> {
            if (!name_user.equals("empty_user_name"))
                startActivity(new Intent(SettingsActivity.this, EditProfilePupil.class));

            if (!name_teather.equals("full_name_teather_empty"))
                startActivity(new Intent(SettingsActivity.this, EditProfileTeacher.class));
        });

        sign_out.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
            builder.setTitle("Внимание");
            builder.setMessage("Вы действительно хотите выйти?");

            builder.setPositiveButton("Выйти", (dialog, id) -> {

                if (!name_user.equals("empty_user_name")) {
                    sharedPreferences.edit().remove("full_name").apply();
                    sharedPreferences.edit().remove("username").apply();
                    sharedPreferences.edit().remove("topik").apply();
                    sharedPreferences.edit().remove("email").apply();
                    sharedPreferences.edit().remove("city").apply();
                    sharedPreferences.edit().remove("school").apply();
                    sharedPreferences.edit().remove("grade").apply();
                    sharedPreferences.edit().remove("birthday").apply();
                    sharedPreferences.edit().remove("pas").apply();
                }

                if (!name_teather.equals("full_name_teather_empty")) {
                    sharedPreferences.edit().remove("full_name_teather").apply();
                    sharedPreferences.edit().remove("username_teather").apply();
                    sharedPreferences.edit().remove("email_teather").apply();
                    sharedPreferences.edit().remove("city_teather").apply();
                    sharedPreferences.edit().remove("topic_teather").apply();
                    sharedPreferences.edit().remove("school_teather").apply();
                    sharedPreferences.edit().remove("birthay_teather").apply();
                    sharedPreferences.edit().remove("password_teather").apply();
                }

                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            });

            builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());

            builder.show();

        });

    }

    /**
     * Вложенный класс для отрисовки фрагмента настроек
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}