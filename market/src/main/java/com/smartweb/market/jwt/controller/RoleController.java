package com.smartweb.market.jwt.controller;

import com.smartweb.market.jwt.entity.Role;
import com.smartweb.market.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

//    @PostMapping({"/createNewRole"})
//    public Role createNewRole(@RequestBody Role role) {
//        return roleService.createNewRole(role);
//    }
}
