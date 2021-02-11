package com.george.devil.Fragments.Pupil;


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

import com.george.devil.BottomSheets.BottomSheetInformationProfile;
import com.george.devil.Activities.Main.Pupil.EditProfilePupil;
import com.george.devil.Activities.Main.Common.FollowingFollowersActivity;
import com.george.devil.R;
import com.george.devil.Activities.Main.Common.SettingsActivity;

public class fragmentProfile extends Fragment {

    TextView nameProfile, usernameText;
    RelativeLayout following_layout, followers_layout;
    Button settings, rditProfile, general_info;

    /**
     * Подключаемся к {@link SharedPreferences}, записываем в строковые переменные сохраненые данные пользователя.
     * Полученыне данные нужны для отрисовки пользовательского интерфейса.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        settings = view.findViewById(R.id.settings_btn);
        rditProfile = view.findViewById(R.id.edit_profile_btn);
        general_info = view.findViewById(R.id.general_info);
        followers_layout = view.findViewById(R.id.followers_layout);
        following_layout = view.findViewById(R.id.following_layout);
        nameProfile = view.findViewById(R.id.main_name_profile);
        usernameText = view.findViewById(R.id.main_username);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String name_user = sharedPreferences.getString("full_name", "empty_user_name");
        String username = sharedPreferences.getString("username", "empty_correct_username");

        String username_full = "@" + username;
        nameProfile.setText(name_user);
        usernameText.setText(username_full);

        general_info.setOnClickListener(v -> {
            BottomSheetInformationProfile bottomSheet = new BottomSheetInformationProfile();
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "BottomSheetInfo");
        });

        followers_layout.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), FollowingFollowersActivity.class)));
        following_layout.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), FollowingFollowersActivity.class)));
        settings.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), SettingsActivity.class)));
        rditProfile.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), EditProfilePupil.class)));

        return view;
    }
}