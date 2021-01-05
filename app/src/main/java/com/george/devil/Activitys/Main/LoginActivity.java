package com.george.devil.Activitys.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.george.devil.Activitys.Main.Pupil.MainActivity;
import com.george.devil.Activitys.Main.Registration.RegistarionPupilActivity;
import com.george.devil.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout email_login_text_layout, password_login_text_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        TextView reg = findViewById(R.id.registr);
        reg.setOnClickListener(v -> showResistrDialog());

        email_login_text_layout = findViewById(R.id.email_login_text_layout);
        password_login_text_layout = findViewById(R.id.password_login_text_layout);
        ImageView login_btn = findViewById(R.id.sign_in_btn_1);


        login_btn.setOnClickListener(v -> {
            String email = email_login_text_layout.getEditText().getText().toString();
            String password = password_login_text_layout.getEditText().getText().toString();
            if(email.equals("liggidarck@gmail.com") & password.equals("qwerty")){

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("full_name", "George Filatov");
                editor.putString("username", "liggidarck");
                editor.putString("topik", "IT");
                editor.putString("email", email);
                editor.putString("city", "Moscow");
                editor.putString("school", "2122");
                editor.putString("grade", "10A");
                editor.putString("birthday", "01.06.2004");
                editor.putString("pas", password);

                editor.apply();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

        });


    }

    private boolean validateEmail() {
        String email = Objects.requireNonNull(email_login_text_layout.getEditText()).getText().toString();

        if(email.isEmpty()){
            email_login_text_layout.setError("Ошибка! Поле не может быть пустым");
            return false;
        } else {
            email_login_text_layout.setError(null);
            return true;
        }
    }

    private boolean validatePssword() {
        String email = Objects.requireNonNull(email_login_text_layout.getEditText()).getText().toString();

        if(email.isEmpty()){
            email_login_text_layout.setError("Ошибка! Поле не может быть пустым");
            return false;
        } else {
            email_login_text_layout.setError(null);
            return true;
        }

    }

    public void showResistrDialog() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_choose_category_login);

        Button next = dialog.findViewById(R.id.cancel_dialog_btn);
        next.setOnClickListener(v -> dialog.dismiss());

        RelativeLayout pupil_ralative_layout = dialog.findViewById(R.id.pupil_ralative_layout);
        pupil_ralative_layout.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistarionPupilActivity.class));
        });

        RelativeLayout teacher_relative_layout = dialog.findViewById(R.id.teacher_relative_dilaog);
        teacher_relative_layout.setOnClickListener(v -> {
            Snackbar.make(v, "Teacher нажат", Snackbar.LENGTH_SHORT)
                    .setAction("done", null).show();
        });

        dialog.show();
    }

}