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
## Menu API

The Menu API allows users to create and retrieve menu items associated with categories.

### Base URL

```text
/api/menu
```

### Endpoints

#### Create Menu

**POST** `/api/menu`

Request Body:

```json
{
  "name": "Chicken Burger",
  "description": "Crispy chicken",
  "price": 55.00,
  "imageUrl": "https://placehold.co/300",
  "categoryId": 1
}
```

Response:

```json
{
  "statusCode": 200,
  "message": "Menu created",
  "data": {
    "id": 1,
    "name": "Chicken Burger",
    "description": "Crispy chicken",
    "price": 55.00,
    "imageUrl": "https://placehold.co/300",
    "categoryId": 1,
    "categoryName": "Burgers"
  },
  "timestamp": "2026-06-19T18:18:46"
}
```

---

#### Get All Menus

**GET** `/api/menu`

Returns a list of all menu items.

---

#### Get Menu By ID

**GET** `/api/menu/{id}`

Example:

```text
GET /api/menu/1
```

Returns a single menu item if found.

---

### Menu Entity Fields

| Field        | Description                     |
| ------------ | ------------------------------- |
| id           | Unique menu identifier          |
| name         | Menu item name                  |
| description  | Menu item description           |
| price        | Item price stored as BigDecimal |
| imageUrl     | Image URL for the menu item     |
| categoryId   | Category identifier             |
| categoryName | Category name                   |

### Validation Rules

* name cannot be blank
* price cannot be null
* price must be greater than or equal to 0
* categoryId cannot be null
* categoryId must reference an existing category

### Database Relationship

A Menu belongs to one Category using a Many-to-One relationship:

```java
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;
```
