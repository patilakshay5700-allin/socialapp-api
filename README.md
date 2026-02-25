A production-style Social Media REST API built with Java Spring Boot.
Implements real-world backend features including JWT authentication, post management,
user follow relationships, and a personalized feed — similar to the backend of Twitter/Instagram.

✨ Features

✅ User Registration & Login with JWT Authentication
✅ BCrypt Password Hashing — passwords never stored as plain text
✅ Spring Security filter chain with stateless session management
✅ Posts CRUD with ownership validation
✅ Follow / Unfollow users (Many-to-Many relationship)
✅ Like / Unlike posts
✅ Personalized Feed — see posts from people you follow
✅ Global Exception Handling — clean, consistent error responses
✅ Swagger UI — interactive API documentation
✅ Input Validation on all endpoints
✅ DTO Pattern to prevent sensitive data leakage


🛠️ Tech Stack
TechnologyPurposeJava 17Core languageSpring Boot 3.xApplication frameworkSpring SecurityAuthentication & authorizationSpring Data JPAORM & database accessMySQL 8Relational databaseJWT (jjwt)Stateless token authenticationHibernateSQL generation & entity mappingSwagger / OpenAPI 3API documentationMavenDependency management

📁 Project Structure
src/main/java/com/socialapp/
├── config/
│   ├── SecurityConfig.java       ← Spring Security rules
│   └── SwaggerConfig.java        ← Swagger / OpenAPI setup
├── controller/
│   ├── AuthController.java       ← Register & Login endpoints
│   ├── PostController.java       ← Post endpoints
│   └── UserController.java       ← User & Follow endpoints
├── dto/
│   ├── RegisterRequest.java      ← Incoming registration data
│   ├── LoginRequest.java         ← Incoming login data
│   ├── AuthResponse.java         ← Outgoing token response
│   ├── PostRequest.java          ← Incoming post data
│   ├── PostResponse.java         ← Outgoing post data
│   ├── UserProfileResponse.java  ← User profile with counts
│   └── UserSummaryResponse.java  ← Simple user info for lists
├── exception/
│   ├── ErrorResponse.java        ← Standard error format
│   └── GlobalExceptionHandler.java ← Handles all errors
├── model/
│   ├── User.java                 ← Users table entity
│   └── Post.java                 ← Posts table entity
├── repository/
│   ├── UserRepository.java       ← User database queries
│   └── PostRepository.java       ← Post database queries
├── security/
│   ├── JwtUtil.java              ← Generate & validate JWT tokens
│   ├── JwtFilter.java            ← JWT request filter
│   └── CustomUserDetailsService.java ← Spring Security user loader
└── service/
├── AuthService.java          ← Register & Login logic
├── PostService.java          ← Post & Like business logic
└── UserService.java          ← Follow/Unfollow logic

🗄️ Database Schema
users
├── id (PK)
├── name
├── email (unique)
├── password (bcrypt hashed)
├── bio
├── profile_picture
└── created_at

posts
├── id (PK)
├── content
├── image_url
├── created_at
├── updated_at
└── user_id (FK → users)

follows
├── follower_id (FK → users)
└── following_id (FK → users)

post_likes
├── post_id (FK → posts)
└── user_id (FK → users)

🚀 Getting Started
Prerequisites

Java 17+
MySQL 8+
Maven

Setup
1. Clone the repository
   bashgit clone https://github.com/patilakshay5700-allin/socialapp-api.git
   cd socialapp-api
2. Create MySQL database
   sqlCREATE DATABASE socialapp;
3. Configure application.yml
   Update the datasource credentials in src/main/resources/application.yml:
   yamlspring:
   datasource:
   url: jdbc:mysql://localhost:3306/socialapp?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   username: your_mysql_username
   password: your_mysql_password
4. Run the application
   bash./mvnw spring-boot:run
5. Open Swagger UI
   http://localhost:8080/swagger-ui/index.html

📖 API Documentation
Interactive Swagger UI is available at:
http://localhost:8080/swagger-ui/index.html
How to test protected endpoints in Swagger:

Use POST /api/auth/login to get a JWT token
Click the Authorize 🔒 button at the top of Swagger UI
Paste your token and click Authorize
All protected endpoints are now unlocked ✅


📡 API Endpoints
🔐 Authentication
MethodEndpointDescriptionAuthPOST/api/auth/registerRegister new user❌POST/api/auth/loginLogin and get JWT token❌
📝 Posts
MethodEndpointDescriptionAuthPOST/api/postsCreate a new post✅GET/api/postsGet all posts✅GET/api/posts/feedGet your personalized feed✅GET/api/posts/{id}Get post by ID✅GET/api/posts/user/{userId}Get all posts by a user✅PUT/api/posts/{id}Update your post✅DELETE/api/posts/{id}Delete your post✅POST/api/posts/{id}/likeLike a post✅DELETE/api/posts/{id}/likeUnlike a post✅
👥 Users
MethodEndpointDescriptionAuthGET/api/users/{id}/profileGet user profile✅POST/api/users/{id}/followFollow a user✅DELETE/api/users/{id}/followUnfollow a user✅GET/api/users/{id}/followersGet user's followers✅GET/api/users/{id}/followingGet who user is following✅

🔐 Authentication Flow
1. Register   →  POST /api/auth/register  →  returns JWT token
2. Login      →  POST /api/auth/login     →  returns JWT token
3. Use token  →  Add to every request header:
   Authorization: Bearer <your_token_here>
   Example Request with Token:
   bashcurl -H "Authorization: Bearer eyJhbGci..." \
   http://localhost:8080/api/posts

📊 Error Handling
All errors return a consistent JSON format:
json{
"status": 404,
"message": "Post not found",
"timestamp": "2024-01-01T10:00:00"
}
Validation errors return field-level details:
json{
"email": "Email should be valid",
"password": "Password must be at least 6 characters"
}

🏗️ Architecture
This project follows a clean 3-layer architecture:
Controller Layer  ←  Handles HTTP requests & responses
↓
Service Layer     ←  Business logic & validation
↓
Repository Layer  ←  Database queries via Spring Data JPA
↓
Database          ←  MySQL

🔑 Key Concepts Implemented
JWT Authentication — Stateless token-based auth. After login, every request carries a JWT token. The server validates the token without hitting the database on every request.
BCrypt Hashing — Passwords are hashed with BCrypt before storing. Even if the database is compromised, passwords cannot be recovered.
DTO Pattern — Data Transfer Objects separate internal entities from API responses, preventing sensitive data (like passwords) from leaking.
Ownership Validation — Users can only edit or delete their own posts. The server checks the logged-in user matches the resource owner.
Many-to-Many Relationships — Follow/unfollow and likes use join tables (follows, post_likes) managed automatically by Hibernate.

👨‍💻 Author
Akshay Patil

GitHub: @patilakshay5700-allin