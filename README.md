# Food Ordering System

A Spring Boot REST API for managing a food ordering system.

## Tech Stack
- Java 17
- Spring Boot 3.4.1
- Spring Data JPA
- MySQL
- Maven

## How to Run
```bash
mvnw spring-boot:run
```

## Endpoints

| Method | URL                   | Body                          |
|--------|-----------------------|-------------------------------|
| POST   | /api/categories       | { "name", "description" }     |
| GET    | /api/categories       | -                             |
| GET    | /api/categories/{id}  | -                             |
| PUT    | /api/categories/{id}  | { "name", "description" }     |
| DELETE | /api/categories/{id}  | -                             |