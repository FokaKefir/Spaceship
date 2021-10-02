package com.fokakefir.spaceship.gui.fragment.commander;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.fokakefir.spaceship.model.SystemData;

public class SystemFragment extends Fragment {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private TextView txtCommunicationStatus;
    private TextView txtCommunicationDelay;
    private TextView txtEngineStatus;
    private TextView txtEngineTemperature;
    private TextView txtEngineThrust;
    private TextView txtEngineFuel;
    private TextView txtHealthOxygen;
    private TextView txtHealthTension;
    private TextView txtWaterPressure;
    private TextView txtReactorOxygen;
    private TextView txtReactorTension;

    private SystemData systemData;

    // endregion

    // region 2. Lifecycle and constructor

    public SystemFragment(MainActivity activity) {
        this.activity = activity;
        this.systemData = new SystemData();
    }

    public SystemFragment(MainActivity activity, SystemData systemData) {
        this.activity = activity;
        this.systemData = systemData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander_system, container, false);

        this.txtCommunicationStatus = this.view.findViewById(R.id.txt_system_communication_status);
        this.txtCommunicationDelay = this.view.findViewById(R.id.txt_system_communication_delay);
        this.txtEngineStatus = this.view.findViewById(R.id.txt_system_engine_status);
        this.txtEngineTemperature = this.view.findViewById(R.id.txt_system_engine_temperature);
        this.txtEngineThrust = this.view.findViewById(R.id.txt_system_engine_thrust);
        this.txtEngineFuel = this.view.findViewById(R.id.txt_system_engine_fuel);
        this.txtHealthOxygen = this.view.findViewById(R.id.txt_system_health_oxygen);
        this.txtHealthTension = this.view.findViewById(R.id.txt_system_health_tension);
        this.txtWaterPressure = this.view.findViewById(R.id.txt_system_water_pressure);
        this.txtReactorOxygen = this.view.findViewById(R.id.txt_system_reactor_oxygen);
        this.txtReactorTension = this.view.findViewById(R.id.txt_system_reactor_tension);

        setCommunicationStatus(this.systemData.isCommunicationStatus());
        setCommunicationDelay(this.systemData.getCommunicationDelay());
        setEngineStatus(this.systemData.isEngineStatus());
        setEngineTemperature(this.systemData.getEngineTemperature());
        setEngineThrust(this.systemData.getEngineThrust());
        setEngineFuel(this.systemData.getEngineFuel());
        setHealthOxygen(this.systemData.getHealthOxygenPercent());
        setHealthTension(this.systemData.getHealthTensionPercent());
        setWaterPressure(this.systemData.getWaterPressurePercent());
        setReactorOxygen(this.systemData.getReactorOxygenPercent());
        setReactorTension(this.systemData.getReactorTensionPercent());

        return this.view;
    }

    // endregion

    // region 3. Getters and Setters

    public void setCommunicationStatus(boolean active) {
        this.txtCommunicationStatus.setText("Status: " + (active ? "active" : "inactive"));
    }

    public void setCommunicationDelay(int delay) {
        this.txtCommunicationDelay.setText("Delay: " + delay + " min");
    }

    public void setEngineStatus(boolean active) {
        this.txtEngineStatus.setText("Status: " + (active ? "active" : "inactive"));
    }

    public void setEngineTemperature(int temperature) {
        this.txtEngineTemperature.setText("Temp.: " + temperature + " °C");
    }

    public void setEngineThrust(int thrust) {
        this.txtEngineThrust.setText("Thrust: " + thrust + " kN");
    }

    public void setEngineFuel(int fuel) {
        this.txtEngineFuel.setText("Fuel: "  + fuel + " L");
    }

    public void setHealthOxygen(int percent) {
        this.txtHealthOxygen.setText("Health s. O₂: " + percent + "%");
    }

    public void setHealthTension(int percent) {
        this.txtHealthTension.setText("Health s. Volt.: "  + percent + "%");
    }

    public void setWaterPressure(int percent) {
        this.txtWaterPressure.setText("Water pressure: " + percent + "%");
    }

    public void setReactorOxygen(int percent) {
        this.txtReactorOxygen.setText("Reactor O₂: " + percent + "%");
    }

    public void setReactorTension(int percent) {
        this.txtReactorTension.setText("Reactor tension: " + percent + "%");
    }

    // endregion
}