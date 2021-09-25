package com.fokakefir.spaceship.gui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class TechnologyFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private Button btnDisplayTop;
    private Button btnDisplayBottom;

    // endregion

    // region 2. Lifecycle and constructor

    public TechnologyFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_technology, container, false);

        this.btnDisplayTop = this.view.findViewById(R.id.btn_technology_display_top);
        this.btnDisplayBottom = this.view.findViewById(R.id.btn_technology_display_bottom);

        this.btnDisplayTop.setOnClickListener(this);
        this.btnDisplayBottom.setOnClickListener(this);

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_technology_display_top) {
            Toast.makeText(this.activity, "top display", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btn_technology_display_bottom) {
            Toast.makeText(this.activity, "bottom display", Toast.LENGTH_SHORT).show();
        }
    }

    // endregion
}