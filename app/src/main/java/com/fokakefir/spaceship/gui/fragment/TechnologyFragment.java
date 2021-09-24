package com.fokakefir.spaceship.gui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class TechnologyFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    // endregion

    // region 2. Lifecycle and constructor

    public TechnologyFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_technology, container, false);

        return this.view;
    }

    // endregion
}