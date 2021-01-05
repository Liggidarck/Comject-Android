package com.george.devil.BottomSheets;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.george.devil.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetInformationProfile extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_information_profile, container, false);

        ImageView close = view.findViewById(R.id.close_btn);
        close.setOnClickListener(v -> dismiss());

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        String topic = sharedPreferences.getString("topik", "empty_topic");
        String city = sharedPreferences.getString("city", "empty_city");
        String birthday = sharedPreferences.getString("birthday", "empty_birthday");

        TextView topicTextView = view.findViewById(R.id.topik_bottom);
        topicTextView.setText("Profile: " + topic);

        TextView bithdayTextView = view.findViewById(R.id.birthday_bottom);
        bithdayTextView.setText("Birthday: " + birthday);

        TextView city_bottomTextView = view.findViewById(R.id.city_bottom);
        city_bottomTextView.setText("City: " + city);

        TextView cityBottomTextView = view.findViewById(R.id.city_bottom_2);
        cityBottomTextView.setText(city);

        TextView date_birthday = view.findViewById(R.id.date_birthday);
        date_birthday.setText(birthday);


        return view;
    }
}
