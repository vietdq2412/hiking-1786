package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Hike;

import java.util.List;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.HikeViewHolder> {

    private List<Hike> hikes;

    public HikeAdapter(List<Hike> hikes) {
        this.hikes = hikes;
    }

    @NonNull
    @Override
    public HikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hike_item, parent, false);
        return new HikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder holder, int position) {
        Hike hike = hikes.get(position);
        holder.hikeNameTextView.setText(hike.getName());
        holder.hikeDistanceTextView.setText(String.valueOf(hike.getLengthOfHike()));
        holder.hikeDateTextView.setText(hike.getDate());
        // Bind other properties if needed
    }

    @Override
    public int getItemCount() {
        return hikes.size();
    }

    class HikeViewHolder extends RecyclerView.ViewHolder {
        TextView hikeNameTextView;
        TextView hikeDistanceTextView;
        TextView hikeDateTextView;

        public HikeViewHolder(@NonNull View itemView) {
            super(itemView);
            hikeNameTextView = itemView.findViewById(R.id.hikeNameTextView);
            hikeDistanceTextView = itemView.findViewById(R.id.hikeDistanceTextView);
            hikeDateTextView = itemView.findViewById(R.id.hikeDateTextView);
            // Initialize other views if needed
        }
    }
}
