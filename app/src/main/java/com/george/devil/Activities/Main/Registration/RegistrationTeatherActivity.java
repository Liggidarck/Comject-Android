package com.george.devil.Activities.Main.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.george.devil.Activities.Main.Teacher.MainActivityTeather;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class RegistrationTeatherActivity extends AppCompatActivity {

    TextInputLayout textField_name_regis_teather, textField_username_regis_teather, textField_email_regis_teather, textField_city_regis_teather,
            textField_topic_teather, textField_school_regis_teather, textField_bithday_regis_teather, textField_password_regis_teather;

    TextInputEditText date_textView_teather;

    MaterialAutoCompleteTextView topic_auto_regis_pupil_new;

    Button reg_teather;

    MaterialToolbar toolbar;

    Calendar datePickCalendar;

    static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_teather);

        textField_name_regis_teather = findViewById(R.id.textField_name_regis_teather);
        textField_username_regis_teather = findViewById(R.id.textField_username_regis_teather);
        textField_email_regis_teather = findViewById(R.id.textField_email_regis_teather);
        textField_city_regis_teather = findViewById(R.id.textField_city_regis_teather);
        textField_topic_teather = findViewById(R.id.textField_topic_teather);
        textField_school_regis_teather = findViewById(R.id.textField_school_regis_teather);
        textField_bithday_regis_teather = findViewById(R.id.textField_bithday_regis_teather);
        textField_password_regis_teather = findViewById(R.id.textField_password_regis_teather);
        topic_auto_regis_pupil_new = findViewById(R.id.topic_auto_regis_pupil_new);
        date_textView_teather = findViewById(R.id.date_textView_teather);
        reg_teather = findViewById(R.id.reg_teather);
        toolbar = findViewById(R.id.topAppBar_reg_teather);

        ClearErrors();

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        datePickCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            datePickCalendar.set(Calendar.YEAR, year);
            datePickCalendar.set(Calendar.MONTH, month);
            datePickCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        date_textView_teather.setOnClickListener(v -> new DatePickerDialog(RegistrationTeatherActivity.this, date, datePickCalendar
                .get(Calendar.YEAR), datePickCalendar.get(Calendar.MONTH), datePickCalendar.get(Calendar.DAY_OF_MONTH)).show());

        String[] items = new String[]{
                "Biology", "Chemistry", "Economics", "English",
                "Engineering/Construction", "Geography", "History",
                "IT", "Literature", "Math", "Physics", "Politics",
                "Sports", "Social studies", "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                RegistrationTeatherActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        topic_auto_regis_pupil_new.setAdapter(adapter);

        reg_teather.setOnClickListener(v -> {

            if (!validateNameTeather() | !validateEmailTeather() | !validateCityTeather() | !validateTopicTeather()
                    | !validateSchoolTeather() | !validateBirthayTeather() | !validatePasswordTeather()) {
                Log.i(TAG, "Во время ввода данных какое-то поле оказалось пустым");
            } else {
                String name = Objects.requireNonNull(textField_name_regis_teather.getEditText()).getText().toString();
                String username = Objects.requireNonNull(textField_username_regis_teather.getEditText()).getText().toString();
                String email = Objects.requireNonNull(textField_email_regis_teather.getEditText()).getText().toString();
                String city = Objects.requireNonNull(textField_city_regis_teather.getEditText()).getText().toString();
                String topic = Objects.requireNonNull(textField_topic_teather.getEditText()).getText().toString();
                String school = Objects.requireNonNull(textField_school_regis_teather.getEditText()).getText().toString();
                String birthay = Objects.requireNonNull(textField_bithday_regis_teather.getEditText()).getText().toString();
                String password = Objects.requireNonNull(textField_password_regis_teather.getEditText()).getText().toString();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("full_name_teather", name);
                editor.putString("username_teather", username);
                editor.putString("email_teather", email);
                editor.putString("city_teather", city);
                editor.putString("topic_teather", topic);
                editor.putString("school_teather", school);
                editor.putString("birthay_teather", birthay);
                editor.putString("password_teather", password);

                editor.apply();

                startActivity(new Intent(RegistrationTeatherActivity.this, MainActivityTeather.class));
            }
        });


    }

    public void updateLabel() {
        String myFormat = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Objects.requireNonNull(textField_bithday_regis_teather.getEditText()).setText(sdf.format(datePickCalendar.getTime()));
    }

    public boolean validateNameTeather() {
        String check = Objects.requireNonNull(textField_name_regis_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_name_regis_teather.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_name_regis_teather.setError(null);
            return true;
        }
    }

    public boolean validateEmailTeather() {
        String check = Objects.requireNonNull(textField_email_regis_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_email_regis_teather.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_email_regis_teather.setError(null);
            return true;
        }
    }

    public boolean validateCityTeather() {
        String check = Objects.requireNonNull(textField_city_regis_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_city_regis_teather.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_city_regis_teather.setError(null);
            return true;
        }
    }

    public boolean validateTopicTeather() {
        String check = Objects.requireNonNull(textField_topic_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_topic_teather.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_topic_teather.setError(null);
            return true;
        }
    }

    public boolean validateSchoolTeather() {
        String check = Objects.requireNonNull(textField_school_regis_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_school_regis_teather.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_school_regis_teather.setError(null);
            return true;
        }
    }

    public boolean validateBirthayTeather() {
        String check = Objects.requireNonNull(textField_bithday_regis_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_bithday_regis_teather.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_bithday_regis_teather.setError(null);
            return true;
        }
    }

    public boolean validatePasswordTeather() {
        String check = Objects.requireNonNull(textField_password_regis_teather.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_password_regis_teather.setError("Это поле не может быть пустом");
            return false;
        } else {
            textField_password_regis_teather.setError(null);
            return true;
        }
    }

    public void ClearErrors() {
        Objects.requireNonNull(textField_name_regis_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_name_regis_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_email_regis_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_email_regis_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_city_regis_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_city_regis_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_topic_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_topic_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_school_regis_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_school_regis_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_bithday_regis_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_bithday_regis_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_password_regis_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_password_regis_teather.setError(null);
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