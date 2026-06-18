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

## API Response Format

All API responses use a standardized `Response<T>` wrapper.

### Success Response

```json
{
  "statusCode": 200,
  "message": "Category retrieved",
  "data": {
    "id": 1,
    "name": "Burgers"
  },
  "timestamp": "2026-06-18T08:42:11"
}
```

### Error Response

```json
{
  "statusCode": 404,
  "message": "Category not found",
  "timestamp": "2026-06-18T08:45:22"
}
```

### Fields

| Field      | Description                              |
| ---------- | ---------------------------------------- |
| statusCode | HTTP status code returned by the API     |
| message    | Description of the result                |
| data       | Returned payload (if available)          |
| timestamp  | Date and time the response was generated |

### Why Use a Standard Response Format?

* Consistent API responses
* Easier frontend integration
* Simplified error handling
* Better debugging and logging
* Improved maintainability

```
```
