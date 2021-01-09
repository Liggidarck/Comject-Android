package com.george.devil.Fragments.Pupil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;

public class fragmentSearchExplore extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_explore, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar_search_explore);
        toolbar.setNavigationOnClickListener(v -> {
            Fragment goToExplore = new fragmentExplore();
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, goToExplore).commit();
        });

        return view;
    }
}
