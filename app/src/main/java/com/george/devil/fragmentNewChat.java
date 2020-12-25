package com.george.devil;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

import static android.content.Context.SEARCH_SERVICE;

public class fragmentNewChat extends Fragment {

    private static final String TAG = "fragmentNewChat";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_chat, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar_new_message);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        toolbar.setNavigationOnClickListener(v -> {
            Fragment fragmentMess = new fragmentMessege();
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentMess).commit();
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_chat, menu);


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.seart_new_chat)
            Log.i(TAG, "Search toolbar menu cliked");


        return super.onOptionsItemSelected(item);
    }
}
