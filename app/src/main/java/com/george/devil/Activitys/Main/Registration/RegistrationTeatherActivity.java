package com.george.devil.Activitys.Main.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class RegistrationTeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_teather);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_reg_teather);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }
}