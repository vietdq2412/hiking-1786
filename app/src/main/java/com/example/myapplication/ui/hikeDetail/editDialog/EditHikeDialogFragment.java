package com.example.myapplication.ui.hikeDetail.editDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ObservationAdapter;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.entity.Observation;
import com.example.myapplication.repo.HikeDAO;
import com.example.myapplication.repo.ObservationDAO;

import java.util.Date;

public class EditHikeDialogFragment extends DialogFragment {
    private Button addButton;
    private static final String ARG_HIKE_ID = "hikeId";
    private EditText nameEditText, locationEditText, lengthOfHikeEditText, estimatedDurationEditText, peakElevationEditText, descriptionEditText, dateEditText;
    private Spinner difficultyLevelSpinner;
    private Hike currentHike;
    private HikeDAO hikeDAO;
    private long hikeId;

    public EditHikeDialogFragment(long hikeId) {
        this.hikeId = hikeId;
    }

    public static EditHikeDialogFragment newInstance(long hikeId) {
        EditHikeDialogFragment fragment = new EditHikeDialogFragment(hikeId);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        hikeDAO = HikeDAO.getInstance(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_hike, null);
        this.currentHike = hikeDAO.findHikeById(hikeId);

        nameEditText = dialogView.findViewById(R.id.nameEditText);
        locationEditText = dialogView.findViewById(R.id.locationEditText);
        lengthOfHikeEditText = dialogView.findViewById(R.id.lengthOfHikeEditText);
        estimatedDurationEditText = dialogView.findViewById(R.id.estimatedDurationEditText);
        peakElevationEditText = dialogView.findViewById(R.id.peakElevationEditText);
        descriptionEditText = dialogView.findViewById(R.id.descriptionEditText);
        dateEditText = dialogView.findViewById(R.id.dateEditText);
        difficultyLevelSpinner = dialogView.findViewById(R.id.difficultyLevelSpinner);

        fillHikeDataToLayout();
        builder.setView(dialogView)
                .setTitle("Update hike")
                .setNegativeButton("Cancel", (dialog, id) -> {
                    EditHikeDialogFragment.this.getDialog().cancel();
                });
        return builder.create();
    }

    private void fillHikeDataToLayout() {
        if (currentHike != null) {
            nameEditText.setText(currentHike.getName());
            locationEditText.setText(currentHike.getLocation());
            lengthOfHikeEditText.setText(String.valueOf(currentHike.getLengthOfHike()));
//            estimatedDurationEditText.setText(currentHike.getEstimatedDuration());
//            peakElevationEditText.setText(String.valueOf(currentHike.getPeakElevation()));
            descriptionEditText.setText(currentHike.getDescription());
            dateEditText.setText(currentHike.getDate());

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.difficulty_levels, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            difficultyLevelSpinner.setAdapter(adapter);


            if (currentHike.getDifficultyLevel() != null) {
                int spinnerPosition = adapter.getPosition(currentHike.getDifficultyLevel());
                difficultyLevelSpinner.setSelection(spinnerPosition);
            }
        }
    }

}

