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

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class SolarShieldFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private RelativeLayout layoutLab;

    private Button btnLabTable;
    private ImageButton btnClose;

    private Button btnHide;

    // endregion

    // region 2. Lifecycle and constructor

    public SolarShieldFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_lab, container, false);

        this.layoutLab = this.view.findViewById(R.id.layout_lab);
        this.btnLabTable = this.view.findViewById(R.id.btn_lab_table);
        this.btnClose = this.view.findViewById(R.id.btn_close_lab_panel);
        this.btnHide = this.view.findViewById(R.id.btn_hide);

        this.layoutLab.setVisibility(View.GONE);

        this.btnLabTable.setOnClickListener(this);
        this.btnClose.setOnClickListener(this);
        this.btnHide.setOnClickListener(this);

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_lab_table) {
            this.layoutLab.setVisibility(View.VISIBLE);
            Animation animSlideUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
            this.layoutLab.startAnimation(animSlideUp);

            this.btnLabTable.setVisibility(View.GONE);
        } else if (view.getId() == R.id.btn_close_lab_panel) {
            Animation animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
            this.layoutLab.startAnimation(animSlideDown);
            this.layoutLab.setVisibility(View.GONE);

            this.btnLabTable.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.btn_hide) {
            if (this.activity.isPlayerMovable()) {
                this.btnHide.setText("Egress Shield");
            } else {
                this.btnHide.setText("Ingress Shield");
            }
            this.activity.setPlayerMovable(!this.activity.isPlayerMovable());
        }
    }

    // endregion
}