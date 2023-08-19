package com.smartweb.market.jwt.controller;

import com.smartweb.market.jwt.entity.JwtRequest;
import com.smartweb.market.jwt.entity.JwtResponse;
import com.smartweb.market.jwt.entity.Role;
import com.smartweb.market.jwt.entity.User;
import com.smartweb.market.jwt.service.JwtUserDetailsService;
import com.smartweb.market.jwt.service.RoleService;
import com.smartweb.market.jwt.service.UserService;
import com.smartweb.market.model.UserDTO;



//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
//    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/authenticate"})//for user have (username and password) & need authentication.
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtUserDetailsService.createJwtToken(jwtRequest);
    }
    @PostMapping({"/authenticateAdmins"})//for user have (username and password) & need authentication.
    public JwtResponse createJwtAdminToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtUserDetailsService.createJwtAdminToken(jwtRequest);
    }
    
    @GetMapping ({"/checkUserAuthentication"})
    @PreAuthorize("hasRole('User')")//if token is accepted then user is authenticated
    public String checkUserAuthentication() {
    	return "authenticated";
    }
    @GetMapping ({"/checkAdminAuthentication"})
    @PreAuthorize("hasRole('Admin')")//if token is accepted then user is authenticated
    public String checkAdminAuthentication() {
    	return "authenticated";
    }
    
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody UserDTO user) throws Exception {
        return userService.registerNewUser(user);
    }
    @PostMapping({"/registerNewAdminUser"})
    @PreAuthorize("hasRole('Admin')")
    public User registerNewAdminUser(@RequestBody User user, String roleName) {
        return userService.registerNewAdminUser(user, roleName);
    }

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    @GetMapping({"/everybody"})
    public String forEveryBody(){
        return "Wellcome EveryBody";
    }
   

   
}
