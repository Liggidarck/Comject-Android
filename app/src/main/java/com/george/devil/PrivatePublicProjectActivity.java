package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class PrivatePublicProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_public_project);

        //Frame private_public_fragme | lock_lay | likes_lay

        RelativeLayout issues = findViewById(R.id.issues_layout);
        issues.setOnClickListener(v -> startActivity(new Intent(PrivatePublicProjectActivity.this, IsuuesActivity.class)));

        RelativeLayout changes = findViewById(R.id.changes_layout);
        changes.setOnClickListener(v -> startActivity(new Intent(PrivatePublicProjectActivity.this, ChangesActivity.class)));

        RelativeLayout tasks = findViewById(R.id.my_tasks_layout);
        tasks.setOnClickListener(v -> startActivity(new Intent(PrivatePublicProjectActivity.this, MyTasksActivity.class)));

        RelativeLayout comments = findViewById(R.id.comments_layout_private_public);
        comments.setOnClickListener(v -> startActivity(new Intent(PrivatePublicProjectActivity.this, CommentsActivity.class)));

        RelativeLayout has = findViewById(R.id.hashtag_layout);
        has.setOnClickListener(v -> {
            BottomSheetHastags hastags = new BottomSheetHastags();
            hastags.show(getSupportFragmentManager(), "BottomSheetHastag");
        });

    }
}