# Architecture

This document describes the architecture of the Claim Service application.

## Overview

The application is a monolithic service built with Java and the Spring Boot framework. It provides a RESTful API for managing claims. The service is containerized using Docker and can be run using Docker Compose.

## Components

### Backend

The backend is a Spring Boot application that provides the following features:

*   **RESTful API:** The API is used to manage claims, users, and funnels.
*   **Database:** The application uses a relational database to store data. The database is accessed using Spring Data JPA.
*   **Security:** The application uses Spring Security for authentication and authorization.

### Frontend

The frontend is a single-page application that is served by the backend. The frontend is built with a modern JavaScript framework.

### Containerization

The application is containerized using Docker. The `dockerfile` in the root directory is used to build the Docker image. The `docker-compose.yml` file is used to run the application and its dependencies.
