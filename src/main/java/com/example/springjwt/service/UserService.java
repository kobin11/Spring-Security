package com.example.springjwt.service;

import com.example.springjwt.entity.Role;
import com.example.springjwt.entity.User;
import com.example.springjwt.repo.RoleRepo;
import com.example.springjwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {




    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public  User registerNewUser(User user) {

        return  userRepo.save(user);

    }

    public void initRoleAndUser(){
        Role adminrole = new Role();
        Role userRole = new Role();
        if(!roleRepo.existsById("Admin"))
        {

            adminrole.setRoleName("Admin");
            adminrole.setRoleDescription("My Admin");
            roleRepo.save(adminrole);
        }

        if(!roleRepo.existsById("admin123"))
        {
            userRole.setRoleName("User");
            userRole.setRoleDescription("My Role");
            roleRepo.save(userRole);
        }
        if(!userRepo.existsById("admin123")) {
            User user = new User();
            user.setUserName("admin123");
            user.setUserPassword(getEncodedPassword("admin@123"));
            user.setUserFirstName("kobi");
            user.setUserLastName("accd");

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminrole);

            user.setRole(adminRoles);
            userRepo.save(user);
        }
        if(!userRepo.existsById("user123")) {
            User user = new User();
            user.setUserName("user123");
            user.setUserPassword(getEncodedPassword("user@123"));
            user.setUserFirstName("Nira");
            user.setUserLastName("Vijay");

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);


            userRepo.save(user);
        }
    }

    public String getEncodedPassword(String password)
    {
        return passwordEncoder.encode(password);
    }

}
