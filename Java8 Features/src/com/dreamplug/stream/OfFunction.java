package com.dreamplug.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OfFunction {
    public static void main(String args[]){

        // This is an example of stream creation.
        // In this example, we are creating a stream of 3 objects.

        Stream<String> st = Stream.of("Naman","John", "Jack");
        System.out.println("Example of Stream Creation!");
    }
}
