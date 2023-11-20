package com.example.myapplication.ui.hikeDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ObservationAdapter;
import com.example.myapplication.databinding.FragmentHikeDetailBinding;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.entity.Observation;
import com.example.myapplication.repo.HikeDAO;
import com.example.myapplication.repo.ObservationDAO;
import com.example.myapplication.ui.hikeDetail.createObservation.AddObservationDialogFragment;
import com.example.myapplication.ui.hikeDetail.editDialog.EditHikeDialogFragment;

import java.util.List;

public class HikeDetailFragment extends Fragment {
    private FragmentHikeDetailBinding binding;
    private RecyclerView observationRecyclerView;
    private ObservationAdapter observationAdapter;
    private Button deleteButton,editButton, addObservationButton;
    private HikeDAO hikeDAO;
    private ObservationDAO observationDAO;
    private Hike hike;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        hikeDAO = HikeDAO.getInstance(getContext());
        observationDAO = ObservationDAO.getInstance(getContext());

        if (getArguments() != null && getArguments().containsKey("hike")) {
            hike = getArguments().getParcelable("hike");
        }
        binding = FragmentHikeDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        deleteButton = binding.deleteButton;
        editButton = binding.editButton;
        addObservationButton = binding.addObservationButton;

        if (getArguments() != null && getArguments().containsKey("hike")) {
            hike = getArguments().getParcelable("hike");
            if (hike != null) {
                binding.textHikeName.setText(hike.getName());
                binding.textHikeLocation.setText(hike.getLocation());
                binding.textHikeDate.setText(hike.getDate());
                binding.textHikeParking.setText(hike.getParkingAvailability() ? getString(R.string.parking_available) : getString(R.string.parking_not_available));
                binding.textHikeLength.setText(String.valueOf(hike.getLengthOfHike())+"km");
                binding.textHikeDifficulty.setText(hike.getDifficultyLevel());
                binding.textHikePeak.setText(String.valueOf(hike.getPeak()));
                binding.textHikeDuration.setText(hike.getDuration() + "h");
                binding.textHikeDescription.setText(hike.getDescription()+"");
            }
        } else {

        }

        List<Observation> observations = observationDAO.getAllObservationsByHikeID(hike.getId());

        observationAdapter = new ObservationAdapter(observations);

        observationRecyclerView = binding.recyclerViewObservations;
        observationRecyclerView.setAdapter(observationAdapter);
        observationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        deleteButton.setOnClickListener(view -> {
            hikeDAO.deleteHike(hike.getId());
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_list_hike);
            Toast.makeText(getContext(), "Deleted "+hike.getName(), Toast.LENGTH_LONG).show();
        });
        addObservationButton.setOnClickListener(view -> {
            showFormAddObservation();
        });
        editButton.setOnClickListener(view -> {
            showFormEditHike();
        });
        return root;
    }

    public void showFormAddObservation(){
        FragmentManager fragmentManager = getFragmentManager();
        AddObservationDialogFragment dialog = AddObservationDialogFragment.newInstance((hike.getId()));
        dialog.show(fragmentManager, "AddObservationDialogFragment");
    }

    public void showFormEditHike(){
        FragmentManager fragmentManager = getFragmentManager();
        EditHikeDialogFragment dialog = EditHikeDialogFragment.newInstance((hike.getId()));
        System.out.println("EditDialogFragment");
        dialog.show(fragmentManager, "EditDialogFragment");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}