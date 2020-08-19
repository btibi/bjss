# Tech Challenge

This is a tech challenge for the BJSS.

# Run the application

## Maven run

    mvnw spring-boot:run
    
## Build docker image and run under the docker

    #Compile docker image
    mvn compile jib:dockerBuild
    
    #Run docker image
    docker-compose up

# Default data

Default data set is available after you start the app (see: *resources/data.sql*). 

We can chek the data in the [H2 Database Consol](http://localhost:8080/h2-console/login.jsp) after we started the application.                                                                              

## Cvs
|cvId|name |
|----|-----|
|1   |Jim  |
|2   |Jack |
|3   |John |

## Skills
|cvId|skill    |
|----|---------|
|1   |Java     |
|1   |Spring   |
|1   |Hibernate|
|2   |Agile    |
|2   |Scrum    |
|3   |Docker   |
|3   |AWS      |

## Company history
|cvId|companyName|startDate |endDate   |
|----|-----------|----------|----------|
|1   |BJSS       |2015-06-01|          |
|1   |Microsoft  |2012-08-01|2015-05-31|
|2   |Apple      |2018-07-01|          |
|2   |Facebook   |2010-01-01|2018-06-30|
|2   |Amazon     |2007-10-01|2009-11-30|
|3   |Google     |2020-08-01|          |

# Endpoints
Check the [OpenAPI documentation](http://localhost:8080/swagger-ui.html)

# How to use the endpoint
## List all items [GET /api/cvs](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/cv-api/all) 
    curl -X GET "http://localhost:8080/api/cvs"


## Get item by id [GET /api/cvs/{id}](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/cv-api/getCv)
    curl -X GET "http://localhost:8080/api/cvs/1"

## Insert item [POST /api/cvs](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/cv-api/newCv)
    curl -X POST "http://localhost:8080/api/cvs" -H "Content-Type: application/json" -d "{\"name\":\"Bill\",\"skills\":[\"RestApi\"],\"companyHistories\":[{\"companyName\":\"Microsoft\",\"startDate\":\"2015-06-01\",\"endDate\":\"2018-07-31\"}]}"
    
## Update item [PUT /api/cvs/{id}](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/cv-api/replaceCv)
    curl -X PUT "http://localhost:8080/api/cvs/4" -H "Content-Type: application/json" -d "{\"name\":\"Bill\",\"skills\":[\"RestApi\"],\"companyHistories\":[{\"companyName\":\"Microsoft\",\"startDate\":\"2015-06-01\",\"endDat
    e\":\"2018-07-31\"}]}"

## Delete item [DELETE /api/cvs/{id}](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/cv-api/deleteCv)
    curl -X DELETE "http://localhost:8080/api/cvs/4"    