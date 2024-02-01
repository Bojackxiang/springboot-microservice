package com.alex.restfulwebservice.pojo;

import com.alex.restfulwebservice.dao.UserDao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

// request body user pojo
public class UserPojo {

  @Size(min = 1, message = "Name size must larger than 1")
  @NotBlank(message = "Name should not be blank")
  @NotNull(message = "Name should not be null")
  private String name;

  public UserPojo() {
    // 默认构造函数
  }

  public UserPojo(String name) {
    this.name = name;
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
        "name='" + name + '\'' +
        '}';
  }
}
