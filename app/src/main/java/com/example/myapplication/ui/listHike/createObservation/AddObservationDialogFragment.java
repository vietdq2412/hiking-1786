package com.example.myapplication.ui.listHike.createObservation;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;

import com.example.myapplication.R;

public class AddObservationDialogFragment extends DialogFragment {

    public static AddObservationDialogFragment newInstance() {
        return new AddObservationDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_add_observation, null))
                .setTitle("Add Observation")
                .setPositiveButton("Save", (dialog, id) -> {
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    AddObservationDialogFragment.this.getDialog().cancel();
                });
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialogTheme);
        return builder.create();
    }
}

