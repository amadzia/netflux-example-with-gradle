package com.example.netfluxexamplewithgradle.repositories;

import com.example.netfluxexamplewithgradle.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
