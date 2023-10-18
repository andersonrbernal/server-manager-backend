# Server Manager Backend

The Server Manager Backend is a RESTful API built with Spring Boot and designed to manage servers and monitor their health status in your application. It enables you to perform CRUD operations on servers, including creating, updating, deleting, pinging, and querying server details. This project is connected to a PostgreSQL database for data storage.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Database Configuration](#database-configuration)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 11 or higher installed.
- Apache Maven or an IDE with Spring Boot support (e.g., IntelliJ IDEA, Eclipse).
- PostgreSQL database server up and running.
- Postman or any API testing tool for interacting with the API.

## Getting Started

Follow these steps to get the Server Manager Backend up and running on your local machine:

1. Clone the repository:

   ```
   git clone https://github.com/andersonrbernal/server-manager-backend.git
   ```

2. Navigate to project directory
   ```
   cd server-manager-backend
   ```

3. Open src/main/resources/application.properties and configure the PostgreSQL database connection:
  ```
  spring.datasource.url=jdbc:postgresql://<host>:<port>/<database>
  spring.datasource.username=<username>
  spring.datasource.password=<password>
  ```

4. Build and run the project:
   ```
   mvn spring-boot:run
   ```

The server will start at http://localhost:8080. You can change the port by modifying the server.port property in the application.properties file.

## Usage
Usage
You can interact with the Server Manager Backend by making HTTP requests to its RESTful endpoints. Here are some common use cases:

# Endpoints
Get a list of servers:

- GET /api/v1/servers

Ping a server to check its status:

- GET /api/v1/servers/ping/{ipAddress}

Create a new server:

- POST /api/v1/servers

Delete a server by ID:

- DELETE /api/v1/servers/{id}

Get a server image by filename:

- GET /api/v1/servers/images/{filename}

For detailed information on request and response formats, refer to the API documentation or use an API testing tool like Postman.

Database Configuration
This project uses a PostgreSQL database for data storage. You can configure the database connection settings in the application.properties file as mentioned in the Getting Started section.

The database schema is created automatically by Hibernate when the application is started for the first time.

Contributing
Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix.
Make your changes and commit them.
Push your changes to your fork.
Submit a pull request to the main repository.
License
This project is licensed under the MIT License. See the LICENSE file for details.

Happy server management with Server Manager Backend!

```
You should replace placeholders like `andersonrbernal`, `localhost`, `your-database-name`, `your-username`, and `your-password` with actual values relevant to your project. Additionally, ensure that you have a valid `LICENSE` file or specify your project's license information accordingly.
```

By Anderson Rodrigues Bernal.
