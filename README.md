
# Microservices Architecture Application

This application is composed of multiple microservices representing a distributed system. Each microservice is responsible for a specific domain within the architecture, allowing the system to scale independently and be highly modular.
<p align="center">
  <img width="790" alt="apka" src="https://github.com/user-attachments/assets/02c3e513-7268-449d-97be-aa6f965bf4e8">
</p>

## Microservices
1. **Customer Service**  
   Manages customer data and interactions, utilizing MongoDB for storage.

2. **Product Service**  
   Manages products and their details, including inventory and descriptions, also using MongoDB for persistence.

3. **Order Service**  
   Manages customer orders, including placing, updating, and tracking orders. It connects to other services such as the Product and Payment services.

4. **Payment Service**  
   Handles payment transactions, processes payments, and communicates payment confirmations asynchronously using Kafka.

5. **Notification Service**  
   Sends notifications related to order and payment confirmations. It receives messages through Kafka and stores notification logs in MongoDB.

## Communication

- **Synchronous communication**  
  Microservices communicate with each other synchronously using REST via **OpenFeign** or **RestTemplate**.

- **Asynchronous communication**  
  Event-driven architecture with **Apache Kafka** for asynchronous communication between services like `Order`, `Payment`, and `Notification`.

## Key Technologies

- **Configuration Server**  
  Centralized configuration management using a Spring Cloud Config Server, enabling all microservices to share and update their configurations from a common source (e.g., Git repository).

- **Discovery Server (Eureka)**  
  Service discovery and registration using **Spring Cloud Netflix Eureka**, which allows the microservices to dynamically discover each other without hard-coding the service URLs.

- **API Gateway**  
  A centralized entry point for clients accessing different services. The **API Gateway** handles routing and provides cross-cutting concerns such as security and rate limiting.

- **Kafka**  
  Used for **asynchronous messaging** between microservices. For example, the `Order` service sends an event to Kafka once an order is confirmed, and the `Payment` service listens for the event to process payments.

- **Distributed Tracing (Zipkin)**  
  For performance monitoring and distributed tracing, **Zipkin** is used alongside **Spring Boot Actuator** to trace requests across multiple services, allowing easier diagnosis of latency issues and bottlenecks.

- **Liquibase**  
  For database version control, **Liquibase** is used to manage and apply schema changes across different services.

## Infrastructure Setup

The application’s infrastructure, including all services and dependencies such as Kafka, MongoDB, Zipkin, Eureka, and the Config Server, is containerized using **Docker** and orchestrated with **Docker Compose**. This allows developers to spin up the entire architecture locally or in a staging environment with minimal setup.

## How It Works

- **Customer Service** exposes `/customers` endpoints for client applications to manage customer data.
- **Product Service** exposes `/products` endpoints to manage product information.
- **Order Service** allows placing orders with synchronous calls to the Product service and an asynchronous flow to Payment and Notification services.
- **Payment Service** processes payments for orders and notifies the Notification service via Kafka once payments are confirmed.
- **Notification Service** listens for Kafka events and sends notifications related to order and payment confirmations.

## Technologies Used

- **Spring Boot**
- **Spring Cloud Config Server** for centralized configuration
- **Spring Cloud Netflix Eureka** for service discovery
- **Spring Cloud Gateway** as the API Gateway
- **Apache Kafka** for asynchronous messaging
- **OpenFeign** and **RestTemplate** for synchronous communication
- **Zipkin** and **Spring Boot Actuator** for distributed tracing
- **Liquibase** for database migrations
- **PostgreSQL for data storage
- **MongoDB** for data storage
- **Docker** and **Docker Compose** for containerization and orchestration
