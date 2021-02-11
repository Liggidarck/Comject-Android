package com.george.devil.Activities.Main.Teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.george.devil.Activities.Main.Pupil.ChangePasswordActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileTeacher extends AppCompatActivity {

    TextInputLayout name_account_edit_profile_teather, username_layout_editProfile_teather, email_layout_editProfile_tether,
            city_layout_editProfile_teather, topic_layout_teather, school_layout_editProfile_teather, textField_bithday_edit_profile_teather;
    MaterialToolbar topAppBar_edit_profile_teather;
    MaterialAutoCompleteTextView topic_auto_edit_profile_teather;
    TextInputEditText textInputDate;
    CircleImageView changable_ava_teather;
    ImageView main_profile_image_edit_teather;
    Button chan_pas_teather;

    Calendar calendar;

    static final String TAG = "EditTeather";
    private final int ava_data_pick = 1;
    private final int main_image_pick = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_teacher);

        topAppBar_edit_profile_teather = findViewById(R.id.topAppBar_edit_profile_teather);
        name_account_edit_profile_teather = findViewById(R.id.name_account_edit_profile_teather);
        username_layout_editProfile_teather = findViewById(R.id.username_layout_editProfile_teather);
        email_layout_editProfile_tether = findViewById(R.id.email_layout_editProfile_tether);
        city_layout_editProfile_teather = findViewById(R.id.city_layout_editProfile_teather);
        topic_layout_teather = findViewById(R.id.topic_layout_teather);
        school_layout_editProfile_teather = findViewById(R.id.school_layout_editProfile_teather);
        textField_bithday_edit_profile_teather = findViewById(R.id.textField_bithday_edit_profile_teather);
        textInputDate = findViewById(R.id.date_textView_edit_teather);
        topic_auto_edit_profile_teather = findViewById(R.id.topic_auto_edit_profile_teather);
        changable_ava_teather = findViewById(R.id.changable_ava_teather);
        main_profile_image_edit_teather = findViewById(R.id.main_profile_image_edit_teather);
        chan_pas_teather = findViewById(R.id.chan_pas_teather);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_teather = sharedPreferences.getString("full_name_teather", "full_name_teather_empty");
        String username_teather = sharedPreferences.getString("username_teather", "username_teather_empty");
        String email_teather = sharedPreferences.getString("email_teather", "email_teather_empty");
        String city_teather = sharedPreferences.getString("city_teather", "city_teather_empty");
        String topic_teather = sharedPreferences.getString("topic_teather", "topic_teather_empty");
        String school_teather = sharedPreferences.getString("school_teather","school_teather_empty");
        String birthay_teather = sharedPreferences.getString("birthay_teather","birthay_teather_empty");

        cleanErrors();

        Objects.requireNonNull(name_account_edit_profile_teather.getEditText()).setText(name_teather);
        Objects.requireNonNull(username_layout_editProfile_teather.getEditText()).setText(username_teather);
        Objects.requireNonNull(email_layout_editProfile_tether.getEditText()).setText(email_teather);
        Objects.requireNonNull(city_layout_editProfile_teather.getEditText()).setText(city_teather);
        Objects.requireNonNull(topic_layout_teather.getEditText()).setText(topic_teather);
        Objects.requireNonNull(school_layout_editProfile_teather.getEditText()).setText(school_teather);
        Objects.requireNonNull(textField_bithday_edit_profile_teather.getEditText()).setText(birthay_teather);

        setSupportActionBar(topAppBar_edit_profile_teather);
        topAppBar_edit_profile_teather.setNavigationOnClickListener(v -> saveArrowTeacher());

        String[] items = getResources().getStringArray(R.array.categories_of_predmeti);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                EditProfileTeacher.this,
                R.layout.dropdown_menu_categories,
                items
        );
        topic_auto_edit_profile_teather.setAdapter(adapter);


        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        textInputDate.setOnClickListener(v -> new DatePickerDialog(EditProfileTeacher.this, date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());


        changable_ava_teather.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, ava_data_pick);
        });

        main_profile_image_edit_teather.setOnClickListener(v -> {
            Intent photoPik = new Intent(Intent.ACTION_PICK);
            photoPik.setType("image/*");
            startActivityForResult(photoPik, main_image_pick);
        });

        chan_pas_teather.setOnClickListener(v -> startActivity(new Intent(EditProfileTeacher.this, ChangePasswordActivity.class)));

    }

    /**
     * Получаем ответ(картинку) от вызванный активити и устанавливаем аватарку или шапку профиля
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ava_data_pick) {
            if (resultCode == RESULT_OK) {
                try {
                    assert data != null;
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    changable_ava_teather.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if(requestCode == main_image_pick){
            if(resultCode == RESULT_OK){
                try {
                    assert data != null;
                    final Uri image = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(image);
                    final Bitmap selectedIm = BitmapFactory.decodeStream(imageStream);
                    main_profile_image_edit_teather.setImageBitmap(selectedIm);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    /**
     * Метод для отстлеживания нажатия на галочку в {@link MaterialToolbar} и сохранения новых данный о пользователе во внутренней памяти телефона
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String name_teather_check = Objects.requireNonNull(name_account_edit_profile_teather.getEditText()).getText().toString();
        String username_teather_check = Objects.requireNonNull(username_layout_editProfile_teather.getEditText()).getText().toString();
        String email_teather_check = Objects.requireNonNull(email_layout_editProfile_tether.getEditText()).getText().toString();
        String city_teather_check = Objects.requireNonNull(city_layout_editProfile_teather.getEditText()).getText().toString();
        String topic_teather_check = Objects.requireNonNull(topic_layout_teather.getEditText()).getText().toString();
        String school_teather_check = Objects.requireNonNull(school_layout_editProfile_teather.getEditText()).getText().toString();
        String birthay_teather_check = Objects.requireNonNull(textField_bithday_edit_profile_teather.getEditText()).getText().toString();


        if (item.getItemId() == R.id.save_changes_profile) {

            if (!validateNameTeacher() | !validateUsernameTeacher() | !validateEmailTeacher()
                    | !validateCityTeacher() | !validateTopicTeacher() | !validateSchoolTeacher() | !validateBithtayTeacher()) {
                return super.onOptionsItemSelected(item);
            } else {
                sharedPreferences.edit().remove("full_name_teather").apply();
                sharedPreferences.edit().remove("username_teather").apply();
                sharedPreferences.edit().remove("email_teather").apply();
                sharedPreferences.edit().remove("city_teather").apply();
                sharedPreferences.edit().remove("topic_teather").apply();
                sharedPreferences.edit().remove("school_teather").apply();
                sharedPreferences.edit().remove("birthay_teather").apply();

                sharedPreferences.edit().putString("full_name_teather", name_teather_check).apply();
                sharedPreferences.edit().putString("username_teather", username_teather_check).apply();
                sharedPreferences.edit().putString("email_teather", email_teather_check).apply();
                sharedPreferences.edit().putString("city_teather", city_teather_check).apply();
                sharedPreferences.edit().putString("topic_teather", topic_teather_check).apply();
                sharedPreferences.edit().putString("school_teather", school_teather_check).apply();
                sharedPreferences.edit().putString("birthay_teather", birthay_teather_check).apply();

                startActivity(new Intent(EditProfileTeacher.this, MainActivityTeather.class));
            }

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Метод для установки в {@link TextInputLayout} даты
     */
    public void updateLabel() {
        String date_text = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(date_text, Locale.US);

        Objects.requireNonNull(textField_bithday_edit_profile_teather.getEditText()).setText(sdf.format(calendar.getTime()));
    }

    /**
     * Метод для отслеживания изменения данных о пользователе.
     *
     * Вызвает {@link AlertDialog} в случае обнаружения изменения данных о пользователе.
     * Сохраняет новые данные по нажатию на "ok" кнопку
     */
    private void saveArrowTeacher() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_teather = sharedPreferences.getString("full_name_teather", "full_name_teather_empty");
        String username_teather = sharedPreferences.getString("username_teather", "username_teather_empty");
        String email_teather = sharedPreferences.getString("email_teather", "password_teather_empty");
        String city_teather = sharedPreferences.getString("city_teather", "password_teather_empty");
        String topic_teather = sharedPreferences.getString("topic_teather", "password_teather_empty");
        String school_teather = sharedPreferences.getString("school_teather","password_teather_empty");
        String birthay_teather = sharedPreferences.getString("birthay_teather","password_teather_empty");
        String password_teather = sharedPreferences.getString("password_teather","password_teather_empty");

        String name_teather_check = Objects.requireNonNull(name_account_edit_profile_teather.getEditText()).getText().toString();
        String username_teather_check = Objects.requireNonNull(username_layout_editProfile_teather.getEditText()).getText().toString();
        String email_teather_check = Objects.requireNonNull(email_layout_editProfile_tether.getEditText()).getText().toString();
        String city_teather_check = Objects.requireNonNull(city_layout_editProfile_teather.getEditText()).getText().toString();
        String topic_teather_check = Objects.requireNonNull(topic_layout_teather.getEditText()).getText().toString();
        String school_teather_check = Objects.requireNonNull(school_layout_editProfile_teather.getEditText()).getText().toString();
        String birthay_teather_check = Objects.requireNonNull(textField_bithday_edit_profile_teather.getEditText()).getText().toString();

        if(!validateNameTeacher() | !validateUsernameTeacher() | !validateEmailTeacher()
                | !validateCityTeacher() | !validateTopicTeacher() | !validateSchoolTeacher() | !validateBithtayTeacher()){
            return;
        } else {

            if (name_teather_check.equals(name_teather) & username_teather_check.equals(username_teather) &
                    email_teather_check.equals(email_teather) & city_teather_check.equals(city_teather) &
                    topic_teather_check.equals(topic_teather) & school_teather_check.equals(school_teather)
                    & birthay_teather.equals(birthay_teather_check)) {

                Log.i(TAG, "Все данные идентичны");
                super.onBackPressed();
            } else {

                Log.i(TAG, "Что-то поменялось");

                final AlertDialog.Builder alert = new AlertDialog.Builder(EditProfileTeacher.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_confirm_editing, null);

                Button cencel = view.findViewById(R.id.cancle_editing);
                Button continuani = view.findViewById(R.id.cont_btn);

                alert.setView(view);
                final AlertDialog alertDialog = alert.create();

                cencel.setOnClickListener(v -> alertDialog.dismiss());

                continuani.setOnClickListener(v -> {
                    alertDialog.dismiss();

                    sharedPreferences.edit().remove("full_name_teather").apply();
                    sharedPreferences.edit().remove("username_teather").apply();
                    sharedPreferences.edit().remove("email_teather").apply();
                    sharedPreferences.edit().remove("city_teather").apply();
                    sharedPreferences.edit().remove("topic_teather").apply();
                    sharedPreferences.edit().remove("school_teather").apply();
                    sharedPreferences.edit().remove("birthay_teather").apply();

                    sharedPreferences.edit().putString("full_name_teather", name_teather_check).apply();
                    sharedPreferences.edit().putString("username_teather", username_teather_check).apply();
                    sharedPreferences.edit().putString("email_teather", email_teather_check).apply();
                    sharedPreferences.edit().putString("city_teather", city_teather_check).apply();
                    sharedPreferences.edit().putString("topic_teather", topic_teather_check).apply();
                    sharedPreferences.edit().putString("school_teather", school_teather_check).apply();
                    sharedPreferences.edit().putString("birthay_teather", birthay_teather_check).apply();

                    startActivity(new Intent(EditProfileTeacher.this, MainActivityTeather.class));
                });

                alertDialog.show();

            }
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    private boolean validateNameTeacher() {
        String check = Objects.requireNonNull(name_account_edit_profile_teather.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            name_account_edit_profile_teather.setError(getText(R.string.error_empty));
            return false;
        } else {
            name_account_edit_profile_teather.setError(null);
            return true;
        }
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    boolean validateUsernameTeacher() {
        String check = Objects.requireNonNull(username_layout_editProfile_teather.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            username_layout_editProfile_teather.setError(getText(R.string.error_empty));
            return false;
        } else {
            username_layout_editProfile_teather.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    boolean validateEmailTeacher() {
        String check = Objects.requireNonNull(email_layout_editProfile_tether.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            email_layout_editProfile_tether.setError(getText(R.string.error_empty));
            return false;
        } else {
            email_layout_editProfile_tether.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    boolean validateCityTeacher() {
        String check = Objects.requireNonNull(city_layout_editProfile_teather.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            city_layout_editProfile_teather.setError(getText(R.string.error_empty));
            return false;
        } else {
            city_layout_editProfile_teather.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    boolean validateTopicTeacher() {
        String check = Objects.requireNonNull(city_layout_editProfile_teather.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            city_layout_editProfile_teather.setError(getText(R.string.error_empty));
            return false;
        } else {
            city_layout_editProfile_teather.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    boolean validateSchoolTeacher() {
        String check = Objects.requireNonNull(school_layout_editProfile_teather.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            school_layout_editProfile_teather.setError(getText(R.string.error_empty));
            return false;
        } else {
            school_layout_editProfile_teather.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    boolean validateBithtayTeacher() {
        String check = Objects.requireNonNull(textField_bithday_edit_profile_teather.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            textField_bithday_edit_profile_teather.setError(getText(R.string.error_empty));
            return false;
        } else {
            textField_bithday_edit_profile_teather.setError(null);
            return true;
        }

    }

    /**
     * Вызывается,когда нужно отправить запрос на отрисовку анимации снятия ошибки ввода
     */
    void cleanErrors(){
        Objects.requireNonNull(name_account_edit_profile_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                name_account_edit_profile_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(username_layout_editProfile_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                username_layout_editProfile_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(email_layout_editProfile_tether.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                email_layout_editProfile_tether.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(city_layout_editProfile_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                city_layout_editProfile_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(topic_layout_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                topic_layout_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(school_layout_editProfile_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                school_layout_editProfile_teather.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(textField_bithday_edit_profile_teather.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textField_bithday_edit_profile_teather.setError(null);
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