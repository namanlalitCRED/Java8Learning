package com.dreamplug.optional;

import java.util.Optional;

public class OptionalClassDemoFunction {
    public static void main(String[] args) {
        String[] words = new String[10];

//      In this example, checkNull variable will check if the string (words[5]) is null or not
//      If it is null, it will return "word is null" when passed through the isPresent() function
        Optional<String> checkNull  = Optional.ofNullable(words[5]);
        if (checkNull.isPresent()) {
            String word = words[5].toLowerCase();
            System.out.print(word);
        } else
            System.out.println("word is null");
    }
}
