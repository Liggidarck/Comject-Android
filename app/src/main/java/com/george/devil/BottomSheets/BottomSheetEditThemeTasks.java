package com.george.devil.BottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.george.devil.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSheetEditThemeTasks extends BottomSheetDialogFragment {

    private BottomSheetEditThemeTasks.StateListener listener;

    CircleImageView check_defualt_tasks, check_red_tasks, check_orange_tasks, check_yellow_tasks,
            check_green_tasks, check_green_secondary_tasks, check_blue_ligth_tasks, check_blue_tasks,
            check_violet_tasks, check_pink_tasks, gray_layout_tasks;

    String themeTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_edit_theme_tasks_layout, container, false);

        check_defualt_tasks = view.findViewById(R.id.check_defualt_tasks);
        check_red_tasks = view.findViewById(R.id.check_red_tasks);
        check_orange_tasks = view.findViewById(R.id.check_orange_tasks);
        check_yellow_tasks = view.findViewById(R.id.check_yellow_tasks);
        check_green_tasks = view.findViewById(R.id.check_green_tasks);
        check_green_secondary_tasks = view.findViewById(R.id.check_green_secondary_tasks);
        check_blue_ligth_tasks = view.findViewById(R.id.check_blue_ligth_tasks);
        check_blue_tasks = view.findViewById(R.id.check_blue_tasks);
        check_violet_tasks = view.findViewById(R.id.check_violet_tasks);
        check_pink_tasks = view.findViewById(R.id.check_pink_tasks);
        gray_layout_tasks = view.findViewById(R.id.gray_layout_tasks);

        RelativeLayout default_theme = view.findViewById(R.id.white_layout_tasks);
        default_theme.setOnClickListener(v -> {
            listener.ThemeChoose("Default");
            dismiss();
        });

        RelativeLayout red_layout_tasks = view.findViewById(R.id.red_layout_tasks);
        red_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Red");
            dismiss();
        });

        RelativeLayout orange_layout_tasks = view.findViewById(R.id.orange_layout_tasks);
        orange_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Orange");
            dismiss();
        });

        RelativeLayout yellow_layout_tasks = view.findViewById(R.id.yellow_layout_tasks);
        yellow_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Yellow");
            dismiss();
        });

        RelativeLayout green_layout_tasks = view.findViewById(R.id.green_layout_tasks);
        green_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Green");
            dismiss();
        });

        RelativeLayout green_secondary_layout_tasks = view.findViewById(R.id.green_secondary_layout_tasks);
        green_secondary_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Green Secondary");
            dismiss();
        });

        RelativeLayout ligth_blue_layout_tasks = view.findViewById(R.id.ligth_blue_layout_tasks);
        ligth_blue_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Light Blue");
            dismiss();
        });

        RelativeLayout blue_layout_tasks = view.findViewById(R.id.blue_layout_tasks);
        blue_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Blue");
            dismiss();
        });

        RelativeLayout violet_layout_tasks = view.findViewById(R.id.violet_layout_tasks);
        violet_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Violet");
            dismiss();
        });

        RelativeLayout pink_layout_tasks = view.findViewById(R.id.pink_layout_tasks);
        pink_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Pink");
            dismiss();
        });

        RelativeLayout gray_layout_tasks = view.findViewById(R.id.gray_layout_tasks);
        gray_layout_tasks.setOnClickListener(v -> {
            listener.ThemeChoose("Gray");
            dismiss();
        });

        return view;
    }

    public interface StateListener{
        void ThemeChoose(String tex);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (StateListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
