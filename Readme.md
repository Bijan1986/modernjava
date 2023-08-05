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


## Chapter 6: Collecting data with streams

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

### Generalised Summerization with reduction 

```text
To get sum of all calories: 

var sumofCalories = menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));

output: 4200

To get the dish with maximum calorie

menu.stream().collect(reducing((dish1, dish2) -> dish1.getCalories()> dish2.getCalories()?dish1:dish2))

output:

Optional[Dish(name=pork, vegetarian=false, calories=800, type=MEAT)]
```

### Grouping by

```text
customized grouping by

public enum CaloricLevel { DIET, NORMAL, FAT }
Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
         groupingBy(dish -> {
                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
         } ));
         
         
 // if you want to filter dishes > 500 calories and group them based on type
Map<Dish.Type, List<Dish>> caloricDishesByType =
      menu.stream()
          .collect(groupingBy(Dish::getType,
                   filtering(dish -> dish.getCalories() > 500, toList())));
                   
output:

{OTHER=[french fries, pizza], MEAT=[pork, beef], FISH=[]}

// just like filtering there is also mapping function

Map<Dish.Type, List<String>> dishNamesByType =
      menu.stream()
          .collect(groupingBy(Dish::getType,
                   mapping(Dish::getName, toList())));
                   
// very interesting

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

Map<Dish.Type, Set<String>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
                                        toSet())));

output:

{FISH=[roasted, tasty, fresh, delicious], OTHER=[salty, greasy, natural, light, tasty, fresh, fried], MEAT=[salty, greasy, roasted, fried, crisp]}
         
```

#### multilevel grouping

```text
This is a better example of multilevel grouping

Person person1 = new Person("John", "USA", "NYC", new Pet("Max", 5));
    Person person2 = new Person("Steve", "UK", "London", new Pet("Lucy", 8));
    Person person3 = new Person("Anna", "USA", "NYC", new Pet("Buddy", 12));
    Person person4 = new Person("Mike", "USA", "Chicago", new Pet("Duke", 10));
     
    List<Person> persons = Arrays.asList(person1, person2, person3, person4);
    
    now if i want to group people based on country and city 
    
    persons.stream().collect(
         groupingBy(Person::getCountry,
            groupingBy(Person::getCity)

the other example is 

Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
menu.stream().collect(
      groupingBy(Dish::getType,
         groupingBy(dish -> {
                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
          } )
      )
);

```

### Partitioning

```text
in partitioning by we can get both true and false values

Map<Boolean, List<Dish>> partitionedMenu =
             menu.stream().collect(partitioningBy(Dish::isVegetarian));
             
output will be:

{false=[pork, beef, chicken, prawns, salmon],
 true=[french fries, rice, season fruit, pizza]}
 
```

#### Advantages of partitioning over groupBy

```text
Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
menu.stream().collect(
        partitioningBy(Dish::isVegetarian,
                       groupingBy(Dish::getType)));

output:

{false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]},
 true={OTHER=[french fries, rice, season fruit, pizza]}}


Multilevel partitioning by

menu.stream().collect(partitioningBy(Dish::isVegetarian,
                          partitioningBy(d -> d.getCalories() > 500)));


output: 

        { false={false=[chicken, prawns, salmon], true=[pork, beef]},
          true={false=[rice, season fruit], true=[french fries, pizza]}}

```

### Collector interface

This needs more time.

## Chapter 7: Parallel data processing and performance

### Parallel Streams :

## Chapter 8: Collection API enhancements

### List Factory

```text

List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
System.out.println(friends);

```

### Set Factory

```text
Set<String> friends = Set.of("Raphael", "Olivia", "Thibaut");
System.out.println(friends);

```

### Map factory

```text
Map<String, Integer> ageOfFriends
   = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
System.out.println(ageOfFriends);

```

## Working with List and Set

Few methods created in java 8

1. removeIf : removes element matching predicate. it is for all List and set
2. replaceAll
3. sort


### removeIf:

```text
transactions.removeIf(transaction ->
     Character.isDigit(transaction.getReferenceCode().charAt(0)));
```

## Working with Map

### forEach

```text
ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " +
     age + " years old"));
     
```

### Sorting

> Entry.comparingByKey
> 
> Entry.comparingByValue
> 

```text

Map<String, String> favouriteMovies
       = Map.ofEntries(entry("Raphael", "Star Wars"),
       entry("Cristina", "Matrix"),
       entry("Olivia",
       "James Bond"));

favouriteMovies
  .entrySet()
  .stream()
  .sorted(Entry.comparingByKey())
  .forEachOrdered(System.out::println);

```


### getOrDefault

This is to avoid null pointer exception

```text
Map<String, String> favouriteMovies
       = Map.ofEntries(entry("Raphael", "Star Wars"),
       entry("Olivia", "James Bond"));

System.out.println(favouriteMovies.getOrDefault("Olivia", "Matrix"));
System.out.println(favouriteMovies.getOrDefault("Thibaut", "Matrix"));

```
movies.entrySet().removeIf(entry -> entry.getValue() < 10);

## Summary

```text
Summary
Java 9 supports collection factories, which let you create small immutable lists, sets, and maps by using List.of, Set.of, Map.of, and Map.ofEntries.
The objects returned by these collection factories are immutable, which means that you can’t change their state after creation.
The List interface supports the default methods removeIf, replaceAll, and sort.
The Set interface supports the default method removeIf.
The Map interface includes several new default methods for common patterns and reduces the scope for bugs.
ConcurrentHashMap supports the new default methods inherited from Map but provides thread-safe implementations for them.
```