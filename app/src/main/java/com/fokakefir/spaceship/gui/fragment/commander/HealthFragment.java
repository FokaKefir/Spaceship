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
import com.fokakefir.spaceship.model.HealthData;

public class HealthFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private ImageView imgHearts[];

    private ProgressBar progressRadioactivity;

    private TextView txtMusclePower;
    private TextView txtBoneDensity;
    private TextView txtLastTimeChecked;

    private HealthData healthData;

    // endregion

    // region 2. Lifecycle and constructor

    public HealthFragment(MainActivity activity) {
        this.activity = activity;
        this.healthData = new HealthData();
    }

    public HealthFragment(MainActivity activity, HealthData healthData) {
        this.activity = activity;
        this.healthData = healthData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander_health, container, false);

        this.imgHearts = new ImageView[]{
                this.view.findViewById(R.id.img_health_heart0),
                this.view.findViewById(R.id.img_health_heart1),
                this.view.findViewById(R.id.img_health_heart2),
                this.view.findViewById(R.id.img_health_heart3),
                this.view.findViewById(R.id.img_health_heart4)
        };

        this.progressRadioactivity = this.view.findViewById(R.id.progress_health_radioactivity);
        this.txtMusclePower = this.view.findViewById(R.id.txt_health_muscle_power);
        this.txtBoneDensity = this.view.findViewById(R.id.txt_health_bone_density);
        this.txtLastTimeChecked = this.view.findViewById(R.id.txt_health_last_time_checked);

        setHealth(this.healthData.getHealth());
        setRadioActivity(this.healthData.getPercentRadioactivity());
        setMusclePower(this.healthData.getPercentMusclePower());
        setBoneDestiny(this.healthData.getPercentBoneDensity());
        setLastTimeChecked(this.healthData.getLastTick());

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