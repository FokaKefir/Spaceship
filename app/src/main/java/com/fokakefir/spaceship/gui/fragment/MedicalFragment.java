package com.fokakefir.spaceship.gui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.fokakefir.spaceship.gui.viewpager.ImageAdapter;

public class MedicalFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private ViewPager viewPager;
    private ImageAdapter adapter;

    private Button btnHeal;
    private Button btnSport;

    // endregion

    // region 2. Lifecycle and constructor

    public MedicalFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_medical, container, false);

        this.btnHeal = this.view.findViewById(R.id.btn_heal);
        this.btnSport = this.view.findViewById(R.id.btn_sport);

        this.btnHeal.setOnClickListener(this);
        this.btnSport.setOnClickListener(this);

        this.viewPager = this.view.findViewById(R.id.view_pager);
        this.adapter = new ImageAdapter(getContext());
        this.viewPager.setAdapter(this.adapter);

        return this.view;
    }

    // endregion

    // region 3. Button listener

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_heal) {
            Toast.makeText(activity, "Heal", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.btn_sport) {
            Toast.makeText(activity, "Sport", Toast.LENGTH_SHORT).show();
        }
    }

    // endregion
}