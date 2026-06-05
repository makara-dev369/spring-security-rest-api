# HRManagerAPI

A RESTful HR Management System built with **Spring Boot**, featuring JWT-based authentication, role-based access control, and full employee/department/attendance management.

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Configuration](#configuration)

---

## Overview

HRManagerAPI is a backend service for managing human resources operations. It provides secure endpoints for handling employees, departments, and attendance records, with JWT authentication protecting all routes.

---

## Project Structure

```
src/main/java/com/makara/HRManagerAPI/
├── config/           # Spring Security & application configuration
├── controller/       # REST controllers (HTTP layer)
├── dto/              # Data Transfer Objects (request/response models)
├── exception/        # Global exception handling
├── mapper/           # Entity ↔ DTO mapping
├── model/            # JPA entity classes
├── repo/             # Spring Data JPA repositories
├── security/         # JWT filter, entry point, security utilities
└── service/
```

---

## Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.x |
| Language | Java 17+ |
| Security | Spring Security + JWT |
| Persistence | Spring Data JPA (Hibernate) |
| Build Tool | Maven |
| Database | MySQL / PostgreSQL (configurable) |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- A running MySQL or PostgreSQL instance

### Installation

```bash
# Clone the repository
git clone https://github.com/your-username/HRManagerAPI.git
cd HRManagerAPI

# Configure your database (see Configuration section)

# Build and run
./mvnw spring-boot:run
```

The server starts on `http://localhost:8080` by default.

---

## API Endpoints

### Auth

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/api/auth/register` | Register a new user | No |
| `POST` | `/api/auth/login` | Login and receive JWT token | No |

### Users

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/users` | List all users | Yes |
| `GET` | `/api/users/{id}` | Get user by ID | Yes |
| `PUT` | `/api/users/{id}` | Update user | Yes |
| `DELETE` | `/api/users/{id}` | Delete user | Yes (Admin) |

### Departments

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/departments` | List all departments | Yes |
| `POST` | `/api/departments` | Create department | Yes (Admin) |
| `PUT` | `/api/departments/{id}` | Update department | Yes (Admin) |
| `DELETE` | `/api/departments/{id}` | Delete department | Yes (Admin) |

### Attendance

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/attendance` | List attendance records | Yes |
| `POST` | `/api/attendance` | Record attendance | Yes |
| `GET` | `/api/attendance/{userId}` | Get records by user | Yes |

> **Note:** Exact endpoint paths depend on your controller mappings. Update this table to match your implementation.

---

## Authentication

This API uses **JWT (JSON Web Token)** for stateless authentication.

1. Call `POST /api/auth/login` with valid credentials.
2. Receive a JWT token in the response.
3. Include the token in the `Authorization` header for all protected routes:

```
Authorization: Bearer <your_token>
```

Tokens are validated by `JWTService` and the custom security filter chain defined in the `security` package.

---

## Configuration

Edit `src/main/resources/application.properties` (or `application.yml`):

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/hrmanager_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=your_secret_key_here
jwt.expiration=86400000
```

---

## License

This project is licensed under the MIT License.
