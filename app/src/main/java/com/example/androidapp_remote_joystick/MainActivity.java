package com.example.androidapp_remote_joystick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;


import android.os.Bundle;
import android.widget.SeekBar;

import com.example.androidapp_remote_joystick.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String ip_adress;
    private int port;
    private EditText ip_adressInput;
    private EditText portInput;
    private  Button connectButton;
    private ViewModel viewModel;
    private  Joystick joystick;
    private   SeekBar seekBarThrottle;
    private SeekBar rudder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityBind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ip_adressInput = findViewById(R.id.ip_adress);
        portInput = findViewById(R.id.port);
        connectButton = findViewById(R.id.connect_button);
        joystick = findViewById(R.id.joystick);
        rudder = (SeekBar) findViewById(R.id.seekBarRudder);
        seekBarThrottle = (SeekBar) findViewById(R.id.seekBarThrottle);

        //when connect is click we will store the ip and port entered
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip_adress = ip_adressInput.getText().toString();
                port = Integer.valueOf(portInput.getText().toString());

                viewModel = new ViewModel(ip_adress, port);
                //  rudder.setOnSeekBarChangeListener(viewModel.RudderChanged()); //???
                // throtlle.setOnSeekBarChangeListener(viewModel.ThrottleChanged()); //???
                activityBind.setViewModel(viewModel);
                activityBind.executePendingBindings();
                joystick.addMoved(viewModel);

                try {
                    viewModel.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        seekBarThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    viewModel.ThrottleChanged(progress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    viewModel.RudderChanged(rudder, progress, fromUser);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
