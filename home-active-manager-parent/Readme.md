## About The Project

Purpose of the project is to create application which allows showing current weather and manage home activities. 

## Technical details

This application base on already existing code which was developed in [RpiApplication](https://github.com/pawbla/RpiApplication) project and base on solution from the project.
Purpose for refactoring was split a previous app which was king of a monolith to several small and simply services. Every service is responsible for different business funcions, like e.g. reading datas from embedded sensor, providing Rest api with measurements, etc. Functionality responsible for sharing static reasources, previosuly build in java application, now is providing with Nginx server. 

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
- Application monitoring module
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

### Hardware
- Raspberry Pi model B+
- Display - [Waveshare 3.5" LCD](https://www.waveshare.com/wiki/3.5inch_RPi_LCD_(B))  
- DHT22 - temperature and humidity sensor

### Shutting down
Every application can be shutted down with the following REST API call:
- **Shutdown application**

  Actuator allowed to shutdown application.
  * **URL**

    /actuator/shutdown
  * **Method**

    `POST`

  * **Success Response**
    * Code: 200

  * **Sample call**
     <details>
     <summary>Click to open </summary>

     ```shell
     curl --location --request POST 'http://localhost:8083/actuator/shutdown'
     ```
     </details>   

  * **Sample response**
      <details>
      <summary>Click to open </summary>

      ```json
      {
        "message": "Shutting down, bye..."
      }
      ```
      </details>

## Change log
All important changes to this project will be documented in this place.

### Version 1.0.0
<details>
<summary><b>Features</b> </summary>

* Create a new module application-monitoring-module
* Create a new module user-management-module
* Configure Nginx to serve HomeActiveRemote static context application
* Scripts for starting application on Raspberry
* Configure Nginx to serve HomeActiveDisplay static context application
* Create a new module weather-service-module
* Create a new module embedded-sensor-module
* Split application into modules

</details>

<details>
<summary><b>Bugs</b></summary>

</details>

## Contact

Pawe?? B??achut - blachut.pawel@gmail.com