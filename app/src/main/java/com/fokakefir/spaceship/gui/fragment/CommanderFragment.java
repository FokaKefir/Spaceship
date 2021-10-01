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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.fokakefir.spaceship.gui.fragment.commander.AlertsFragment;
import com.fokakefir.spaceship.gui.fragment.commander.HealthFragment;
import com.fokakefir.spaceship.gui.fragment.commander.KnowledgeFragment;
import com.fokakefir.spaceship.gui.fragment.commander.OrbitalFragment;
import com.fokakefir.spaceship.gui.fragment.commander.SystemFragment;
import com.fokakefir.spaceship.model.Alert;

import java.util.ArrayList;
import java.util.List;

public class CommanderFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private RelativeLayout layoutCommander;

    private TextView txtCommanderPanel;

    private Button btnCommanderTable;
    private ImageButton btnClose;

    private Button btnSystemCheck;
    private Button btnOrbitalCheck;
    private Button btnAlertsCheck;
    private Button btnHealthStatusCheck;
    private Button btnDailyKnowledgeCheck;

    private Fragment selectedFragment;

    // endregion

    // region 2. Lifecycle and constructor

    public CommanderFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander, container, false);

        this.layoutCommander = this.view.findViewById(R.id.layout_commander);
        this.txtCommanderPanel = this.view.findViewById(R.id.txt_commander);
        this.btnCommanderTable = this.view.findViewById(R.id.btn_commander_table);
        this.btnClose = this.view.findViewById(R.id.btn_close_command_panel);
        this.btnSystemCheck = this.view.findViewById(R.id.btn_system_check);
        this.btnOrbitalCheck = this.view.findViewById(R.id.btn_orbital_check);
        this.btnAlertsCheck = this.view.findViewById(R.id.btn_alerts_check);
        this.btnHealthStatusCheck = this.view.findViewById(R.id.btn_last_health_status_check);
        this.btnDailyKnowledgeCheck = this.view.findViewById(R.id.btn_daily_knowledge_check);

        this.layoutCommander.setVisibility(View.GONE);

        this.btnCommanderTable.setOnClickListener(this);
        this.btnClose.setOnClickListener(this);
        this.btnSystemCheck.setOnClickListener(this);
        this.btnOrbitalCheck.setOnClickListener(this);
        this.btnAlertsCheck.setOnClickListener(this);
        this.btnHealthStatusCheck.setOnClickListener(this);
        this.btnDailyKnowledgeCheck.setOnClickListener(this);

        this.selectedFragment = new KnowledgeFragment(this.activity);
        this.activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_commander, this.selectedFragment).commit();

        this.txtCommanderPanel.setText("Daily knowledge");

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
        } else if (view.getId() == R.id.btn_system_check) {
            this.activity.getSupportFragmentManager().beginTransaction().remove(this.selectedFragment).commit();
            this.selectedFragment = new SystemFragment(this.activity, this.activity.getSystemData());
            this.activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_commander, this.selectedFragment).commit();
            this.txtCommanderPanel.setText("System check");
        } else if (view.getId() == R.id.btn_orbital_check) {
            this.activity.getSupportFragmentManager().beginTransaction().remove(this.selectedFragment).commit();
            this.selectedFragment = new OrbitalFragment(this.activity, this.activity.getOrbitalData());
            this.activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_commander, this.selectedFragment).commit();
            this.txtCommanderPanel.setText("Orbital check");
        } else if (view.getId() == R.id.btn_alerts_check) {
            this.activity.getSupportFragmentManager().beginTransaction().remove(this.selectedFragment).commit();
            this.selectedFragment = new AlertsFragment(this.activity, this.activity.getAlerts());
            this.activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_commander, this.selectedFragment).commit();
            this.txtCommanderPanel.setText("Alerts");
        } else if (view.getId() == R.id.btn_last_health_status_check) {
            this.activity.getSupportFragmentManager().beginTransaction().remove(this.selectedFragment).commit();
            this.selectedFragment = new HealthFragment(this.activity, this.activity.getHealthData());
            this.activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_commander, this.selectedFragment).commit();
            this.txtCommanderPanel.setText("Health status");
        } else if (view.getId() == R.id.btn_daily_knowledge_check) {
            this.activity.getSupportFragmentManager().beginTransaction().remove(this.selectedFragment).commit();
            this.selectedFragment = new KnowledgeFragment(this.activity);
            this.activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_commander, this.selectedFragment).commit();
            this.txtCommanderPanel.setText("Daily knowledge");
        }
    }

    // endregion
}