package com.example.androidapp_remote_joystick;
import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Model {
        private PrintWriter out;
        private final BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<Runnable>();
        private final String ip;
        private final int port;
        private double aileron;
        private double elevator;
        private double rudder;
        private double throttle;

        public Model(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

    public void connect() throws IOException{
            new Thread(new Runnable() {
                public void run() {
                    try {
                       // System.out.println("port is" + port);
                        //System.out.println("ip ip is" + ip);
                           Socket fg = new Socket(ip,port);
                       // Socket fg = new Socket("192.168.0.92", 4477);
                        out = new PrintWriter(fg.getOutputStream(), true);
                        while (true) {
                            dispatchQueue.take().run();
                        }
                    } catch (Exception e) {
                        System.out.println("Server problem");
                    }
                }
            }).start();
    }

    public void dispatch_fly() throws InterruptedException { //for joystick values -aileron+elevator
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    out.print("set /controls/flight/aileron " + aileron + "\r\n");
                    out.flush();
                    out.print("set /controls/flight/elevator " + elevator + "\r\n");
                    out.flush();
                }
            });

        }

        void dispatch_rudder() throws InterruptedException{
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                   // System.out.println("rudder is " + rudder);
                    out.print("set /controls/flight/rudder " + rudder + "\r\n");
                    out.flush();
                }
            });
        }

        void dispatch_throttle() throws InterruptedException{
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                 //   System.out.println("throttle dispatch is " + throttle);
                    out.print("set /controls/engines/current-engine/throttle " + throttle + "\r\n");
                    out.flush();
                }
            });
        }

        public void setAileron(double aileron) {
            this.aileron=aileron;
           // System.out.println("aileron is " + this.aileron);
        }

        public void setElevator(double elevator) {

            this.elevator = elevator;
           // System.out.println("elevator is " + this.elevator);
        }

        public void setRudder(double rudder) {
            this.rudder = rudder/100;
          //  System.out.println("rudder is " + this.rudder);
        }

        public void setThrottle(double throttle) {
            this.throttle = throttle/100;
           // System.out.println("trhottle is " + this.throttle);

        }
}
