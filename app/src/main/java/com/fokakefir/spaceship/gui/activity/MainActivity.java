package com.fokakefir.spaceship.gui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.fragment.AirlockFragment;
import com.fokakefir.spaceship.gui.fragment.CommanderFragment;
import com.fokakefir.spaceship.gui.fragment.LabFragment;
import com.fokakefir.spaceship.gui.fragment.MedicalFragment;
import com.fokakefir.spaceship.gui.fragment.TechnologyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    // region 0. Constants

    // endregion

    // region 1. Decl and Init

    private BottomNavigationView bottomNav;

    private MedicalFragment medicalFragment;
    private CommanderFragment commanderFragment;
    private LabFragment labFragment;
    private TechnologyFragment technologyFragment;
    private AirlockFragment airlockFragment;

    private Fragment selectedFragment;

    private FloatingActionButton fabLeft;
    private FloatingActionButton fabRight;

    // endregion

    // region 2. Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.fabLeft = findViewById(R.id.fab_left);
        this.fabRight = findViewById(R.id.fab_right);

        this.fabLeft.setAlpha(0.33f);
        this.fabRight.setAlpha(0.33f);
        this.fabLeft.setOnClickListener(this);
        this.fabRight.setOnClickListener(this);
        this.fabLeft.setVisibility(View.GONE);

        this.bottomNav = findViewById(R.id.bottom_navigation);
        this.bottomNav.setOnNavigationItemSelectedListener(this);

        this.medicalFragment = new MedicalFragment(this);
        this.commanderFragment = new CommanderFragment(this);
        this.labFragment = new LabFragment(this);
        this.technologyFragment = new TechnologyFragment(this);
        this.airlockFragment = new AirlockFragment(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, this.medicalFragment).hide(this.medicalFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, this.commanderFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, this.labFragment).hide(this.labFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, this.technologyFragment).hide(this.technologyFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, this.airlockFragment).hide(this.airlockFragment).commit();

        this.selectedFragment = this.commanderFragment;

    }


    // endregion

    // region 3. BottomNavigationView

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_commander_module:
                getSupportFragmentManager().beginTransaction().hide(this.selectedFragment).show(this.commanderFragment).commit();
                this.selectedFragment = this.commanderFragment;
                this.fabLeft.setVisibility(View.GONE);
                this.fabRight.setVisibility(View.VISIBLE);
                return true;

            case R.id.nav_medical_module:
                getSupportFragmentManager().beginTransaction().hide(this.selectedFragment).show(this.medicalFragment).commit();
                this.selectedFragment = this.medicalFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.VISIBLE);
                return true;

            case R.id.nav_lab_module:
                getSupportFragmentManager().beginTransaction().hide(this.selectedFragment).show(this.labFragment).commit();
                this.selectedFragment = this.labFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.VISIBLE);
                return true;

            case R.id.nav_technology_module:
                getSupportFragmentManager().beginTransaction().hide(this.selectedFragment).show(this.technologyFragment).commit();
                this.selectedFragment = this.technologyFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.VISIBLE);
                return true;

            case R.id.nav_airlock_module:
                getSupportFragmentManager().beginTransaction().hide(this.selectedFragment).show(this.airlockFragment).commit();
                this.selectedFragment = this.airlockFragment;
                this.fabLeft.setVisibility(View.VISIBLE);
                this.fabRight.setVisibility(View.GONE);
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


}