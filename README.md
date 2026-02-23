
# SocialApp API 🚀

A production-style **Social Media REST API** built with Java Spring Boot.
Implements real-world features like JWT authentication, user relationships,
and post management.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security** — JWT Authentication
- **Spring Data JPA** — ORM
- **MySQL** — Database
- **Maven** — Dependency Management

## ✨ Features

- ✅ User Registration & Login with JWT Authentication
- ✅ BCrypt Password Hashing
- ✅ Create, Read, Update, Delete Posts
- ✅ Ownership validation (users can only edit/delete their own posts)
- ✅ Stateless REST API
- ✅ Global Exception Handling
- ✅ Input Validation

## 📁 Project Structure
```
src/main/java/com/socialapp/
├── config/          ← Security configuration
├── controller/      ← REST API endpoints
├── dto/             ← Data Transfer Objects
├── model/           ← Database entities
├── repository/      ← Database queries
├── security/        ← JWT filter and utilities
└── service/         ← Business logic
```

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven

### Setup

1. Clone the repository
```bash
git clone https://github.com/patilakshay5700-allin/socialapp-api.git
cd socialapp-api
```

2. Create MySQL database
```sql
CREATE DATABASE socialapp;
```

3. Configure environment variables
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
export JWT_SECRET=yourSecretKeyHere
```

4. Run the application
```bash
./mvnw spring-boot:run
```

## 📡 API Endpoints

### Auth
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register new user | No |
| POST | `/api/auth/login` | Login user | No |

### Posts
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/posts` | Create a post | Yes |
| GET | `/api/posts` | Get all posts | Yes |
| GET | `/api/posts/{id}` | Get post by ID | Yes |
| GET | `/api/posts/user/{userId}` | Get user's posts | Yes |
| PUT | `/api/posts/{id}` | Update post | Yes |
| DELETE | `/api/posts/{id}` | Delete post | Yes |

## 🔐 Authentication

This API uses JWT Bearer tokens.

After login, include the token in every request header:
```
Authorization: Bearer <your_token_here>
```

## 👨‍💻 Author

**Your Name**
- GitHub: [@patilakshay5700-allin](https://github.com/patilakshay5700-allin)
- LinkedIn: [https://www.linkedin.com/in/akshaya-kumar-9596ba26b/]