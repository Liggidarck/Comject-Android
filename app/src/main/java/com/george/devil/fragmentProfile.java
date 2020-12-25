package com.george.devil;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class fragmentProfile extends Fragment {

    TextView nameProfile, usernameText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //Изменить цвет статус бара
//        Window window = Objects.requireNonNull(fragmentProfile.this.getActivity()).getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(fragmentProfile.this.getActivity(),R.color.status_bar_color_profile));

//        Button settings = view.findViewById(R.id.settings_btn);
//        settings.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), SettingsActivity.class)));

        Button rditProfile = view.findViewById(R.id.edit_profile_btn);
        rditProfile.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), EditProfileActivity.class)));

        Button general_info = view.findViewById(R.id.general_info);
        general_info.setOnClickListener(v -> {
            BottomSheetInformationProfile bottomSheet = new BottomSheetInformationProfile();
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "BottomSheetInfo");
        });

        RelativeLayout followers_layout = view.findViewById(R.id.followers_layout);
        followers_layout.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), FollowingFollowersActivity.class)));

        RelativeLayout following_layout = view.findViewById(R.id.following_layout);
        following_layout.setOnClickListener(v -> startActivity(new Intent(fragmentProfile.this.getActivity(), FollowingFollowersActivity.class)));

        nameProfile = view.findViewById(R.id.main_name_profile);
        usernameText = view.findViewById(R.id.main_username);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        String name_user = sharedPreferences.getString("full_name", "empty_user_name");
        String username = sharedPreferences.getString("username", "empty_correct_username");

        nameProfile.setText(name_user);
        usernameText.setText(username);


        return view;
    }
}