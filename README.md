## **Project Architecture**
 <img title="Logotipo" alt="Logo" src="https://github.com/hugosilva12/car_parts/blob/main/Documents/Arquitetura.png" width="600">
 
## *Description of services*

### Auth-Service
This service is responsible for creating accounts that allow authentication in the system. It also manages user permissions and profiles.

### User-Service
The purpose of this service is to manage users in the system. There are 3 types of users: Admin, Worker, and Client.

### Purchase-Service
This service is responsible for managing car purchases. <br>

### Cardisassembly-Service
This service is responsible for managing disassembled parts and is the `central` service of the project. <br>

### Advertising-Service
This service is responsible for managing the advertisements of the parts. <br>

### Storage-Service
This service manages the company's warehouse and oversees the occupancy of the 8 sections that make up the warehouse. <br>

### Precarious-Service
This service stores the company's sales history. It is also in this service that the profit/loss for each car is calculated. <br>

### Sales-Service
This service manages the sale of parts. <br>

### Hystrix-Service
Given the architecture of the project, there is a set of services that collaborate with each other, and if something fails, it can affect others or even make the system inaccessible (for example, the Auth Service, in case of failure, prevents the system from being used). To minimize these failures, Hystrix was used. Hystrix is a library that helps control the interaction between services, providing failure tolerance to latency. When a service fails, it is isolated from others so they can continue functioning. <br>
**Footer Note**: https://github.com/Netflix/Hystrix 

### Gateway-Service
Spring Cloud Gateway provides a library to build an API Gateway over Spring WebFlux. It aims to provide a simple and effective way to route to services and provide cross-cutting concerns to them, such as security and monitoring. All communications from the front-end are sent to the Gateway service, which then redirects them to the service responsible for the endpoint. <br>
Advantages of using the Gateway:
- Centralized system authorization service, as front-end to back-end communication is only handled through the gateway.
- The communication port is always the same, so there is no need to know exactly on which port each service is running.
- It has the functionality of adding filters to the routes. <br>

Disadvantages of using the Gateway:
- In case of failure, the system becomes unavailable.

### ServiceRegistry
Eureka Server is one of the key principles of a microservices-based architecture and contains information about all `client` services. Each microservice in the project is registered with the Eureka server, which then knows the port and IP address of each service. The main advantage of using Eureka is that services can find and communicate with each other without hardcoding the host or port. 

## **Asynchronous Communications Between Services**
**User-Service** :arrow_right: **Auth-Service** The user service sends an authentication message with the account data when a new account is registered. <br><br>

**Auth-Service** :arrow_right: **User-Service** After receiving the data to create an authentication account, it sends a message confirming the registration or, if the data is invalid, sends a message to User-Service to remove the account. <br><br>
**User-Service** :arrow_right: **Auth-Service** The user service sends a message to deactivate the authentication account when a user is removed. <br><br>

**Cardisassembly-Service** :arrow_right: **Purchase-Service** The disassembly service, when it receives a new part to register, sends a message for the purchase service to verify if the car introduced in the creation of a part is valid. <br><br>

**Purchase-Service** :arrow_right: **Cardisassembly-Service** The purchase service sends a message to the disassembly service to inform whether the car is registered in the system. <br><br>

**Cardisassembly-Service** :arrow_right: **Advertising-Service** When the disassembly service receives a message from the purchase service indicating whether the car exists or not, it verifies it and, if valid, sends a message to the advertising service with the data to create a new advertisement. <br><br>

**Advertising-Service** :arrow_right: **Cardisassembly-Service** As soon as an advertisement is created, the advertising service sends a message to the disassembly service indicating that the part is now advertised for sale. 

**Cardisassembly-Service** :arrow_right: **Storage-Service** When the disassembly service receives a message from the purchase service indicating whether the car exists or not, it verifies it, and if valid, sends a message to the warehouse management service indicating that a new part is in stock. <br><br>

**Purchase-Service** :arrow_right: **Cardisassembly-Service** When the purchase service receives a POST request to create a new sale, it sends a message to the disassembly service to check if the part is still available for sale. <br><br>

**Cardisassembly-Service** :arrow_right: **Purchase-Service** When the disassembly service receives a message from the purchase service to verify if the part is available for sale, it performs the check and sends a message confirming or rejecting the sale. <br><br>

**Cardisassembly-Service** :arrow_right: **Storage-Service** When the disassembly service receives a message from the purchase service to check if the part is available for sale, it performs the check and, when the purchase can be confirmed, sends a message to the stock management service to inform that the part is no longer in stock. <br><br>

**Cardisassembly-Service** :arrow_right: **Advertising-Service** When the disassembly service receives a message from the purchase service to check if the part is available for sale, it performs the check and, when the purchase can be confirmed, sends a message to the advertising service to remove the advertisement. <br><br>

**Cardisassembly-Service** :arrow_right: **Precarious-Service** When the disassembly service receives a message from the purchase service to check if the part is available for sale, it performs the check and, when the purchase is confirmed, sends a message to the price history service with the value, date, and details of the part sold. <br><br>

## **Technologies Used**

**Spring Boot** is an open-source framework developed in Java. It was created with the aim of facilitating the development of applications by exploring the concepts of Inversion of Control and Dependency Injection.

**React** React JS is a JavaScript library for creating user interfaces (UI). React is based on components, allowing code reuse and simplifying maintenance.

**MySQL** The relational databases used for services are in MySQL.

**Docker** is an open-source container creation platform, with the main goal of allowing developers to create application packages within containers, simplifying the delivery of distributed applications. The three types of containers used in the project were:
- Spring Boot containers for the services;
- MySQL container for the databases;
- RabbitMQ container for the communication protocol;

**RabbitMQ** is an open-source message broker that implements the Advanced Message Queuing Protocol (AMQP). It handles message traffic quickly and reliably. The choice of this technology for the project was due to its ease of implementation and ability to handle asynchronous communications between services, storing messages in queues.
**Link to RabbitMQ information**: https://en.wikipedia.org/wiki/RabbitMQ (Footer)

**Kubernetes** is an open-source container orchestration system that automates the deployment, scaling, and management of containerized applications. 
**Link to Kubernetes information**: https://www.vmware.com/topics/glossary/content/kubernetes.html (Footer)

## **Docker Compose**
Use `docker-compose-single-database.yaml` for a single database setup. The `docker-compose.yaml` is for demonstrating the functionality of multiple containers for databases.



