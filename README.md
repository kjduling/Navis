# Coding Challenge
## Problem
Given a minefield, detonate as many mines in a chain reaction as possible.
### Rules
* Only one mine may be detonated manually
### Attributes
* Mines have an X/Y position
* Mines have a blast radius
### Goals
To demonstrate the following skills:
* Spring Boot
* Spring Data / JPA
* Documentation via Swagger
* Microservices
* Unit and Integration tests
## Usage
 
 ### To Run
 This is a Spring Boot project with Gradle.
 #### Compile
 ./gradlew clean build
 #### Run
 ./gradlew bootRun
 
 Application interaction can be achieved by using Postman or Swagger to generate the REST requests.
 Swagger should be reachable at http://localhost:8080/navis/swagger-ui.html

 ### Actions
 * Upload a minefield file with <br><b>POST /navis/v1/minefield/upload</b>
 * Verify the minefield was saved to the database<br><b>GET /navis/v1/minefield/get</b>
 * Find all the solutions<br><b>GET /navis/v1/minefield/solve
  