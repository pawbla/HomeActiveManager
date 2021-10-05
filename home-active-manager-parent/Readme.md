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

### Contact

Paweł Błachut - blachut.pawel@gmail.com