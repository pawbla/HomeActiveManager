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
        "moon": {
          "text":{
            "date":"2000-00-00",
            "isError":false,
            "value":"Przybywający sierp"
          },
        "picture":{
          "date":"2000-00-00",
          "isError":false,
          "value":"1"
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
        "history": {
            "date": "05.05 17:17",
            "isError": false,
            "pressure": [
                {
                    "date": "2019-10-04T16:00:00.000Z",
                    "value": "1011"
                },
                {
                    "date": "2019-10-04T17:00:00.000Z",
                    "value": "1011"
                },
                {
                    "date": "2019-10-04T18:00:00.000Z",
                    "value": "1011"
                },
                {
                    "date": "2019-10-04T19:00:00.000Z",
                    "value": "1011"
                },
                {
                    "date": "2019-10-04T20:00:00.000Z",
                    "value": "1011"
                },
                {
                    "date": "2019-10-04T21:00:00.000Z",
                    "value": "1010"
                },
                {
                    "date": "2019-10-04T22:00:00.000Z",
                    "value": "1010"
                },
                {
                    "date": "2019-10-04T23:00:00.000Z",
                    "value": "1010"
                },
                {
                    "date": "2019-10-05T00:00:00.000Z",
                    "value": "1009"
                },
                {
                    "date": "2019-10-05T01:00:00.000Z",
                    "value": "1009"
                },
                {
                    "date": "2019-10-05T02:00:00.000Z",
                    "value": "1009"
                },
                {
                    "date": "2019-10-05T03:00:00.000Z",
                    "value": "1008"
                },
                {
                    "date": "2019-10-05T04:00:00.000Z",
                    "value": "1008"
                },
                {
                    "date": "2019-10-05T05:00:00.000Z",
                    "value": "1008"
                },
                {
                    "date": "2019-10-05T06:00:00.000Z",
                    "value": "1008"
                },
                {
                    "date": "2019-10-05T07:00:00.000Z",
                    "value": "1008"
                },
                {
                    "date": "2019-10-05T08:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T09:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T10:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T11:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T12:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T13:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T14:00:00.000Z",
                    "value": "1007"
                },
                {
                    "date": "2019-10-05T15:00:00.000Z",
                    "value": "1007"
                }
            ]
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
            "responseCode": 200,
              "dailyCounter": {
                "requests": 1,
                "errors": 0
              },
              "sumCounter": {
                "requests": 1,
                "errors": 0
              }
          },
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "AccuWeather",
            "name": "accuweather",
            "link": "https://www.accuweather.com/",
            "errorMessage": "",
            "responseCode": 200,
              "dailyCounter": {
                "requests": 1,
                "errors": 0
              },
              "sumCounter": {
                "requests": 1,
                "errors": 0
              }
          },
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "Sunrise Sunset",
            "name": "sun",
            "link": "https://sunrise-sunset.org/",
            "errorMessage": "",
            "responseCode": 200,
              "dailyCounter": {
                "requests": 1,
                "errors": 0
              },
              "sumCounter": {
                "requests": 1,
                "errors": 0
              }
          },
          {
            "date": "10.05 21:22",
            "isError": false,
            "provider": "AirLy",
            "name": "airLy",
            "link": "https://www.airly.eu/",
            "errorMessage": "",
            "responseCode": 200,
              "dailyCounter": {
                "requests": 1,
                "errors": 0
              },
              "sumCounter": {
                "requests": 1,
                "errors": 0
              }
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