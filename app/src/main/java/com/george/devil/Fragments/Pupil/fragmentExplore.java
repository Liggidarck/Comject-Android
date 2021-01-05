package com.george.devil.Fragments.Pupil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.george.devil.Activitys.Projects.PostProjectActivity;
import com.george.devil.R;
import com.google.android.material.card.MaterialCardView;

public class fragmentExplore extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        MaterialCardView cardView = view.findViewById(R.id.card1_explore);
        cardView.setOnClickListener(v -> startActivity(new Intent(fragmentExplore.this.getActivity(), PostProjectActivity.class)));

        return view;

    }
}
