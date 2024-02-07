package com.alex.restfulwebservice.repo;

import com.alex.restfulwebservice.dao.Post;
import com.alex.restfulwebservice.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
