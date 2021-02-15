package com.dreamplug.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }
}


public class FlatMapFunction {

    /*FlatMap transforms each element of the stream into a stream of other objects.
    So each object will be transformed into zero, one or multiple other objects backed by streams.
    The contents of those streams will then be placed into the returned stream of the flatMap operation.*/


    public static void main(String[] args) {


//        In this example, we are trying to create a stream of objects of objects using flatmap
//        Implementation 1
        List<Foo> foos = new ArrayList<>();

//        Create foo objects
        IntStream
                .range(1,4)
                .forEach(i -> foos.add(new Foo("Naman: " + i)));

//        Creating bars objects from foo class objects
        foos.forEach( f ->
                IntStream
                    .range(1,4)
                    .forEach(i -> f.bars.add(new Bar("Bar: " + i + " <- " + f.name))));
//       Printing the objects using flatmap function
        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(i -> System.out.println( i.name));


        System.out.println("Lets look at another implementation of the same example using peek function");

/*      In this implementation, a lot of things are happening:
        1. Range(i,j) :- It is used to give a range from i to j-1
        2. MapToObject() :- It is basically used to perform some action on the object that it has
        3. Peek() :- It is used to perform functions similar to forEach, but since it is an intermediate
                     Operation, it is useful in such cases like these
        4. FlatMap() :- It is basically used to access the stream of the object that is passed to it, not the object
 */

        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Naman: " + i))
                .peek( f->
                        IntStream.range(1, 4)
                        .mapToObj(b -> new Bar("Bar: " + b + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(i -> i.bars.stream())
                .forEach(b -> System.out.println(b.name));

    }
}
