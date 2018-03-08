# evChargingAPI
Electric vehicles Charging points API

[![Build Status](https://travis-ci.org/tumakha/evChargingAPI.svg?branch=master)](https://travis-ci.org/tumakha/evChargingAPI)

### Prerequisites

Java 8, Gradle 3+ or gradle-wrapper

### Run tests

    gradle clean test
    
### Build

    gradle build
    
### Run web app

    gradle bootRun
    
REST API examples
=================

 * Extract the nearest charge points

    
    http://localhost:8888/charge_points?latitude=51.533875&longitude=-0.486539&results=10
    
    curl -v http://localhost:8888/charge_points?latitude=51.533875\&longitude=-0.486539\&results=10
    
 * Import charge points from CSV file

        
    curl -v POST --header "Content-Type: application/json" -d "{\"provider\": \"NATIONAL_REGISTRY\", \"file\": \"http://chargepoints.dft.gov.uk/api/retrieve/registry/format/csv\"}" http://localhost:8888/charge_points/import
    
 * Update only the specified properties of charge point. (123 - Point id)


    curl -v -X PATCH --header "Content-Type: application/json" -d "{\"name\": \"Point Name\"}" http://localhost:8888/charge_points/123
    