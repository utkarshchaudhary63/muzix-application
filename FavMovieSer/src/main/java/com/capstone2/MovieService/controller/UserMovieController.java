package com.capstone2.MovieService.controller;
import com.capstone2.MovieService.domain.Details;
import com.capstone2.MovieService.domain.Movie;
import com.capstone2.MovieService.domain.OrderResponse;
import com.capstone2.MovieService.domain.User;
import com.capstone2.MovieService.exception.MovieAlreadyExistsException;
import com.capstone2.MovieService.exception.MovieNotFoundException;
import com.capstone2.MovieService.exception.UserAlreadyExistsException;
import com.capstone2.MovieService.exception.UserNotFoundException;
import com.capstone2.MovieService.service.IUserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movie/api/v1/")

public class UserMovieController {
    private IUserService userService;
    private String secretKey = "rzp_test_rEeShHD8vGAvnS";
    private String secretId = "njhR3LcfbVQ3nbZXj8N0YVOc";
    private RazorpayClient client;
    @Autowired
    public UserMovieController(IUserService userService) {
        this.userService = userService;
    }


//==================================User=======================================================
    /*
    POST
        http://localhost:5555/movie/api/v1/register
     */

    @PostMapping("register")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
    try {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }catch (UserAlreadyExistsException ex){
        throw new UserAlreadyExistsException();
    }
    }
    @GetMapping("Account/{email}")
        public ResponseEntity<?> getAccount(@PathVariable String email) throws UserNotFoundException{
        return new ResponseEntity<>(userService.getAccount(email),HttpStatus.OK);
    }

    @GetMapping("users/{email}")
    public ResponseEntity<?> getUsers(@PathVariable String email)throws UserNotFoundException{
        return new ResponseEntity<>(userService.getUsers(email),HttpStatus.ACCEPTED);
    }
    @GetMapping("user/{email}/{firstName}")
    public ResponseEntity<?> getUserById(@PathVariable String email,@PathVariable String firstName)throws UserNotFoundException{
        return new ResponseEntity<>(userService.getUser(email,firstName),HttpStatus.ACCEPTED);
    }
//    @PutMapping("update/{email}/{firstName}")
//    public ResponseEntity<?> updateUserByEmail(@PathVariable String email,@PathVariable String firstName,@RequestBody Details detail)throws UserNotFoundException{
//        return new ResponseEntity<>(userService.updateUser(email,firstName,detail),HttpStatus.OK);
//    }

    @PutMapping("update/{email}/{firstName}")
    public ResponseEntity<?> updateUserByEmail(@PathVariable String email,@PathVariable String firstName,@RequestBody Details detail)throws UserNotFoundException{
        return new ResponseEntity<>(userService.updateUser(email,firstName,detail),HttpStatus.OK);
    }

    @PostMapping("adduser/{email}")
    public ResponseEntity<?> addUserDetails(@PathVariable String email,@RequestBody Details detail)throws UserAlreadyExistsException{
        return new ResponseEntity<>(userService.addDetailUser(email,detail),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("deleteUser/{email}/{firstName}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String email,@PathVariable String firstName)throws UserNotFoundException{
        return new ResponseEntity<>(userService.DeleteUser(email, firstName),HttpStatus.ACCEPTED);
    }
//====================================Favourite=============================================

    @PostMapping("favList/addMovie/{email}/{firstName}")
    public ResponseEntity<?> addMovieToUserFavList(@PathVariable String email,@PathVariable String firstName, @RequestBody Movie movie)throws MovieAlreadyExistsException{
        try {
            return new ResponseEntity<>(userService.addMovieToUserFavList(email,firstName,movie), HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("favList/get/{email}/{firstName}")
    public ResponseEntity<?> getFavAccByEmail(@PathVariable String email,@PathVariable String firstName)throws MovieNotFoundException{
        return new ResponseEntity<>(userService.getMoviesFromFavorites(email,firstName),HttpStatus.OK);
    }
    @DeleteMapping("favList/deleteMovie/{email}/{firstName}/{MovieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable String email,@PathVariable String firstName,  @PathVariable int MovieId){
        return new ResponseEntity<>(userService.deleteMovieFromMovieList(email,firstName,MovieId),HttpStatus.ACCEPTED);
    }
//=======================================Subscription=======================================

@PostMapping("updateAmount/{email}")
public ResponseEntity<?> updateAmountByEmail(@PathVariable String email,@RequestBody User data)throws UserNotFoundException{
    return new ResponseEntity<>(userService.updateAmount(email,data),HttpStatus.ACCEPTED);
}
        @PostMapping( "/createOrder")
    public OrderResponse createOrder(@RequestBody  User user) throws RazorpayException {
        OrderResponse response = new OrderResponse();
//        System.out.println("user = " + user);
        try{
            client = new RazorpayClient(secretKey,secretId);
            Order order = createRazorPayOrder(user.getAmount());
            System.out.println("---------------------------");
            String orderId = (String) order.get("id");
            System.out.println("Order ID: " + orderId);
            System.out.println("---------------------------");
            response.setRazorpayOrderId(orderId);
            response.setApplicationFee(user.getAmount());
            response.setSecretKey(secretKey);
            response.setSecretId(secretId);
            response.setPgName("razor1");
            return response;
        } catch (RazorpayException e) {
            e.printStackTrace();
        }

        return response;
    }
    private Order createRazorPayOrder(int amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", amount*100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1);
        return client.orders.create(options);
    }



}



