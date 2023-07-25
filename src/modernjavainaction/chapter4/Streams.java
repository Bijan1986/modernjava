package modernjavainaction.chapter4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Streams {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        System.out.println("============================================");
        System.out.println("FILTERING AND SLICING");
        System.out.println("============================================");
//        // name three high calorie dishes name
//        menu.stream().filter(dish -> dish.getCalories() > 300)
//                .map(Dish::getName)
//                .limit(3)
//                .toList().forEach(System.out::println);
//
//        // distinct
//        List<Integer> number = Arrays.asList(1,2,3,4,5,1,2,6,2,6,7);
//        number.stream().distinct().forEach(System.out::println);

        // slicing using takewhile and dowhile
        // lets say you have sorted list of items
        // and you want to find items < 320 calories
        var specialMenu = menu.stream()
                .sorted(comparing(Dish::getCalories))
                .toList();
        specialMenu.stream().takeWhile(dish -> dish.getCalories() < 320)
                .toList().forEach(System.out::println);
        specialMenu.stream().dropWhile(dish -> dish.getCalories() < 320)
                .toList().forEach(System.out::println);
        System.out.println("=====================================");
        System.out.println("First two meat dishes");
        System.out.println("=====================================");
        specialMenu.stream().filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .limit(2).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("============================================");
        System.out.println("MAPPING");
        System.out.println("============================================");
        menu.stream().map(Dish::getName).toList().forEach(System.out::println);

    }
}
