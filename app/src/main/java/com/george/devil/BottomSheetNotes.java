package com.george.devil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetNotes extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_notes, container, false);

        RelativeLayout del = view.findViewById(R.id.delet_note_layout);
        del.setOnClickListener(v -> {
            mListener.onButtonClicked("Button delete clicked");
            dismiss();
        });

        RelativeLayout copy = view.findViewById(R.id.copy_content_bottom);
        copy.setOnClickListener(v -> {
            mListener.onButtonClicked("Button copy clicked");
            dismiss();
        });

        RelativeLayout share = view.findViewById(R.id.share_content_bottom);
        share.setOnClickListener(v -> {
            mListener.onButtonClicked("Button share clicked");
            dismiss();
        });

        return view;

    }


    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

}
