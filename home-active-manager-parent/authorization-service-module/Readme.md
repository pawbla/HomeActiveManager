## Authorization Service Module

Authorization Service Module is working as a service which provides authentication mechanism to configured Nginx.

## Technical details

The module provides oauth mechanism. First endpoint, based on request with username and password, provides generated
JWT bearer token. The second endpoint allows to authenticated request based on JWT token which should be included
in header. 

### REST API

Rest api is available on port 8086
- **Authencitaion**

  Authenticate user based on user name and password.
    * **URL**

      /oauth/token?grant_type=password&scope=any
    * **Method**

      `POST`
    * **Param**
      
        grant_type - grant type name
        
        scope - scope name, permission set to the token, a context which token may act
    * **Success Response**
        * Code: 200

  * **Sample call**
     <details>
     <summary>Click to open </summary>

     ```shell
     curl --location --request POST 'http://localhost:8086/oauth/token?grant_type=password&scope=any' \
    --header 'Authorization: Basic Zmlyc3QtY2xpZW50Om5vb25ld2lsbGV2ZXJndWVzcw==' \
    --form 'username="admin"' \
    --form 'password="password"'
     ```
     </details>

  * **Sample response**
    <details>
    <summary>Click to open </summary>

    ```json
       {
            "access_token": "XXDis9v3wAGm9BotgCqk5TiHv6w",
            "token_type": "bearer",
            "refresh_token": "VqyV1Q7-QtFbygeam-YcoPADHNw",
            "expires_in": 43199,
            "scope": "any"
        }
    ```
    </details>

- **Authorization**    
    Authorize access to the application base on JWT token.
    * **URL**

        /api/v1/authorization/validate
    * **Method**

        `POST`
    * **Param - header**

        Authorization: Bearer <token>
    * **Success Response**
          * Code: 200

    * **Sample call**
     <details>
     <summary>Click to open </summary>

     ```shell
     curl --location --request POST 'http://localhost:8086/api/v1/authorization/validate' \
     --header 'Authorization: Bearer XXDis9v3wAGm9BotgCqk5TiHv6w
     ```
     </details>

    * **Sample response**
    <details>
    <summary>Click to open </summary>

    ```json
       {
           "active": true
       }
    ```
    </details>

### Usage

1. Development with IntelliJ

   Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    ```

   That configuration allows to test runtime application with mocked service for development testing purpose.
