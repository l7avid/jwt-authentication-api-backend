/*
package com.example.utils;

import com.example.enums.RoleName;
import com.example.models.Role;
import com.example.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;
    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        Role userRole = new Role(RoleName.ROLE_USER);
        roleService.save(adminRole);
        roleService.save(userRole);
    }
}


 */