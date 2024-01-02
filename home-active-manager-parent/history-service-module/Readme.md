## History Service Module

History Service Module gather weather measurement historical data and provides statistics via endpoints.

## Technical details

The module provides gather data via weather service module api and then store it into MySQL database. Data are stored at every hour.
Based on gathered data the mean, max and min value for seleted period can be provided with REST API.

### REST API

Rest api is available on port 8087, beginning with `/api/v1/history/weather`
- **Valid Data**
  Return pair of valid year and months of stored datas
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
        curl --location --request GET 'http://localhost:8082/api/v1/history/weather/measurements' \
       --header 'Content-Type: application/json'
       ```
       </details>

    * **Sample response**

       <details>
       <summary>Click to open </summary>

       ```json
        {
          "values": [
            {
              "year": "2023",
              "months": [
                "01",
                "02",
                "12"
              ]
            },
            {
              "year": "2022",
              "months": [
                "12"
              ]
            }
          ]
        }
       ```
       </details>

- **Statistics per year**
  Return mean, max and min for provided year for requested measurements (temperature, humidity, pressure)
    * **URL**

      /year?type={type}&year={year}
    * **Method**

      `GET`
    * **Params** 
        * type - one of valid: tempIn, humIn, tempOut, humOut, pressure
        * year
      
    * **Success Response**
        * Code: 200
        
    * **Incorrect Response**
      * Code: 404 - when incorrect type provided

    * **Sample call**

       <details>
       <summary>Click to open </summary>

       ```shell
        curl --location --request GET 'http://localhost:8082/api/v1/history/weather/year?type=pressure&year=2023' \
       --header 'Content-Type: application/json'
       ```
       </details>

    * **Sample response**

       <details>
       <summary>Click to open </summary>

       ```json
        {
          "year": "2023",
          "history": [
            {
              "month": "01",
              "min": 19.91,
              "max": 22,
              "mean": 20.72
            },
            {
              "month": "02",
              "min": 1.01,
              "max": 1.01,
              "mean": 1.01
            },
            {
              "month": "12",
              "min": 1.2,
              "max": 32.1,
              "mean": 16.65
            }
          ]
        }
       ```
       </details> 

- **Statistics per month**
  Return mean, max and min for provided year and month for requested measurements (temperature, humidity, pressure)
    * **URL**

      /month?type={type}&year={year}&month={month}
    * **Method**

      `GET`
    * **Params**
        * type - one of valid: tempIn, humIn, tempOut, humOut, pressure
        * year
        *month

    * **Success Response**
        * Code: 200

    * **Incorrect Response**
        * Code: 404 - when incorrect type provided

    * **Sample call**

       <details>
       <summary>Click to open </summary>

       ```shell
        curl --location --request GET 'http://localhost:8082/api/v1/history/weather/month?type=pressure&year=2023&month=01' \
       --header 'Content-Type: application/json'
       ```
       </details>

    * **Sample response**

       <details>
       <summary>Click to open </summary>

       ```json
        {
          "month": "01",
          "year": "2023",
          "history": [
            {
              "day": "01",
              "min": 21,
              "max": 22,
              "mean": 21.5
            },
            {
              "day": "02",
              "min": 19.91,
              "max": 20.01,
              "mean": 19.95
            }
          ]
        }
       ```
       </details>    