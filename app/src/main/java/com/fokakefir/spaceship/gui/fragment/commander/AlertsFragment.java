package com.fokakefir.spaceship.gui.fragment.commander;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.fokakefir.spaceship.gui.recyclerview.AlertAdapter;
import com.fokakefir.spaceship.model.Alert;

import java.util.List;

public class AlertsFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private RecyclerView recyclerView;
    private AlertAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Alert> alerts;

    // endregion

    // region 2. Lifecycle and constructor

    public AlertsFragment(MainActivity activity, List<Alert> alerts) {
        this.activity = activity;
        this.alerts = alerts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander_alerts, container, false);

        this.recyclerView = this.view.findViewById(R.id.recycler_view_alerts);
        this.layoutManager = new LinearLayoutManager(getContext());
        this.adapter = new AlertAdapter(this.alerts);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setAdapter(this.adapter);

        return this.view;
    }

    // endregion
}