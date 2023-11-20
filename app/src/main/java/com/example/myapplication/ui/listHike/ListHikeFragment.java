package com.example.myapplication.ui.listHike;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.HikeAdapter;
import com.example.myapplication.databinding.FragmentListHikeBinding;
import com.example.myapplication.entity.Hike;
import com.example.myapplication.repo.HikeDAO;

import java.util.List;

public class ListHikeFragment extends Fragment implements HikeAdapter.OnHikeListener {
    private HikeDAO hikeDAO;
    private FragmentListHikeBinding binding;
    private RecyclerView hikeRecyclerView;
    private Button clearButton, searchButton;
    private TextView searchEditText;
    private List<Hike> hikes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListHikeViewModel listHikeViewModel = new ViewModelProvider(this).get(ListHikeViewModel.class);
        hikeDAO = HikeDAO.getInstance(getContext());

        binding = FragmentListHikeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        hikeRecyclerView = binding.hikeRecyclerView;
        clearButton = binding.clearButton;
        searchButton = binding.searchButton;
        searchEditText = binding.searchEditText;

        final TextView textView = binding.textListHike;
        listHikeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        hikes = hikeDAO.getAllHikes();

        System.out.println("57 đ: " + hikeDAO.getAllHikes().size());
        System.out.println(hikeDAO.getAllHikes());

        HikeAdapter hikeAdapter = new HikeAdapter(hikes);
        hikeAdapter.setOnHikeListener(this);
        hikeRecyclerView.setAdapter(hikeAdapter);
        hikeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        clearButton.setOnClickListener(view -> {
            hikeDAO.clearAllHikes();
            hikeAdapter.clear();
        });

        searchButton.setOnClickListener(view -> {
            String searchText = searchEditText.getText().toString();
            hikeAdapter.update(hikeDAO.getHikesByNameContaining(searchText));
        });

        return root;
    }

    @Override
    public void onHikeClick(Hike hike) {
        displayHikeDetails(hike);
    }

    private void displayHikeDetails(Hike hike) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("hike", hike);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_listHikeFragment_to_hikeDetailFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}