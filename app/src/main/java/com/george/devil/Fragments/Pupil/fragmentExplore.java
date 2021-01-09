package com.george.devil.Fragments.Pupil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.george.devil.Activitys.Projects.PagesProjects.PostProjectActivity;
import com.george.devil.BottomSheets.BottomSheetFilters;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class fragmentExplore extends Fragment {

    static final String TAG = "fragmentExplore";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar_explore);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(v -> {
            BottomSheetFilters bottom_sheet_filter = new BottomSheetFilters();
            bottom_sheet_filter.show(requireActivity().getSupportFragmentManager(), "BottomSheetNotes");
        });

        MaterialCardView cardView = view.findViewById(R.id.card1_explore);
        cardView.setOnClickListener(v -> startActivity(new Intent(fragmentExplore.this.getActivity(), PostProjectActivity.class)));

        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.search_explore_menu) {
            Fragment searchFrag = new fragmentSearchExplore();
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, searchFrag).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
