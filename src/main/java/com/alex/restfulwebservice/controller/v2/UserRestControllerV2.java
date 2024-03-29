package com.alex.restfulwebservice.controller.v2;

import com.alex.restfulwebservice.dao.UserDao;
import com.alex.restfulwebservice.exception.UserNotFoundException;
import com.alex.restfulwebservice.pojo.UserPojo;
import com.alex.restfulwebservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v2")
public class UserRestControllerV2 {

  private final UserService userService;

  public UserRestControllerV2(UserService userService) {
    this.userService = userService;
  }

  //
  @GetMapping(path = "/users")
  public ResponseEntity<List<UserDao>> getUsers() {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findAllUser());
  }

  //
  @GetMapping(path = "/user/{userId}")
  public EntityModel<UserDao> getUserById(@PathVariable Integer userId) {
    UserDao user = userService.findOne(userId);
    if (user == null) {
      throw new UserNotFoundException("userId=" + userId);
    }

    EntityModel<UserDao> model = EntityModel.of(user);
    WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());

    model.add(link.withRel("all-users"));

    return model;
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
