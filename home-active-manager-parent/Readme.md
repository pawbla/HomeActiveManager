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
- Authorization service module  
- User management module
- History service module
- Testing module

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
#### [1.0.0] - 16.12.2023
* **History service module:** new module

## Change log
#### [1.0.0] - 19.01.2022

* **Application monitoring service:** Add system restart option
* **Weather service module:** Decimal numbers handling for temperature
* **Embedded sensor module:** Temperature with decimals

#### [1.0.0] - 11.01.2022
* **Weather service module:** Improvement in errors and data measurements
* **Weather service module:** Handling AirLy sensor disabled

#### [1.0.0] - 3.10.2022
* **Weather service module:** Parsers refactoring

#### [1.0.0] - 3.10.2022
* **Embedded sensor module:** DHT measurement based on JNI and C library
* **Weather service module:** Additional measured values  

#### [1.0.0] - 16.08.2022
* **Application monitoring service:** Added system status and monitoring
* **Fix:** Issue with refreshing error status in weather module
* **Fix:** Not refreshed AirLy data when sensor was inactive before

#### [1.0.0] - 22.06.2022
* **Weather service module:** Added request counter

#### [1.0.0] - 22.05.2022
* **Weather service module:** Moon phase implementation

#### [1.0.0] - 14.03.2022
* **Weather service module:** Pressure history implementation
* **Fix:** missing init conditions in weather sensor 

#### [1.0.0] - 14.03.2022
* **Add:** Authentication service module

#### [1.0.0] - 14.02.2022
* **Add:** Application monitoring module
* **Application monitoring module:** shutdown system implementation

#### [1.0.0] - 22.01.2022
* **Weather Service Module:** Parsers refactoring
* **User management module:** implementation
* **Update:** user management scripts

#### [1.0.0] - 3.12.2021
* Integration with Home Active Display app
* Configuration for DHT22

#### [1.0.0] - 3.11.2021
* **Add:** Start application scripts

#### [1.0.0] - 23.10.2021
* **Add:** Embedded sensor module

#### [1.0.0] - 5.10.2021
* **Add:** weather service module
* **Add:** user management module
* **Weather service module:** app refactoring based on old application

## Creating new module

1. Enter into parent folder `...\HomeActiveManager\home-active-manager-parent` and executed the following comand `mvn archetype:generate -DgroupId=com.pawbla.project.home -DartifactId=<artifact-id>` 
  

## Contact

Paweł Błachut - blachut.pawel@gmail.com