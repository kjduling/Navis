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
  
## Original Message  
```
//
// The purpose of this coding exercise is to have high quality code.
// This means multiple files can be used, object oriented programming, tests, documentation, etc.
//

// 
//  EXERCISE  
//
// There are bunch of mines in a mine field, and you are tasked with
// exploding as many of them as you can.  The only caveat is you can
// only explode one manually.  The mine you manually explode will set
// off a chain reaction.  For any mine that explodes, all mines within
// the blast radius of that mine will be triggered to explode in one
// second.  The mine you manually explode blows up at time 0.

// Your Task: Write a program which will read in a mines file and
// output the maximum number of mines you can explode.  Also output 
// which mine you need to manually set off to explode this maximum 
// number.  Since there may be multiple mines that blow up a maximal 
// number of mines you should output all the mines that do that.

// We'll provide you with:

// Mines File (space separated) with values:
// (x, y, r) - where x is x coordinate, y is y coordinate and r is blast radius

// Example:
// 1 2 53
// -32 40 100
// 10 15 25
// -15 -15 200

// Formula to determine if a mine explodes another mine:
// class Exploder {
//   boolean mine_exploded?(Float x1, Float y1, Float r, Float x2, Float y2) {
//     return r <= Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2))
//   }
// }

//
// Your code here
//
```
