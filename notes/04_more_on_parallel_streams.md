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
- Performance difference between SEQUENTIAL and PARALLEL stream for the above use case where input list is LinkedList very significant.
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