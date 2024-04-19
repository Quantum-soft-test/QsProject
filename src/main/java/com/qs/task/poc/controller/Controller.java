package com.qs.task.poc.controller;

import com.qs.task.poc.UserNotfound;
import com.qs.task.poc.model.QSUser;
import com.qs.task.poc.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {

	@Autowired
  ServiceImpl Categoryservice;

  private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  public static boolean isValidEmail(String email) {
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

//  public static boolean isValidPassword(String password) {
//     int minLength=6, maxLength=12;
//    // Check if the password length is within the specified range
//    return password.length() >= minLength && password.length() <= maxLength;
//  }

  @GetMapping("/welcome")
  public String welcome(){
    return "welcome to user helping application";
  }
  @PostMapping("/login")
  private ResponseEntity<?> loginUser(@RequestBody QSUser userData) throws UserNotfound{
    QSUser user=Categoryservice.getUser(userData.getUserId());
    if(isValidEmail(userData.getUserId())==false){
      return new ResponseEntity<>("enter valid userId",HttpStatus.BAD_REQUEST);

    }else if(!(user.getUserId().equals(userData.getUserId()))){
      //  throw new UserNotfound("no user found");
      return new ResponseEntity<>("no user",HttpStatus.NO_CONTENT);

    }
    if (user.getUserId().equals(userData.getUserId()) && user.getPassword().equals(userData.getPassword())) {
      return new ResponseEntity<QSUser>(user, HttpStatus.OK);
    }
    return new ResponseEntity<>("bad Request ", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

