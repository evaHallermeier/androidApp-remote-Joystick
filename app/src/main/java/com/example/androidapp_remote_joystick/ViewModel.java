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
        //the x and y need to change?
        model.setAileron(x);
        model.setElevator(y);
        model.dispatch_fly(); //why the model don t call dispatch_fly()?
        //plus logique que en cas de set un des parametres alors on appel dispatch fly
    }

    public void RudderChanged(SeekBar seekBar, int value, boolean fromUser) throws InterruptedException {
        //is the value good?
        model.setRudder(seekBar.getProgress());
        model.dispatch_rudder();
    }

    public void ThrottleChanged(SeekBar seekBar, int value, boolean fromUser) throws InterruptedException {
        //is the value good?
        model.setThrottle(seekBar.getProgress());
        model.dispatch_throttle();
    }
}
