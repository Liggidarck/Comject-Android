package com.george.devil.Activities.Projects.EditsProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.george.devil.Fragments.Images.fragmentImageBio;
import com.george.devil.Fragments.Images.fragmentImageChem;
import com.george.devil.Fragments.Images.fragmentImageEconim;
import com.george.devil.Fragments.Images.fragmentImageEngener;
import com.george.devil.Fragments.Images.fragmentImageEnglish;
import com.george.devil.Fragments.Images.fragmentImageGeo;
import com.george.devil.Fragments.Images.fragmentImageHistory;
import com.george.devil.Fragments.Images.fragmentImageIT;
import com.george.devil.Fragments.Images.fragmentImageLiter;
import com.george.devil.Fragments.Images.fragmentImageMath;
import com.george.devil.Fragments.Images.fragmentImagePhys;
import com.george.devil.Fragments.Images.fragmentImagePolit;
import com.george.devil.Fragments.Images.fragmentImageSocial;
import com.george.devil.Fragments.Images.fragmentImageSport;
import com.george.devil.Fragments.Pupil.fragmentHome;
import com.george.devil.R;

public class ImagesActivity extends AppCompatActivity {

    long id_image;

    private static final String TAG = "ImagesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            id_image = extras.getLong("id_image");

        Log.i(TAG, String.valueOf(id_image));

        if(id_image == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageBio()).commit();

        if(id_image == 1)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageChem()).commit();

        if(id_image == 2)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageEconim()).commit();

        if(id_image == 3)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageEnglish()).commit();

        if(id_image == 4)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageEngener()).commit();

        if(id_image == 5)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageGeo()).commit();

        if(id_image == 6)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageHistory()).commit();

        if(id_image == 7)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageIT()).commit();

        if(id_image == 8)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageLiter()).commit();

        if(id_image == 9)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageMath()).commit();

        if(id_image == 10)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImagePhys()).commit();

        if(id_image == 11)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImagePolit()).commit();

        if(id_image == 12)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageSport()).commit();

        if(id_image == 13)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_images, new fragmentImageSocial()).commit();

    }
}