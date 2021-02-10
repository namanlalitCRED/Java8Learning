package com.dreamplug;

interface Sayable {

    // default method
    default void say() {
        System.out.println("Default Method calling..");
    }

    // Abstract method
    void sayMore(String msg);

    // static method
    static void sayLouder(String msg) {
        System.out.println(msg);
    }
}





public class StaticMethodExample implements Sayable{

    // implementing abstract method
    public void sayMore(String msg){
        System.out.println(msg);
    }
    public static void main(String[] args) {
        StaticMethodExample dm = new StaticMethodExample();

        // calling default method
        dm.say();

        // calling abstract method
        dm.sayMore("Abstract Method calling..");

        // calling static method
        Sayable.sayLouder("Static Method calling..");
    }
}
