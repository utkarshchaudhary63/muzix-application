package com.capstone2.MovieService.service;

import com.capstone2.MovieService.domain.Details;
import com.capstone2.MovieService.domain.Movie;
import com.capstone2.MovieService.domain.User;
import com.capstone2.MovieService.exception.MovieAlreadyExistsException;
import com.capstone2.MovieService.exception.MovieNotFoundException;
import com.capstone2.MovieService.exception.UserAlreadyExistsException;
import com.capstone2.MovieService.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    //     ==========================User===========================
    public User addUser(User user) throws UserAlreadyExistsException;

     public Details getUser(String email,String firstName) throws UserNotFoundException;
    public List<Details> getUsers(String email ) throws UserNotFoundException;

//     public User updateUser(String email,String firstName,Details detail) throws UserNotFoundException;

    public List<Details> updateUser(String email,String firstName ,Details detail) throws UserNotFoundException;
     public User addDetailUser(String email,Details detail) throws UserAlreadyExistsException;
     public User getAccount(String email) throws UserNotFoundException;
     public boolean DeleteUser(String email,String firstName) throws UserNotFoundException;
public User updateAmount(String email,User user)throws UserNotFoundException;
//     ==========================Favourite===========================

    public boolean addMovieToUserFavList(String email,String firstName,Movie movie) throws MovieAlreadyExistsException;



    public boolean deleteMovieFromMovieList(String email,String firstName,int MovieId);

     public List<Movie> getMoviesFromFavorites(String email,String firstName)throws MovieNotFoundException;










}
