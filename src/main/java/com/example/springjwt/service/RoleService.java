package com.example.springjwt.service;

import com.example.springjwt.entity.Role;
import com.example.springjwt.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {


    @Autowired
    private RoleRepo roleRepo;

    public Role createNewRole(Role role) {

      return roleRepo.save(role);


    }
}
