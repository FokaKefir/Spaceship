package com.fokakefir.spaceship.gui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class AirlockFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    // endregion

    // region 2. Lifecycle and constructor

    public AirlockFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_airlock, container, false);

        return this.view;
    }

    // endregion
}