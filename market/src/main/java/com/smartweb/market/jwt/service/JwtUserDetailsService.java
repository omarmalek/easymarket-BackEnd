package com.smartweb.market.jwt.service;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.jwt.dao.UserDao;
import com.smartweb.market.jwt.entity.JwtRequest;
import com.smartweb.market.jwt.entity.JwtResponse;
import com.smartweb.market.jwt.entity.Role;
import com.smartweb.market.jwt.entity.User;
import com.smartweb.market.jwt.util.JwtTokenUtil;
import com.smartweb.market.model.UserDTO;
import com.smartweb.market.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    CustomerRepository customerReopsitory;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
    	
    	//Extract username and password from request.
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        
        //validate user
        authenticate(userName, userPassword);//This method returns 'void' anyway, it works as a "Checkpoint". if this procedure catch an error everything stops right here.

        //fetching user details from the database using the username.
        UserDetails userDetails = loadUserByUsername(userName);
        
        //Generate Token
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        //fetching customer info from the database using the username. 
        User user = userDao.findById(userName).get();
        Customer  customer = customerReopsitory.findById(user.getCustomerId()).get();
        		
       
        return new JwtResponse(customer.getId(), user.getCustomerName(),  customer.getPhoneNumber(),  customer.getAddress(), newGeneratedToken);
    }
    public JwtResponse createJwtAdminToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);//This is a "Checkpoint". if this procedure catch an error everything stops right here.

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userDao.findById(userName).get();
        
        Set<Role> role = user.getRole();
        Role firstRole = role.stream().findFirst().get();
        String roleName = firstRole.getRoleName();
        
        return new JwtResponse( user.getUserName(), newGeneratedToken,roleName);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
