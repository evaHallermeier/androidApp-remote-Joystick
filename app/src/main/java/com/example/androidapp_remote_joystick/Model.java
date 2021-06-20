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


        public Model(String ip, int port)  {
            this.ip = ip;
            this.port = port;
        }

        void connect() throws IOException{
            try {
                Socket fg = new Socket(ip, port);
                out = new PrintWriter(fg.getOutputStream(), true);

                new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            try {
                                dispatchQueue.take().run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
            catch(Exception e){
                System.out.println("Server problem");
            }
        }

        void dispatch_fly() throws InterruptedException {
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    out.print("set/controls/flight/aileron" + aileron + "\r\n");
                    out.flush();
                    out.print("set/controls/flight/elevator" + elevator + "\r\n");
                    out.flush();
                }
            });
        /*dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                out.print("set/controls/flight/elevator" + elevator + "\r\n");
                out.flush();
            }
        });*/
        }

        void dispatch_rudder() throws InterruptedException{
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    out.print("set/controls/flight/rudder" + rudder + "\r\n");
                    out.flush();
                }
            });
        }

        void dispatch_throttle() throws InterruptedException{
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    out.print("set/controls/engines/current-engine/throttle" + throttle + "\r\n");
                    out.flush();
                }
            });
        }

        public void setAileron(double aileron) {
            this.aileron = aileron;
        }

        public void setElevator(double elevator) {

            this.elevator = elevator;
        }

        public void setRudder(double rudder) {
            this.rudder = rudder;
        }

        public void setThrottle(double throttle) {
            this.throttle = throttle;
        }
}
