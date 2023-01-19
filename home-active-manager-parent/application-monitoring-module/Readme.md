## Monitoring Application Module

Monitoring Application Module is created to monitor and gather applications health and shutdown system.

## Technical details

Health data are gathered with using of Spring Boot actuators and information is shared with REST API.
Applications health is checking by enabling on them proper actuator and periodically send request to fetch information.
Module allows also shutdown application by send REST request on the correct endpoint. 


System status and monitoring endpoint provide information about:
- used hardware;
- operating system;
- network;
- memory;
- used java environment;
- registered applications status: 
    - name and version
    - health check result.
    
Every application should be registered in `application.properties` by providing its port. It allows to support 
both shutdown and monitoring functionality.


## REST API

Rest api is available on port 8085, with url beginning with `/api/v1/monitoring`
- **Shutdown or restart application and system**

  Return data about user.
    * **URL**

      /shutdown/{command}
    * **Method**

      `POST`

    * **Param**
        
        command - allowed: ***hold*** - close applications and shutdown the system, ***restart*** - close applications and restart the system       

    * **Success Response**
        * Code: 200

  * **Incorrect Response**
      * Code: 400 - when used param is not allowed, e.g. ***./shutdown/badparam***

    * **Sample call**
       <details>
       <summary>Click to open </summary>

       ```shell
       curl --location --request POST 'http://localhost:8085/api/v1/monitoring/shutdown/hold' --header 'Content-Type: application/json'
       ```
       </details>

- **System status and application monitoring**
  Return information about the system (hardware, OS, java, network, memory) and about application (version, name, health check).
    * **URL**

      /status
    * **Method**

      `GET`
    * **Success Response**
        * Code: 200

    * **Sample call**
       <details>
       <summary>Click to open </summary>

       ```shell
       curl --location --request POST 'http://localhost:8085/api/v1/monitoring/status' --header 'Content-Type: application/json'
       ```
       </details>

        * **Sample response(mocked data)**

         <details>
         <summary>Click to open </summary>
    
        ```json
          {
              "hardware": {
                  "platformLabel": "platform_label",
                  "platformId": "platform_id",
                  "cpuRevision": "cpu_revision",
                  "cpuArchitecture": "cpu_architecture",
                  "modelName": "model_name",
                  "processor": "processor",
                  "boardTypeName": "board_type_name",
                  "cpuTemperature": 12.0,
                  "cpuVoltage": 3.5,
                  "dateTime": "2022-08-16T20:40:01"
              },
              "os": {
                  "name": "OS Name",
                  "version": "OS Version",
                  "architecture": "OS Architecture",
                  "firmwareBuild": "OS Firmware build",
                  "firmwareDate": "OS Firmware date"
              },
              "network": {
                  "hostName": "Network Host Name",
                  "ipAddress": [
                      "1.1.1.1",
                      "1.1.1.2"
                  ],
                  "fqdn": [
                      "1.1.2.1",
                      "1.1.2.2"
                  ],
                  "nameserver": [
                      "Nameserver1",
                      "Namserver2"
                  ]
              },
              "memory": {
                  "total": 50,
                  "used": 20,
                  "free": 5,
                  "shared": 15,
                  "buffers": 5,
                  "cached": 5
              },
              "java": {
                  "vendor": "Vendor",
                  "vendorUrl": "vendor.url",
                  "version": "1.8",
                  "virtualMachinve": "vm",
                  "runtime": "jre-1.8"
              },
              "applications": [
                  {
                      "port": "8081",
                      "name": "FirstApp",
                      "version": "1.0",
                      "health": true,
                      "dateTime": "2022-08-16T19:40:01"
                  },
                  {
                      "port": "8082",
                      "name": "SecondApp",
                      "version": "2.0s",
                      "health": false,
                      "dateTime": "2022-08-16T19:40:01"
                  }
              ]
          }      
         ```
         </details>
    
    
## Usage

1. Development with IntelliJ

   Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    ```

   That configuration allows to test runtime application with mocked service for development testing purpose.
