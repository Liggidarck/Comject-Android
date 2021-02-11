package com.george.devil.Activities.Main.Pupil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputLayout current_pass_LayoutText, new_pass_LayoutText, confirm_pass_LayoutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        current_pass_LayoutText = findViewById(R.id.current_pass_LayoutText);
        new_pass_LayoutText = findViewById(R.id.new_pass_LayoutText);
        confirm_pass_LayoutText = findViewById(R.id.confirm_pass_LayoutText);
        Button changePs = findViewById(R.id.change);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar_change_password);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        cleanErrors();

        changePs.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String password = sharedPreferences.getString("pas", "empty_pas");
            String password_teather = sharedPreferences.getString("password_teather","password_teather_empty");

            if(!password.equals("empty_pas"))
                ChangePupilPassword();

            if(!password_teather.equals("password_teather_empty"))
                ChangeTeacherPassword();
        });

        Button forgotPaa = findViewById(R.id.forgot_pass);
        forgotPaa.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String password = sharedPreferences.getString("pas", "empty_pas");
            String password_teather = sharedPreferences.getString("password_teather","password_teather_empty");

            if(!password.equals("empty_pas"))
                forgotPupilPassword(v);

            if(!password_teather.equals("password_teather_empty"))
                forgotTeacherPassword(v);

        });


    }

    /**
     * Вызывается, для изменения пароля ученика
     */
    public void ChangePupilPassword() {
        if (!validateConfirmPas() | !validateNewPas() | !validatePas()) {
            return;
        } else {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String password = sharedPreferences.getString("pas", "empty_pas");

            String currentPassword = Objects.requireNonNull(current_pass_LayoutText.getEditText()).getText().toString();
            String checkPasNew = Objects.requireNonNull(new_pass_LayoutText.getEditText()).getText().toString();
            String checkPasConfirm = Objects.requireNonNull(confirm_pass_LayoutText.getEditText()).getText().toString();

            if (currentPassword.equals(password)) {
                if (checkPasNew.equals(checkPasConfirm)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                    builder.setTitle("Внимание");
                    builder.setMessage("Вы действительно хотите изменить пароль?");
                    builder.setPositiveButton("Ок", (dialog, id) -> {
                        sharedPreferences.edit().remove("pas").apply();
                        sharedPreferences.edit().putString("pas", checkPasConfirm).apply();
                        dialog.dismiss();
                        onBackPressed();
                    });
                    builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
                    builder.show();
                } else {
                    new_pass_LayoutText.setError("Пароль разный");
                    confirm_pass_LayoutText.setError("Пароль разный");
                }

            } else
                current_pass_LayoutText.setError("Неверный пароль");

        }
    }

    /**
     * Вызывается для изменения пароля учителя
     */
    public void ChangeTeacherPassword() {
        if (!validateConfirmPas() | !validateNewPas() | !validatePas()) {
            return;
        } else {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String password = sharedPreferences.getString("password_teather", "password_teather_empty");

            String currnetPas = Objects.requireNonNull(current_pass_LayoutText.getEditText()).getText().toString();
            String checkPasNew = Objects.requireNonNull(new_pass_LayoutText.getEditText()).getText().toString();
            String checkPasConfirm = Objects.requireNonNull(confirm_pass_LayoutText.getEditText()).getText().toString();

            if (currnetPas.equals(password)) {
                if (checkPasNew.equals(checkPasConfirm)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                    builder.setTitle("Внимание");
                    builder.setMessage("Вы действительно хотите изменить пароль?");
                    builder.setPositiveButton("Ок", (dialog, id) -> {
                        sharedPreferences.edit().remove("password_teather").apply();
                        sharedPreferences.edit().putString("password_teather", checkPasConfirm).apply();
                        dialog.dismiss();
                        onBackPressed();
                    });
                    builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
                    builder.show();
                } else {
                    new_pass_LayoutText.setError("Пароль разный");
                    confirm_pass_LayoutText.setError("Пароль разный");
                }

            } else
                current_pass_LayoutText.setError("Неверный пароль");

        }
    }

    /**
     * Вызывыается по нажатию на кнопку "Forgot Password"
     * Отрисосвывает в {@link Snackbar} пароль ученика
     * @param view для отрисовки {@link Snackbar}
     */
    public void forgotPupilPassword(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String password = sharedPreferences.getString("pas", "empty_pas");
        Snackbar
                .make(view, "DEMO Comject PASSWORD: " + password, Snackbar.LENGTH_LONG)
                .setAction("COPY", v1 -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", password);
                    assert clipboard != null;
                    clipboard.setPrimaryClip(clip);
                })
                .show();
    }

    /**
     * Вызывыается по нажатию на кнопку "Forgot Password"
     * Отрисосвывает в {@link Snackbar} пароль учителя
     * @param view для отрисовки {@link Snackbar}
     */
    public void forgotTeacherPassword(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String password = sharedPreferences.getString("password_teather", "password_teather_empty");
        Snackbar
                .make(view, "DEMO Comject PASSWORD: " + password, Snackbar.LENGTH_LONG)
                .setAction("COPY", v1 -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", password);
                    assert clipboard != null;
                    clipboard.setPrimaryClip(clip);
                })
                .show();
    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateConfirmPas() {
        String check = Objects.requireNonNull(confirm_pass_LayoutText.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            confirm_pass_LayoutText.setError("Это поле не может быть пустом");

            return false;
        } else {
            confirm_pass_LayoutText.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validateNewPas() {
        String check = Objects.requireNonNull(new_pass_LayoutText.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            new_pass_LayoutText.setError("Это поле не может быть пустом");

            return false;
        } else {
            new_pass_LayoutText.setError(null);
            return true;
        }

    }

    /**
     * @return возвращает true/false для проверки поля на пустоту и отрисовывает ошибку.
     */
    public boolean validatePas() {
        String check = Objects.requireNonNull(current_pass_LayoutText.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            current_pass_LayoutText.setError("Это поле не может быть пустом");

            return false;
        } else {
            current_pass_LayoutText.setError(null);
            return true;
        }

    }

    /**
     * Вызывается,когда нужно отправить запрос на отрисовку анимации снятия ошибки ввода
     */
    void cleanErrors() {
        Objects.requireNonNull(current_pass_LayoutText.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                current_pass_LayoutText.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Objects.requireNonNull(new_pass_LayoutText.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                new_pass_LayoutText.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Objects.requireNonNull(confirm_pass_LayoutText.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirm_pass_LayoutText.setError(null);
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