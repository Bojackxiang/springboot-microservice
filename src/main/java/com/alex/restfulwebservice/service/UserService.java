package com.alex.restfulwebservice.service;

import com.alex.restfulwebservice.dao.UserDao;
import com.alex.restfulwebservice.pojo.UserPojo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserService {


  private static final List<UserDao> users = new ArrayList<>();

  // static
  static {
    users.add(new UserDao(1, LocalDate.now(), "alex"));
    users.add(new UserDao(2, LocalDate.now().minusYears(2), "bob"));
    users.add(new UserDao(3, LocalDate.now().minusDays(2), "cindy"));
    users.add(new UserDao(4, LocalDate.now().minusMonths(6), "Dane"));
  }

  // find all users
  public List<UserDao> findAllUser() {
    return UserService.users;
  }

  // find one user
  public UserDao findOne(Integer id) {
    return UserService.users.stream().filter(user -> user.getId().equals(id)).findFirst()
        .orElse(null);

  }

  // save users
  public void saveUser(UserPojo user) {
    String username = user.getName();

    UserDao newUser = new UserDao(UserService.users.size() - 1, LocalDate.now(), username);

    UserService.users.add(newUser);

  }


}
