package com.george.devil.Fragments.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.george.devil.Activities.Main.Pupil.CommentsActivity;
import com.george.devil.Activities.Main.Pupil.ProfileActivityPupil;
import com.george.devil.Activities.Projects.PagesProjects.PostProjectActivity;
import com.george.devil.BottomSheets.BottomSheetFilters;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class fragmentExplore extends Fragment {

    Button like_btn_1, like_btn_2, comment_post_Explore, subscribe_btn, subscribed_btn;
    MaterialToolbar toolbar;
    RelativeLayout top_profile_card;
    MaterialCardView cardView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        like_btn_1 = view.findViewById(R.id.like_btn_1);
        like_btn_2 = view.findViewById(R.id.like_btn_2);
        subscribe_btn = view.findViewById(R.id.subscribe_btn);
        subscribed_btn = view.findViewById(R.id.subscribed_btn);
        top_profile_card = view.findViewById(R.id.top_profile_card);
        comment_post_Explore = view.findViewById(R.id.comment_post_Explore);
        toolbar = view.findViewById(R.id.topAppBar_explore);
        cardView = view.findViewById(R.id.card1_explore);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(v -> {
            BottomSheetFilters bottom_sheet_filter = new BottomSheetFilters();
            bottom_sheet_filter.show(requireActivity().getSupportFragmentManager(), "BottomSheetNotes");
        });

        cardView.setOnClickListener(v -> startActivity(new Intent(fragmentExplore.this.getActivity(), PostProjectActivity.class)));
        top_profile_card.setOnClickListener(v -> startActivity(new Intent(fragmentExplore.this.getActivity(), ProfileActivityPupil.class)));

        like_btn_1.setOnClickListener(v -> {
            like_btn_1.setVisibility(View.INVISIBLE);
            like_btn_2.setVisibility(View.VISIBLE);
        });

        like_btn_2.setOnClickListener(v -> {
            like_btn_1.setVisibility(View.VISIBLE);
            like_btn_2.setVisibility(View.INVISIBLE);
        });

        subscribe_btn.setOnClickListener(v -> {
            subscribe_btn.setVisibility(View.INVISIBLE);
            subscribed_btn.setVisibility(View.VISIBLE);
        });

        subscribed_btn.setOnClickListener(v -> {
            subscribe_btn.setVisibility(View.VISIBLE);
            subscribed_btn.setVisibility(View.INVISIBLE);
        });

        comment_post_Explore.setOnClickListener(v -> startActivity(new Intent(fragmentExplore.this.getActivity(), CommentsActivity.class)));

        return view;

    }

    /**
     * Подключаемся к menu для отрисовки в {@link MaterialToolbar} кнопки Search
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
