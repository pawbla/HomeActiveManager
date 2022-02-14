## Monitoring Application Module

Monitoring Application Module is created to monitor and gather applications health and shutdown system.

## Technical details

Health data are gathered with using of Spring Boot actuators and information is shared with REST API.
Applications health is checking by enabling on them proper actuator and periodically send request to fetch information.
Module allows also shutdown application by send REST request on the correct endpoint. To do that every application
should be properly registered in `application.properties` to inform the module that shutdown request should be sent 
on defined port.

Currently is implemented shutdown mechanism only.

### REST API

Rest api is available on port 8085, with url beginning with `/api/v1/monitoring`
- **Shutdown application and system**

  Return data about user.
    * **URL**

      /shutdown
    * **Method**

      `POST`
    * **Success Response**
        * Code: 200

    * **Sample call**
       <details>
       <summary>Click to open </summary>

       ```shell
       curl --location --request POST 'http://localhost:8085/api/v1/monitoring/shutdown' --header 'Content-Type: application/json'
       ```
       </details>
    
### Usage

1. Development with IntelliJ

   Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    ```

   That configuration allows to test runtime application with mocked service for development testing purpose.
