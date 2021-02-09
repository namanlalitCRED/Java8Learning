package com.dreamplug.stream;


import java.util.Arrays;
import java.util.stream.Stream;

public class FilterFunction {
    public static void main(String[] args) {

//      Example 1: Using Filter to get a list of users whose age is more than 33
//      We have used 1. Sorted Function, 2. Filter Function and 3. ForEach Function

        int[] ages = {34, 36, 38, 31, 39};
        Arrays.stream(ages)
                .sorted()
                .filter(age -> age > 33)
                .forEach(System.out::println);


    }
}
