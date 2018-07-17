package com.example.netfluxexamplewithgradle.service;

import com.example.netfluxexamplewithgradle.domain.Movie;
import com.example.netfluxexamplewithgradle.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieEvent> events(String movieId);

    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();
}
