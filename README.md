# Remote Control Joystick for Flight Gear - Android Application
[Click here for the Demo Video](link here to add)

This project is an android application, developed in Java with Android Studio. The app uses a client-server structure and MVVM architecture, it give to the user to control the plane in the Flight Gear simulator.
Flight Gear is an open-source program which is free for download for anyone onto their computer Mac, Windows, and Linux.

The Flight Gear will provide the simulation of the flight that the user can be the pilot and our app will provide controls to interact with.

The user need to open the app, enter his ip adress, a port and click on the CONNECT button and he can control the different element (Joystick and two seek bar to move the different features of the plane).


<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/screen.PNG" width="140" height="283"/>

## Getting Started

### Prerequisites

Before starting using our app, there are a few steps you need to follow:
- Download the Flight Gear for your OS, at: https://www.flightgear.org/download/.
    We recommend version 2020.3.6.
- Open FG, go into `Settings > Additional Settings`. Paste there:
```
--telnet=socket,in,10,127.0.0.1,6400,tcp
```
Before compiling and running, please make sure to have installed Android Studio and to have available port: `6400` or any port you choose to use.
You also need to choose a smartphone (make sure that AVD Manager is installed).

## Compiling & Running

1. Download the project and open it in Android Studio (we used Android Studio 4.2.1).
2. Define as Android SDK Android 11.0 -API level 30
3. Open FG and click on the Fly button.
4. When you see the plane you can swith view with V click on the keyboard and you need to click on Cessna C172P-> Autostart
5. Start the App from Android Studio or from your android smartphone
6. Now you can use the app

## Deployment

### MVVM Architechture

**Model**: hold the business logic and responsible for the communication with Flight Gear via TCP Socket: send values of the different features in order to mantain the plane correctly during the flight and FG can display the flight in real time depends on the choice of the user. 
**View**: the user interface on the application on the smartphone or emulator
**View Model**: send command /notify  to the model when the value of a feature has been changed by the user

The code has been separated in different classes: Joystick and mainActivity are part of View, and we have a ViewModel class and a class Model
We provide a UMLdiagram of the project

## Functionalities
**IP and Port**

First you need to write your Ip v4 and not localhost! Also you need to specify the port you choose to use
Then click on CONNECT.

<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/ipPort.PNG" width="160" height="105"/>
<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/ip-port.PNG" width="160" height="105"/>


**Joystick and Seek Bars**
After click on CONNECT - you can start your flight and control the plane -your are now the pilot!
You can control the Joystick and the two seek bar with your fingers (smartphone) or your mouse (emulator).
In the Joystick the user control values of aileron and elevator that are between -1 and 1.
There are also 2 seek bar : 
- In the left side: the user control the value of throttle between 0 and 1
- At the bottom of the screen there is another seek bar that control the value of rudder between -1 and 1


<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/joystickSeekBar.PNG" width="115" height="115"/>

## Authors
- Eva Hallermeier
- Samuel Memmi


