## User Management Module

User Management Module is responsible for management of users stored into MySQL databse. 
Manipulation with data is allowed using REST Api.

### REST API

Rest api is available on port 8084, with url beginning with `/api/v1/usermanagement`
- **Show user**
  
   Return data about user.
   * **URL**
     
     /user?login=user
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
      curl --location --request GET 'http://localhost:8084/api/v1/usermanagement/user?login=user'
      ```
      </details>
     
    * **Sample response**
      <details>
      <summary>Click to open </summary>
      
      ```json
         {
           "firstName": "userName1",
           "role": "ROLE_USER",
           "user_id": 1,
           "enabled": false,
           "username": "user"
        }
      ```
      </details>
   
   
- **Users list**
  
  Return data about users stored in database.
   * **URL**

      /users
   * **Method**

     `GET`

   * **Success Response**
      * Code: 200

   * **Sample call**
      <details>
      <summary>Click to open </summary>

      ```shell
      curl --location --request GET 'http://localhost:8084/api/v1/usermanagement/users'
      ```
      </details>

   * **Sample response**
     <details>
     <summary>Click to open </summary>

     ```json
        {
           "users": [
              {
                 "firstName": "userName1",
                 "role": "ROLE_USER",
                 "user_id": 1,
                 "enabled": false,
                 "username": "user"
              },
              {
                 "firstName": "userName2",
                 "role": "ROLE_GUEST",
                 "user_id": 2,
                 "enabled": false,
                 "username": "guest"
              },
              {
                 "firstName": "adminFirstName",
                 "lastName": "adminLastName",
                 "role": "ROLE_ADMIN",
                 "user_id": 3,
                 "enabled": true,
                 "email": "adres.email@email.com",
                 "username": "admin"
              }
           ]
        }
     ```
     </details>
  
   
- **Roles list**
  
  Return all available roles.
   * **URL**

      /roles
   * **Method**

     `GET`

   * **Success Response**
      * Code: 200

   * **Sample call**
      <details>
      <summary>Click to open </summary>

      ```shell
      curl --location --request GET 'http://localhost:8084/api/v1/usermanagement/roles'
      ```
      </details>

   * **Sample response**
     <details>
     <summary>Click to open </summary>

     ```json
        {
          "roles": [
              {
                 "role": "ROLE_USER",
                 "role_id": 1
              },
              {
                 "role": "ROLE_ADMIN",
                 "role_id": 2
              },
              {
                 "role": "ROLE_GUEST",
                 "role_id": 3
              }
           ]
        }
     ```
     </details>
   
  
- **Register user**
  
  Register new user, add to the database.
   * **URL**

      /user
   * **Method**

     `POST`
   * **Request Body**

     ```json
      {
         "firstName": "userToStoreFName",
         "lastName": "userToStoreLName",
         "email": "emai@toStore",
         "userName": "userToStore",
         "password": "passToStore"
      }
     ```
   * **Success Response**
      * Code: 200

   * **Sample call**
      <details>
      <summary>Click to open </summary>

      ```shell
      curl --location --request POST 'http://localhost:8084/api/v1/usermanagement/user' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "firstName": "userToStoreFName",
      "lastName": "userToStoreLName",
      "email": "emai@toStore",
      "userName": "userToStore",
      "password": "passToStore"
      }'
      ```
      </details>

   * **Sample response**
     <details>
     <summary>Click to open </summary>

     ```text
        user stored succesfully
     ```
     </details>
  
   
- **Remove user**
  
  Remove user from database.
   * **URL**

      /user/{userId}
   * **Method**

     `DELETE`
   * **Param**

     userId - id of the user stored in a database
   * **Success Response**
      * Code: 200

   * **Sample call**
      <details>
      <summary>Click to open </summary>

      ```shell
      curl --location --request DELETE 'http://localhost:8084/api/v1/usermanagement/user/4'
      ```
      </details>
   
  
- **Update user**
  
  Update data in the database for the user.
   * **URL**
   
     /user/{userId}
   * **Method**

      `PUT`
   * **Param**
   
     userId - id of the user to be updated
   * **Request Body**

     ```json
      {
        "firstName":"newFirstName",
        "lastName":"newLastName",
        "role": {"role": "ROLE_GUEST"},
        "email":"new.email@com",
        "enabled":false,
        "username":"updateUser"
     }
     ```
   * **Success Response**
      * Code: 200

   * **Sample call**
      <details>
      <summary>Click to open </summary>

      ```shell
      curl --location --request PUT 'http://localhost:8084/api/v1/usermanagement/user/5' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "firstName":"newFirstName",
      "lastName":"newLastName",
      "role": {"role": "ROLE_GUEST"},
      "email":"new.email@com",
      "enabled":false,
      "username":"updateUser"
      }'
      ```
      </details>
  
- **Update password**
  
  Set new password for the user.
   * **URL**

      /password/{userId}
   * **Method**

     `PUT`
   * **Param**

     userId - id of the user to be updated password
   * **Request Body**

     ```json
      {
        "oldPassword":"password",
        "newPassword":"newpass"
     }
     ```
   * **Success Response**
      * Code: 200

   * **Sample call**
      <details>
      <summary>Click to open </summary>

      ```shell
      curl --location --request PUT 'http://localhost:8084/api/v1/usermanagement/password/6' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "oldPassword":"password",
      "newPassword":"newpass"
      }'
      ```
      </details>


### Usage

1. Development with IntelliJ

   Use the following configuration for running app in IntelliJ IDEA:
    ```
    --spring.profiles.active=dev
    ```

   That configuration allows to test runtime application with mocked service for development testing purpose.
