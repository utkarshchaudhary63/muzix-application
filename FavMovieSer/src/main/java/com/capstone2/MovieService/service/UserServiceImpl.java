package com.capstone2.MovieService.service;

import com.capstone2.MovieService.domain.Details;
import com.capstone2.MovieService.domain.Movie;
import com.capstone2.MovieService.domain.User;
import com.capstone2.MovieService.exception.MovieAlreadyExistsException;
import com.capstone2.MovieService.exception.MovieNotFoundException;
import com.capstone2.MovieService.exception.UserAlreadyExistsException;
import com.capstone2.MovieService.exception.UserNotFoundException;
import com.capstone2.MovieService.proxy.UserMovieProxy;
import com.capstone2.MovieService.rabbitmq.EmailDTO;
import com.capstone2.MovieService.rabbitmq.MailProducer;
import com.capstone2.MovieService.repository.MovieRepository;
import com.capstone2.MovieService.repository.UserFavList;
import com.capstone2.MovieService.repository.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    UserMovieRepository userMovieRepository;
    MovieRepository movieRepository;
    UserFavList userFavList;
    UserMovieProxy userMovieProxy;
    MailProducer mailProducer;
    boolean check;
 @Autowired
    public UserServiceImpl(UserMovieRepository userMovieRepository, MovieRepository movieRepository, UserFavList userFavList, UserMovieProxy userMovieProxy, MailProducer mailProducer) {
        this.userMovieRepository = userMovieRepository;
        this.movieRepository = movieRepository;
        this.userFavList = userFavList;
        this.userMovieProxy = userMovieProxy;
        this.mailProducer = mailProducer;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {

            userMovieProxy.saveUser(user);
            User result = userMovieRepository.save(user);


            EmailDTO emailDTO = new EmailDTO(result.getEmail(), "Welcome user, Thank you for Registration with Muzix app. Enjoy Seamless Streaming Videos ☺️ . Continue with app by Logging in into to the new movies world.", "Sign-up Successful!\n");
            mailProducer.sendEmailDtoToQueue(emailDTO);
            return result;


    }
    @Override
    public List<Details> getUsers(String email) throws UserNotFoundException {
        if(userMovieRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }else {
            List<Details> result =userMovieRepository.findById(email).get().getUsers();
            return result;
        }

    }

    @Override
    public Details getUser(String email,String firstName) throws UserNotFoundException {
        if(userMovieRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }else {
            List<Details> result =userMovieRepository.findById(email).get().getUsers();
            Details mainresult=null;
            for(Details d:result){
              if(d.getFirstName().equalsIgnoreCase(firstName)){
                  mainresult=d;
              }

              }
            return mainresult;
            }
        }



//    @Override
//    public List<Details> updateUser(String email,String firstName ,Details detail) throws UserNotFoundException {
//     User user=userMovieRepository.findById(email).get();
//     List <Details> userDetail=user.getUsers();
//        for(Details d : userDetail){
//            if(d.getFirstName().equalsIgnoreCase(firstName)) {
//                d.setLastName(detail.getLastName());
//                d.setAge(detail.getAge());
//                d.setAddress(detail.getAddress());
//                d.setProfilePic(detail.getProfilePic());
//                d.setMobileNo(detail.getMobileNo());
//
//            }
//            userDetail.add(d);
//            user.setUsers(userDetail);
//        }
//        userMovieRepository.save(user);
//        return userDetail;
//    }


    @Override
    public List<Details> updateUser(String email,String firstName ,Details detail) throws UserNotFoundException {
        if(userMovieRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User isExist = userMovieRepository.findById(email).get();
        List<Details> allDetails = isExist.getUsers();
        Details singleDetail = null;
        boolean isExistDetail = false;
        for(Details prof:allDetails){
            if(prof.getFirstName().equalsIgnoreCase(firstName)){
                isExistDetail= true;
                singleDetail = prof;
                break;
            }
        }
        singleDetail.setFirstName(detail.getFirstName());
        singleDetail.setLastName(detail.getLastName());
        singleDetail.setAddress(detail.getAddress());
        singleDetail.setProfilePic(detail.getProfilePic());
        singleDetail.setAge(detail.getAge());
        singleDetail.setMobileNo(detail.getMobileNo());

        allDetails.add(singleDetail);
        userMovieRepository.save(isExist);
        return allDetails;
    }
    @Override
    public User addDetailUser(String email,Details detail) throws UserAlreadyExistsException {
        User user1=userMovieRepository.findById(email).get();
        List<Details> playlistList=user1.getUsers();
        if (playlistList!= null)
        {
            boolean var=false;
            for (Details play :playlistList)
            {
                if(play.getFirstName().equals(detail.getFirstName())) {
                    var = true;
                    break;
                }

            }
            if(!var)
            {
                playlistList.add(detail);
                user1.setUsers(playlistList);
            }
        }
        else
        {
            user1.setUsers(new ArrayList<>());
            user1.getUsers().add(detail);
        }
        userMovieRepository.save(user1);
        return user1;
    }

    @Override
    public User getAccount(String email) throws UserNotFoundException {
        return userMovieRepository.findById(email).get();
    }


    @Override
    public boolean DeleteUser(String email,String firstName) throws UserNotFoundException {
        User user=userMovieRepository.findById(email).get();
        List<Details> playlists=user.getUsers();
        Boolean check=false;
        Iterator<Details> iterator=playlists.iterator();
        while(iterator.hasNext())
        {
            Details playlist=iterator.next();
            if(playlist.getFirstName().equals(firstName))
            {
                iterator.remove();
                check=true;
            }
        }
        user.setUsers(playlists);
        userMovieRepository.save(user);
        return check;
        }

    @Override
    public User updateAmount(String email, User user) throws UserNotFoundException {
        User result=userMovieRepository.findById(email).get();
        result.setAmount(user.getAmount());
        result.setSubscriptionPlan(user.getSubscriptionPlan());
        //Save the updated user to the repository
        userMovieRepository.save(result);

        // Send a welcome email to the user
        String message = "You Have been subscribed successfully. Enjoy Muzix  with your "+user.getSubscriptionPlan()+" plan";
        String subject = "Subscription Successful!";
        EmailDTO emailDTO = new EmailDTO(result.getEmail(), message, subject);
        mailProducer.sendEmailDtoToQueue(emailDTO);
        return result;
    }

//    =============================Favourite=====================================================

    @Override
    public boolean addMovieToUserFavList(String email,String firstName, Movie movie) throws MovieAlreadyExistsException {

        User account=userMovieRepository.findById(email).get();
        List<Details> user=account.getUsers();
        for(Details u : user) {
            if(u.getFirstName().equalsIgnoreCase(firstName))
            {

                List<Movie> movieLists = u.getFavourite();
                if (movieLists != null) {
                    check = false;
                    for (Movie movieList1 : movieLists) {
                        if (movieList1.getId() == movie.getId()) {
                            check = true;
                        }

                    }
                    if (!check) {

                        u.getFavourite().add(movie);
                    }
                }
                else {
                    u.setFavourite(new ArrayList<>());
                    u.getFavourite().add(movie);

                }
            }
        }
        userMovieRepository.save(account);
        return check;

    }



    @Override
    public boolean deleteMovieFromMovieList( String email,String firstName,int MovieId) {
        User user=userMovieRepository.findById(email).get();
        List<Details> playlist=user.getUsers();
        Boolean check=false;
        Details playlist1=null;
        for(Details p:playlist)
        {
            if(p.getFirstName().equals(firstName))
            {
                playlist1=p;
            }
        }
        playlist.remove(playlist1);
        List<Movie> songList=playlist1.getFavourite();
        Iterator<Movie> iterator=songList.iterator();
        while(iterator.hasNext())
        {
            Movie songList1=iterator.next();
            if(songList1.getId()==MovieId)
            {
                iterator.remove();
                check=true;
            }
        }
        playlist1.setFavourite(songList);
        playlist.add(playlist1);
        user.setUsers(playlist);
        userMovieRepository.save(user);
        return check;
 }

    @Override
    public List<Movie> getMoviesFromFavorites(String email,String firstName) throws MovieNotFoundException {
        User account=userMovieRepository.findById(email).get();
                List<Details>user=account.getUsers();
        List<Movie>Movies=null;
                for(Details u : user){
                    if(u.getFirstName().equalsIgnoreCase(firstName)){
                Movies=u.getFavourite();}
                }
                return Movies;
    }

}


