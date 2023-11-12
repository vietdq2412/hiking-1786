package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Observation;

import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ViewHolder> {

    private List<Observation> observations;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView observationName;
        private final TextView observationComment;

        public ViewHolder(View view) {
            super(view);
            observationName = view.findViewById(R.id.observation_name);
            observationComment = view.findViewById(R.id.observation_comment);
        }

        public void bind(Observation observation) {
            observationName.setText(observation.getName());
            observationComment.setText(observation.getComment());
        }
    }

    public ObservationAdapter(List<Observation> observations) {
        this.observations = observations;
    }

    @NonNull
    @Override
    public ObservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_observation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(observations.get(position));
    }

    @Override
    public int getItemCount() {
        return observations != null ? observations.size() : 0;
    }

    public void updateObservations(List<Observation> newObservations) {
        observations = newObservations;
        notifyDataSetChanged();
    }
}

