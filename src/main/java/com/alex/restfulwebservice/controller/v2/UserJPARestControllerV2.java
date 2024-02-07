package com.alex.restfulwebservice.controller.v2;

import com.alex.restfulwebservice.dao.UserDao;
import com.alex.restfulwebservice.exception.UserNotFoundException;
import com.alex.restfulwebservice.pojo.UserPojo;
import com.alex.restfulwebservice.repo.UserRepo;
import jakarta.validation.Valid;
import java.time.LocalDate;
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
@RequestMapping(path = "/v2/jpa")
public class UserJPARestControllerV2 {

  private final UserRepo userRepo;

  public UserJPARestControllerV2(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  //
  @GetMapping(path = "/users")
  public ResponseEntity<List<UserDao>> getUsers() {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userRepo.findAll());
  }

  //
  @GetMapping(path = "/user/{userId}")
  public EntityModel<UserDao> getUserById(@PathVariable Integer userId) {
    UserDao user = userRepo.findById(userId).orElse(null);
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
    // userRepo.save(new UserDao(LocalDateTime.now(), newUser.getName()));
    userRepo.save(new UserDao(null, LocalDate.now(), newUser.getName()));
    
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newUser);

  }

  @DeleteMapping(path = "/user/{userId}")
  public ResponseEntity<UserPojo> saveUsers(@PathVariable Integer userId) {
    userRepo.deleteById(userId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
