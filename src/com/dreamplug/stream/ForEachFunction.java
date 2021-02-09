package com.dreamplug.stream;

import java.util.stream.Stream;

class Person {
    String name;
    int age;
    String address;

//    Constructor Initialisation
    public Person(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

}

public class ForEachFunction {
    public static void main(String[] args) {

//        In this example, we are implementing ForEach function.
//        We have created a class "Person" and we want to print the attributes for each object using streams


        Person person1 = new Person("Naman", 21, "Ambala");
        Person person2 = new Person("John", 18, "Delhi");
        Person person3 = new Person("Jack", 13, "New York");


//        Stream Creation for 3 objects
        Stream<Person> st = Stream.of(person1, person2, person3);
        st.forEach(person -> System.out.println(person.name + " " + person.age + " " + person.address));

    }
}
