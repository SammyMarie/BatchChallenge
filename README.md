# BatchChallenge

This is a Spring Batch application leveraging on technologies listed below:
1. Java 8.
2. Docker.
3. Junit/Hamcrest.
4. Spring Batch. 
5. Spring Web for exposing server port.
6. Travis-ci for continuous integration

## Application Features
1. Reads from wikipedia page data dump file(s).
2. Writes to a MongoDB repository.
3. Scaling capability using local partitioning giving restartability and thread safety.
4. Fault(error handling) tolerance done by implementing retry, restart logic.
5. Data Analysis of word count using Apache Spark.

###To Do List:
1. Add Fault(error handling) tolerance done by implementing skip batch logic.
2. Add unit tests for batch configuration logic.
    - Will be done using spring batch testing suite.
3. Change configuration to run application with commandline arguments.
4. Fix StackOverFlowError problem

[![Build Status](https://travis-ci.org/SammyMarie/BatchChallenge.svg?branch=develop)](https://travis-ci.org/SammyMarie/BatchChallenge)