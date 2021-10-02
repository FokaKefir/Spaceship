package com.fokakefir.spaceship.gui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.fragment.AirlockFragment;
import com.fokakefir.spaceship.gui.fragment.CommanderFragment;
import com.fokakefir.spaceship.gui.fragment.LabFragment;
import com.fokakefir.spaceship.gui.fragment.MedicalFragment;
import com.fokakefir.spaceship.gui.fragment.TechnologyFragment;
import com.fokakefir.spaceship.logic.GameService;
import com.fokakefir.spaceship.model.Alert;
import com.fokakefir.spaceship.model.HealthData;
import com.fokakefir.spaceship.model.OrbitalData;
import com.fokakefir.spaceship.model.Ship;
import com.fokakefir.spaceship.model.SystemData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    // region 0. Constants

    public static final int ONE_TICK_MINUTES = 2; // Hany perc egy tick
    //public static final int ONE_MINUTE_IN_MILLISECONDS = 60000; // Ez egy perc
    public static final int ONE_MINUTE_IN_MILLISECONDS = 1000; // Ez egy masodperc

    // endregion

    // region 1. Decl and Init

    private GameService gameService;

    private BottomNavigationView bottomNav;

    private MedicalFragment medicalFragment;
    private CommanderFragment commanderFragment;
    private LabFragment labFragment;
    private TechnologyFragment technologyFragment;
    private AirlockFragment airlockFragment;

    private List<Fragment> fragments;

    private Fragment selectedFragment;

    private FloatingActionButton fabLeft;
    private FloatingActionButton fabRight;

    private TextView txtTime;
    private TextView txtTick;

    private Handler handler;

    // endregion

    // region 2. Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.fabLeft = findViewById(R.id.fab_left);
        this.fabRight = findViewById(R.id.fab_right);
        this.txtTime = findViewById(R.id.txt_time);
        this.txtTick = findViewById(R.id.txt_tick);

        this.fabLeft.setOnClickListener(this);
        this.fabRight.setOnClickListener(this);
        this.fabLeft.setVisibility(View.GONE);

        this.bottomNav = findViewById(R.id.bottom_navigation);
        this.bottomNav.setOnNavigationItemSelectedListener(this);

        this.commanderFragment = new CommanderFragment(this);
        this.medicalFragment = new MedicalFragment(this);
        this.labFragment = new LabFragment(this);
        this.technologyFragment = new TechnologyFragment(this);
        this.airlockFragment = new AirlockFragment(this);

        this.fragments = new ArrayList<>();
        this.fragments.add(this.commanderFragment);
        this.fragments.add(this.medicalFragment);
        this.fragments.add(this.labFragment);
        this.fragments.add(this.technologyFragment);
        this.fragments.add(this.airlockFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_main, this.commanderFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_main, this.medicalFragment).hide(this.medicalFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_main, this.labFragment).hide(this.labFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_main, this.technologyFragment).hide(this.technologyFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_main, this.airlockFragment).hide(this.airlockFragment).commit();

        this.selectedFragment = this.commanderFragment;

        this.gameService = new GameService();
        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, ONE_MINUTE_IN_MILLISECONDS);
    }


    // endregion

    // region 3. BottomNavigationView

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (!this.gameService.isPlayerMovable())
            return false;

        Fragment newFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_commander_module:
                newFragment = this.commanderFragment;
                this.fabLeft.setVisibility(View.GONE);
                this.fabRight.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_medical_module:
                newFragment = this.medicalFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_lab_module:
                newFragment = this.labFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_technology_module:
                newFragment = this.technologyFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_airlock_module:
                newFragment = this.airlockFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.GONE);
                break;
        }

        if (newFragment != null && newFragment != this.selectedFragment) {
            getSupportFragmentManager().beginTransaction()
                    .hide(this.selectedFragment).show(newFragment).commit();
            this.selectedFragment = newFragment;
            this.gameService.setPlayerRoom(this.fragments.indexOf(this.selectedFragment));
            return true;
        }
        return false;
    }

    // endregion

    // region 4. FloatingActionButton listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_left) {
            if (this.selectedFragment == this.medicalFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_commander_module);
            } else if (this.selectedFragment == this.labFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_medical_module);
            } else if (this.selectedFragment == this.technologyFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_lab_module);
            } else if (this.selectedFragment == this.airlockFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_technology_module);
            }
        } else if (view.getId() == R.id.fab_right) {
            if (this.selectedFragment == this.commanderFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_medical_module);
            } else if (this.selectedFragment == this.medicalFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_lab_module);
            } else if (this.selectedFragment == this.labFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_technology_module);
            } else if (this.selectedFragment == this.technologyFragment) {
                this.bottomNav.setSelectedItemId(R.id.nav_airlock_module);
            }
        }
    }

    // endregion

    // region 5. Game loop

    final Runnable runnable = new Runnable() {
        public void run() {
            int minutes = gameService.getMinutes();
            minutes++;
            displayTime(minutes);
            if (minutes % ONE_TICK_MINUTES == 0) {
                nextTick();
            }
            gameService.setMinutes(minutes);
            handler.postDelayed(this, ONE_MINUTE_IN_MILLISECONDS);
        }
    };

    public void healPlayer() {
        this.gameService.heal();
    }

    public void makeNewHealthCheck() {
        this.gameService.makeNewHealthCheck();
    }

    public void changeShipData(int type) {
        this.gameService.changeShipData(type);
    }

    private void nextTick() {
        if (this.gameService.tick() == -1) {
            endGame(false);
        } else {
            this.technologyFragment.setShip(this.gameService.getShip());
        }
        this.txtTick.setText("Tick: " + this.gameService.getTime());
    }

    private void endGame(boolean win) {
        if (win) {
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        } else {    
            Toast.makeText(this, "Game ended, you loose", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayTime(int minutes) {
        String strHour;
        String strMin;
        int hour = minutes / 60;
        int min = minutes % 60;

        if (hour < 10)
            strHour = "0" + hour;
        else
            strHour = hour + "";

        if (min < 10)
            strMin = "0" + min;
        else
            strMin = min + "";

        this.txtTime.setText(strHour + ":" + strMin);
    }

    // endregion

    // region 6. Getters and Setters

    public SystemData getSystemData() {
        return this.gameService.getSystemData();
    }

    public OrbitalData getOrbitalData() {
        return this.gameService.getOrbitalData();
    }

    public List<Alert> getAlerts() {
        return this.gameService.getAlerts();
    }

    public HealthData getHealthData() {
        return this.gameService.getHealthData();
    }

    public void setPlayerMovable(boolean movable) {
        this.gameService.setPlayerMovable(movable);
    }

    public boolean isPlayerMovable() {
        return this.gameService.isPlayerMovable();
    }

    public Ship getShip() {
        return this.gameService.getShip();
    }

    // endregion


}