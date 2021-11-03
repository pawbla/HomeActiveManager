## About The Project

Purpose of the project is to create application which allows showing current weather and manage home activities. 

## Technical details

This application base on already existing code which was developed in [RpiApplication](https://github.com/pawbla/RpiApplication) project and base on solution from the project.
Purpose for refactoring was to divide a previous app which was king of a monolith to several small and simply modules.

### Assumptions:
- applications will be run on Raspberry Pi
- embedded part is written in Java, some scripts in shell
- web interface (React) allow configuration and remotely control application
- measurements are displayed on the dedicated to Rpi display
- access to web interface has authentication mechanism based on JWT
- all data are stored in mySQL database

### Modules
The application consist of the following modules:
- Weather service module
- Embedded sensor module
- Internal resource handler module
- User management module

### Application package
Application release package is created on maven `package` phase. 
During the phase is created `release` folder in `home-active-manager-parent`. 
This folder contains:
- start_apps.sh
- application folders with:
    - start_app.sh
    - configuration
    - runnable application jar

### Start application
Application can be started in two ways:
1. Start simultaneously all applications - execute `sudo bash start_apps.sh <optional profile>` 
2. Start selected application - enter to folder with application name and execute `sudo bash start_app.sh <optional profile>`

Setting `<optional parameter>` with `dev` allows to start application(s) with **dev** profile, in other case application will
start in **prod**, e.g. `sudo bash start_app.sh dev`.

### Contact

Paweł Błachut - blachut.pawel@gmail.com