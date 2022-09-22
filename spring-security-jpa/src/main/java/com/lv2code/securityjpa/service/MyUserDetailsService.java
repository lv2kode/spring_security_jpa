package com.lv2code.securityjpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lv2code.securityjpa.model.MyUserDetails;
import com.lv2code.securityjpa.model.User;
import com.lv2code.securityjpa.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
		return user.map(MyUserDetails::new).get();
	}

}
