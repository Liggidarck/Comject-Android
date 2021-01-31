package com.george.devil.Fragments.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.george.devil.Activities.Projects.PagesProjects.ProjectTeacher;
import com.george.devil.R;
import com.google.android.material.card.MaterialCardView;

public class fragmentHomeTeather extends Fragment {

    MaterialCardView card_teather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_teather, container, false);

        card_teather = view.findViewById(R.id.card_teather);
        card_teather.setOnClickListener(v -> startActivity(new Intent(fragmentHomeTeather.this.getActivity(), ProjectTeacher.class)));

        return view;
    }
}
