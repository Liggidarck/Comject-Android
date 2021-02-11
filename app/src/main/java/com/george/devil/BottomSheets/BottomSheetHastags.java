package com.george.devil.BottomSheets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.george.devil.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class BottomSheetHastags extends BottomSheetDialogFragment {

    private StateListenerHashtag listenerHastags;

    TextView text_hatsk_sheet;

    TextInputLayout text_has;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_hastags_lay, container, false);

        text_hatsk_sheet = view.findViewById(R.id.text_hatsk_sheet);
        Button edit = view.findViewById(R.id.btn_edit_chas);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity().getBaseContext());
        String hastags = sharedPreferences.getString("hastags", "Edit project hastags!");
        text_hatsk_sheet.setText(hastags);

        edit.setOnClickListener(v -> {
            final AlertDialog.Builder alert = new AlertDialog.Builder(BottomSheetHastags.this.getActivity());
            View mvie = getLayoutInflater().inflate(R.layout.dialog_edit_hastags, null);

            text_has = mvie.findViewById(R.id.schats_editex);
            Button cencel = mvie.findViewById(R.id.cancel_button_edit_hsatag);
            Button ok = mvie.findViewById(R.id.done_edit_hsatag);

            Objects.requireNonNull(text_has.getEditText()).setText(hastags);

            alert.setView(mvie);
            final AlertDialog alertDialog = alert.create();

            ok.setOnClickListener(v1 -> {
                if(!validateHashtag()){
                    return;
                } else {
                    String hastags_edited = text_has.getEditText().getText().toString();
                    sharedPreferences.edit().remove("hastags").apply();
                    sharedPreferences.edit().putString("hastags", hastags_edited).apply();
                    text_hatsk_sheet.setText(hastags_edited);
                    listenerHastags.hastagsText("" + hastags_edited);
                    alertDialog.dismiss();
                }

            });
            cencel.setOnClickListener(v1 -> alertDialog.dismiss());

            alertDialog.show();

        });

        return view;
    }

    /**
     * Метод для отправки полученных данных из {@link android.widget.EditText} в активити.
     */
    public interface StateListenerHashtag {
        void hastagsText(String textHast);
    }


    /**
     * @return возвращает true/false для проверки поля {@link TextInputLayout} на пустоту. и отрисовывает ошибку.
     */
    public boolean validateHashtag() {

        String check = Objects.requireNonNull(text_has.getEditText()).getText().toString().trim();

        if(check.isEmpty()){
            text_has.setError("Это поле не может быть пустом");

            return false;
        } else {
            text_has.setError(null);
            return true;
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listenerHastags = (StateListenerHashtag) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
