SocialApp API
A social media backend REST API built with Java and Spring Boot. I built this project to learn backend development and understand how real-world APIs work under the hood.
It covers the kind of features you'd find in apps like Twitter or Instagram — user accounts, posts, following people, and liking posts.

What it does
Users can register and log in. After logging in they get a JWT token which they use to access everything else. They can create posts, edit or delete their own posts, follow other users, like posts, and get a feed of posts from people they follow.

Tech used

Java 17
Spring Boot 3
Spring Security with JWT
MySQL
Spring Data JPA / Hibernate
Maven
Swagger for API docs


How to run it locally
You need Java 17 and MySQL installed.
Clone the repo and create a database called socialapp in MySQL:
sqlCREATE DATABASE socialapp;
Then open src/main/resources/application.yml and update the database credentials:
yamlspring:
datasource:
username: your_username
password: your_password
Run the app:
bash./mvnw spring-boot:run
Once it starts, the API is available at http://localhost:8080 and Swagger docs at http://localhost:8080/swagger-ui/index.html.

API endpoints
Auth — no token needed
POST /api/auth/register
POST /api/auth/login
Posts — token required
POST   /api/posts
GET    /api/posts
GET    /api/posts/feed
GET    /api/posts/{id}
GET    /api/posts/user/{userId}
PUT    /api/posts/{id}
DELETE /api/posts/{id}
POST   /api/posts/{id}/like
DELETE /api/posts/{id}/like
Users — token required
GET    /api/users/{id}/profile
POST   /api/users/{id}/follow
DELETE /api/users/{id}/follow
GET    /api/users/{id}/followers
GET    /api/users/{id}/following

Authentication
Register or log in to get a JWT token. Add it to the header of every request after that:
Authorization: Bearer your_token_here
You can also use the Authorize button in Swagger UI to set the token once and test all endpoints from the browser.

Project structure
config/       - security and swagger configuration
controller/   - API endpoints
dto/          - request and response objects
exception/    - error handling
model/        - database entities
repository/   - database queries
security/     - JWT filter and utilities
service/      - business logic

A few things worth noting
Passwords are hashed with BCrypt before saving — plain text is never stored. The JWT token is stateless so the server doesn't store sessions. Users can only edit or delete their own posts. The follow system uses a Many-to-Many relationship with a join table, same for likes.

Author
Akshay Patil
GitHub: https://github.com/patilakshay5700-allin