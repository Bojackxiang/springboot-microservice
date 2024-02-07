package com.alex.restfulwebservice.repo;

import com.alex.restfulwebservice.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDao, Integer> {

}
