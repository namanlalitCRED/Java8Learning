package com.dreamplug.optional;

import java.util.Optional;

public class EmptyAndOfFunction {
    public static void main(String[] args) {

//      In this example, we are implementing 2 important features:
//      1. Optional.of()
//      2. Optional.empty()

//      Declaring a string
        String[] str = new String[2];

        str[1] = "This is a sample example";

        Optional<String> empty = Optional.empty();
        System.out.println(empty);

//      Passing str[1] object and storing the result in the "value" variable
//      If the value is not empty, it should return the result, otherwise gives "NULLPointerException"
        Optional<String> value1 = Optional.of(str[1]);
        System.out.println(value1);


//      Not passing anything in the str[0] variable
        try {
            Optional<String> value2 = Optional.of(str[0]);
            System.out.println(value2);
        } catch(Exception e){
            System.out.println(e);
        }

    }
}
