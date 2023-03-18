package com.capstone2.MovieService.repository;

import com.capstone2.MovieService.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavList extends MongoRepository<Movie,String> {
}
