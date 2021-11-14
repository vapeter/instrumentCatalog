# Instrument catalog demo application

This application was developed for registering instruments ofthe school. The main features of the application are adding andediting instruments, importing personal data, managing therentals of the musical instruments.

## Prerequisites
* Java 11
* Spring Boot 2.4.4
* Maven 3.5.0+
* MySQL 8.0.23
* Angular CLI: 10.0.8
* Node js 14.15.3
* Docker Engine 19.03.0+
* Keycloak 11.0.2

#### The application can be tested at [https://instrumentcatalog.vargapeter.eu](https://instrumentcatalog.vargapeter.eu)
---
# Development
## Building the backend

If you would like to run the application on a local machine, please set in the application.properties -> spring.profiles.active to local and
set the keycloak.auth-server-url variable to your ip address.

If you would like to run the application on a server machine in production mode, please set in the application.properties -> spring.profiles.active to production, and don't forget to set the keycloak.auth-server-url variable at your server ip.

Navigate to the backend folder and:
* From command line `mvn clean package`

## Building the frontend

If you would like to run the application on a local machine:

Navigate to the frontend folder and:
* From command line `ng build --configuration=local`

If you would like to run the application on a server machine in production mode:

please set in the environment.prod.ts -> backendUrl, redirectUrl, keycloakUrl variable at your server ip.

Navigate to the frontend folder and:
* From command line `ng build --prod`

---
## Running the Application

From command line:
```
'sudo' docker network create instrumentcatalog-net
```
* Please navigate to the instrument_catalog folder, then enter 

```
'sudo' docker-compose up -d
```
* please, wait a few minute (it may take several minutes, depending on your computer)

Open a browser and visit [http://localhost:9080](http://localhost:9080) for Configuring Keycloak
* Click to the Administration Console
* >Username: admin
* >Password: KeycloakAdmin
  
If you would like to choose a different username and password, feel free to change on the docker-compose.yaml's KEYCLOAK_USER and KEYCLOAK_PASSWORD fields

#### 1. Create the following user
* On Users page, click Add user, choose a username, and the Email Verified switch should be ON -> save
* On the Credentials tab, set a password
* On the Role Mappings tab, highlight the `users` and the `admin` option in the **Available Roles** block and then click on the "Add selected" button.
* On the Client Roles select choose catalog, and highlight the `users` and the `admin` option in the **Available Roles** block and then click on the "Add selected" button.

### Enter the application
* >Open a browser and visit [http://localhost:9200](http://localhost:9200) (*orYourServerIp:9200*) for using the application
  * Click login, and enter the username and password

---
# Spring Boot Configuration Properties
Please see [here](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
for details.
