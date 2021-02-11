package com.george.devil.BottomSheets;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.george.devil.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetInformationProfile extends BottomSheetDialogFragment {

    TextView topicTextView, bithdayTextView, city_bottomTextView, cityBottomTextView, date_birthday;
    ImageView close;

    /**
     * Подключаемся к {@link SharedPreferences} для получения данных о пользователе.
     * Устанавливаем в {@link TextView} данные о пользовате взятые из {@link SharedPreferences}
     */
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_information_profile, container, false);

        topicTextView = view.findViewById(R.id.topik_bottom);
        bithdayTextView = view.findViewById(R.id.birthday_bottom);
        city_bottomTextView = view.findViewById(R.id.city_bottom);
        cityBottomTextView = view.findViewById(R.id.city_bottom_2);
        date_birthday = view.findViewById(R.id.date_birthday);
        close = view.findViewById(R.id.close_btn);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String topic_pupil = sharedPreferences.getString("topik", "empty_topic");
        String city_pupil = sharedPreferences.getString("city", "empty_city");
        String birthday_pupil = sharedPreferences.getString("birthday", "empty_birthday");

        String topic_teather = sharedPreferences.getString("topic_teather", "topic_teather_empty");
        String city_teather = sharedPreferences.getString("city_teather", "city_teather_empty");
        String birthday_teather = sharedPreferences.getString("birthay_teather", "birthay_teather_empty");

        if(!topic_pupil.equals("empty_topic")) {
            topicTextView.setText(getString(R.string.profile) + ": " + topic_pupil);
            bithdayTextView.setText(getString(R.string.birthday) + ": " + birthday_pupil);
            city_bottomTextView.setText(getString(R.string.city) + ": " + city_pupil);
            cityBottomTextView.setText(city_pupil);
            date_birthday.setText(birthday_pupil);
        }

        if(!topic_teather.equals("topic_teather_empty")){
            topicTextView.setText(getString(R.string.profile) + ": " + topic_teather);
            bithdayTextView.setText(getString(R.string.birthday) + ": " + birthday_teather);
            city_bottomTextView.setText(getString(R.string.city) + ": " + city_teather);
            cityBottomTextView.setText(city_teather);
            date_birthday.setText(birthday_teather);
        }

        close.setOnClickListener(v -> dismiss());

        return view;
    }
}
