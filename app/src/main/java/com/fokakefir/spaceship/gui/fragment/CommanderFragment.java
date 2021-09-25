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

public class CommanderFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private Button btnCommanderTable;

    // endregion

    // region 2. Lifecycle and constructor

    public CommanderFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander, container, false);

        this.btnCommanderTable = this.view.findViewById(R.id.btn_commander_table);

        this.btnCommanderTable.setOnClickListener(this);

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_commander_table) {
            Toast.makeText(this.activity, "commander table", Toast.LENGTH_SHORT).show();
        }
    }

    // endregion
}