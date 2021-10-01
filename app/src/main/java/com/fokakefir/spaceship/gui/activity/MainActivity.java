package com.fokakefir.spaceship.gui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.fragment.AirlockFragment;
import com.fokakefir.spaceship.gui.fragment.CommanderFragment;
import com.fokakefir.spaceship.gui.fragment.LabFragment;
import com.fokakefir.spaceship.gui.fragment.MedicalFragment;
import com.fokakefir.spaceship.gui.fragment.TechnologyFragment;
import com.fokakefir.spaceship.logic.GameService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    // region 0. Constants

    public static final int DELAY_MINUTES = 1;

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

    private Handler handler;

    // endregion

    // region 2. Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.fabLeft = findViewById(R.id.fab_left);
        this.fabRight = findViewById(R.id.fab_right);

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
    }


    // endregion

    // region 3. BottomNavigationView

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    final Runnable r = new Runnable() {
        public void run() {
            nextTick();
            handler.postDelayed(this, DELAY_MINUTES * 60 * 1000);
        }
    };

    private void nextTick() {
        if (this.gameService.tick() == 0) {
            // TODO dead
        } else {
            // TODO refresh
        }
    }

    // endregion

    // region 6. Getters and Setters


    // TODO getters
    public int getTraveledDistance() {
        return 0;
    }
    public int getDistance(){
        return 0;
    }

    public int getSpeed(){
        return 0;
    }
    public int getAcceleration(){
        return 0;
    }

    public int getTickRemaining(){
        return 0;
    }
    public int getTrackDeviation(){
        return 0;
    }
    public int getDistancePercent(){
        return 0;
    }


    // endregion


}