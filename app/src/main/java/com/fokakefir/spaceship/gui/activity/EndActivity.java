package com.fokakefir.spaceship.gui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fokakefir.spaceship.R;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtEnding;
    private Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        this.txtEnding = findViewById(R.id.txt_ending);
        this.btnPlayAgain = findViewById(R.id.btn_play_again);

        this.btnPlayAgain.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("win", true)) {
            this.txtEnding.setText("Congratulations!\nYou have successfully reached Mars!");
        } else {
            this.txtEnding.setText("You fucked up!");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_play_again) {
            Intent intent = new Intent(EndActivity.this, MainActivity.class);
            intent.putExtra("restart", true);
            startActivity(intent);
            finish();
        }
    }
}