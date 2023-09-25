package com.smartContact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartContact.dao.UserRepository;
import com.smartContact.entities.User;

@Configuration
@Service
public class UserDetailServiceImple implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.getUserByUserName(username);
		if (user==null) {
			throw new UsernameNotFoundException("Not found User");
		}
		CustomUserDetail userByUserName=new CustomUserDetail(user);
		System.out.println(userByUserName.getUsername()+""+userByUserName.getPassword());
		return userByUserName;
	}
	
}
