package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.student.entity.User;
import com.student.repository.UserRepository;
import com.student.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	   @Autowired
	    private UserRepository userRepository;
	    private PasswordEncoder passwordEncoder;
	    
	    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
	    	this.passwordEncoder = passwordEncoder;
	    }
	     
	    @Override
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException {
	        User user = userRepository.getUserByUsername(username);
	         
	        if (user == null) {
	            throw new UsernameNotFoundException("Could not find user");
	        }
	        user.setPassword(passwordEncoder.encode(user.getPassword())); 
	        return new MyUserDetails(user);
	    }

}
