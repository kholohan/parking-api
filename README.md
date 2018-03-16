# Parking Api

This project contains a RESTful service, which allows a user to enter a date time range and get back the rate at which
they would be charged to park for that time span.  Project was limited to using
Jetty, Jackson, Jersey and HK2.

## Requirements

This project requires the following:

* Java 8
* Git
* Docker & Docker Compose
* An IDE such as IntelliJ or Eclipse

## Building

The project uses Gradle, so run:

	$ ./gradlew build

After building, can construct a docker image:

    $ docker build -t parking-app .

## Running

This project can be run locally using:

    $ ./gradlew bootRun

Or via docker, once an image has been built:
    
    $ docker run -p 8080:8080 parking-app

## Further Documentation

Once the service is up and running, swagger documentation can be accessed at:

    $ http://localhost:8080/api/swagger.json
