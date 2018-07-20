package com.example.netfluxexamplewithgradle;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReactiveStreamsExamples {

    @Test
    public void simpleStreamExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.toStream()
                .forEach(System.out::println);
    }

    @Test
    public void simpleStreamExample2() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.subscribe(System.out::println);
    }

    @Test
    public void simpleStreamExample3() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        //no subscriber = no output
        dogs.doOnEach(dog -> System.out.println(dog.get()));
    }

    @Test
    public void simpleStreamExample4() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        //trigger subscription
        dogs.doOnEach(dog -> System.out.println(dog.get())).subscribe();
    }

    @Test
    public void simpleStreamExample5WithSubscriber() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        //trigger subscription
        dogs.subscribe(((s) -> {
            System.out.println(s);
        }), null, (() -> {
            System.out.println("All done");
        }));
    }

    @Test
    public void simpleStreamExample6WithSubscriberConsumers() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        //subscriber lambda
        Consumer<String> println = System.out::println;

        //error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some error occured");

        //Runnable upon complete
        Runnable allDone = () -> System.out.println("All done");

        //trigger subscription
        dogs.subscribe(println, errorHandler, allDone);
    }

    @Test
    public void mapStreamExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.map(String::length)
                .doOnEach(System.out::println)
                .subscribe();
    }

    @Test
    public void filterStreamExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.filter(s -> s.length() == 6)
                .subscribe(System.out::println);
    }

    @Test
    public void filterAndLimitStreamExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.filter(s -> s.length() == 6)
                .take(2) //limit elements
                .subscribe(System.out::println);
    }

    @Test
    public void filterLimitAndSortStreamExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.filter(s -> s.length() == 6)
                .take(2)
                .sort()
                .subscribe(System.out::println);
    }

    @Test
    public void filterLimitAndSortStreamWithCollectorExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.filter(s -> s.length() == 6)
                .take(3)
                .sort()
                .collect(Collectors.joining(", ")) //converts to Mono<String>
                .subscribe(System.out::println);
    }

    @Test
    public void flatMapExample() {
        Flux<List<List<Integer>>> listFlux = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));

        listFlux.flatMap(lists -> Flux.fromIterable(lists))
                .flatMap(lists -> Flux.fromIterable(lists))
                .subscribe(System.out::println);
    }

    @Test //mix: Java 8 streams with reactive streams
    public void flatMap2Example() {
        Flux<List<List<Integer>>> listFlux = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));

        listFlux.flatMap(lists -> Flux.fromIterable(
                (lists.stream()
                        .flatMap(Collection::stream)
                ).collect(Collectors.toList())))
                .subscribe(System.out::println);
    }

    @Test
    public void reductionExample() {
        Flux<String> dogs = Flux.just("Reks", "Azorek", "Goldie", "Ciapek", "Puszek");

        dogs.reduce((a, b) -> a + " - " + b).subscribe(System.out::println);
    }
}
