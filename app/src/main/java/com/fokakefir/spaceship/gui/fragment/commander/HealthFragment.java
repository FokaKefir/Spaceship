package com.fokakefir.spaceship.gui.fragment.commander;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class HealthFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private ImageView imgHearts[];

    private ProgressBar progressRadioactivity;

    private TextView txtMusclePower;
    private TextView txtBoneDensity;
    private TextView txtLastTimeChecked;

    private int health;
    private int percentRadioactivity;
    private int percentMusclePower;
    private int percentBoneDensity;
    private int lastTick;

    // endregion

    // region 2. Lifecycle and constructor

    public HealthFragment(MainActivity activity) {
        this.activity = activity;
        this.health = 1;
        this.percentRadioactivity = 0;
        this.percentMusclePower = 0;
        this.percentBoneDensity = 0;
        this.lastTick = 0;
    }

    public HealthFragment(MainActivity activity, int health, int percentRadioactivity, int percentMusclePower, int percentBoneDensity, int lastTick) {
        this.activity = activity;
        this.health = health;
        this.percentRadioactivity = percentRadioactivity;
        this.percentMusclePower = percentMusclePower;
        this.percentBoneDensity = percentBoneDensity;
        this.lastTick = lastTick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander_health, container, false);

        this.imgHearts = new ImageView[]{
                this.view.findViewById(R.id.img_heart0),
                this.view.findViewById(R.id.img_heart1),
                this.view.findViewById(R.id.img_heart2),
                this.view.findViewById(R.id.img_heart3),
                this.view.findViewById(R.id.img_heart4)
        };

        this.progressRadioactivity = this.view.findViewById(R.id.progress_radioactivity);
        this.txtMusclePower = this.view.findViewById(R.id.txt_muscle_power);
        this.txtBoneDensity = this.view.findViewById(R.id.txt_bone_density);
        this.txtLastTimeChecked = this.view.findViewById(R.id.txt_last_time_checked);

        setHealth(this.health);
        setRadioActivity(this.percentRadioactivity);
        setMusclePower(this.percentMusclePower);
        setBoneDestiny(this.percentBoneDensity);
        setLastTimeChecked(this.lastTick);

        return this.view;
    }

    // endregion

    // region 3. Getters and Setters

    public void setHealth(int n) {
        for (int i = 0; i < n; i++)
            this.imgHearts[i].setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_1));

        for (int i = n; i < 5; i++)
            this.imgHearts[i].setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_0));
    }

    public void setRadioActivity(int percent) {
        this.progressRadioactivity.setProgress(percent);
    }

    @SuppressLint("SetTextI18n")
    public void setMusclePower(int percent) {
        this.txtMusclePower.setText("Muscle power: " + percent + "%");
    }

    @SuppressLint("SetTextI18n")
    public void setBoneDestiny(int percent) {
        this.txtBoneDensity.setText("Bone density: " + percent + "%");
    }

    @SuppressLint("SetTextI18n")
    public void setLastTimeChecked(int tick) {
        this.txtLastTimeChecked.setText("Last time checked: " + tick + " tick");
    }

    // endregion
}