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

import com.george.devil.Activities.Main.Teacher.MainActivityTeacher;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class RegisterTeacherActivity extends AppCompatActivity {

    TextInputLayout textField_name_register_teacher, textField_username_register_teacher,
            textField_email_register_teacher, textField_city_register_teacher,
            textField_topic_teacher, textField_school_register_teacher,
            textField_birthday_register_teacher, textField_password_register_teacher;

    TextInputEditText date_textView_teacher;

    MaterialAutoCompleteTextView topic_auto_register_pupil_new;
    Button reg_teacher;
    MaterialToolbar toolbar;

    Calendar datePickCalendar;

    static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_teather);

        textField_name_register_teacher = findViewById(R.id.textField_name_regis_teather);
        textField_username_register_teacher = findViewById(R.id.textField_username_regis_teather);
        textField_email_register_teacher = findViewById(R.id.textField_email_regis_teather);
        textField_city_register_teacher = findViewById(R.id.textField_city_regis_teather);
        textField_topic_teacher = findViewById(R.id.textField_topic_teather);
        textField_school_register_teacher = findViewById(R.id.textField_school_regis_teather);
        textField_birthday_register_teacher = findViewById(R.id.textField_bithday_regis_teather);
        textField_password_register_teacher = findViewById(R.id.textField_password_regis_teather);
        topic_auto_register_pupil_new = findViewById(R.id.topic_auto_regis_pupil_new);
        date_textView_teacher = findViewById(R.id.date_textView_teather);
        reg_teacher = findViewById(R.id.reg_teather);
        toolbar = findViewById(R.id.topAppBar_reg_teather);

        cleanErrors();

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        datePickCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            datePickCalendar.set(Calendar.YEAR, year);
            datePickCalendar.set(Calendar.MONTH, month);
            datePickCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        date_textView_teacher.setOnClickListener(v -> new DatePickerDialog(RegisterTeacherActivity.this, date, datePickCalendar
                .get(Calendar.YEAR), datePickCalendar.get(Calendar.MONTH), datePickCalendar.get(Calendar.DAY_OF_MONTH)).show());

        String[] items = getResources().getStringArray(R.array.categories_of_predmeti);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                RegisterTeacherActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        topic_auto_register_pupil_new.setAdapter(adapter);

        reg_teacher.setOnClickListener(v -> {

            if (!validateNameTeacher() | !validateEmailTeacher() | !validateCityTeacher() | !validateTopicTeacher()
                    | !validateSchoolTeacher() | !validateBirthdayTeacher() | !validatePasswordTeacher()) {
                Log.i(TAG, "Во время ввода данных какое-то поле оказалось пустым");
            } else {
                String name = Objects.requireNonNull(textField_name_register_teacher.getEditText()).getText().toString();
                String username = Objects.requireNonNull(textField_username_register_teacher.getEditText()).getText().toString();
                String email = Objects.requireNonNull(textField_email_register_teacher.getEditText()).getText().toString();
                String city = Objects.requireNonNull(textField_city_register_teacher.getEditText()).getText().toString();
                String topic = Objects.requireNonNull(textField_topic_teacher.getEditText()).getText().toString();
                String school = Objects.requireNonNull(textField_school_register_teacher.getEditText()).getText().toString();
                String birthay = Objects.requireNonNull(textField_birthday_register_teacher.getEditText()).getText().toString();
                String password = Objects.requireNonNull(textField_password_register_teacher.getEditText()).getText().toString();

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

                startActivity(new Intent(RegisterTeacherActivity.this, MainActivityTeacher.class));
            }
        });
    }

    /**
     * Метод для установки в {@link TextInputLayout} даты
     */
    public void updateLabel() {
        String date_text = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(date_text, Locale.US);

        Objects.requireNonNull(textField_birthday_register_teacher.getEditText()).setText(sdf.format(datePickCalendar.getTime()));
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateNameTeacher() {
        String check = Objects.requireNonNull(textField_name_register_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_name_register_teacher.setError(getText(R.string.error_empty));

            return false;
        } else {
            textField_name_register_teacher.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateEmailTeacher() {
        String check = Objects.requireNonNull(textField_email_register_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_email_register_teacher.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_email_register_teacher.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateCityTeacher() {
        String check = Objects.requireNonNull(textField_city_register_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_city_register_teacher.setError(getText(R.string.error_empty));

            return false;
        } else {
            textField_city_register_teacher.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateTopicTeacher() {
        String check = Objects.requireNonNull(textField_topic_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_topic_teacher.setError(getText(R.string.error_empty));

            return false;
        } else {
            textField_topic_teacher.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateSchoolTeacher() {
        String check = Objects.requireNonNull(textField_school_register_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_school_register_teacher.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_school_register_teacher.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateBirthdayTeacher() {
        String check = Objects.requireNonNull(textField_birthday_register_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_birthday_register_teacher.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_birthday_register_teacher.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validatePasswordTeacher() {
        String check = Objects.requireNonNull(textField_password_register_teacher.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_password_register_teacher.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_password_register_teacher.setError(null);
            return true;
        }
    }

    /**
     * Вызывается,когда нужно отправить запрос на отрисовку анимации снятия ошибки ввода
     */
    public void cleanErrors() {
        Objects.requireNonNull(textField_name_register_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_name_register_teacher.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_email_register_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_email_register_teacher.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_city_register_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_city_register_teacher.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_topic_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_topic_teacher.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_school_register_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_school_register_teacher.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_birthday_register_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_birthday_register_teacher.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_password_register_teacher.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_password_register_teacher.setError(null);
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