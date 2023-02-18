package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    private LinearLayout green;
    private LinearLayout yellow;
    private LinearLayout red;
    private Button button;
    private Button button_reset;
    private boolean running = false;
    private int step = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        green = findViewById(R.id.green);
        yellow = findViewById(R.id.yellow);
        red = findViewById(R.id.red);
        button = findViewById(R.id.button);
        button_reset = findViewById(R.id.button_reset);
        button_reset.setVisibility(View.VISIBLE);

    }

    public void onClickStart(View view) {
        if (!running) {
            running = true;
            button.setText(R.string.stop);
            button_reset.setVisibility(View.INVISIBLE);
            new Thread(() -> {
                while (running) {
                    try {
                        if (step == 1) {
                            green.setBackgroundColor(getResources().getColor(R.color.green));
                            yellow.setBackgroundColor(getResources().getColor(R.color.gray));
                            red.setBackgroundColor(getResources().getColor(R.color.gray));
                        } else if (step == 2) {
                            green.setBackgroundColor(getResources().getColor(R.color.gray));
                            yellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                            red.setBackgroundColor(getResources().getColor(R.color.gray));
                        } else {
                            green.setBackgroundColor(getResources().getColor(R.color.gray));
                            yellow.setBackgroundColor(getResources().getColor(R.color.gray));
                            red.setBackgroundColor(getResources().getColor(R.color.red));
                        }
                        if (step < 3) {
                            step++;
                        } else {
                            step = 1;
                        }
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }).start();
        } else {
            running = false;
            button.setText(R.string.start);
            button_reset.setVisibility(View.VISIBLE);
        }
    }

    public void onClickReset(View view) {
        if (!running) {
            step = 1;
            green.setBackgroundColor(getResources().getColor(R.color.gray));
            yellow.setBackgroundColor(getResources().getColor(R.color.gray));
            red.setBackgroundColor(getResources().getColor(R.color.gray));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
    }
}