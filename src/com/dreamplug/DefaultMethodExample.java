package com.dreamplug;

interface doable{
    default void say(){
        System.out.println("Default Method Calling..");
    }

    void saymore(String message);

}


public class DefaultMethodExample implements doable{

    public void saymore(String message){
        System.out.println(message);
    }

    public static void main(String[] args) {

//      New Object creation
        doable example = new DefaultMethodExample();

//      Default Method
        example.say();

//      Abstract Method
        example.saymore("Abstract Method Calling....");
    }
}
