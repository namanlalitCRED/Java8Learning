package com.dreamplug.stream;

import java.util.Arrays;
import java.util.List;

class Employee {
    String name;
    int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }
}

public class CollectorFunction {
    public static void main(String[] args) {

//      Collector is a terminal kind of operation, which is basically used to tranform the result,
//      in the form of a list, map or set

//      Example 1:
        List<Employee> list = Arrays.asList(
                new Employee("Naman", 21),
                new Employee("John", 25),
                new Employee("Jack", 27)
        );




    }
}
