package com.smartweb.market.jwt.service;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.jwt.dao.RoleDao;
import com.smartweb.market.jwt.dao.UserDao;
import com.smartweb.market.jwt.entity.Role;
import com.smartweb.market.jwt.entity.User;
import com.smartweb.market.model.UserDTO;
import com.smartweb.market.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteLookupFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    CustomerRepository customerRepository;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);
        
        Role setterRole = new Role();
        setterRole.setRoleName("Setter");
        setterRole.setRoleDescription("Default role for newly created record");
        roleDao.save(setterRole);
        
        Role deliveryRole = new Role();
        deliveryRole.setRoleName("Delivery");
        deliveryRole.setRoleDescription("Default role for newly created record");
        roleDao.save(deliveryRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("omar");
        adminUser.setUserPassword(getEncodedPassword("123"));
        
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User setterUser = new User();
        setterUser.setUserName("setter");
        setterUser.setUserPassword(getEncodedPassword("123"));
        
        Set<Role> setterRoles = new HashSet<>();
        setterRoles.add(setterRole);
        setterUser.setRole(setterRoles);
        userDao.save(setterUser);
        
        User user = new User();
        user.setUserName("user");
        user.setUserPassword(getEncodedPassword("123"));
       
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
        
        User deliveryUser = new User();
        deliveryUser.setUserName("delivery");
        deliveryUser.setUserPassword(getEncodedPassword("123"));
      
        Set<Role> deliveryRoles = new HashSet<>();
        deliveryRoles.add(deliveryRole);
        deliveryUser.setRole(userRoles);
        userDao.save(deliveryUser);
    }

    public User registerNewUser(UserDTO userDTO) throws Exception {
    	//we should check if username (which is phone) is already exist and throw exception as username is taken
    	// but i'll leave it as any user can take over phone number from previos user but every time new customer id is created 
    	//make new customer and get its id
    	//response.sendError(HttpServletResponse.SC_CONFLICT, "User already exists");
    	//long customerId = 0 ;
    	if(!userDao.existsById(userDTO.getPhoneNumber())) {
    		Customer customer = customerRepository.findByPhoneNumber(userDTO.getPhoneNumber());
    		
    		customer.setName(userDTO.getUserName());
    		customer.setAddress(userDTO.getAddress());
    		Customer savedCustomer = customerRepository.save(customer);
    		
    		User newUser = new User();
            //Assign the new customer id to the user class.
            newUser.setCustomerId(savedCustomer.getId());
        	Role role = roleDao.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            newUser.setRole(userRoles);
            newUser.setUserPassword(getEncodedPassword(userDTO.getPassword()));
            //هنا تم تبديل اسم المستخدم برقم الهاتف
            //USING PHONE NUMBER AS A USERNAME.
            newUser.setUserName(userDTO.getPhoneNumber());
            newUser.setCustomerName(userDTO.getUserName());
            
            return userDao.save(newUser);
    	}else
    	throw new  Exception();
    	
    	
    }
    public User registerNewAdminUser(User user, String roleName) {
        Role role2 = roleDao.findById(roleName).get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role2);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
