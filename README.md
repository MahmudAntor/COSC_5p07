### Task

There are three set implementations in Java: HashSet, TreeSet, and LinkedHashSet. The purpose
of this assignment is to compare the following operations in those set implementations using Java
Microbenchmarking Harness:
• Insert items
• Sort items
• Search (contain)
Analyse and compare the different performance metrics (throughput, speed, etc.) of above
operations with respect to the three different set implementations. Whenever needed,
test/benchmark your class(es) with 1 million to 10 million integer items.

### Compile & Build
mvn clean install

### RUN
java -jar target/benchmarks.jar