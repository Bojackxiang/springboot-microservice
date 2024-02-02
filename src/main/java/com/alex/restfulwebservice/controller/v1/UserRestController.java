package com.alex.restfulwebservice.controller.v1;

import com.alex.restfulwebservice.dao.UserDao;
import com.alex.restfulwebservice.exception.UserNotFoundException;
import com.alex.restfulwebservice.pojo.UserPojo;
import com.alex.restfulwebservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<List<UserDao>> getUsers() {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findAllUser());
  }

  //
  @GetMapping(path = "/user/{userId}")
  public ResponseEntity<UserDao> getUserById(@PathVariable Integer userId) {
    UserDao user = userService.findOne(userId);
    if (user == null) {
      throw new UserNotFoundException("userId=" + userId);
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
  }

  @PostMapping(path = "/user")
  public ResponseEntity<UserPojo> saveUsers(@Valid @RequestBody UserPojo newUser) {
    userService.saveUser(newUser);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newUser);

  }

  @DeleteMapping(path = "/user/{userId}")
  public ResponseEntity<UserPojo> saveUsers(@PathVariable Integer userId) {
    userService.deleteUserById(userId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
