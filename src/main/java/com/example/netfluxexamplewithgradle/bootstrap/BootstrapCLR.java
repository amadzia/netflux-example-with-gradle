package com.example.netfluxexamplewithgradle.bootstrap;

import com.example.netfluxexamplewithgradle.domain.Movie;
import com.example.netfluxexamplewithgradle.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        movieRepository.deleteAll()
                .thenMany(
                        Flux.just("Silence", "Flux", "Mono",
                                "Back to the future", "Lord of Dawn")
                                .map(title -> new Movie(title))
                                .flatMap(movieRepository::save))
                .subscribe(null, null, () -> {
                    movieRepository.findAll().subscribe(System.out::println);
                });
    }
}
