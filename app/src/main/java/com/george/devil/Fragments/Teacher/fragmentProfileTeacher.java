package com.george.devil.Fragments.Teacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.george.devil.Activities.Main.Common.FollowingFollowersActivity;
import com.george.devil.Activities.Main.Common.SettingsActivity;
import com.george.devil.Activities.Main.Teacher.EditProfileTeacher;
import com.george.devil.R;

public class fragmentProfileTeacher extends Fragment {

    TextView main_name_profile_teacher, main_username_teacher, topik_bottom, birthday_bottom, city_bottom, shloee_bottom;
    Button edit_profile_btn_teacher, settings_btn_teacher;
    RelativeLayout followers_layout_teacher, following_layout_teacher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_teather, container, false);

        main_name_profile_teacher = view.findViewById(R.id.main_name_profile_teather);
        main_username_teacher = view.findViewById(R.id.main_username_teather);
        settings_btn_teacher = view.findViewById(R.id.settings_btn_teather);
        edit_profile_btn_teacher = view.findViewById(R.id.edit_profile_teather);

        topik_bottom = view.findViewById(R.id.topik_bottom);
        birthday_bottom = view.findViewById(R.id.birthday_bottom);
        city_bottom = view.findViewById(R.id.city_bottom);
        shloee_bottom = view.findViewById(R.id.shloee_bottom);

        followers_layout_teacher = view.findViewById(R.id.followers_layout_teather);
        following_layout_teacher = view.findViewById(R.id.following_layout_teather);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String name_teacher = sharedPreferences.getString("full_name_teather", "full_name_teather_empty");
        String username_teacher = sharedPreferences.getString("username_teather", "username_teather_empty");

        String topic_teacher = sharedPreferences.getString("topic_teather", "topic_teather_empty");
        String birthday_teacher = sharedPreferences.getString("birthay_teather", "topic_teather_empty");
        String city_teacher = sharedPreferences.getString("city_teather", "topic_teather_empty");
        String schoole_teatcher = sharedPreferences.getString("school_teather", "topic_teather_empty");


        String username = "@" + username_teacher;
        main_name_profile_teacher.setText(name_teacher);
        main_username_teacher.setText(username);

        topik_bottom.setText(getText(R.string.topic) + ": " + topic_teacher);
        birthday_bottom.setText(getText(R.string.birthday) + ": " + birthday_teacher);
        city_bottom.setText(getText(R.string.city) + ": " + city_teacher);
        shloee_bottom.setText(getText(R.string.school) + ": " + schoole_teatcher);

        edit_profile_btn_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), EditProfileTeacher.class)));
        settings_btn_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), SettingsActivity.class)));
        followers_layout_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), FollowingFollowersActivity.class)));
        following_layout_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), FollowingFollowersActivity.class)));

        return view;
    }
}
