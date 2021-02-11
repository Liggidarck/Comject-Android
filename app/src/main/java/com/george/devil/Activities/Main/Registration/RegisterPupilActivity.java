
package com.george.devil.Activities.Main.Registration;

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

import androidx.appcompat.app.AppCompatActivity;

import com.george.devil.Activities.Projects.NewProject.NewProjectActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class RegisterPupilActivity extends AppCompatActivity {

    TextInputLayout textField_name_register_pupil, textField_username_register_pupil, textField_topic_edit, textField_email_register_pupil,
            textField_city_register_pupil, textField_school_register_pupil, textField_grade_register_pupil, textField_bithday_register_pupil,
            textField_password_register_pupil;

    MaterialAutoCompleteTextView topic_auto_register_pupil;
    Button register;
    MaterialToolbar toolbar;

    Calendar calendarDatePick;

    public static final String TAG = "RegPupilActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registarion_pupil);

        textField_name_register_pupil = findViewById(R.id.textField_name_regis_pupil);
        textField_username_register_pupil = findViewById(R.id.textField_username_regis_pupil);
        textField_topic_edit           = findViewById(R.id.textField_topic_edit);
        textField_email_register_pupil = findViewById(R.id.textField_email_regis_pupil);
        textField_city_register_pupil = findViewById(R.id.textField_city_regis_pupil);
        textField_school_register_pupil = findViewById(R.id.textField_school_regis_pupil);
        textField_grade_register_pupil = findViewById(R.id.textField_grade_regis_pupil);
        textField_bithday_register_pupil = findViewById(R.id.textField_bithday_regis_pupil);
        textField_password_register_pupil = findViewById(R.id.textField_password_regis_pupil);
        topic_auto_register_pupil = findViewById(R.id.topic_auto_regis_pupil_new);
        register = findViewById(R.id.reg_pupil);
        toolbar = findViewById(R.id.topAppBar_reg_pupil);
        TextInputEditText textDate = findViewById(R.id.date_textView_3);

        clearErrors();

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        calendarDatePick = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            calendarDatePick.set(Calendar.YEAR, year);
            calendarDatePick.set(Calendar.MONTH, month);
            calendarDatePick.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        textDate.setOnClickListener(v -> new DatePickerDialog(RegisterPupilActivity.this, date, calendarDatePick
                .get(Calendar.YEAR), calendarDatePick.get(Calendar.MONTH),
                calendarDatePick.get(Calendar.DAY_OF_MONTH)).show());

        String[] items = getResources().getStringArray(R.array.categories_of_predmeti);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                RegisterPupilActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        topic_auto_register_pupil.setAdapter(adapter);

        register.setOnClickListener(v -> {

            if (!validateName() | !validateUserName() | !validateTopik() | !validateEmail() | !validateCity()
                    | !validateSchool() | !validateGrade() | !validateBirthay() | !validatePassword()) {
                return;
            } else {
                String name = Objects.requireNonNull(textField_name_register_pupil.getEditText()).getText().toString();
                String userName = Objects.requireNonNull(textField_username_register_pupil.getEditText()).getText().toString();
                String topic = Objects.requireNonNull(textField_topic_edit.getEditText()).getText().toString();
                String email = Objects.requireNonNull(textField_email_register_pupil.getEditText()).getText().toString();
                String city = Objects.requireNonNull(textField_city_register_pupil.getEditText()).getText().toString();
                String school = Objects.requireNonNull(textField_school_register_pupil.getEditText()).getText().toString();
                String grade = Objects.requireNonNull(textField_grade_register_pupil.getEditText()).getText().toString();
                String birthday = Objects.requireNonNull(textField_bithday_register_pupil.getEditText()).getText().toString();
                String pas = Objects.requireNonNull(textField_password_register_pupil.getEditText()).getText().toString();

                Log.i(TAG, "name = " + name);
                Log.i(TAG, "userName = " + userName);
                Log.i(TAG, "topic = " + topic);
                Log.i(TAG, "email = " + email);
                Log.i(TAG, "city = " + city);
                Log.i(TAG, "school = " + school);
                Log.i(TAG, "grade = " + grade);
                Log.i(TAG, "birthday = " + birthday);
                Log.i(TAG, "password = " + pas);

                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("full_name", name);
                editor.putString("username", userName);
                editor.putString("topik", topic);
                editor.putString("email", email);
                editor.putString("city", city);
                editor.putString("school", school);
                editor.putString("grade", grade);
                editor.putString("birthday", birthday);
                editor.putString("pas", pas);

                editor.apply();

                startActivity(new Intent(RegisterPupilActivity.this, NewProjectActivity.class));
            }

        });

    }

    /**
     * Метод для установки в {@link TextInputLayout} даты
     */
    private void updateLabel() {
        String date_text = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(date_text, Locale.US);

        Objects.requireNonNull(textField_bithday_register_pupil.getEditText()).setText(sdf.format(calendarDatePick.getTime()));
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateName() {
        String check = Objects.requireNonNull(textField_name_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_name_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_name_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateUserName() {
        String check = Objects.requireNonNull(textField_username_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_username_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_username_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateTopik() {
        String check = Objects.requireNonNull(textField_topic_edit.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_topic_edit.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_topic_edit.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateEmail() {
        String check = Objects.requireNonNull(textField_email_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_email_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_email_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateCity() {
        String check = Objects.requireNonNull(textField_city_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_city_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_city_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateSchool() {
        String check = Objects.requireNonNull(textField_school_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_school_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_school_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateGrade() {
        String check = Objects.requireNonNull(textField_grade_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_grade_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_grade_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateBirthay() {
        String check = Objects.requireNonNull(textField_bithday_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_bithday_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_bithday_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validatePassword() {
        String check = Objects.requireNonNull(textField_password_register_pupil.getEditText()).getText().toString().trim();

        if (check.isEmpty()) {
            textField_password_register_pupil.setError("Это поле не может быть пустом");

            return false;
        } else {
            textField_password_register_pupil.setError(null);
            return true;
        }
    }

    /**
     * Вызывается,когда нужно отправить запрос на отрисовку анимации снятия ошибки ввода
     */
    public void clearErrors() {

        Objects.requireNonNull(textField_name_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_name_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_username_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_username_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_topic_edit.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_topic_edit.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_email_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_email_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_city_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_city_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_school_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_school_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_grade_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_grade_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_bithday_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_bithday_register_pupil.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_password_register_pupil.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_password_register_pupil.setError(null);
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