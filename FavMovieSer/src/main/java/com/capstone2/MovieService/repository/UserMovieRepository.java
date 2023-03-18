package com.capstone2.MovieService.repository;

import com.capstone2.MovieService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMovieRepository extends MongoRepository<User,String> {
}
