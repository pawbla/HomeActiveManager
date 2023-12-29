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

    /validData
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
            "isError": false,
            "value": "62"
          },
          "pm10percent": {
            "isError": false,
            "value": "62"
          },
          "pm25": {
            "isError": false,
            "value": "17"
          },
          "pm1": {
            "isError": false,
            "value": "13"
          },
          "caqiColor": {
            "isError": false,
            "value": "#D1CF1E"
          },
          "caqi": {
            "isError": false,
            "value": "31"
          },
          "pm10": {
            "isError": false,
            "value": "31"
          }
        },
        "moon": {
          "text":{
            "isError":false,
            "value":"Przybywający sierp"
          },
        "picture":{
          "isError":false,
          "value":"1"
          }
        },
        "in": {
          "temperature": {
            "isError": false,
            "value": "1"
          },
          "humidity": {
            "isError": false,
            "value": "35"
          }
        },
        "weather": {
          "ceiling": {
            "isError": false,
            "value": "457"
          },
            "isPrecipitation": {
            "isError": false,
            "value": false
          },
          "windDirectionDeg": {
            "isError": false,
            "value": "45"
          },
          "weatherIcon": {
            "isError": false,
            "value": "7"
          },
          "visibility": {
            "isError": false,
            "value": "10"
          },
          "cloudCover": {
            "isError": false,
            "value": "100"
          },
          "isDayTime": {
            "isError": false,
            "value": true
          },
          "uvIndexDescription": {
            "isError": false,
            "value": "Niska"
          },
          "uvIndexColor": {
            "isError": false,
            "value": "#ffc800"
          },
          "pressure": {
            "isError": false,
            "value": "1007"
          },
          "windDirection": {
            "isError": false,
            "value": "NE"
          },
          "windSpeed": {
            "isError": false,
            "value": "18"
          },
          "uvIndexValue": {
            "isError": false,
            "value": "7"
          },
            "precipitationType": {
            "isError": false,
            "value": ""
          },
          "weatherText": {
            "isError": false,
            "value": "Czesciowo slonecznie "
          }
        },
        "history": {
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
            "isError": false,
            "value": "11:23"
          },
          "set": {
            "isError": false,
            "value": "18:10"
          },
          "rise": {
            "isError": false,
            "value": "06:46"
          }
        },
        "out": {
          "temperature": {
            "isError": false,
            "value": "9"
          },
          "humidity": {
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
            "date": "2023-01-11T19:06:13Z",
            "okResponseDate": "2023-01-11T19:06:13Z",
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
            "date": "2023-01-11T19:06:13Z",
            "okResponseDate": "2023-01-11T19:06:13Z",
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
            "date": "2023-01-11T19:06:13Z",
            "okResponseDate": "2023-01-11T19:06:13Z",
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
            "date": "2023-01-11T19:06:13Z",
            "okResponseDate": "2023-01-11T19:06:13Z",
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