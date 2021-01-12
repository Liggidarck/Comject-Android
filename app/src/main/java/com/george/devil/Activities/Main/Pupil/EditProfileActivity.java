package com.george.devil.Activities.Main.Pupil;

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

public class EditProfileActivity extends AppCompatActivity {

    //TODO: Сделать что-нибудть с этим пиздецомю Читать этот код нереально!

    private static final String TAG = "EditProfileActivity";

    TextInputLayout namePersonTextLayout, usernameTextLayout, topikTextLayout, emailTextLayout, cityTextLayout, schoolTextLayout, gradeTextLayout, birdayTextLayout;
    Calendar myCalendar;

    CircleImageView changable_ava;
    ImageView main_profile_image_edit;
    private final int ava_data_pick = 1;
    private final int main_image_pick = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_edit_profile);

        namePersonTextLayout    = findViewById(R.id.name_account_edit_profile);
        usernameTextLayout      = findViewById(R.id.username_layout_editProfile);
        topikTextLayout         = findViewById(R.id.topic_layout);
        emailTextLayout         = findViewById(R.id.email_layout_edit_profile);
        cityTextLayout          = findViewById(R.id.city_layout_edit_profile);
        schoolTextLayout        = findViewById(R.id.school_edit_profile);
        gradeTextLayout         = findViewById(R.id.grade_edit_profile);
        birdayTextLayout        = findViewById(R.id.textField_bithday_edit_profile);
        changable_ava           = findViewById(R.id.changable_ava);
        main_profile_image_edit = findViewById(R.id.main_profile_image_edit);

        changable_ava.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, ava_data_pick);
        });

        main_profile_image_edit.setOnClickListener(v -> {
            Intent photoPik = new Intent(Intent.ACTION_PICK);
            photoPik.setType("image/*");
            startActivityForResult(photoPik, main_image_pick);
        });

        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.topic_auto_edit_profile);

        clearErrors();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_user = sharedPreferences.getString("full_name", "empty_user_name");
        String username = sharedPreferences.getString("username", "empty_correct_username");
        String topic = sharedPreferences.getString("topik", "empty_topic");
        String email = sharedPreferences.getString("email", "empty_email");
        String city = sharedPreferences.getString("city", "empty_city");
        String school = sharedPreferences.getString("school", "empty_school");
        String grade = sharedPreferences.getString("grade", "empty_grade");
        String birthday = sharedPreferences.getString("birthday", "empty_birthday");
        String password = sharedPreferences.getString("pas", "empty_pas");

        Objects.requireNonNull(namePersonTextLayout.getEditText()).setText(name_user);
        Objects.requireNonNull(usernameTextLayout.getEditText()).setText(username);
        Objects.requireNonNull(topikTextLayout.getEditText()).setText(topic);
        Objects.requireNonNull(emailTextLayout.getEditText()).setText(email);
        Objects.requireNonNull(cityTextLayout.getEditText()).setText(city);
        Objects.requireNonNull(schoolTextLayout.getEditText()).setText(school);
        Objects.requireNonNull(gradeTextLayout.getEditText()).setText(grade);
        Objects.requireNonNull(birdayTextLayout.getEditText()).setText(birthday);

        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        TextInputEditText text = findViewById(R.id.date_textView_4);

        text.setOnClickListener(v -> new DatePickerDialog(EditProfileActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> saveArrow());

        String[] items = new String[] {
                "Biology", "Chemistry", "Economics", "English",
                "Engineering/Construction","Geography", "History",
                "IT", "Literature", "Math", "Physics", "Politics",
                "Sports", "Social studies", "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                EditProfileActivity.this,
                R.layout.dropdown_menu_categories,
                items
        );

        autoCompleteTextView.setAdapter(adapter);

        Button changePass = findViewById(R.id.chan_pas);
        changePass.setOnClickListener(v -> startActivity(new Intent(EditProfileActivity.this, ChangePasswordActivity.class)));

    }

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
                    changable_ava.setImageBitmap(selectedImage);
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
                    main_profile_image_edit.setImageBitmap(selectedIm);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

    }

    private void updateLabel() {
        String myFormat = "MM.dd.yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Objects.requireNonNull(birdayTextLayout.getEditText()).setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_profile, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String name_user_chek = Objects.requireNonNull(namePersonTextLayout.getEditText()).getText().toString();
        String username_chek = Objects.requireNonNull(usernameTextLayout.getEditText()).getText().toString();
        String topic_chek = Objects.requireNonNull(topikTextLayout.getEditText()).getText().toString();
        String emai_chekl = Objects.requireNonNull(emailTextLayout.getEditText()).getText().toString();
        String city_chek = Objects.requireNonNull(cityTextLayout.getEditText()).getText().toString();
        String school_chek = Objects.requireNonNull(schoolTextLayout.getEditText()).getText().toString();
        String grade_chek = Objects.requireNonNull(gradeTextLayout.getEditText()).getText().toString();
        String birthday_chek = Objects.requireNonNull(birdayTextLayout.getEditText()).getText().toString();

        if (item.getItemId() == R.id.save_changes_profile) {

            if(validateNameProfile() | !validateUsername() |!validateTopic() | !validateEmail() | !validateCity() | !validateSchool() | !validateGrade() | !validateBirthay() ) {
                return super.onOptionsItemSelected(item);
            } else {
                sharedPreferences.edit().remove("full_name").apply();
                sharedPreferences.edit().remove("username").apply();
                sharedPreferences.edit().remove("topik").apply();
                sharedPreferences.edit().remove("email").apply();
                sharedPreferences.edit().remove("city").apply();
                sharedPreferences.edit().remove("school").apply();
                sharedPreferences.edit().remove("grade").apply();
                sharedPreferences.edit().remove("birthday").apply();

                sharedPreferences.edit().putString("full_name", name_user_chek).apply();
                sharedPreferences.edit().putString("username", username_chek).apply();
                sharedPreferences.edit().putString("topik", topic_chek).apply();
                sharedPreferences.edit().putString("email", emai_chekl).apply();
                sharedPreferences.edit().putString("city", city_chek).apply();
                sharedPreferences.edit().putString("school", school_chek).apply();
                sharedPreferences.edit().putString("grade", grade_chek).apply();
                sharedPreferences.edit().putString("birthday", birthday_chek).apply();

                Intent intent = new Intent(this, MainActivityPupil.class);
                startActivity(intent);

            }

        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveArrow();
    }

    public void saveArrow() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_user = sharedPreferences.getString("full_name", "empty_user_name");
        String username = sharedPreferences.getString("username", "empty_correct_username");
        String topic = sharedPreferences.getString("topik", "empty_topic");
        String email = sharedPreferences.getString("email", "empty_email");
        String city = sharedPreferences.getString("city", "empty_city");
        String school = sharedPreferences.getString("school", "empty_school");
        String grade = sharedPreferences.getString("grade", "empty_grade");
        String birthday = sharedPreferences.getString("birthday", "empty_birthday");
        String password = sharedPreferences.getString("pas", "empty_pas");

        String name_user_chek = Objects.requireNonNull(namePersonTextLayout.getEditText()).getText().toString();
        String username_chek = Objects.requireNonNull(usernameTextLayout.getEditText()).getText().toString();
        String topic_chek = Objects.requireNonNull(topikTextLayout.getEditText()).getText().toString();
        String emai_chekl = Objects.requireNonNull(emailTextLayout.getEditText()).getText().toString();
        String city_chek = Objects.requireNonNull(cityTextLayout.getEditText()).getText().toString();
        String school_chek = Objects.requireNonNull(schoolTextLayout.getEditText()).getText().toString();
        String grade_chek = Objects.requireNonNull(gradeTextLayout.getEditText()).getText().toString();
        String birthday_chek = Objects.requireNonNull(birdayTextLayout.getEditText()).getText().toString();

        if(validateNameProfile() | !validateUsername() |!validateTopic() | !validateEmail() | !validateCity() | !validateSchool() | !validateGrade() | !validateBirthay() ) {
            return;
        } else {

            if (name_user_chek.equals(name_user) & username_chek.equals(username) & topic_chek.equals(topic) & emai_chekl.equals(email) & city_chek.equals(city) &
                    school_chek.equals(school) & grade_chek.equals(grade) & birthday_chek.equals(birthday)) {

                Log.i(TAG, "Все данные идентичны");
                super.onBackPressed();

            } else {

                Log.i(TAG, "Что-то поменялось");

                final AlertDialog.Builder alert = new AlertDialog.Builder(EditProfileActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_confirm_editing, null);

                Button cencel = view.findViewById(R.id.cancle_editing);
                Button continuani = view.findViewById(R.id.cont_btn);

                alert.setView(view);
                final AlertDialog alertDialog = alert.create();

                cencel.setOnClickListener(v -> alertDialog.dismiss());

                continuani.setOnClickListener(v -> {

                    alertDialog.dismiss();

                    sharedPreferences.edit().remove("full_name").apply();
                    sharedPreferences.edit().remove("username").apply();
                    sharedPreferences.edit().remove("topik").apply();
                    sharedPreferences.edit().remove("email").apply();
                    sharedPreferences.edit().remove("city").apply();
                    sharedPreferences.edit().remove("school").apply();
                    sharedPreferences.edit().remove("grade").apply();
                    sharedPreferences.edit().remove("birthday").apply();

                    sharedPreferences.edit().putString("full_name", name_user_chek).apply();
                    sharedPreferences.edit().putString("username", username_chek).apply();
                    sharedPreferences.edit().putString("topik", topic_chek).apply();
                    sharedPreferences.edit().putString("email", emai_chekl).apply();
                    sharedPreferences.edit().putString("city", city_chek).apply();
                    sharedPreferences.edit().putString("school", school_chek).apply();
                    sharedPreferences.edit().putString("grade", grade_chek).apply();
                    sharedPreferences.edit().putString("birthday", birthday_chek).apply();

                    Intent intent = new Intent(this, MainActivityPupil.class);
                    startActivity(intent);

                });

                alertDialog.show();

            }

        }


    }

    public boolean validateNameProfile(){
        String check = Objects.requireNonNull(namePersonTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            namePersonTextLayout.setError("Это поле не может быть пустом");
            return true;
        } else {
            namePersonTextLayout.setError(null);
            return false;
        }
    }

    public boolean validateUsername(){
        String check = Objects.requireNonNull(usernameTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            usernameTextLayout.setError("Это поле не может быть пустом");
            return false;
        } else {
            usernameTextLayout.setError(null);
            return true;
        }
    }

    public boolean validateTopic(){
        String check = Objects.requireNonNull(topikTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            topikTextLayout.setError("Это поле не может быть пустом");
            return false;
        } else {
            topikTextLayout.setError(null);
            return true;
        }
    }

    public boolean validateEmail(){
        String check = Objects.requireNonNull(emailTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            emailTextLayout.setError("Это поле не может быть пустом");

            return false;
        } else {
            emailTextLayout.setError(null);
            return true;
        }
    }

    public boolean validateCity(){
        String check = Objects.requireNonNull(cityTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            cityTextLayout.setError("Это поле не может быть пустом");

            return false;
        } else {
            cityTextLayout.setError(null);
            return true;
        }
    }

    public boolean validateSchool(){
        String check = Objects.requireNonNull(schoolTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            schoolTextLayout.setError("Это поле не может быть пустом");

            return false;
        } else {
            schoolTextLayout.setError(null);
            return true;
        }
    }

    public boolean validateGrade() {
        String check = Objects.requireNonNull(gradeTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            gradeTextLayout.setError("Это поле не может быть пустом");

            return false;
        } else {
            gradeTextLayout.setError(null);
            return true;
        }
    }

    public boolean validateBirthay() {
        String check = Objects.requireNonNull(birdayTextLayout.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            birdayTextLayout.setError("Это поле не может быть пустом");

            return false;
        } else {
            birdayTextLayout.setError(null);
            return true;
        }
    }

    public void clearErrors() {

        Objects.requireNonNull(namePersonTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                namePersonTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(usernameTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                usernameTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(topikTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                topikTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(emailTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                emailTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(cityTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                cityTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(schoolTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                schoolTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(gradeTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                gradeTextLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(birdayTextLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                birdayTextLayout.setError(null);
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