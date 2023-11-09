package com.example.myapplication.ui.listHike.hikeDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHikeDetailBinding;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.repo.HikeDAO;

public class HikeDetailFragment extends Fragment {
    private HikeDetailViewModel mViewModel;
    private FragmentHikeDetailBinding binding;
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

        if (getArguments() != null && getArguments().containsKey("hike")) {
            Hike hike = getArguments().getParcelable("hike");
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
        return root;
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