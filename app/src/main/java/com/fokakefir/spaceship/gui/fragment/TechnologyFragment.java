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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;

public class TechnologyFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private RelativeLayout layoutTechnology;

    private Button btnDisplayTop;
    private Button btnDisplayBottom;
    private ImageButton btnClose;

    private ImageButton btnThrustRaise;
    private ImageButton btnThrustLower;
    private TextView txtThrustValue;

    private ImageButton btnVoltageRaise;
    private ImageButton btnVoltageLower;
    private TextView txtVoltageValue;

    private ImageButton btnOxygenLevelRaise;
    private ImageButton btnOxygenLevelLower;
    private TextView txtOxygenLevelValue;

    private ImageButton btnReactorTemperatureRaise;
    private ImageButton btnReactorTemperatureLower;
    private TextView txtReactorTemperatureValue;

    private ImageButton btnWaterPressureRaise;
    private ImageButton btnWaterPressureLower;
    private TextView txtWaterPressureValue;

    // endregion

    // region 2. Lifecycle and constructor

    public TechnologyFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_technology, container, false);

        this.layoutTechnology = this.view.findViewById(R.id.layout_technology);
        this.btnDisplayTop = this.view.findViewById(R.id.btn_technology_display_top);
        this.btnDisplayBottom = this.view.findViewById(R.id.btn_technology_display_bottom);
        this.btnClose = this.view.findViewById(R.id.btn_close_technology_panel);
        this.btnThrustRaise = this.view.findViewById(R.id.btn_technology_thrust_raise);
        this.btnThrustLower = this.view.findViewById(R.id.btn_technology_thrust_lower);
        this.txtThrustValue = this.view.findViewById(R.id.txt_technology_thrust_value);
        this.btnVoltageRaise = this.view.findViewById(R.id.btn_technology_voltage_raise);
        this.btnVoltageLower = this.view.findViewById(R.id.btn_technology_voltage_lower);
        this.txtVoltageValue = this.view.findViewById(R.id.txt_technology_voltage_value);
        this.btnOxygenLevelRaise = this.view.findViewById(R.id.btn_technology_oxygen_level_raise);
        this.btnOxygenLevelLower = this.view.findViewById(R.id.btn_technology_oxygen_level_lower);
        this.txtOxygenLevelValue = this.view.findViewById(R.id.txt_technology_oxygen_level_value);
        this.btnReactorTemperatureRaise = this.view.findViewById(R.id.btn_technology_reactor_temperature_raise);
        this.btnReactorTemperatureLower = this.view.findViewById(R.id.btn_technology_reactor_temperature_lower);
        this.txtReactorTemperatureValue = this.view.findViewById(R.id.txt_technology_reactor_temperature_value);
        this.btnWaterPressureRaise = this.view.findViewById(R.id.btn_technology_water_pressure_raise);
        this.btnWaterPressureLower = this.view.findViewById(R.id.btn_technology_water_pressure_lower);
        this.txtWaterPressureValue = this.view.findViewById(R.id.txt_technology_water_pressure_value);

        this.layoutTechnology.setVisibility(View.GONE);

        this.btnDisplayTop.setOnClickListener(this);
        this.btnDisplayBottom.setOnClickListener(this);
        this.btnClose.setOnClickListener(this);
        this.btnThrustRaise.setOnClickListener(this);
        this.btnThrustLower.setOnClickListener(this);
        this.btnVoltageRaise.setOnClickListener(this);
        this.btnVoltageLower.setOnClickListener(this);
        this.btnOxygenLevelRaise.setOnClickListener(this);
        this.btnOxygenLevelLower.setOnClickListener(this);
        this.btnReactorTemperatureRaise.setOnClickListener(this);
        this.btnReactorTemperatureLower.setOnClickListener(this);
        this.btnWaterPressureRaise.setOnClickListener(this);
        this.btnWaterPressureLower.setOnClickListener(this);

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_technology_display_top || view.getId() == R.id.btn_technology_display_bottom) {
            this.layoutTechnology.setVisibility(View.VISIBLE);
            Animation animSlideUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
            this.layoutTechnology.startAnimation(animSlideUp);

            this.btnDisplayTop.setVisibility(View.GONE);
            this.btnDisplayBottom.setVisibility(View.GONE);
        } else if (view.getId() == R.id.btn_close_technology_panel) {
            Animation animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
            this.layoutTechnology.startAnimation(animSlideDown);
            this.layoutTechnology.setVisibility(View.GONE);

            this.btnDisplayTop.setVisibility(View.VISIBLE);
            this.btnDisplayBottom.setVisibility(View.VISIBLE);
        }
    }

    // endregion
}