package com.example.myapplication.ui.createHike;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCreateHikeBinding;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.repo.HikeDAO;

import java.util.Calendar;

public class CreateHikeFragment extends Fragment {
    private HikeDAO hikeDAO;

    private FragmentCreateHikeBinding binding;
    private EditText nameEditText, locationEditText, descriptionEditText, estimatedDurationEditText,
            peakElevationEditText, dateEditText, lengthOfHikeEditText;
    private RadioGroup parkingRadioGroup;
    private Spinner difficultyLevelSpinner;
    private Button submitButton, clearButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        hikeDAO = HikeDAO.getInstance(getContext());

        binding = FragmentCreateHikeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nameEditText = binding.nameEditText;
        locationEditText = binding.locationEditText;
        descriptionEditText = binding.descriptionEditText;
        estimatedDurationEditText = binding.estimatedDurationEditText;
        peakElevationEditText = binding.peakElevationEditText;
        parkingRadioGroup = binding.parkingAvailabilityRadioGroup;
        lengthOfHikeEditText = binding.lengthOfHikeEditText;
        difficultyLevelSpinner = binding.difficultyLevelSpinner;
        submitButton = binding.submitButton;
        clearButton = binding.clearButton;

        dateEditText = binding.dateEditText;
        dateEditText.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog.OnDateSetListener dateSetListener = (view, year1, monthOfYear, dayOfMonth) -> {
                String selectedDate = year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                dateEditText.setText(selectedDate);
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    dateSetListener, year, month, day);
            datePickerDialog.show();
        });

        setSpinner(difficultyLevelSpinner);
        handleButtons();

        CreateHikeViewModel createHikeViewModel = new ViewModelProvider(this).get(CreateHikeViewModel.class);

        final TextView textView = binding.textCreateHike;
        createHikeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setSpinner(Spinner difficultyLevelSpinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.difficulty_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyLevelSpinner.setAdapter(adapter);
    }

    public void handleButtons() {
        clearButton.setOnClickListener(v -> {
            clearAllFields();
        });

        submitButton.setOnClickListener(v -> {
            if (areRequiredFieldsFilled()) {
                Hike hike = new Hike();
                hike.setName(nameEditText.getText().toString());
                hike.setLocation(locationEditText.getText().toString());
                hike.setDate(dateEditText.getText().toString());

                boolean isParkingAvalable = true;
                int selectedId = parkingRadioGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.parkingNotAvailableRadioButton) {
                    isParkingAvalable = false;
                }

                hike.setParkingAvailability(isParkingAvalable);
                hike.setLengthOfHike(Integer.parseInt(lengthOfHikeEditText.getText().toString()));
                hike.setDifficultyLevel(difficultyLevelSpinner.getSelectedItem().toString());
                hike.setDescription(descriptionEditText.getText().toString());

                Log.d(TAG, hike.toString());

                hikeDAO.addHike(hike);

                System.out.println("133 create hike :" + hikeDAO.hikes.size());
                Toast.makeText(getContext(), "Done!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean areRequiredFieldsFilled() {
        if (TextUtils.isEmpty(nameEditText.getText())
                || TextUtils.isEmpty(locationEditText.getText())
                || TextUtils.isEmpty(estimatedDurationEditText.getText())
                || TextUtils.isEmpty(peakElevationEditText.getText())
                || TextUtils.isEmpty(locationEditText.getText())
                || TextUtils.isEmpty(dateEditText.getText())
                || TextUtils.isEmpty(lengthOfHikeEditText.getText())
                || difficultyLevelSpinner.getSelectedItem() == null
        ) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void clearAllFields() {
        nameEditText.setText("");
        locationEditText.setText("");
        descriptionEditText.setText("");
        estimatedDurationEditText.setText("");
        peakElevationEditText.setText("");
        nameEditText.setText("");
        locationEditText.setText("");
        dateEditText.setText("");
        parkingRadioGroup.clearCheck();
        lengthOfHikeEditText.setText("");
        difficultyLevelSpinner.setSelection(0);
    }


}