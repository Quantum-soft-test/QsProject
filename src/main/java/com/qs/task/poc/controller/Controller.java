package com.qs.task.poc.controller;

//import com.neosoft.task.poc.model.HelpRequest;
import com.qs.task.poc.model.QSUser;
import com.qs.task.poc.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {

	@Autowired
  ServiceImpl Categoryservice;

  @GetMapping("/welcome")
  public String welcome(){
    return "welcome to user helping application";
  }
  @PostMapping("/login")
  private ResponseEntity<?> loginUser(@RequestBody QSUser userData){
    QSUser user=Categoryservice.getUser(userData.getUserId());
    if (user.getPassword().equals(userData.getPassword())){
      return new ResponseEntity<QSUser>(user, HttpStatus.OK);
    }
    return new ResponseEntity<>("bad Request", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
