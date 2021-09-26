package com.fokakefir.spaceship.gui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class CommanderFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private RelativeLayout layoutCommander;

    private Button btnCommanderTable;
    private ImageButton btnClose;

    // endregion

    // region 2. Lifecycle and constructor

    public CommanderFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander, container, false);

        this.layoutCommander = this.view.findViewById(R.id.layout_commander);
        this.btnCommanderTable = this.view.findViewById(R.id.btn_commander_table);
        this.btnClose = this.view.findViewById(R.id.btn_close_command_panel);

        this.layoutCommander.setVisibility(View.GONE);
        this.layoutCommander.setAlpha(0.75f);

        this.btnCommanderTable.setOnClickListener(this);
        this.btnClose.setOnClickListener(this);

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_commander_table) {
            this.layoutCommander.setVisibility(View.VISIBLE);
            Animation animSlideUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
            this.layoutCommander.startAnimation(animSlideUp);

            this.btnCommanderTable.setVisibility(View.GONE);
        } else if (view.getId() == R.id.btn_close_command_panel) {
            Animation animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
            this.layoutCommander.startAnimation(animSlideDown);
            this.layoutCommander.setVisibility(View.GONE);

            this.btnCommanderTable.setVisibility(View.VISIBLE);
        }
    }

    // endregion
}