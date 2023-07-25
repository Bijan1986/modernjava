package modernjavainaction.chapter3;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Lambdas {
    public static void main(String[] args) {
        // Filtering with Lambdas
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED),
                new Apple(190, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(180, Color.RED)
        );
        Predicate<Apple> greenApplePredicate = apple -> apple.getColor().equals(Color.GREEN);
        inventory.stream()
                .filter(greenApplePredicate.negate()
                        .and(apple -> apple.getWeight()>150))
                .toList().forEach(System.out::println);

    }
}
