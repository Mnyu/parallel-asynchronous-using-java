# More on Parallel Streams

## Performance : ArrayList Parallel Streams v/s LinkedList Parallel Streams

### Spliterator in Parallel Streams :

- Data source is split into multiple chunks by the Spliterator.
- Each and every type of Collection has a different Spliterator Implementation.
- Since different implementations, therefore, **the performance differs for each collection based on the implementation.**

#### Techniques to compare the performance of parallel streams vs sequential streams for different collections :

- Can be used against any collection in Java.
- Simple use-case to compare performance : **Multiply each value in the collection by a user passed value.**

#### Test Results of use-case where input list is ArrayList :

- Performance difference between SEQUENTIAL and PARALLEL stream for the above use case where input list is ArrayList is not much - around 10ms difference for 1 million numbers.
- This is because - ArrayList is an indexed collection. So, when we invoke the parallel stream or parallel() method, the spliterator can slice the data into chunks really well.
- Interesting results when compared with LinkedList.

#### Test Results of use-case where input list is LinkedList :
- Performance difference between SEQUENTIAL and PARALLEL stream for the above use case where input list is LinkedList is very significant.
- Parallel streams are slower.
- The is because LinkedList is a type of collection which is difficult to split into chunks.

### RECOMMENDATION :
    Always and always test/compare the code performance for sequential and parallel streams. 
    Do not assume that parallel stream is going to give a better performance.


### Summary - Spliterator in ParallelStreams :

- Invoking ```parallelStream()``` does not guarantee faster performance of your code.
- Parallel streams need to perform additional steps compared to sequential :
  - Splitting, Executing and Combining.
- **If the Collection can be split easily, then only parallel streams will improve the overall performance of the code.**
- ArrayList example - parallel streams improve performance.
- LinkedList example - parallel streams reduce performance.


## Parallel Streams - Final Computation Result Order :

- The order of the collection depends on :
  1. Type of Collection and
  2. Spliterator Implementation of the collection


- Example : ArrayList
  - Type of Collection - **Ordered**
  - Spliterator Implementation - **Ordered Spliterator Implementation**


- Example : Set
  - Type of Collection - **Unordered**
  - Spliterator Implementation - **Unordered Spliterator Implementation**


## Terminal Operations : Collect and Reduce

![Image1](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/collect-reduce.png)

![Image2](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/reduce-seq.png)

![Image3](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/reduce-par.png)


### Identity in Reduce
![Image4](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/reduce-identity.png)


## Parallel Stream Operations and Poor Performance

1. Certain stream operators don't perform well when we run in parallel streams. Better to run in sequential.
2. If we have any Boxing/Unboxing involved in the stream pipeline, it leads to poor performance when we use parallel streams.
- Refer ParallelStreamPerformanceTest code.


## Parallel Streams - Common Fork Join Pool

![Image5](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/common-fork-join-1.png)

![Image6](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/common-fork-join-2.png)

![Image7](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/common-fork-join-3.png)

![Image8](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/common-fork-join-4.png)


## Parallel Streams - Parallelism & Threads

![Image9](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/parallelism-1.png)

![Image10](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/parallelism-2.png)


## Parallel Streams - Summary

![Image11](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/parallel-sum-1.png)

![Image12](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/parallel-sum-2.png)

![Image13](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/parallel-sum-3.png)
