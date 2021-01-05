package com.george.devil.Activitys.Main.Pupil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.george.devil.Activitys.Main.LoginActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_setting);
        toolbar.setNavigationOnClickListener(v -> startActivity(new Intent(SettingsActivity.this, MainActivity.class)));

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

        Button edit_account = findViewById(R.id.btn_edit_account);
        edit_account.setOnClickListener(v -> startActivity(new Intent(SettingsActivity.this, EditProfileActivity.class)));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        Button sign_out = findViewById(R.id.btn_sign_out);
        sign_out.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
            builder.setTitle("Внимание");
            builder.setMessage("Вы действительно хотите выйти?");

            builder.setPositiveButton("Выйти", (dialog, id) -> {
                sharedPreferences.edit().remove("full_name").apply();
                sharedPreferences.edit().remove("username").apply();
                sharedPreferences.edit().remove("topik").apply();
                sharedPreferences.edit().remove("email").apply();
                sharedPreferences.edit().remove("city").apply();
                sharedPreferences.edit().remove("school").apply();
                sharedPreferences.edit().remove("grade").apply();
                sharedPreferences.edit().remove("birthday").apply();
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            });

            builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());

            builder.show();

        });

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}