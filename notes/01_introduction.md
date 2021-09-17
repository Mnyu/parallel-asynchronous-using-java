# Introduction of Concepts 

## Why Parallel and Asynchronous Programming?

- We are living in a fast-paced environment.

- In Software Programming:
  - Code that we write should execute faster.

- Goal of Asynchronous and Parallel Programming :
  - **Provide Techniques to improve the performance of the code.**

- Parallel and Asynchronous programming styles are fundamentally used for totally different types of use cases. 
    
    - (Details to be covered)


## What is the need to learn Parallel and Async Programming? 

### Technology Advancements :

### Hardware :

- Devices or computers comes up with Multiple cores.
- Developer needs to learn programming patterns to maximize the use of multiple cores for faster code execution.
- Apply the Parallel Programming concepts to maximize usage of CPU cores.
- ```Parallel Streams API``` - technique available in modern Java.

### Software :

- MicroServices Architecture style  adopted lately.
- It is pretty common for applications in MicroServices architecture to interact with multiple services to fulfill a request, most of these interactions are blocking I/O calls - which basically exhibit Synchronous behaviour.
- Any time we make a blocking call, it leads to wastage of resources because we are blocked and waiting for data.
- This also impacts the latency and performance of the application. 
- Apply the Asynchronous(Non-blocking) Programming concepts to improve latency and effective resource usage.
- ```CompletableFuture API``` - technique available in modern Java.
 

#### There are 2 common themes with the above 2 APIs :

1. Both of these programming styles/APIs involve ```Threads``` to improve performance of the code.
2. Both of these programming styles/APIs use ```Functional style of programming```.


## Evolution of Concurrency and Parallelism APIs in Java :

![Image1](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/main/src/image.png)


## Concurrency v/s Parallelism :

## Concurrency

- Concurrency is a concept where two or more task can run simultaneously.
- In Java, Concurrency is achieved using ```Threads```.
- The definition of Concurrency i itself is vague : 2 or more tasks can run simultaneously, but 
  - Are the tasks running in interleaved fashion?
  - Are the tasks running simultaneously at the same time?

- This will be totally based on the underlying core(s) that we are trying to run this program on.

  - Single Core : Tasks are running in interleaved fashion as there is only 1 core, CPU which has a scheduler that takes care of scheduling these multiple threads.
  - Multiple Cores : Tasks are running simultaneously

![Image2](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/main/src/image.png)

![Image3](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/main/src/image.png)


## Parallelism

- Parallelism is a concept in which tasks are literally going to run in parallel.
- Parallelism involves these steps:

  - Decomposing the tasks into SubTasks(Forking).
  - Execute the subtasks in sequential.
  - Joining the results of the tasks(Join).

- Whole process is also called **Fork & Join**.

![Image4](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/main/src/image.png)

![Image5](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/main/src/image.png)

![Image6](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/main/src/image.png)



