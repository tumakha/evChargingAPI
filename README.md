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
    
REST API
===========

 * Extract the nearest ’n' charging points

    curl -v http://localhost:8888/evCharging?n=10