# Modern Java in action

## Chapter 5 : Streams

### Sorting

for sorting a list we can use the below
**sorted(comparing(Dish::getName))**

```java
menu.stream()
        .sorted(comparing(Dish::getCalories))
        .toList();
```

### Filtering

