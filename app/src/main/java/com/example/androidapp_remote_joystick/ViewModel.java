package com.example.androidapp_remote_joystick;

import android.widget.SeekBar;
import java.io.IOException;

public class ViewModel implements Joystick.JoystickMoved{

   Model model;

    public ViewModel(String ip, int port){
        model = new Model(ip, port);
    }

    public void connect() throws IOException {
        model.connect();
    }

    @Override
    public void joystickMoved(double x, double y) throws InterruptedException {
        model.setAileron(x);
        model.setElevator(y);
        model.dispatch_fly();//actualize values to send them
    }

    public void RudderChanged(SeekBar seekBar, int value, boolean fromUser) throws InterruptedException {
        model.setRudder(seekBar.getProgress());
        model.dispatch_rudder();
    }

    public void ThrottleChanged(int value) throws InterruptedException {
        model.setThrottle(value);
        model.dispatch_throttle();
    }
}