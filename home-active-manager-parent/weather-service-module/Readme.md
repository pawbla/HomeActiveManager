## Weather Service Module

Weather Service module is responsible for gathering condition measurements and share them with REST API.

### Providers

Measurements are gathered from several free APIs:
- AccuWeather [API](https://developer.accuweather.com/)
- AirLy [API](https://airly.org/pl/produkty/airly-api/)
- Sunrise and Sunset [API](https://sunrise-sunset.org/api)

### REST API

### Usage

1. Development with IntelliJ

    Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    --custom.ipInternalSensor=http://localhosat:8081/mocked_internalsensor
    --custom.intSensorPassword=mockedApiKey
    --custom.ipExternalSensor=http://localhost:8081/mocked_externalsensor
    --custom.ipAirLy=http://localhost:8081/mocked_airly
    --custom.ipAirLyInstallation=http://localhost:8081/mocked_airlyInstalation
    --custom.apiKeyAirLy=mockedApiKey
    --custom.ipSunSetRise=http://localhost:8081/mocked_sunsetrise
    --custom.urlAccuWeather=http://localhost:8081/mocked_accuweather
    ```

    That configuration allows to test runtime application with mocked service for development testing purpose.
   
2. Linux (Raspberry Pi)

### TO DO:
- Improve existing and add unit tests