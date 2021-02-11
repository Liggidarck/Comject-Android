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

import com.george.devil.Activities.Projects.EditsProject.EditProjectActivity;
import com.george.devil.Activities.Projects.PagesProjects.MainProjectActivity;
import com.george.devil.Activities.Projects.NewProject.NewProjectActivity;
import com.george.devil.Activities.Projects.PagesProjects.PrivatePublicProjectActivity;
import com.george.devil.Activities.Projects.PagesProjects.PublishProjectActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragmentHome extends Fragment {

    TextView main_title_project_1, main_description_project, hastags_mail_proj_text;
    FloatingActionButton add_proj;
    MaterialCardView card1, card2, card3, card4;
    Button edit_main_project_card_btn, publish_main_project_brn;
    MaterialToolbar toolbar;

    /**
     * Подключаемся к {@link SharedPreferences}, записываем в строковые переменные сохраненые данные пользователя. Полученыне данные нужны для отрисовки интерфейса.
     *
     * Метод {@link View}  должен вернуть созданный xml. Благодаря этому можно обращаться
     * по id к любому фронтенд интерфейсу.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.topAppBar_home);
        main_title_project_1 = view.findViewById(R.id.main_title_project);
        main_description_project = view.findViewById(R.id.main_description_project);
        hastags_mail_proj_text = view.findViewById(R.id.hastags_mail_proj_text);
        add_proj = view.findViewById(R.id.add_proj);
        edit_main_project_card_btn = view.findViewById(R.id.edit_main_project_card_btn);
        publish_main_project_brn = view.findViewById(R.id.publish_main_project_brn);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String titleMain = sharedPreferences.getString("nameMainProject", "empty_main_project");
        String descroption = sharedPreferences.getString("descriptionMainProject", "empty_absic_description");
        String hastags = sharedPreferences.getString("hastags", "Edit project hastags!");

        main_title_project_1.setText(titleMain);
        main_description_project.setText(descroption);
        hastags_mail_proj_text.setText(hastags);

        add_proj.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), NewProjectActivity.class)));
        card1.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), MainProjectActivity.class)));
        card2.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PrivatePublicProjectActivity.class)));
        card3.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PrivatePublicProjectActivity.class)));
        card4.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PrivatePublicProjectActivity.class)));
        edit_main_project_card_btn.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), EditProjectActivity.class)));
        publish_main_project_brn.setOnClickListener(v -> startActivity(new Intent(fragmentHome.this.getActivity(), PublishProjectActivity.class)));

        return view;
    }
}