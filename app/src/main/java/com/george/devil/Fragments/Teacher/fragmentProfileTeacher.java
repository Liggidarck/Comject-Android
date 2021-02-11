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

    TextView main_name_profile_teacher, main_username_teacher;
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

        followers_layout_teacher = view.findViewById(R.id.followers_layout_teather);
        following_layout_teacher = view.findViewById(R.id.following_layout_teather);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String name_teacher = sharedPreferences.getString("full_name_teather", "full_name_teather_empty");
        String username_teacher = sharedPreferences.getString("username_teather", "username_teather_empty");

        String username = "@" + username_teacher;
        main_name_profile_teacher.setText(name_teacher);
        main_username_teacher.setText(username);

        edit_profile_btn_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), EditProfileTeacher.class)));
        settings_btn_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), SettingsActivity.class)));
        followers_layout_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), FollowingFollowersActivity.class)));
        following_layout_teacher.setOnClickListener(v -> startActivity(new Intent(fragmentProfileTeacher.this.getActivity(), FollowingFollowersActivity.class)));

        return view;
    }
}
