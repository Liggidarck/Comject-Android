package com.george.devil.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.george.devil.Activitys.Main.Pupil.EditProfileActivity;
import com.george.devil.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class BottomSheetFilters extends BottomSheetDialogFragment {

    TextInputLayout topic_layout_filter;
    MaterialAutoCompleteTextView topic_auto_edit_filter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filters, container, false);

        ImageView closeBottomSheet = view.findViewById(R.id.close_btn_filter);
        closeBottomSheet.setOnClickListener(v -> dismiss());

        topic_auto_edit_filter = view.findViewById(R.id.topic_auto_edit_filter);

        String[] items = new String[] {
                "Biology", "Chemistry", "Economics", "English",
                "Engineering/Construction","Geography", "History",
                "IT", "Literature", "Math", "Physics", "Politics",
                "Sports", "Social studies", "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                BottomSheetFilters.this.getActivity(),
                R.layout.dropdown_menu_categories,
                items
        );

        topic_auto_edit_filter.setAdapter(adapter);

        return view;
    }
}
