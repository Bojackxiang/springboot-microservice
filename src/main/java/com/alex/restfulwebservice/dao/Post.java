package com.alex.restfulwebservice.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserDao author;

  @Size(min = 2, message = "Description should have at least 2 characters")
  private String description;

  public Post() {

  }

  public Post(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public UserDao getAuthor() {
    return author;
  }

  public void setAuthor(UserDao author) {
    this.author = author;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", description='" + description + '\'' +
        '}';
  }
}
