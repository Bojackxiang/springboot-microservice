package com.alex.restfulwebservice.dao;

import java.time.LocalDate;

public class UserDao {
  private Integer id;
  private LocalDate localDate;
  private String name;

  public UserDao(Integer id, LocalDate localDate, String name) {
    this.id = id;
    this.localDate = localDate;
    this.name = name;
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
