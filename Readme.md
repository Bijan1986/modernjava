# Modern Java in action

## Chapter 5 : Streams

### Sorting

for sorting a list we can use the below
**sorted(comparing(Dish::getName))**

```
menu.stream()
        .sorted(comparing(Dish::getCalories))
        .toList();
```

### Mapping

in mapping we can use the mapToInt to get the int values

```
dishes.stream().mapToInt(Dish::getCalories).max();
```

The above code will give you OptionalInt();

### Summary

The Streams API lets you express complex data processing queries. 

Common stream operations are summarized in table 5.1.
You can filter and slice a stream using the filter, distinct, takeWhile (Java 9), dropWhile (Java 9), skip, and limit methods.


The methods takeWhile and dropWhile are more efficient than a filter when you know that the source is sorted.

You can extract or transform elements of a stream using the map and flatMap methods.

You can find elements in a stream using the findFirst and findAny methods. 

You can match a given predicate in a stream using the allMatch, noneMatch, and anyMatch methods.<br>
These methods make use of short-circuiting: a computation stops as soon as a result is found; there’s no need to process the whole stream.


You can combine all elements of a stream iteratively to produce a result using the reduce method, for example, to calculate the sum or find the maximum of a stream.


Some operations such as filter and map are stateless: they don’t store any state. <br>
Some operations such as reduce store state to calculate a value. <br>
Some operations such as sorted and distinct also store state because they need to <br>
buffer all the elements of a stream before returning a new stream. <br>
Such operations are called stateful operations.


There are three primitive specializations of streams: IntStream, DoubleStream, and LongStream.<br>
Their operations are also specialized accordingly.

Streams can be created not only from a collection but also from values, arrays, files, and specific methods such as iterate and generate.


An infinite stream has an infinite number of elements (for example all possible strings). <br>
This is possible because the elements of a stream are only produced on demand. <br>
You can get a finite stream from an infinite stream using methods such as limit.