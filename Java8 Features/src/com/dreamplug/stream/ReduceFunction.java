package com.dreamplug.stream;

import java.util.Arrays;
import java.util.List;

class Employees {
    String name;
    int age;

    Employees(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class ReduceFunction {
    public static void main(String[] args) {


//      In this example, we are going to find the highest age of the person using reduce function, which takes
//      "BinaryOperator" Accumulation Function as input.

        System.out.println("1st Method of Reduce");

        List<Employees> persons =
                Arrays.asList(
                        new Employees("Naman", 21),
                        new Employees("Peter", 23),
                        new Employees("Jack", 25),
                        new Employees("David", 12));
        persons
                .stream()
                .reduce((p1,p2) -> p1.age > p2.age ? p1: p2)
                .ifPresent(System.out::println);

        System.out.println("2nd Method of Reduce");


//      The second reduce method accepts both an identity value and a BinaryOperator accumulator.
//      This method can be utilized to construct a new Person with the aggregated names and ages from all other persons in the stream:

        Employees result =
                persons
                        .stream()
                        .reduce(new Employees("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });

        System.out.format("name=%s; age=%s", result.name, result.age);
        System.out.println();
        System.out.println("3rd Method of Reduce");


//      The third reduce method accepts three parameters: an identity value, a BiFunction accumulator and a combiner function of type BinaryOperator.
//      Since the identity values type is not restricted to the Person type, we can utilize this reduction to determine the sum of ages from all persons:

        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);

    }
}
