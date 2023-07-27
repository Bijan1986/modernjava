package modernjavainaction.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class ExerciseStream {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high).");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        transactions.stream().filter(transaction -> transaction.getYear()==2011).sorted(comparing(Transaction::getValue))
                .toList().forEach(System.out::println);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("2. What are all the unique cities where the trader has worked");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList()
                .forEach(System.out::println);


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("3. Find all the traders from cambridge and sort the by name");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("Cambridge"))
                .distinct()
                .sorted(comparing(trader -> trader.getName()))
                .toList()
                .forEach(System.out::println);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("4. Return a string of all traders’ names sorted alphabetically.");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        var allNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted(String::compareTo)
                .reduce("",(n1,n2) -> n1 + n2);
        System.out.println(allNames);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("4. mapping to a numeric stream");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(transactions.stream().mapToInt(Transaction::getValue).sum());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("4. mapping to a numeric stream and getting max value");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(transactions.stream().mapToInt(Transaction::getValue).max().getAsInt());
    }
}
