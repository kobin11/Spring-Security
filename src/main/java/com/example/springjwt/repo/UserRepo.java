package com.example.springjwt.repo;

import com.example.springjwt.entity.Role;
import com.example.springjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {


}
