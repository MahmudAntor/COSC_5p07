## COSC 5P07 - Assignment 2 (Benchmark)

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

[Assignment Repository](https://github.com/MahmudAntor/COSC_5p07)