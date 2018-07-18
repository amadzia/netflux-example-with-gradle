package com.example.netfluxexamplewithgradle;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FunctionalProgrammingExamplesTests {

    //properties of a function: 1. name, 2. return type, 3. parameter list and 4. body

    @Test
    public void functionWith4PropertiesTest() {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {//method

                System.out.println("Inside thread t1"); //body
            }
        });

        t1.start();

        System.out.println("In main test");
    }

    @Test
    public void lambdaExpressionTest() {

        //lambda expression: 1. anonymous, 2. return type - can be inferred, 3. parameter lists and 4. body

        Thread t1 = new Thread(() -> System.out.println("Lambdas"));

        //Constructor is the higher order function. Function is a first class citizen. We need parentheses for Thread

        t1.start();

        System.out.println("In main test");
    }

    @Test
    public void listIteratorHighCeremonyTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i));
        }

        //very complex - a lot can go wrong
    }

    @Test
    public void listIteratorLessCeremonyExternalIterTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        for (String doggy : dogs) {
            System.out.println(doggy);
        }

        //simpler - still using external iterator
    }

    @Test
    public void listInternalIterConsumerTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void listInternalIterLambdaMethodTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.forEach((String s) -> System.out.println(s));
    }

    @Test
    public void listInternalIterLambdaMethodTypeInferenceTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.forEach(s -> System.out.println(s)); //inferred by complier
    }

    @Test
    public void listinternalIterLambdaMethodTypeJava8MethodRefTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.forEach(System.out::println);
    }

    @Test
    public void countDogsWithSixCharactersImpTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        int dogCount = 0;

        for (String dog : dogs) {//external iterator

            if (dog.length() == 6) {
                dogCount++; //dogCount is mutating
            }
        }
        System.out.println(dogCount);
    }

    @Test
    public void countDogsWith6CharactersDecTest() {
        List<String> dogs = Arrays.asList("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        //no mutability
        System.out.println(dogs.stream()
                .filter(dog -> dog.length() == 6) //like iterator Java 8 Streams
                .collect(Collectors.toList()) //collect to list
                .size()); //return size
    }
}
