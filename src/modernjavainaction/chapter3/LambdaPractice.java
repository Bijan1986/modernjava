package modernjavainaction.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaPractice {
    public static void main(String[] args) {
        // filter list of employees
        // look for employees who have experience > 5
        // if it is a female then she should have > 7 years
        //(age > 5) and ((gender.female and yoe >7) or (gender.male)

        List<Employee> employeeList = Arrays.asList(
          new Employee("Bijan",Gender.MALE,12),
                new Employee("Rudra",Gender.MALE,12),
                new Employee("Bapuni",Gender.MALE,13),
                new Employee("Binay",Gender.MALE,2),
                new Employee("Shruti",Gender.FEMALE,11),
                new Employee("Sofy",Gender.FEMALE,3),
                new Employee("Nadia",Gender.FEMALE,9)
                );
        Predicate <Employee> isMoreThane5 = employee -> employee.getYoe() > 5;
        Predicate <Employee> isFemale = employee -> employee.getGender()
                .equals(Gender.FEMALE);
        Predicate <Employee> isMorethan7 = employee -> employee.getYoe() > 7;
        employeeList.stream()
                .filter(isMoreThane5.
                        and(isFemale.and(isMorethan7)
                                .or(isFemale.negate()))
                ).toList().forEach(System.out::println);
    }
}
