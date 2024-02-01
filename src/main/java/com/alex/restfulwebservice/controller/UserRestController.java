package com.alex.restfulwebservice.controller;

import com.alex.restfulwebservice.dao.UserDao;
import com.alex.restfulwebservice.exception.UserNotFoundException;
import com.alex.restfulwebservice.pojo.UserPojo;
import com.alex.restfulwebservice.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private final UserService userService;

  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  //
  @GetMapping(path = "/users")
  public List<UserDao> getUsers() {
    return userService.findAllUser();
  }

  //
  @GetMapping(path = "/user/{userId}")
  public UserDao getUserById(@PathVariable Integer userId) {
    UserDao user = userService.findOne(userId);
    if (user == null) {
      throw new UserNotFoundException("userId=" + userId);
    }

    return user;
  }

  @PostMapping(path = "/user")
  public ResponseEntity<UserPojo> saveUsers(@RequestBody UserPojo newUser) {
    System.out.println("newUser = " + newUser.toString());
    userService.saveUser(newUser);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newUser);

  }

}
