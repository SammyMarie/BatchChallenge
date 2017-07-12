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

###To Do List:
1. Add data Analysis of most and least frequently used words.
    - Will be done using either with Apache Spark or Google's guava Multiset.
2. Add Fault(error handling) tolerance done by implementing skip batch logic.
3. Add unit tests for batch configuration logic.
    - Will be done using spring batch testing suite.
4. Change configuration to run application with commandline arguments.

[![Build Status](https://travis-ci.org/SammyMarie/BatchChallenge.svg?branch=develop)](https://travis-ci.org/SammyMarie/BatchChallenge)