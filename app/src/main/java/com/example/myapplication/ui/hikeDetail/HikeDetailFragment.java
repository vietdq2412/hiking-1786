package com.example.myapplication.ui.hikeDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ObservationAdapter;
import com.example.myapplication.databinding.FragmentHikeDetailBinding;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.entity.Observation;
import com.example.myapplication.repo.HikeDAO;
import com.example.myapplication.ui.listHike.createObservation.AddObservationDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HikeDetailFragment extends Fragment {
    private HikeDetailViewModel mViewModel;
    private FragmentHikeDetailBinding binding;
    private RecyclerView observationRecyclerView;
    private Button deleteButton, addObservationButton;
    private HikeDAO hikeDAO;
    private Hike hike;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        hikeDAO = HikeDAO.getInstance(getContext());

        if (getArguments() != null && getArguments().containsKey("hike")) {
            hike = getArguments().getParcelable("hike");
        }
        binding = FragmentHikeDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        deleteButton = binding.deleteButton;
        addObservationButton = binding.addObservationButton;

        if (getArguments() != null && getArguments().containsKey("hike")) {
            hike = getArguments().getParcelable("hike");
            if (hike != null) {
                binding.textHikeName.setText(hike.getName());
                binding.textHikeLocation.setText(hike.getLocation());
                binding.textHikeDate.setText(hike.getDate());
                binding.textHikeParking.setText(hike.getParkingAvailability() ? getString(R.string.parking_available) : getString(R.string.parking_not_available));
                binding.textHikeLength.setText(String.valueOf(hike.getLengthOfHike()));
                binding.textHikeDifficulty.setText(hike.getDifficultyLevel());
                binding.textHikeDescription.setText(hike.getDescription());
            }
        } else {
            // Handle the case where no Hike data was passed in
            // You might want to close the fragment or show an error message
        }

        List<Observation> observations = new ArrayList<>();
        observations.add(new Observation(1l, "OB1", "comment", new Date(),hike.getId()));
        observations.add(new Observation(1l, "OB1", "comment", new Date(),hike.getId()));
        observations.add(new Observation(1l, "OB1", "comment", new Date(),hike.getId()));
        ObservationAdapter adapter = new ObservationAdapter(observations);

        observationRecyclerView = binding.recyclerViewObservations;
        observationRecyclerView.setAdapter(adapter);
        observationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        deleteButton.setOnClickListener(view -> {
            //dosthing
        });
        addObservationButton.setOnClickListener(view -> {
            showFormAddObservation();
        });
        return root;
    }

    public void showFormAddObservation(){
        FragmentManager fragmentManager = getFragmentManager();
        AddObservationDialogFragment dialog = AddObservationDialogFragment.newInstance();
        dialog.show(fragmentManager, "AddObservationDialogFragment");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HikeDetailViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}