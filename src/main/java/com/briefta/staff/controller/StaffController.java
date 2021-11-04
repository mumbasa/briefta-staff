package com.briefta.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briefta.staff.configuration.JwtTokenUtil;
import com.briefta.staff.configuration.JwtUserDetailsService;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.model.Staff;
import com.briefta.staff.service.StaffService;

@RestController
@RequestMapping("/v1/api")
public class StaffController {

	@Autowired
	StaffService staffService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping("/staff/directory")
	public ResponseMessage getStaff(@RequestParam String name, @RequestParam int page, @RequestParam int size,
			@RequestParam long companyId) {
		if (name.isBlank()) {
			return staffService.getCompanyStaff(companyId, page, size);
		} else {
			return staffService.getCompanyStaff(name, companyId, page, size);
		}
	}

	@PatchMapping("/staff/password")
	public ResponseMessage changePassword(@RequestParam String contactInfo) {
		return staffService.changePassword(contactInfo);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<ResponseMessage> createAuthenticationToken(@RequestBody Staff authenticationRequest) throws Exception {
		ResponseMessage responseMessage = new ResponseMessage();
		if (authenticate(authenticationRequest.getContactInfo(), authenticationRequest.getPassword()) == 1) {

			final MyPrincipal userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getContactInfo());

			final String token = jwtTokenUtil.generateToken(userDetails);
			userDetails.getUser().setToken(token);
			responseMessage.setStatusCode(200);
			responseMessage.setMessage("User logged in successfully");
			responseMessage.setPayload(userDetails);
			return ResponseEntity.ok(responseMessage);
		} else {
			responseMessage.setStatusCode(500);
			responseMessage.setMessage("User credentials wrong");
			return ResponseEntity.ok(responseMessage);
		}
		
	}

	private int authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return 1;
		} catch (DisabledException e) {
			// throw new Exception("USER_DISABLED", e);
			return 2;
		} catch (BadCredentialsException e) {
			// throw new Exception("INVALID_CREDENTIALS", e);
			return 3;
		}
	}

}
