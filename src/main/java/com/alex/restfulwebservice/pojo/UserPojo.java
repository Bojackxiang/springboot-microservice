package com.alex.restfulwebservice.pojo;

// request body user pojo
public class UserPojo {

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
