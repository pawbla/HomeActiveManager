## Embedded sensor module

Embedded sensor module is responsible for reading datas from DHT11 or DHT22 sensor and share them with REST API.

## Technical details

Reading data from DHT sensor base on part of [Arduino_Python_DHT](https://github.com/adafruit/Adafruit_Python_DHT) written in C. 
The library is compiled to so file and then included and using with JNI mechanism.

Executable library preparation for Raspbian:
1. Crate C header file. To do so enter into `embedded-sensor-module/src/main.java` and execute below command:
   
    ```shell
    javac -target 1.8 -source 1.8 -h .\ \com\pawbla\project\home\embedded\sensor\reader\DHTJniReader.java
    ```
   
    File `com_pawbla_project_home_embedded_sensor_reader_DHTJniReader` should be created.
2. Login on Raspbian
3. Create folder e.g. jni
4. Copy to jni:
    - already created `com_pawbla_project_home_embedded_sensor_reader_DHTJniReader.h` file
    - from `dht_lib`:
      ```
      com_pawbla_project_home_embedded_sensor_reader_DHTJniReader.c
      common_dth_read.c
      common_dht_read.h
      ```
    - all files from Raspberry_Pi or Raspberry_Pi_2 depends on Rpi version
5. Inside jni create another folder `inclide` and copy:
    - jni.h - from `$JAVA_HOME/include`
    - jni.md - from `$JAVA_HOME/include/linux`

6. Execute command:
   
    ```shell
    gcc -o libdhtread.so com_pawbla_project_home_embedded_sensor_reader_DHTJniReader.c common_dht_read.c pi_dht_read.c pi_mmio.c -I"." -I"./include/" -shared -fPIC
    ```

7. Copy already created library `libdhtread.so` into `scripts` folder 

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
        * Header:
            * Date - The date and time at which the message was originated in "HTTP-date" format, e.g. "Sat, 1 Jan 2000 01:01:01 GMT"
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
