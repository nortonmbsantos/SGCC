package br.edu.utfpr.sgcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	UserService userService = new UserService(); 
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.returnByUserName(userName);
		if(user == null) {
			throw new UsernameNotFoundException("Not found: " + userName);
		}
		
		return new MyUserDetails(user);
	}
}
