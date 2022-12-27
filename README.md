
## COSC 5P07 - Assignment 2 (Benchmark)

### Task 1
---

There are three set implementations in Java: HashSet, TreeSet, and LinkedHashSet. The purpose of this assignment is to compare the following operations in those set implementations using Java
Microbenchmarking Harness:
• Insert items
• Sort items
• Search (contain)
Analyse and compare the different performance metrics (throughput, speed, etc.) of above operations with respect to the three different set implementations. Whenever needed, test/benchmark your class(es) with 1 million to 10 million integer items.

### Experimental Environment
```
Java SE Development Kit 19.0.1
Processor: 11th Gen Intel(R) Core(TM) i7-11800H @ 2.30GHz   2.30 GHz
RAM: 16.0 GB (15.7 GB usable)
System Type: 64-bit operating system, x64-based processor

Data Size - 1 million (integer)
Data Structure - HashSet, LinkedHashSet, TreeSet
Data Operation - Insert, Sort, Search

Benchmark parameters:
JMH version: 1.36
Warmup: 5 iterations, 10 s each
Measurement: 10 iterations, 10 s each
Timeout: 10 min per iteration
Threads: 1 thread, will synchronize iterations

```

### Compile & Build
mvn clean install

### RUN
java -jar target/benchmarks.jar

## Evaluation 

### **Insertion Analysis**

| **Data Structure**        | **Throughput** (ops/s)          | **Average Speed** (s/op) |
| :------------- |:-------------:| :-----:|
| HashSet     | 11.896 | 0.090 |
| LinkedHashSet      | 11.083      |   0.099 |
| TreeSet | 2.335      |    0.443 |


HashSet & LinkedHashSet gives performance of order O(1) for insertion but Hashset gives slightly better performance than LinkedHashSet because the LinkedHashSet uses linked hash map which is based on both hash table and linked list to enhance the functionality of hashmap. It maintains a doubly-linked list running through all its entries in addition to an underlying array of default size 16.

TreeSet gives performance of order O(log(n)) for insertion because it maintains a sorted order of the items. That's why it’s the slowest of all three in terms of insertion. 



### **Sorting Analysis**

| **Data Structure**        | **Throughput** (ops/s)          | **Average Speed** (s/op) |
| :------------- |:-------------:| :-----:|
| HashSet     | 67678318.742 | 10⁻⁸ |
| LinkedHashSet      | 69530434.427      |   10⁻⁸ |
| TreeSet | 2910356804.002, 548099417.424(Descending)     |   10⁻⁹ |

HashSet doesn’t maintain any order of elements. LinkedHashSet maintains insertion order of elements. i.e elements are placed as they are inserted. Possible reason for LinkedHashSet to give a better performance than Hashset is LinkedHashSet does not have to use too much shifting operation required for sorting. If for instance, the smallest item is the last item and it needs to be put first, shifting n-1 items by one, that is very expensive for an array. For a linked list it is a small fixed amount of linking and unlinking for every needed movement, very easily optimized. 

TreeSet is already sorted on insertion. Even of the sorted order needs to be descending it just needs to revert the order of the tree which is still really fast. 




### **Search Analysis**

| **Data Structure**        | **Throughput** (ops/s)          | **Average Speed** (s/op) |
| :------------- |:-------------:| :-----:|
| HashSet     | 104042870.737 | 10⁻⁸ |
| LinkedHashSet      | 102039791.049      |  10⁻⁸ |
| TreeSet | 102055424.139   |    10⁻⁸ |

Searching is fastest on HashSet because it uses a hashtable to store the elements and doesn’t need as much time to traverse the memory locations like LinkedHashSet and TreeSet. 

TreeSet is faster than LinkedHashSet because TreeSet Uses TreeMap which implements Red-Black Tree(a Self Balancing Binary Search Tree).

## Discussion

- HashSet gives better performance than the LinkedHashSet and TreeSet. Use HashSet if you don’t want to maintain any order of elements
- The performance of LinkedHashSet is between HashSet and TreeSet. It’s performance is almost similar to HashSet. But slightly in the slower side as it also maintains LinkedList internally to maintain the insertion order of elements. Use LinkedHashSet if you want to maintain insertion order of elements.
- TreeSet gives less performance than the HashSet and LinkedHashSet as it has to sort the elements after each insertion operations. Use TreeSet if you want to sort the elements according to some Comparator.



### Task 2
---

For this assignment, we created a Server program and a Client program in Python. The client was responsible for sending 10 text files to Server and Server was responsible for writing them in a local file. Both programs were instrumented with OpenTelemetry. Tracer data was sent to Jaegar and Zipkin running on Docker containers for analysis. However, in the end, we only used Jaegar for analysis of the tracer data as it generated better visulizations.

### Experimental Environment & Tools Used:
```
Processor: Intel Core i7-10750H @ 2.60Ghz
RAM: 16.0 GB
OS: Windows 10 Home
WSL Version: 2.0

1. Python3
2. Docker
3. Jaegar
4. Zipkin
5. VS Code
```

### Server Overview:

The server creates a socket and keep listening for incoming requests. When request is received from a new client, a new thread gets created for handling the client. This thread takes care of receiving the data, decoding it and saving it in the output file. Upon completing it's job, the thread gets terminated.

### Client Overview:

For every single file that needs to be sent, a thread is opened.  Each thread opens a connection to the server and takes care of sending file data to server. Once the file sending task is complete, the thread gets terminated.

### Visualizations:

- Client:

<image src="https://github.com/MahmudAntor/COSC_5p07/blob/task-two-farhana/Task2/Visualization/client-graph.jpg" style="height: 300px">
<image src="https://github.com/MahmudAntor/COSC_5p07/blob/task-two-farhana/Task2/Visualization/client-timeline.jpg" style="height: 300px">
<image src="https://github.com/MahmudAntor/COSC_5p07/blob/task-two-farhana/Task2/Visualization/client-metrics.jpg" style="height: 300px">
  
- Server:
<image src="https://github.com/MahmudAntor/COSC_5p07/blob/task-two-farhana/Task2/Visualization/server-graph.jpg" style="height: 300px">
<image src="https://github.com/MahmudAntor/COSC_5p07/blob/task-two-farhana/Task2/Visualization/server-metrics.jpg" style="height: 300px">
  


## Discussion:

From the trace graphs, we can see that for handing each file, both server and client created a new thread.
In total it took 30.12 ms for the client to complete sending the data of all files.  And the server was running for about 2 seconds, most of which actually was waiting time for requests. The time required for sending or saving was not always proportional to the file size. Also, even though multi-threading was used for both client and server, we didn't see any overlap of the threads. We suspect this is because of the Global Interpreter Lock in Python.

[Assignment Repository](https://github.com/MahmudAntor/COSC_5p07)
