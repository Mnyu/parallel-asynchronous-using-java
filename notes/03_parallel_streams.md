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

## Streams API : ```sequential()``` and ```parallel()``` functions

- ```sequential()``` : Executes the stream in sequential
- ```parallel()``` : Executes the stream in parallel
- **Both the functions() change the behavior of the whole pipeline.**

![Image1](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/seq.png)

![Image2](https://github.com/Mnyu/parallel-asynchronous-using-java/blob/practice/notes/images/par.png)

### When to use sequential() and parallel() ?

- 1 use-case : when we have to decide between sequential and parallel streams in the actual code.