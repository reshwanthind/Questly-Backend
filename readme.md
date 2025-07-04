# Questly 🧠
*A community-powered Q&A platform – ask, answer, learn.*

## Project Overview

Questly is a scalable backend system for a modern Question & Answer platform. The application allows users to register, ask questions, answer them, follow subjects using tags, and participate in a collaborative knowledge community.

## Features

- User registration and authentication
- Posting, updating, and searching questions and answers
- Tag-based the content organization and discovery
- Followers and notification system for updates on relevant topics (using tags)
- RESTful API endpoints for frontend consumption
- Scalable architecture ready for cloud deployment

## Tech Stack

- **Java 21**
- **Spring Boot (Spring MVC & Spring Data JPA)**
- **Jakarta EE Annotations**
- **Lombok** for boilerplate code 
- **Elasticsearch** for advanced search capabilities and scalability
- **Docker** for containerized deployment of Elasticsearch

## Getting Started

### Prerequisites

- Java 21+
- Docker

### Running Elasticsearch (Required Service)

Pull and run the Elasticsearch Docker image:


```
docker pull elasticsearch:9.0.2
```

```
docker run -d --name elasticsearch-container \
-p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e "xpack.security.enabled=false" \
-e "xpack.security.http.ssl.enabled=false" \
elasticsearch:9.0.2
```




### Setup and Run Application

1. Clone this repository:
    ```bash
    git clone <repository-url>
    cd questly-backend
    ```

2. Configure application properties for your database and Elasticsearch.

3. Build and run:
    ```bash
    ./mvnw spring-boot:run
    ```

## REST API Endpoints

These are some of the main endpoints provided by the backend (routes may vary based on your configuration):

### User APIs

| Method | Endpoint                  | Description            |
|--------|--------------------------|------------------------|
| POST   | `/api/users`             | Register new user      |
| GET    | `/api/users/{id}`        | Retrieve user profile  |
| POST   | `/api/users/login`       | Authenticate user      |
| PUT    | `/api/users/{id}`        | Update user details    |

### Question APIs

| Method | Endpoint                    | Description                      |
|--------|----------------------------|----------------------------------|
| POST   | `/api/questions`           | Post a new question              |
| GET    | `/api/questions`           | Get list of questions            |
| GET    | `/api/questions/{id}`      | Get question by ID               |
| PUT    | `/api/questions/{id}`      | Update a question                |
| DELETE | `/api/questions/{id}`      | Delete a question                |

### Answer APIs

| Method | Endpoint                         | Description                  |
|--------|----------------------------------|------------------------------|
| POST   | `/api/questions/{id}/answers`    | Post an answer to a question |
| GET    | `/api/questions/{id}/answers`    | List answers for a question  |
| PUT    | `/api/answers/{answerId}`        | Update an answer             |
| DELETE | `/api/answers/{answerId}`        | Delete an answer             |

### Tag APIs

| Method | Endpoint            | Description              |
|--------|--------------------|--------------------------|
| GET    | `/api/tags`        | List all tags            |
| POST   | `/api/tags`        | Create a new tag         |
| GET    | `/api/tags/{id}`   | Get tag details by ID    |

### Search

| Method | Endpoint                  | Description            |
|--------|--------------------------|------------------------|
| GET    | `/api/search`            | Full-text search       |



---

**Questly brings together the best of community Q&A with scalable, modern backend engineering.**


