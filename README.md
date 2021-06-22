# Remote Joystick Control for Flight Gear -Android Application
[Click here for the Demo Video](link here to add)

This project is an android application, developed in Java with Android Studio. The app uses a client-server structure and MVVM architecture, it give to the user to control the plane in the Flight Gear simulator.

The user need to open the app, enter his ip, a port and click in the CONNECT button and he can move the different element (Joystick and two seek bar to move the different features of the plane).

## Getting Started

### Prerequisites
Before compiling and running, please make sure to have installed Android Studio and to have available port: `6400`.

## Compiling & Running
You need to open your Flight Gear application on your Desktop and change in the parameter to:

Then click on the Fly button and click on Cezna and Autostart
Compiling and running are extremely easy. After downloading the project from `git`, you can click on the build button and then to the run button it will open an emulator of a phone and the app will open

## Deployment

### MVVVM Architechture

**Model**: the business logic and responsible with communication with Fligh Gear: send values of the different features in order to mantain the plane correctly during the flight
**View**: the user interface on the application on the smartphone
**View Model**: send command to the model

The code has been separated in different classes: Joystick and mainactivity are part of View, and we have a ViewModel class and a class Model
We provide a Uml diagram of the project in the file (FlightUml.png)

## Functionalities

//explain different features: srat process+ seek bar +joystick

## Authors
- Eva Hallermeier
- Samuel Memmi


