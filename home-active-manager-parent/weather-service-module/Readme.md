## Weather Service Module

Weather Service module is responsible for gathering condition measurements and share them with REST API.

### Providers

Measurements are gathered from several free APIs:
- AccuWeather [API](https://developer.accuweather.com/)
- AirLy [API](https://airly.org/pl/produkty/airly-api/)
- Sunrise and Sunset [API](https://sunrise-sunset.org/api)

### REST API

Rest api is available on port 8082, beginning with `/api/v1/weather/`
  - **Show measured datas**

    Return weather datas gathered by module
  * **URL**

    /measurements
  * **Method**

    `GET`
  * **Success Response**
    * Code: 200

  * **Sample call**

     <details>
     <summary>Click to open </summary>
    
     ```shell
      curl --location --request GET 'http://localhost:8082/api/v1/weather/measurements' \
     --header 'Content-Type: application/json'
     ```
     </details>
  
  * **Sample response**

     <details>
     <summary>Click to open </summary>

     ```json
       {
        "airPolution": {
          "pm25percent": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "62"
          },
          "pm10percent": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "62"
          },
          "pm25": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "17"
          },
          "pm1": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "13"
          },
          "caqiColor": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "#D1CF1E"
          },
          "caqi": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "31"
          },
          "pm10": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "31"
          }
        },
        "in": {
          "temperature": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "1"
          },
          "humidity": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "35"
          }
        },
        "weather": {
          "ceiling": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "457"
          },
          "windDirectionDeg": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "45"
          },
          "weatherIcon": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "7"
          },
          "visibility": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "10"
          },
          "cloudCover": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "100"
          },
          "uvIndexDescription": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "Niska"
          },
          "uvIndexColor": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "#ffc800"
          },
          "pressure": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "1007"
          },
          "windDirection": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "NE"
          },
          "windSpeed": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "18"
          },
          "uvIndexValue": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "7"
          },
          "weatherText": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "Czesciowo slonecznie "
          }
        },
        "sun": {
          "dayLength": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "11:23"
          },
          "set": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "18:10"
          },
          "rise": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "06:46"
          }
        },
        "out": {
          "temperature": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "9"
          },
          "humidity": {
            "date": "10.05 21:22",
            "isError": false,
            "value": "95"
          }
        }
      }
     ```
     </details>

- **Show services information**

  Return status information about used services to gather weather informations.
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
      curl --location --request GET 'http://localhost:8082/api/v1/weather/status' \
      --header 'Content-Type: application/json'
     ```
     </details>

  * **Sample response**

     <details>
     <summary>Click to open </summary>

    ```json
      {
        "connectors": [
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "Internal ESP8266",
            "name": "internal",
            "link": "",
            "errorMessage": "",
            "responseCode": 200
          },
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "AccuWeather",
            "name": "accuweather",
            "link": "https://www.accuweather.com/",
            "errorMessage": "",
            "responseCode": 200
          },
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "Sunrise Sunset",
            "name": "sun",
            "link": "https://sunrise-sunset.org/",
            "errorMessage": "",
            "responseCode": 200
          },
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "AirLy",
            "name": "airLy",
            "link": "https://www.airly.eu/",
            "errorMessage": "",
            "responseCode": 200
          }
        ]
      }      
     ```
     </details>


### Usage

1. Development with IntelliJ

    Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    --custom.ipInternalSensor=http://localhost:8082/mocked_internalsensor
    --custom.intSensorPassword=mockedApiKey
    --custom.ipExternalSensor=http://localhost:8082/mocked_externalsensor
    --custom.ipAirLy=http://localhost:8082/mocked_airly
    --custom.ipAirLyInstallation=http://localhost:8082/mocked_airlyInstalation
    --custom.apiKeyAirLy=mockedApiKey
    --custom.ipSunSetRise=http://localhost:8082/mocked_sunsetrise
    --custom.urlAccuWeather=http://localhost:8082/mocked_accuweather
    ```

    That configuration allows to test runtime application with mocked service for development testing purpose.
   

2. Linux (Raspberry Pi)

### TO DO:
- Improve existing and add unit tests