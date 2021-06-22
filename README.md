# Remote Joystick Control for Flight Gear - Android Application
[Click here for the Demo Video](link here to add)

This project is an android application, developed in Java with Android Studio. The app uses a client-server structure and MVVM architecture, it give to the user to control the plane in the Flight Gear simulator.
Flight Gear is an open-source program which is free for download for anyone onto their computer Mac, Windows, and Linux. The program simulates a plane in different modes.

The Flight Gear will provide the simulation of the flight that the user can be the pilot and our app will provide controls to interact with.

The user need to open the app, enter his ip, a port and click in the CONNECT button and he can move the different element (Joystick and two seek bar to move the different features of the plane).
<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/screen.PNG" width="600" height="300"/>

## Getting Started

### Prerequisites

Before starting using our app, there are a few steps you need to follow:
- Download the Flight Gear for your OS, at: https://www.flightgear.org/download/.
    We recommend version 2020.3.6.
- Open FG, go into `Settings > Additional Settings`. Paste there:
```
----telnet=socket,in,10,127.0.0.1,6400,tcpl
```
Before compiling and running, please make sure to have installed Android Studio and to have available port: `6400`.
You also need to choose a smartphone (first you need to install AVD Manager).

## Compiling & Running

1. Download the project and open it in Android Studio (we used Android Studio 4.2.1).
2. Define as Android SDK Android 11.0 -API level 30
3. Open FG and click on the Fly button.
3. Start the App from Android Studio

## Deployment

### MVVVM Architechture

**Model**: the business logic and responsible for the communication with Fligh Gear via TCP Socket: send values of the different features in order to mantain the plane correctly during the flight and FG can display the flight in real time depends on the choice of the user. 
**View**: the user interface on the application on the smartphone
**View Model**: send command /notify  to the model when the value of a feature has been changed by the user

The code has been separated in different classes: Joystick and mainactivity are part of View, and we have a ViewModel class and a class Model
We provide a Uml diagram of the project in the file (FlightUml.png)

## Functionalities

//explain different features: srat process+ seek bar +joystick

## Authors
- Eva Hallermeier
- Samuel Memmi


