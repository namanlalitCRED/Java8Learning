package com.dreamplug.stream;

import java.util.Arrays;

public class MapFunction {
    public static void main(String[] args) {

//      Here, we will consider 2 examples for implementing map and other functions

        System.out.println("Example 1");
        System.out.println("Square of Numbers:");
//      Example 1: Square the numbers in the array
        int[] arr;
        arr = new int[]{2, 5, 6, 8};
        Arrays.stream(arr)
                .map(value -> value * value)
                .forEach(System.out::println);

        System.out.println("Example 2");


//      Example 2: Given an array of Strings, remove the first character, convert the rest into Integers and then find the max value and print if it is present
//      In this example, we will use 4 functions:
//      1. Map, 2. mapToInt, 3. Max, 4. IfPresent

        String values[] = {"v10", "v100", "v999", "v693"};


        System.out.println("Maximum Value: ");
        Arrays.stream(values)
                .map(value -> value.substring(1))
                .mapToInt(Integer :: parseInt)
                .max()
                .ifPresent(System.out::println);

    }
}
