package com.dreamplug;


// Implementation of a Functional Interface, which contains only one abstract method and it can
// also contain object class methods like:
// 1. Hashcode: The JVM converts the address of an object into some hashcode.This method can be overridden for certain examples
// 2. toString: This function is used to print the name of the class, but can be overridden by other functions
// These methods can be declared in the functional interface and can be overridden in the inherited classes.

@FunctionalInterface
interface hello{
    void say(String message); // Abstract Method
    // It can contain any number of Object class methods.
    int hashCode();
    String toString();
}

public class FunctionalInterfaceExample implements hello{

    public void say(String message){
        System.out.println(message);
    }

    public static void main(String[] args) {
        hello example = new FunctionalInterfaceExample();
        example.say("Hello Naman!");
    }
}
