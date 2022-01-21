## Embedded sensor module

Embedded sensor module is responsible for reading datas from DHT11 or DHT22 sensor and share them with REST API.

Sensor is connected into Raspberry Pi.

### REST API

Rest api is available on port 8083 with prefixed `/api/v1/embedded`

   - **Show measured datas**
    
        Return weather datas measured by module
   * **URL**

      /measurements
   * **Method**

      `GET`
   * **Param**

      user - username of user storend in database
   * **Success Response**
        * Code: 200

   * **Sample call**
    <details>
    <summary>Click to open </summary>
    ```shell
     curl --location --request GET 'http://localhost:8082/api/v1/embedded/measurements' \
     --header 'Content-Type: application/json'
    ```
    </details>

   * **Sample response**
      <details>
      <summary>Click to open </summary>

      ```json
      {
        "dateTime":"2021-10-23 01:01:01",
        "temperature":12,
        "humidity":35,
        "isError": false
      }
      ```
      </details>


### Usage

1. Development with IntelliJ

   Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    --custom.dhtDataPin=25
    --custom.dhtSupplyPin=24
    ```

   That configuration allows to test runtime application with mocked service for development testing purpose.


2. Linux (Raspberry Pi)

### TO DO:
- Fix problem with reading from DHT (probably hardware error) and describe connection and pinout
- Improve existing and add unit tests
