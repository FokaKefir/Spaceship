package com.fokakefir.spaceship.gui.fragment.commander;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class OrbitalFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private TextView txtTraveledDistance;
    private TextView txtDistance;
    private TextView txtSpeed;
    private TextView txtAcceleration;
    private TextView txtTickRemaining;
    private TextView txtTrackDeviation;

    private ProgressBar progressDistance;

    // endregion

    // region 2. Lifecycle and constructor

    public OrbitalFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander_orbital, container, false);

        this.txtTraveledDistance = this.view.findViewById(R.id.txt_orbital_traveled_distance);
        this.txtDistance = this.view.findViewById(R.id.txt_orbital_distance);
        this.txtSpeed = this.view.findViewById(R.id.txt_orbital_speed);
        this.txtAcceleration = this.view.findViewById(R.id.txt_orbital_acceleration);
        this.txtTickRemaining = this.view.findViewById(R.id.txt_orbital_tick_remaining);
        this.txtTrackDeviation = this.view.findViewById(R.id.txt_orbital_track_deviation);
        this.progressDistance = this.view.findViewById(R.id.progress_orbital_distance);

        return this.view;
    }

    // endregion

    // region 3. Getters and Setters

    public void setTraveledDistance(int distance) {
        this.txtTraveledDistance.setText("Distance traveled: " + distance + " km");
    }

    public void setDistance(int distance) {
        this.txtDistance.setText("Distance: " + distance + " km");
    }

    // endregion
}