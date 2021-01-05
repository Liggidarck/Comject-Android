package com.george.devil.Activitys.Main.Pupil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputLayout current_pass_LayoutText, new_pass_LayoutText, confirm_pass_LayoutText;

    private static final String TAG = "ChangePasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        current_pass_LayoutText = findViewById(R.id.current_pass_LayoutText);
        new_pass_LayoutText = findViewById(R.id.new_pass_LayoutText);
        confirm_pass_LayoutText = findViewById(R.id.confirm_pass_LayoutText);


        MaterialToolbar toolbar = findViewById(R.id.topAppBar_change_password);
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        Button changePs = findViewById(R.id.change);
        changePs.setOnClickListener(v -> {
            if(!validateConfirmPas() | !validateNewPas() | !validatePas()){
                return;
            } else {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String password = sharedPreferences.getString("pas", "empty_pas");
                Log.i(TAG, password+"");

                String currnetPas = current_pass_LayoutText.getEditText().getText().toString();
                String checkPasNew = new_pass_LayoutText.getEditText().getText().toString();
                String checkPasConfirm = confirm_pass_LayoutText.getEditText().getText().toString();


                if(currnetPas.equals(password)) {

                    if(checkPasNew.equals(checkPasConfirm)) {
                        sharedPreferences.edit().remove("pas").apply();
                        sharedPreferences.edit().putString("pas", checkPasConfirm).apply();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                        builder.setTitle("Внимание");
                        builder.setMessage("Вы действительно хотите изменить пароль?");
                        builder.setPositiveButton("Ок", (dialog, id) -> {
                            dialog.dismiss();
                            startActivity(new Intent(ChangePasswordActivity.this, EditProfileActivity.class));
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
        });

        Button forgotPaa = findViewById(R.id.forgot_pass);
        forgotPaa.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String password = sharedPreferences.getString("pas", "empty_pas");
            Snackbar
                    .make(v, "DEMO Comject PASSWORD: " + password, Snackbar.LENGTH_LONG)
                    .setAction("COPY", v1 -> {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("", password);
                        assert clipboard != null;
                        clipboard.setPrimaryClip(clip);
                    })
                    .show();
        });


    }

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

}