package com.alex.restfulwebservice.controller.v2;

import com.alex.restfulwebservice.dao.Post;
import com.alex.restfulwebservice.dao.UserDao;
import com.alex.restfulwebservice.exception.UserNotFoundException;
import com.alex.restfulwebservice.pojo.UserPojo;
import com.alex.restfulwebservice.repo.PostRepo;
import com.alex.restfulwebservice.repo.UserRepo;
import com.alex.restfulwebservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
@RequestMapping(path = "/v2/post")
public class PostRestControllerV2 {

  private final PostRepo postPojo;
  private final UserRepo userRepo;

  public PostRestControllerV2(PostRepo postRepo, UserRepo userRepo) {
    this.postPojo = postRepo;
    this.userRepo = userRepo;
  }

  //
  @GetMapping(path = "/all")
  public ResponseEntity<List<Post>> getUsers() {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(postPojo.findAll());
  }

  //
  @GetMapping(path = "/{postId}")
  public EntityModel<Post> getUserById(@PathVariable Integer postId) {
    Post postById = postPojo.findById(postId).orElse(null);
    if (postById == null) {
      throw new UserNotFoundException("userId=" + postId);
    }

    EntityModel<Post> model = EntityModel.of(postById);
    WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());

    model.add(link.withRel("post-by-id"));

    return model;
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity<Post> saveUsers(@PathVariable Integer id, @Valid @RequestBody Post post) {

    // find user
    UserDao userById = userRepo.findById(id).orElse(null);
    if (userById == null) {
      throw new UserNotFoundException("userId=" + id);
    }

    post.setAuthor(userById);
    Post savedPost = postPojo.save(post);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedPost);

  }

  @DeleteMapping(path = "/{userId}")
  public ResponseEntity<Post> saveUsers(@PathVariable Integer postId) {
    postPojo.deleteById(postId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
