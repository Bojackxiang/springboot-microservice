package com.alex.restfulwebservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class UserDao {
  // @JsonIgnore()
  @Id
  @GeneratedValue()
  private Integer id;
  private LocalDate localDate;
  @JsonProperty("user_name")
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "author")
  private List<Post> postList;

  public List<Post> getPostList() {
    return postList;
  }

  public void setPostList(List<Post> postList) {
    this.postList = postList;
  }

  public UserDao(Integer id, LocalDate localDate, String name) {
    this.id = id;
    this.localDate = localDate;
    this.name = name;
  }

  public UserDao() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserPojo{" +
        "id=" + id +
        ", localDate=" + localDate +
        ", name='" + name + '\'' +
        '}';
  }
}
