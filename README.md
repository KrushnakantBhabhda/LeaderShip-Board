#CoderHack 

It is online LeaderShip Borad which is being displayed the ScoreBoard of the contestant

## Installation

### Prerequisites

- Java 11 or higher
- Gradle
- Git

Inorder to run the project 
Clone the repository and enter a command ./gradlew bootrun 
The application will be available at http://localhost:8080. You can interact with the API using tools like curl, Postman, or any HTTP client


Endpoints
GET /users - Retrieve a list of all registered users
GET /users/{userId} - Retrieve the details of a specific user
POST /users - Register a new user to the contest
PUT /users/{userId} - Update the score of a specific user
DELETE /users/{userId} - Deregister a specific user from the contest


