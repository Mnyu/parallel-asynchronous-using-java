# Parallel Streams

## Stream API :
- Streams API got introduced in Java 8
- **Streams API is used to process a collection of Objects.**
- Streams in Java are created by using the ```stream()``` method.
- Pipeline - The whole computation starting from ```stream()``` to ```intermediate operation``` to ```terminal operation``` is a pipeline.
- Terminal Operation - very powerful because that's the one which triggers the whole pipeline.
- **Streams API by default is SEQUENTIAL** - meaning the pipeline processes the elements one by one once the terminal operation is invoked. 


## Parallel Streams

- This allows your code to run in parallel.
- ParallelStreams are designed to solve **Data Parallelism**.
- With Parallel streams, **Number of tasks that can run in parallel = No of cores.**
- Find no of cores by using ```System.out.println(Runtime.getRuntime().availableProcessors());```

## Streams API : ```sequential()``` and ```parallel()``` functions

- ```sequential()``` : Executes the stream in sequential
- ```parallel()``` : Executes the stream in parallel
- **Both the functions() change the behavior of the whole pipeline.**

![Image1](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/seq.png)

![Image2](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/par.png)

### When to use sequential() and parallel() ?

- 1 use-case : when we have to decide between sequential and parallel streams in the actual code.

## How Parallel Streams Work ?

- When ```parallel()``` function is invoked on a ```Collection```, it performs 3 steps behind the scenes: 
1. ```Split``` the data into chunks.
   1. Data source is split into small data chunks.
   2. Example : ```List Collection``` split into chunks of elements of **size 1**
   3. This data source split is done by a class ```Spliterator```.
   4. For every type of collection, there is a dedicated Spliterator, so mostly we don't have to write any custom spliterator.
   5. Example : ```ArrayList``` ---> ```ArrayListSpliterator```  and so on.
   

2. ```Execute``` the stream pipeline on the data that is split into chunks in step 1.
   1. Actual execution of the stream pipeline happens.
   2. Data chunks are applied to the stream pipeline and the **Intermediate** operations are executed in a ```Common ForkJoin Pool```.
   3. Parallel stream pretty much used the same approach to interact with the ForkJoin pool as in ForkJoin framework.
   4. From the developer perspective, we don't have to write the code to create the ForkJoin Tasks.
   

3. ```Combine``` the results of execution in step 2.
   1. Combine the executed results into a final result.
   2. Combine phase in stream API maps to **Terminal** operations.
   3. This phase typically uses the ```collect()``` and ```reduce()``` operations to produce the final result.



#### For the most part the ```Data Split``` and ```Execute``` phases run in parallel because the data split will happen in a recursive manner, as soon as a chunk is split into its least possible size, it gets executed immediately.

## Example : Checkout Service Flow
![Image3](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/checkout.png)

## Example : Checkout Service Code
![Image4](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/par-stream-code.png)

## Example : Checkout Service Parallel Stream Working
![Image5](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/parallel-stream-working.png)

