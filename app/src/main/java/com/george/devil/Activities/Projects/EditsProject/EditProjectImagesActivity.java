package com.george.devil.Activities.Projects.EditsProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.george.devil.Activities.Projects.PagesProjects.MainProjectActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class EditProjectImagesActivity extends AppCompatActivity {

    TextInputLayout textField_name_proj_edit_images;
    EditText main_description_proj_edit;

    ImageView image_proj;

    String topic_for_image;

    private static final String TAG = "editProjIm";

    int generared_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project_images);

        textField_name_proj_edit_images = findViewById(R.id.textField_name_proj_edit_images);
        main_description_proj_edit = findViewById(R.id.main_description_proj_edit);
        image_proj = findViewById(R.id.image_proj);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String name_project = sharedPreferences.getString("nameMainProject", "empty_main_project");
        String description = sharedPreferences.getString("description_main", "empty_description_main");

        Objects.requireNonNull(textField_name_proj_edit_images.getEditText()).setText(name_project);
        main_description_proj_edit.setText(description);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            topic_for_image = extras.getString("topic_image");

        if(topic_for_image.contentEquals(getText(R.string.bio))) {
            generate(5);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.bio1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.bio2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.bio3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.bio4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.bio5);
        }

        if(topic_for_image.contentEquals(getText(R.string.Chemistry))) {
            generate(5);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.chem1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.chem2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.chem3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.chem4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.chem5);
        }

        if(topic_for_image.contentEquals(getText(R.string.Economics))) {
            generate(5);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.econom1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.econom2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.econom3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.econom4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.econom5);
        }

        if(topic_for_image.contentEquals(getText(R.string.English))) {
            generate(3);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.eng1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.eng2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.eng3);

        }

        if(topic_for_image.contentEquals(getText(R.string.Engineering))) {
            generate(4);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.engen1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.engen2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.engen3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.engen4);

        }

        if(topic_for_image.contentEquals(getText(R.string.Geography))) {
            generate(5);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.geo1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.geo2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.geo3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.geo4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.geo5);
        }

        if(topic_for_image.contentEquals(getText(R.string.History))) {
            generate(3);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.histor1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.histor2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.histor3);
        }

        if(topic_for_image.contentEquals(getText(R.string.IT))) {
            generate(6);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.it1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.it2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.it3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.it4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.it5);

            if(generared_num == 6)
                image_proj.setImageResource(R.drawable.it6);
        }

        if(topic_for_image.contentEquals(getText(R.string.Literature))) {
            generate(4);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.liter1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.liter2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.liter3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.liter4);
        }

        if(topic_for_image.contentEquals(getText(R.string.Math))) {
            generate(5);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.math1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.math2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.math3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.math4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.math4);
        }

        if(topic_for_image.contentEquals(getText(R.string.Physics))) {
            generate(4);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.phys1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.phys2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.phys3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.phys4);

        }

        if(topic_for_image.contentEquals(getText(R.string.Politics))) {
            generate(4);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.politics1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.politics2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.politics3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.politics4);

        }

        if(topic_for_image.contentEquals(getText(R.string.Sports))) {
            generate(5);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.sport1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.sport2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.sport3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.sport4);

            if(generared_num == 5)
                image_proj.setImageResource(R.drawable.sport5);
        }

        if(topic_for_image.contentEquals(getText(R.string.SocialStudies))) {
            generate(4);

            Log.i(TAG, String.valueOf(generared_num));

            if(generared_num == 1)
                image_proj.setImageResource(R.drawable.studies1);

            if(generared_num == 2)
                image_proj.setImageResource(R.drawable.studies2);

            if(generared_num == 3)
                image_proj.setImageResource(R.drawable.studies3);

            if(generared_num == 4)
                image_proj.setImageResource(R.drawable.studies4);
        }

        ExtendedFloatingActionButton save_btn_images_edit = findViewById(R.id.save_btn_images_edit);
        save_btn_images_edit.setOnClickListener(v -> {
            saveData();
            startActivity(new Intent(EditProjectImagesActivity.this, MainProjectActivity.class));

        });

        MaterialToolbar topAppBar_edit_proj1 = findViewById(R.id.topAppBar_edit_proj1);
        topAppBar_edit_proj1.setNavigationOnClickListener(v -> {
            saveData();
            startActivity(new Intent(EditProjectImagesActivity.this, EditProjectActivity.class));
        });

        Button find_more_images_btn_edit = findViewById(R.id.find_more_images_btn_edit);
        find_more_images_btn_edit.setOnClickListener(v -> startActivity(new Intent(EditProjectImagesActivity.this, FindMoreImagesActivity.class)));

    }

    @Override
    public void onBackPressed() {
        saveData();
        startActivity(new Intent(EditProjectImagesActivity.this, EditProjectActivity.class));
    }

    public void saveData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String description_main = main_description_proj_edit.getText().toString();

        if(description_main.isEmpty()){
            description_main = "This will be the most important text for your project";
        }

        sharedPreferences.edit().remove("description_main").apply();
        sharedPreferences.edit().putString("description_main", description_main).apply();
    }

    private void generate(int b) {
        generared_num = 1 + (int) (Math.random() * ((b - 1) + 1));
    }

}