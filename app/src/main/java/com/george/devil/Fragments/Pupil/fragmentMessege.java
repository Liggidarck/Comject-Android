package com.george.devil.Fragments.Pupil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.george.devil.Activitys.Main.Pupil.MessageActivity;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragmentMessege extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messege, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar_message);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        FloatingActionButton newChat = view.findViewById(R.id.new_chat_fab);
        newChat.setOnClickListener(v -> {
            Fragment newChatFrag = new fragmentNewChat();
            assert getParentFragmentManager() != null;
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, newChatFrag).commit();

        });

        RelativeLayout fist = view.findViewById(R.id.fist_layout_chat);
        RelativeLayout second = view.findViewById(R.id.second_layout_message);
        RelativeLayout fird = view.findViewById(R.id.fird_layout_message);
        RelativeLayout four = view.findViewById(R.id.four_layout_message);
        Intent goMessage = new Intent(fragmentMessege.this.getActivity(), MessageActivity.class);

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
}
