package com.dreamplug.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

public class CollectorFunction {
    public static void main(String[] args) {

//      Collector is a terminal kind of operation, which is basically used to tranform the result,
//      in the form of a list, map or set

//      Example 1: Filter the names of the employees whose name starts with "N"
        List<Employee> list = Arrays.asList(
                new Employee("Naman", 21),
                new Employee("John", 25),
                new Employee("Jack", 27)
        );

        List<Employee> people = list
                .stream()
                .filter( p -> p.name.startsWith("N"))
                .collect(Collectors.toList());

        boolean result = people.isEmpty();
        System.out.println(result);


//      Example 2: Group all employees by their age
        Map<Integer, List<Employee> > groups = list.stream()
                .collect(Collectors.groupingBy(p -> p.age));

        groups.forEach((age, p) -> System.out.format("Age: %s and Name: %s", age, p));



    }
}
