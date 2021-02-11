package com.george.devil.Fragments.Pupil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.george.devil.Activities.Main.Pupil.MessageActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragmentMessege extends Fragment {

    static final String TAG = "fragmentMessage";
    MaterialToolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messege, container, false);

        RelativeLayout fist = view.findViewById(R.id.fist_layout_chat);
        RelativeLayout second = view.findViewById(R.id.second_layout_message);
        RelativeLayout fird = view.findViewById(R.id.fird_layout_message);
        RelativeLayout four = view.findViewById(R.id.four_layout_message);
        Intent goMessage = new Intent(fragmentMessege.this.getActivity(), MessageActivity.class);

        toolbar = view.findViewById(R.id.topAppBar_message);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        FloatingActionButton newChat = view.findViewById(R.id.new_chat_fab);
        newChat.setOnClickListener(v -> {
            Fragment newChatFrag = new fragmentNewChat();
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_pupil_container, newChatFrag).commit();
        });

        fist.setOnClickListener(v -> {
            goMessage.putExtra("name_message", "Kate Sheptukhina");
            goMessage.putExtra("id_message", 0);
            startActivity(goMessage);
        });

        second.setOnClickListener(v -> {
            goMessage.putExtra("name_message", "Anton Rovenko");
            goMessage.putExtra("id_message", 1);
            startActivity(goMessage);
        });

        fird.setOnClickListener(v -> {
            goMessage.putExtra("name_message", "Fire Owl");
            goMessage.putExtra("id_message", 2);
            startActivity(goMessage);
        });

        four.setOnClickListener(v -> {
            goMessage.putExtra("name_message", "George Filatov");
            goMessage.putExtra("id_message", 3);
            startActivity(goMessage);
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.search_explore_menu)
            Log.i(TAG, "Search clicked!");

        return super.onOptionsItemSelected(item);
    }
}