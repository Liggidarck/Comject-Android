package com.george.devil.Activities.Main.Pupil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    TextView text_main_message;
    String name;
    int id_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_message_main);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name_message");
            id_mes = extras.getInt("id_message");
        }

        text_main_message = findViewById(R.id.text_main_messege);
        text_main_message.setText(name);

        CircleImageView ava = findViewById(R.id.ava_mainMes);

        if(id_mes == 0)
            ava.setImageResource(R.drawable.main_kate_ava);

        if(id_mes == 1)
            ava.setImageResource(R.drawable.anton_ava);

        if(id_mes == 2)
            ava.setImageResource(R.drawable.ava_agamir);

        if(id_mes == 3)
            ava.setImageResource(R.drawable.ava_me);

    }
}