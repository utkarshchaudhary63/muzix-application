package com.capstone2.MovieService.repository;

import com.capstone2.MovieService.domain.Movie;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie,Integer> {
}
