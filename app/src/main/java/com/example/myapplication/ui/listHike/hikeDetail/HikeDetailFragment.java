package com.example.myapplication.ui.listHike.hikeDetail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHikeDetailBinding;
import com.example.myapplication.entity.Hike;

import java.io.Serializable;

public class HikeDetailFragment extends Fragment {
    private static HikeDetailFragment instance;
    private HikeDetailViewModel mViewModel;
    private FragmentHikeDetailBinding binding;

    private Hike hike;

    public static HikeDetailFragment getInstance(Hike hike) {
        if (instance == null) {
            instance = new HikeDetailFragment();
            instance.hike = hike;
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (getArguments() != null && getArguments().containsKey("hike")) {
            hike = getArguments().getParcelable("hike");
        }
        binding = FragmentHikeDetailBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        binding.textHikeDetail.setText(hike.getName());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HikeDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}