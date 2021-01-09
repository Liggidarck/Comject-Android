package com.george.devil.Fragments.Pupil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.george.devil.Activitys.Projects.EditsProject.EditProjectActivity;
import com.george.devil.Activitys.Projects.PagesProjects.MainProjectActivity;
import com.george.devil.Activitys.Projects.NewProject.NewProjectActivity;
import com.george.devil.Activitys.Projects.PagesProjects.PrivatePublicProjectActivity;
import com.george.devil.Activitys.Projects.PagesProjects.PublishProjectActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragmentHome extends Fragment {

    TextView main_title_project_1, main_description_project, hastags_mail_proj_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar_home);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        main_title_project_1     = view.findViewById(R.id.main_title_project);
        main_description_project = view.findViewById(R.id.main_description_project);
        hastags_mail_proj_text   = view.findViewById(R.id.hastags_mail_proj_text);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String titleMain = sharedPreferences.getString("nameMainProject", "empty_main_project");
        String username = sharedPreferences.getString("username", "empty_correct_username");
        String descroption = sharedPreferences.getString("descriptionMainProject", "empty_absic_description");
        String topic = sharedPreferences.getString("topicMainProject", "topic_main_proj_empty");
        String hastags = sharedPreferences.getString("hastags", "Edit project hastags!");

        main_title_project_1.setText(titleMain);
        main_description_project.setText(descroption);
        hastags_mail_proj_text.setText(hastags);

        FloatingActionButton add_proj = view.findViewById(R.id.add_proj);
        add_proj.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), NewProjectActivity.class)));

        MaterialCardView card1 = view.findViewById(R.id.card1);
        card1.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), MainProjectActivity.class)));

        MaterialCardView card2 = view.findViewById(R.id.card2);
        card2.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PrivatePublicProjectActivity.class)));

        MaterialCardView card3 = view.findViewById(R.id.card3);
        card3.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PrivatePublicProjectActivity.class)));

        MaterialCardView card4 = view.findViewById(R.id.card4);
        card4.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PrivatePublicProjectActivity.class)));

//        Button shareMainProject = view.findViewById(R.id.share_main_project_brn);
//        shareMainProject.setOnClickListener(v -> {
//
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//            sendIntent.setType("text/plain");
//
//            Intent shareIntent = Intent.createChooser(sendIntent, null);
//            startActivity(shareIntent);
//        });

        Button edit_main_project_card_btn = view.findViewById(R.id.edit_main_project_card_btn);
        edit_main_project_card_btn.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), EditProjectActivity.class)));

        Button publish_main_project_brn = view.findViewById(R.id.publish_main_project_brn);
        publish_main_project_brn.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PublishProjectActivity.class)));

        return view;
    }
}