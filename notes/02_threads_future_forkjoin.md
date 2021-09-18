# Asynchronous and Parallel Programming prior to Java 8

- Threads
- Futures
- ForkJoin Framework
- Limitations


## Threads API:

- Threads API got introduced in Java1
- Threads are basically used to offload the blocking tasks as background tasks.
- Threads allowed the developers to write Asynchronous style of code.

**Limitations :**

- Lot of code has to be written to introduce Async.
- Need to have an instance of a ```Thread``` and ```Runnable``` for every Async operation to be performed.
- ```Runnable``` also does not take any input and return any output, hence additional properties need to be added to ```Runnable``` class for input and output.
- Manually ```create```, ```start``` and ```join``` the thread.
- Thread API nowadays can be considered as very low level API.
- Easy to introduce complexity in code when trying to implement Async operations using Thread API.
- Threads, in general, are expensive.
    - Because threads have their own runtime-stack, memory, registers etc.
    - This is the reason why creating and destroying the thread is an expensive process.

## ThreadPool, ExecutorService & Future :

## ThreadPool

- To overcome the limitations of Threads (majorly the expensive creation & destruction of threads), Thread Pool was created.
- ```Thread Pool : group of threads that are created and readily available to handle any work submitted to them.```
- Thread Pool will typically have a ```work queue``` and threads pull new work from that queue.


- **Thread Pool Size considerations:**
  - **CPU intensive tasks** (Complex mathematical calculation or any algorithm related computation), then
    - ```Thread Pool Size = No of Cores```
    - Because at any given time, the number of tasks that can be run in parallel = no of cores in the machine.
  - **I/O intensive tasks** (REST API calls or DB calls), then
    - ```Thread Pool Size > No of Cores```
    - Because the thread executing the task will be waiting for the most part.

**Benefits :**

- No need to manually ```create```, ```start``` and ```join``` threads.
- Achieve concurrency in our application.


## ExecutorService

- Released as part of Java 5.
- ExecutorService in Java is an **Asynchronous Task Execution Engine.**
- It provides a way to asynchronously execute tasks and provides the results in a much simpler way compared to threads.
- This has enabled coarse-grained ```Task based parallelism``` in Java.

### ExecutorService Components :
1. **Thread Pool**
2. **WorkQueue** - ```Blocking Queue```
   1. Any time a client submits a task for the executor service, the task will be placed in the work queue.
3. **CompletionQueue**
   1. Tasks completed by threads are placed in the completion queue.

![Image1](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/executor-service-work.png)


### Limitations :
- Executor Service is designed to block threads.
- Since executor service returns a ```Future``` and the ```main/calling thread``` has to call ```future.get()```, implies that the ```main/calling thread``` is blocked until the ```future.get()``` returns.
- Though ```future.get()``` has a timeout variation but that does not serve the purpose fully as we will be timing out the task, but we won't be getting the desired result.
- There is no way to combine the ```Future``` objects by the single caller thread. Different ```Future``` objects if present in same caller will make different ```get``` calls and block caller again and again.



## Fork-Join Framework :

 - This got introduced as part of Java 7.
 - This is an **extension of ExecutorService**.
 - ExecutorService is designed to achieve ```Task Based Parallelism```.

        Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture =  executorService.submit(() -> reviewService.retrieveReviews(productId)); 
 
- Fork/Join framework is designed to achieve ```Data Parallelism```.
 
### Data Parallelism :

- Data Parallelism is a concept where a given ```Task``` is ```recursively``` split into ```Sub-Tasks``` until it reaches its least possible size and then execute those ```Sub-Tasks``` in parallel.
- Basically it uses the ```divide & conquer``` approach.

### Working :

- Fork-Join framework has a dedicated pool, ```ForkJoin Pool``` to support Data Parallelism.
- ```ForkJoin Task``` :
  - ForkJoin Task represents part of the data and its computation.
  - In e.g. below the fine-grained ForkJoin task would be each individual element of the list and along with that the computation i.e. uppercase operation in this case.
  - From a developer perspective, we need to write code that is going to perform the splitting operation and the actual uppercase operation and join results operation.
  - **Only ForkJoin Tasks can be submitted to ForkJoin Pool.**
  - In reality, the application code does not work with ForkJoin Task class, this is used by Java internally.
  - Developers normally use 2 separate classes:
    - ```RecursiveTask``` - task that returns a value.
    - ```RecursiveAction``` - task that does not return a value.

![Image2](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/fork-join-pool.png)


### ForkJoin Task :
![Image3](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/fork-join-task.png)


### Limitations :

- Code using Fork Join framework is really complex and not developer friendly.
  - Java 8 streams API really simplified the usage.