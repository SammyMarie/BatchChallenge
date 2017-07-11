# BatchChallenge

This is a Spring Batch application leveraging on technologies listed below:
1. Java 8.
2. Docker.
3. Junit/Hamcrest.
4. Spring Batch. 
5. Spring Web for exposing server port.
6. Travis-ci for continuous integration

## Application Features
1. Reads from one or more wikipedia page data dump file(s).
2. Writes to a MongoDB repository.

###To Do List:
1. Add scaling capability to batch processing.
    - This will be implemented using local partitioning which gives 
    restartability and is thread safe.
2. Add data Analysis of most and least frequently used words.
    - Will be done using either with Apache Spark or Google's guava Multiset.
3. Add Fault(error handling) tolerance.
    - Will be done by implementing retry, restart & skip batch logic.
4. Add unit tests for batch configuration logic.
    - Will be done using spring batch testing suite.