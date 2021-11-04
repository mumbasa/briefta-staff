package com.briefta.staff.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.briefta.staff.controller.MyPrincipal;
import com.briefta.staff.model.Staff;
import com.briefta.staff.repository.StaffRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	StaffRepository userRepo;

	@Override
	public MyPrincipal loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		MyPrincipal k ;
		Optional<Staff> user = userRepo.findStaffBycontactInfo(username);

		if (user.isPresent()){
			 k = new MyPrincipal(user.get());
		

		} else {
			k = new MyPrincipal();
			
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return k;
	}



}
