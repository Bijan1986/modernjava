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


## Collecting data with streams

### collect transactions based on currency

```
var currencyAndTransaction = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println(currencyAndTransaction);
```

This will give you the output as 

```
{
EUR=[Transaction(currency=EUR, value=1500.0), Transaction(currency=EUR, value=1100.0), Transaction(currency=EUR, value=5600.0), Transaction(currency=EUR, value=6800.0)], 
JPY=[Transaction(currency=JPY, value=7800.0), Transaction(currency=JPY, value=5700.0)], 
USD=[Transaction(currency=USD, value=2300.0), Transaction(currency=USD, value=4500.0), Transaction(currency=USD, value=4600.0)], 
GBP=[Transaction(currency=GBP, value=9900.0), Transaction(currency=GBP, value=3200.0)], 
CHF=[Transaction(currency=CHF, value=6700.0), Transaction(currency=CHF, value=3400.0)]
}

```


### Finding maximum and minimum in a stream of values

```text
Comparator<Dish> dishCaloriesComparator =
    Comparator.comparingInt(Dish::getCalories);
Optional<Dish> mostCalorieDish =
    menu.stream()
        .collect(maxBy(dishCaloriesComparator));
```

### Summing and summerizing


```text
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

double avgCalories =
    menu.stream().collect(averagingInt(Dish::getCalories));
```

> This is really cool. for an example if I want to get a summary based on calories then
> I can do this

```text
var sumDouble = transactions.stream().collect(summarizingDouble(Transaction::getValue));
        System.out.println(sumDouble);
```

Then the output will be 

```text
DoubleSummaryStatistics
{
count=13, 
sum=63100.000000, 
min=1100.000000, 
average=4853.846154, 
max=9900.000000
}
```

### Joining Strings

```text
String shortMenu = menu.stream().collect(joining());

output:

porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon

// better way to add comma

String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));

Output:
pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon


```
