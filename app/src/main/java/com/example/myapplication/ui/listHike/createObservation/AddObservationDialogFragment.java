package com.example.myapplication.ui.listHike.createObservation;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ObservationAdapter;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.entity.Observation;
import com.example.myapplication.repo.ObservationDAO;

import java.util.Date;

public class AddObservationDialogFragment extends DialogFragment {
    private Button addButton;
    private static final String ARG_HIKE_ID = "hikeId";
    private EditText name, comment;
    private ObservationDAO observationDAO;
    private long hikeId;
    private ObservationAdapter observationAdapter;

    public AddObservationDialogFragment(long hikeId) {
        this.hikeId = hikeId;
    }

    public static AddObservationDialogFragment newInstance(long hikeId) {
        AddObservationDialogFragment fragment = new AddObservationDialogFragment(hikeId);
//        Bundle args = new Bundle();
//        args.putLong(ARG_HIKE_ID, hikeId);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        observationDAO = ObservationDAO.getInstance(getContext());
//        if (getArguments() != null) {
//            hikeId = Long.parseLong(getArguments().getString(ARG_HIKE_ID));
//        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_observation, null);
        addButton = dialogView.findViewById(R.id.buttonSaveObservation);
        name = dialogView.findViewById(R.id.editObservationName);
        comment = dialogView.findViewById(R.id.editObservationComment);

        builder.setView(dialogView)
                .setTitle("Add Observation")
                .setPositiveButton("Save", (dialog, id) -> {
                    Observation observation = new Observation(name.getText().toString(),comment.getText().toString(), new Date(), hikeId);
                    observationDAO.insertObservation(observation);
                    observationAdapter = new ObservationAdapter(observationDAO.getAllObservations());
                    observationAdapter.updateObservations(observationDAO.getAllObservationsByHikeID(hikeId));
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    AddObservationDialogFragment.this.getDialog().cancel();
                });
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialogTheme);

        return builder.create();
    }
}

