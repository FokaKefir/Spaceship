package com.fokakefir.spaceship.gui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.fokakefir.spaceship.gui.fragment.commander.HealthFragment;
import com.fokakefir.spaceship.gui.viewpager.ImageAdapter;

public class MedicalFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private RelativeLayout layoutMedical;

    private Button btnMedicalTable;
    private ImageButton btnClose;

    private Button btnCheck;
    private Button btnHeal;

    // endregion

    // region 2. Lifecycle and constructor

    public MedicalFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_medical, container, false);

        this.layoutMedical = this.view.findViewById(R.id.layout_medical);
        this.btnMedicalTable = this.view.findViewById(R.id.btn_medical_table);
        this.btnClose = this.view.findViewById(R.id.btn_close_medical_panel);
        this.btnCheck = this.view.findViewById(R.id.btn_check);
        this.btnHeal = this.view.findViewById(R.id.btn_heal);

        this.layoutMedical.setVisibility(View.GONE);

        this.btnMedicalTable.setOnClickListener(this);
        this.btnClose.setOnClickListener(this);
        this.btnCheck.setOnClickListener(this);
        this.btnHeal.setOnClickListener(this);

        this.activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_medical, new HealthFragment(this.activity, this.activity.getHealthData())).commit();

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_medical_table) {
            this.layoutMedical.setVisibility(View.VISIBLE);
            Animation animSlideUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
            this.layoutMedical.startAnimation(animSlideUp);

            this.btnMedicalTable.setVisibility(View.GONE);
        } else if (view.getId() == R.id.btn_close_medical_panel) {
            Animation animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
            this.layoutMedical.startAnimation(animSlideDown);
            this.layoutMedical.setVisibility(View.GONE);

            this.btnMedicalTable.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.btn_heal) {
            this.activity.healPlayer();
        } else if (view.getId() == R.id.btn_check) {
            
        }
    }

    // endregion


}